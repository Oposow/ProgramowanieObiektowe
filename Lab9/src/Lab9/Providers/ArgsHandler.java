package Lab9.Providers;

import Lab9.Consts.DataTypeToBeShown;
import Lab9.Consts.ExpensesTypesNames;
import Lab9.DTO.*;
import Lab9.Interfaces.IArgsHandler;
import Lab9.Interfaces.IShowingData;
import Lab9.Interfaces.IWebCommunication;
import Lab9.LineArgs;
import org.omg.CORBA.Environment;

import java.util.LinkedList;

/**
 * Created by Filip i Paulinka on 17.12.2016.
 */
public class ArgsHandler implements IArgsHandler {

    IWebCommunication _communicationProvider;
    IShowingData _showingDataProvider;

    public ArgsHandler()
    {
       _communicationProvider = new WebCommunication();
       _showingDataProvider = new ShowingData();
    }

    public void ShowChoosenData(LineArgs args) throws Exception
    {
            switch (args.DataType)
            {
                case EnvoyTotalExpenses:
                    _showingDataProvider.ShowEnvoyTotalExpenses(args.EnvoyName, args.Term);
                    break;
                case EnvoyMinorRepairsExpenses:
                    _showingDataProvider.ShowEnvoyMinorExpenses(args.EnvoyName, args.Term);
                    break;
                case EnvoysAverageExpenses:
                    _showingDataProvider.ShowEnvoysAverageExpenses(args.Term);
                    break;
                case AllEnvoysVisitingItaly:
                    _showingDataProvider.ShowEnvoysWhoVisitedItaly(args.Term);
                    break;
                case TheLongestStayingAbroadSum:
                    _showingDataProvider.ShowTheLongestStayingAbroadSum(args.Term);
                    break;
                case EnvoyMostOftenTravellingAbroad:
                    _showingDataProvider.ShowEnvoyMostOftenTravellingAbroad(args.Term);
                    break;
                case MostExpensiveAbroadTrip:
                    _showingDataProvider.ShowEnvoyWithMostExpensiveAbroadJourney(args.Term);
                    break;
            }
    }

    @Override
    public LineArgs ParseLineArgs(String[] args)
    {
        try {
            if (args.length < 2) {
                System.out.println("Podano za mało argumentów");
                ShowHelp();
                System.exit(1);
            }
            int term = Integer.parseInt(args[0]);
            if (term < 1 || term > 8) {
                System.out.println("Podano błędny numer kadencji sejmu");
                ShowHelp();
                System.exit(1);
            }
            int typeNumber = Integer.parseInt(args[1]);
            if (typeNumber < 0 || typeNumber > 6) {
                System.out.println("Podano błędny numer typu danych");
                ShowHelp();
                System.exit(1);
            }
            DataTypeToBeShown type = DataTypeToBeShown.values()[typeNumber];
            String envoyName = null;
            if (type == DataTypeToBeShown.EnvoyMinorRepairsExpenses || type == DataTypeToBeShown.EnvoyTotalExpenses) {
                if (args.length < 4) {
                    System.out.println("Nie podano imienia i nazwiska posła");
                    ShowHelp();
                    System.exit(1);
                }
                StringBuilder builder = new StringBuilder();
                for (int i = 2; i<args.length; i++)
                    builder.append(args[i]).append(' ');
                builder.deleteCharAt(builder.length()-1);
                envoyName = builder.toString();
            }
            return new LineArgs(type, term, envoyName);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Podano argumenty w błędnym formacie");
            System.exit(1);
        }
        return null;
    }

    private void ShowHelp()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Opis argumentów linii poleceń:").append(System.lineSeparator())
                .append("Pierwszy argument (obowiązkowy): Numer kadencji sejmu <1;8>").append(System.lineSeparator())
                .append("Drugi argument (obowiązkowy): Numer danych do wyświetlenia").append(System.lineSeparator())
                .append("0 - Suma wydatków posła").append(System.lineSeparator())
                .append("1 - Kwota wydana przez posła na drobne naprawy biura poselskiego").append(System.lineSeparator())
                .append("2 - Srednia suma wydatków na posła").append(System.lineSeparator())
                .append("3 - Poseł, który najczęściej wyjeżdzał zagranicę").append(System.lineSeparator())
                .append("4 - Poseł, który najdłużej pozostawał zagranicą").append(System.lineSeparator())
                .append("5 - Poseł, który odbył najdroższą podróż zagraniczną").append(System.lineSeparator())
                .append("6 - Lista posłów, którzy odwiedzili Włochy").append(System.lineSeparator())
                .append("Trzeci argument (opcjonalny): Imię i nazwisko posła").append(System.lineSeparator());
        System.out.println(builder.toString());
    }
}
