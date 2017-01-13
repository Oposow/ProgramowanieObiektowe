package Lab9.DTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class EnvoyYearExpensesDTO {
    public final List<Double> Amounts;
    public final int Year;

    public EnvoyYearExpensesDTO(List<Double> _Amounts, int _Year)
    {
        Amounts = _Amounts;
        Year = _Year;
    }
}
