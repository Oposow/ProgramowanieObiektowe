package Lab9.Interfaces;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public interface IShowingData {
    void ShowEnvoyTotalExpenses(String envoyName, int term) throws Exception;
    void ShowEnvoyMinorExpenses(String envoyName, int term) throws Exception;
    void ShowEnvoysAverageExpenses(int term) throws Exception;
    void ShowEnvoyMostOftenTravellingAbroad(int term) throws Exception;
    void ShowTheLongestStayingAbroadSum(int term) throws Exception;
    void ShowEnvoyWithMostExpensiveAbroadJourney(int term) throws Exception;
    void ShowEnvoysWhoVisitedItaly(int term) throws Exception;
}
