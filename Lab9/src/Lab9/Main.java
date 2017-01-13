package Lab9;

import Lab9.DTO.EnvoyExpensesDTO;
import Lab9.DTO.EnvoyPersonalDataDTO;
import Lab9.Exceptions.NoSuchEnvoyException;
import Lab9.Interfaces.IArgsHandler;
import Lab9.Interfaces.IWebCommunication;
import Lab9.Providers.ArgsHandler;
import Lab9.Providers.WebCommunication;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args)
    {
        try
        {
            IArgsHandler _argsHandler = new ArgsHandler();
            LineArgs arguments = _argsHandler.ParseLineArgs(args);
            _argsHandler.ShowChoosenData(arguments);
        }
        catch(NoSuchEnvoyException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println("Krytyczny błąd aplikacji, skontaktuj się z pomocą techniczną");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}
