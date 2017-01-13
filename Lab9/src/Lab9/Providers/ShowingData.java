package Lab9.Providers;

import Lab9.Consts.CountryIds;
import Lab9.Consts.DataTypeToBeShown;
import Lab9.Consts.ExpensesTypesNames;
import Lab9.DTO.*;
import Lab9.Exceptions.NoSuchEnvoyException;
import Lab9.Interfaces.IShowingData;
import Lab9.Interfaces.IWebCommunication;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Created by Filip i Paulinka on 17.12.2016.
 */
public class ShowingData implements IShowingData
{
    IWebCommunication _webCommunicationProvider;

    ShowingData()
    {
        _webCommunicationProvider = new WebCommunication();
    }

    @Override
    public void ShowEnvoyTotalExpenses(String envoyName, int term) throws Exception
    {
        EnvoyExpensesDTO expenses = _webCommunicationProvider.GetEnvoyExpenses(envoyName, term);
        double totalExpenses = 0;
        for (EnvoyYearExpensesDTO year : expenses.Years)
            for (Double amount : year.Amounts)
            totalExpenses += amount;
        System.out.println(String.format("Suma wszystkich wydatków posła/posłanki %1s w wybranej kadencji wyniosła: %2$.2f zł.", envoyName, totalExpenses));
    }

    @Override
    public void ShowEnvoyMinorExpenses(String envoyName, int term) throws Exception
    {
        EnvoyExpensesDTO expenses = _webCommunicationProvider.GetEnvoyExpenses(envoyName, term);
        double minorRepairsExpenses = 0;
        if (!expenses.Types.containsKey(ExpensesTypesNames.MinorRepairsAndRenovations)) {
            System.out.println(String.format("Wybrany poseł nie posiada typu wydatku: %s", ExpensesTypesNames.MinorRepairsAndRenovations));
            return;
        }
        int expenseIndex = expenses.Types.get(ExpensesTypesNames.MinorRepairsAndRenovations);
        for (EnvoyYearExpensesDTO year : expenses.Years)
            minorRepairsExpenses += year.Amounts.get(expenseIndex);
        System.out.println(String.format("%1s - %2s: %3$.2f zł.", envoyName, ExpensesTypesNames.MinorRepairsAndRenovations, minorRepairsExpenses));
    }

    @Override
    public void ShowEnvoysAverageExpenses(int term) throws Exception
    {
        double total = 0;
        LinkedList<EnvoyExpensesDTO> envoysExpenses = _webCommunicationProvider.GetAllEnvoysExpenses(term);
        for (EnvoyExpensesDTO envoyExpenses : envoysExpenses)
            for (EnvoyYearExpensesDTO year : envoyExpenses.Years)
                for (double amount : year.Amounts)
                    total += amount;
        System.out.println(String.format("Srednia kwota wydatków na posła w wybranej kadencji sejmu wyniosła: %1$.2f zł", total/envoysExpenses.size()));
    }

    @Override
    public void ShowEnvoyWithMostExpensiveAbroadJourney(int term) throws Exception
    {
        LinkedList<EnvoyJourneysDTO> envoysJourneys = _webCommunicationProvider.GetAllEnvoysJourneys(term);
        JourneyDTO mostExpensiveJourney = null;
        EnvoyPersonalDataDTO envoyWithMostExpensiveJourney = null;
        for (EnvoyJourneysDTO envoyJourneys : envoysJourneys)
            for (JourneyDTO journey : envoyJourneys.Journeys)
                if (mostExpensiveJourney == null || mostExpensiveJourney.Cost < journey.Cost) {
                    mostExpensiveJourney = journey;
                    envoyWithMostExpensiveJourney = envoyJourneys.PersonalData;
                }
        if (mostExpensiveJourney != null)
            System.out.println(String.format("Najdroższą podróż w wybranej kadencji sejmu odbył/a %1s. Kosztowała ona %2$.2f zł.", envoyWithMostExpensiveJourney.Name, mostExpensiveJourney.Cost));
        else
            System.out.println("W tej kadencji sejmu nikt nie odbył żadnych podróży");
    }

    @Override
    public void ShowEnvoysWhoVisitedItaly(int term) throws Exception
    {
        LinkedList<EnvoyJourneysDTO> envoysJourneys = _webCommunicationProvider.GetAllEnvoysJourneys(term);
        LinkedList<EnvoyPersonalDataDTO> envoysWhoVisistedItaly = new LinkedList<>();
        for (EnvoyJourneysDTO envoyJourneys : envoysJourneys)
            for (JourneyDTO journey : envoyJourneys.Journeys)
                if (journey.CountryCode.equals(CountryIds.Italy))
                {
                    envoysWhoVisistedItaly.add(envoyJourneys.PersonalData);
                    break;
                }
        if (envoysWhoVisistedItaly.size() == 0)
            System.out.println("Żaden poseł nie odwiedził Włoch w wybranej kadencji sejmu");
        else
        {
            StringBuilder builder = new StringBuilder().append("Posłowie, którzy odwiedzili Włochy w wybranej kadencji sejmu, to: ");
            for (EnvoyPersonalDataDTO envoy : envoysWhoVisistedItaly)
                builder.append(envoy.Name).append(", ");
            builder.deleteCharAt(builder.length()-2);
            System.out.println(builder.toString());
        }
    }

    @Override
    public void ShowEnvoyMostOftenTravellingAbroad(int term) throws Exception
    {
        LinkedList<EnvoyJourneysDTO> envoysJourneys = _webCommunicationProvider.GetAllEnvoysJourneys(term);
        EnvoyPersonalDataDTO envoyMostOftenTravelling = null;
        int topJourneysAmount = 0;
        for (EnvoyJourneysDTO envoy : envoysJourneys)
            if (topJourneysAmount < envoy.Journeys.size())
            {
                topJourneysAmount = envoy.Journeys.size();
                envoyMostOftenTravelling = envoy.PersonalData;
            }
        if (topJourneysAmount == 0)
            System.out.println("Żaden poseł nie odbył podróży zagranicznej w wybranej kadencji sejmu");
        else
            System.out.println(String.format("Poseł/Posłanka, który odbył/a najwięcej podróży zagranicznych w wybranej kadencji sejmu to: %1s. Liczba odbytych podróży: %2d", envoyMostOftenTravelling.Name, topJourneysAmount));
    }

    @Override
    public void ShowTheLongestStayingAbroadSum(int term) throws Exception
    {
        LinkedList<EnvoyJourneysDTO> envoysJourneys = _webCommunicationProvider.GetAllEnvoysJourneys(term);
        int maxDaysSpentAbroad = 0;
        EnvoyPersonalDataDTO envoyMostOftenStayedAbroad = null;
        for (EnvoyJourneysDTO envoyJourneys : envoysJourneys)
        {
            int daysSpentAbroad = 0;
            for (JourneyDTO journey : envoyJourneys.Journeys)
                daysSpentAbroad += journey.Length;
            if (maxDaysSpentAbroad < daysSpentAbroad)
            {
                maxDaysSpentAbroad = daysSpentAbroad;
                envoyMostOftenStayedAbroad = envoyJourneys.PersonalData;
            }
        }
        if (maxDaysSpentAbroad != 0)
            System.out.println(String.format("Najwięcej dni za granicą w wybranej kadencji sejmu spędził/a %1s. Było to %2d dni.", envoyMostOftenStayedAbroad.Name, maxDaysSpentAbroad));
        else
            System.out.println("W tej kadencji sejmu nikt nie odbył żadnych podróży");
    }


}
