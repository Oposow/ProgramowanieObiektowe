package Lab9.Providers;

import Lab9.Consts.HttpStrings;
import Lab9.Consts.JsonPartNames;
import Lab9.DTO.*;
import Lab9.Exceptions.NoSuchEnvoyException;
import Lab9.Interfaces.IWebCommunication;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Filip i Paulinka on 17.12.2016.
 */
public class WebCommunication implements IWebCommunication {

    public LinkedList<EnvoyPersonalDataDTO> GetAllEnvoysPersonalData(int term)
    {
        try {
            return GetAllEnvoysPersonalData(String.format(HttpStrings.GetAllEvnoysFromTermHttpString, term));
        }
        catch(Exception ex)
        {
            return new LinkedList<>();
        }
    }

    @Override
    public EnvoyExpensesDTO GetEnvoyExpenses(int term, EnvoyPersonalDataDTO envoy) throws Exception
    {
            HashMap<String, Integer> expensesTypes = new HashMap<>();
            LinkedList<EnvoyYearExpensesDTO> years = new LinkedList<>();

            JSONObject jsonData = GetJsonFromURL(String.format(HttpStrings.GetEnvoyExpensesHttpString, envoy.Id));

            // Najpierw przetwarzamy dane o typach wydatków danego posła
            JSONObject envoyExpensesJson = jsonData.getJSONObject(JsonPartNames.Layers).getJSONObject(JsonPartNames.Expenses);
            JSONArray expensesTypesJson = envoyExpensesJson.getJSONArray(JsonPartNames.Points);
            for (int i = 0; i<expensesTypesJson.length(); i++)
            {
                JSONObject expenseType = expensesTypesJson.getJSONObject(i);
                expensesTypes.put(expenseType.getString(JsonPartNames.Title), expenseType.getInt(JsonPartNames.Number)-1);
            }
            // A następnie dla każdego roku zapisujemy kwoty wydatków
            JSONArray expensesByYearsJson = envoyExpensesJson.getJSONArray(JsonPartNames.Years);
            for (int i=0; i<expensesByYearsJson.length(); i++)
            {
                LinkedList<Double> amounts = new LinkedList<>();
                JSONObject yearExpensesJson = expensesByYearsJson.getJSONObject(i);
                JSONArray amountsJson = yearExpensesJson.getJSONArray(JsonPartNames.Fields);
                for (int j = 0; j<amountsJson.length(); j++)
                    amounts.add(amountsJson.getDouble(j));
                years.add(new EnvoyYearExpensesDTO(amounts, yearExpensesJson.getInt(JsonPartNames.Year)));
            }

            EnvoyExpensesDTO result = new EnvoyExpensesDTO(envoy, expensesTypes, years);
            return result;
    }

    @Override
    public EnvoyJourneysDTO GetEnvoyJournes(int term, EnvoyPersonalDataDTO envoy) throws Exception
    {
        LinkedList<JourneyDTO> journeys = new LinkedList<>();
        JSONObject jsonData = GetJsonFromURL(String.format(HttpStrings.GetEnvoyJournesHtppString, envoy.Id));
        // Po kolei przetwarzamy wszystkie wyjazdy posła
        if (!(jsonData.getJSONObject(JsonPartNames.Layers).get(JsonPartNames.Journeys) instanceof  JSONArray))
            return new EnvoyJourneysDTO(journeys, envoy);
        JSONArray envoyJourneysJson = jsonData.getJSONObject(JsonPartNames.Layers).getJSONArray(JsonPartNames.Journeys);
        for(int i=0; i<envoyJourneysJson.length(); i++)
        {
            JSONObject journeyJson = envoyJourneysJson.getJSONObject(i);
            journeys.add(new JourneyDTO(journeyJson.getString(JsonPartNames.CountryCode), journeyJson.getDouble(JsonPartNames.TotalCost), journeyJson.getInt(JsonPartNames.JourneyLength)));
        }
        return new EnvoyJourneysDTO(journeys, envoy);
    }

