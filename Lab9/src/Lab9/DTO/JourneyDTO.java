package Lab9.DTO;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class JourneyDTO {
    public final String CountryCode;
    public final double Cost;
    public final int Length;

    public JourneyDTO(String _CountryCode, double _Cost, int _Length)
    {
        CountryCode = _CountryCode;
        Cost = _Cost;
        Length = _Length;
    }
}