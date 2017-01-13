package Lab9.Consts;

/**
 * Created by Filip i Paulinka on 17.12.2016.
 */
public final class HttpStrings {
    public static String GetAllEvnoysFromTermHttpString = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=%0$d";
    public static String GetEnvoyExpensesHttpString = "https://api-v3.mojepanstwo.pl/dane/poslowie/%0$d.json?layers[]=wydatki";
    public static String GetEnvoyJournesHtppString = "https://api-v3.mojepanstwo.pl/dane/poslowie/%0$d.json?layers[]=wyjazdy";

    private HttpStrings() {}
}
