package Lab9.DTO;

import java.util.HashMap;
import java.util.LinkedList;
/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class EnvoyExpensesDTO {
    public final List<ExpenseTypeDTO> Types;
    public final List<EnvoyYearExpensesDTO> Years;

    public EnvoyExpensesDTO(LinkedList<ExpenseTypeDTO> _Types, List<EnvoyYearExpensesDTO> _Years)
    {
        Years = _Years;
        Types = _Types;
    }
}
