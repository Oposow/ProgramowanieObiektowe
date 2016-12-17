package Lab9.DTO;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class EnvoyDataDTO {
    public final MainEnvoyDataDTO MainData;
    public final EnvoyExpensesDTO Expenses;
    public final List<JourneyDTO> Journeys;

    public EnvoyDataDTO(MainEnvoyDataDTO _MainData, EnvoyExpensesDTO _Expenses, List<JourneyDTO> _Journeys)
    {
        MainData = _MainData;
        Expenses = _Expenses;
        Journeys = _Journeys;
    }
}

public class MainEnvoyDataDTO
{
    public final String Name;
    public final int Id;

    MainEnvoyDataDTO(String _Name, int _Id)
    {
        Name = _Name;
        Id = _Id;
    }
}