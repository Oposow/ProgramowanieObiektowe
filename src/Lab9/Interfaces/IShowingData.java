package Lab9.Interfaces;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public interface IShowingData {
    void ShowEnvoyExpenses(String envoyName, int term);
    void ShowEnvoyMinorExpenses(String envoyName, int term);
    void ShowEnvoysAverageExpenses(int term);
    void ShowEnvoyMostOftenTravellingAbroad(int term);
    void ShowEnvoyWithLongestAbroadSojourn(int term);
    void ShowEnvoyWithMostExpensiveAbroadJourney(int term);
    void ShowEnvoysWhoVisitedItaly(int term);
}