    @Override
    public LinkedList<EnvoyJourneysDTO> GetAllEnvoysJourneys(int term) throws Exception
    {
        LinkedList<EnvoyPersonalDataDTO> envoys = GetAllEnvoysPersonalData(term);
        LinkedList<EnvoyJourneysDTO> result = new LinkedList<>();
        LinkedList<Thread> threads = new LinkedList<>();
        for (EnvoyPersonalDataDTO envoy : envoys)
        {
            Runnable task = () -> {
                try {
                    result.add(GetEnvoyJournes(term, envoy));
                }
                catch(Exception ex)
                {
                    System.out.println("Upss, coś poszło nie tak");
                    System.out.println(ex.getMessage());
                }
            };
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads)
            thread.join();
        return result;
    }

    @Override
    public LinkedList<EnvoyExpensesDTO> GetAllEnvoysExpenses(int term) throws Exception
    {
        LinkedList<EnvoyPersonalDataDTO> envoys = GetAllEnvoysPersonalData(term);
        LinkedList<EnvoyExpensesDTO> result = new LinkedList<>();
        LinkedList<Thread> threads = new LinkedList<>();
        for (EnvoyPersonalDataDTO envoy : envoys)
        {
            Runnable task = () -> {
                try {
                    result.add(GetEnvoyExpenses(term, envoy));
                }
                catch(Exception ex)
                {
                    System.out.println("Upss, coś poszło nie tak");
                    System.out.println(ex.getMessage());
                }
            };
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads)
            thread.join();
        return result;
    }

    @Override
    public EnvoyExpensesDTO GetEnvoyExpenses(String envoyName, int term) throws Exception
    {
        envoyName = envoyName.toLowerCase().trim();
        LinkedList<EnvoyPersonalDataDTO> allEnvoys = GetAllEnvoysPersonalData(term);
        EnvoyPersonalDataDTO envoy = null;
        for (EnvoyPersonalDataDTO en : allEnvoys)
            if (en.Name.toLowerCase().trim().equals(envoyName)) {
                envoy = en;
                break;
            }
        if (envoy == null) {
            throw new NoSuchEnvoyException();
        }
        EnvoyExpensesDTO expenses = GetEnvoyExpenses(term, envoy);
        return expenses;
    }


    private LinkedList<EnvoyPersonalDataDTO> GetAllEnvoysPersonalData(String url) throws Exception
    {
        LinkedList<EnvoyPersonalDataDTO> result = new LinkedList<>();
        JSONObject mainJson = GetJsonFromURL(url);
        JSONObject links = mainJson.getJSONObject(JsonPartNames.Links);
        Thread thread = null;
        // Wywołujemy rekurencyjnie pobieranie danych jeśli nie dotarliśmy do ostatniej strony wyników
        if (links.has(JsonPartNames.Next)) {
            Runnable runnable = () -> {
                try {
                    result.addAll(GetAllEnvoysPersonalData(links.getString(JsonPartNames.Next)));
                }
                catch(Exception ex)
                {
                    System.out.println("Wystąpił błąd podczas pobierania danych");
                    System.out.println(ex.getMessage());
                }
            };
            thread = new Thread(runnable);
            thread.start();
        }

        // Po kolei przetwarzamy dane wszystkich posłów wyciągając Id i nazwę.
        JSONArray envoys = mainJson.getJSONArray(JsonPartNames.Dataobject);
        for (int i =0; i<envoys.length(); i++)
        {
            JSONObject envoy = envoys.getJSONObject(i);
            int envoyId = envoy.getInt(JsonPartNames.Id);
            String envoyName = envoy.getJSONObject(JsonPartNames.Data).getString(JsonPartNames.EnvoyName);
            result.add(new EnvoyPersonalDataDTO(envoyId, envoyName));
        }
        if (thread != null)
            thread.join();
        return result;
    }

    private JSONObject GetJsonFromURL(String url) throws Exception
    {
        InputStream stream = new URL(url).openStream();
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
        String line;
        while((line = reader.readLine()) != null)
            builder.append(line);
        return new JSONObject(builder.toString());
    }
}
