package Lab9.DTO;

import java.util.LinkedList;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class EnvoyYearExpensesDTO {
    public final List<double> Amounts;
    public final int Year;

    public EnvoyYearExpensesDTO(List<double> _Amounts, int _Year)
    {
        Amounts = _Amounts;
        Year = _Year;
    }
}
