import Consts.PartType;
import Models.ArgumentsModel;
import Models.ConstitutionModel;
import Providers.ArgumentsParser;
import Providers.ConstitutionLoadingProvider;
import Providers.ConstitutionDisplayingProvider;

import javax.naming.NoPermissionException;
import java.io.IOException;

/**
 * Created by Filip on 2016-11-26.
 */
public class Main {

    // Pierwszy argument - ścieżka do pliku z konstytucją
    // Drugi argument - rodzaj wyświetlanej części (1 - rodział, 2 - artykuł)
    // Trzeci argument - numer części do wyświetlenia
    public static void main(String [] args)
    {
        ConstitutionDisplayingProvider _constitutionDisplayingProvider = new ConstitutionDisplayingProvider();
        ConstitutionLoadingProvider _constitutionLoadingProvider = new ConstitutionLoadingProvider();
        try{
            ArgumentsModel arguments = ArgumentsParser.ParseArguments(args);
            ConstitutionModel constitution = _constitutionLoadingProvider.LoadConstitution(arguments.FilePath);
            _constitutionDisplayingProvider.DisplayConstitution(constitution, arguments.DisplayingArguments);
        }
        catch(NoPermissionException ex)
        {
            System.out.println("Brak dostępu do wskazanego pliku");
        }
        catch(IOException ex)
        {
            System.out.println("Podany plik nie istnieje");
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println("Wprowadzono błędny numer fragmentu lub typu fragmentu");
        }
        catch(Exception ex)
        {
            System.out.println("Podano błędne argumenty");
        }
    }
}
