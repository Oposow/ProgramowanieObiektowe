import Consts.PartType;
import Models.ConstitutionModel;
import Providers.ConstitutionLoadingProvider;
import Providers.ConstitutionProvider;

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
        ConstitutionProvider _constitutionProvider = new ConstitutionProvider();
        ConstitutionLoadingProvider _constitutionLoadingProvider = new ConstitutionLoadingProvider();
        try{
            if (args.length < 3)
                throw new NullPointerException();
            ConstitutionModel constitution = _constitutionLoadingProvider.LoadConstitution(args[0]);
            PartType type = PartType.values()[Integer.parseInt(args[1])];
            int fragmentNumber = Integer.parseInt(args[2]);

            if (type == PartType.Chapter)
                System.out.println(_constitutionProvider.GetChapterAsString(constitution, fragmentNumber));
            else if (type == PartType.Article)
            {
                int rangeEnd = fragmentNumber;
                if (args.length >3)
                    rangeEnd = Integer.parseInt(args[3]);
                for(int i = fragmentNumber; i<=rangeEnd; i++)
                    System.out.println(_constitutionProvider.GetArticleAsString(constitution, i));
            }
            else
                throw new IllegalArgumentException();
        }
        catch(NullPointerException ex)
        {
            System.out.println("Podano za mało argumentów");
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
