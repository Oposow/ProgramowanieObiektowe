package Lab9.DTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class EnvoyExpensesDTO {
    public final EnvoyPersonalDataDTO PersonalData;
    public final HashMap<String, Integer> Types;
    public final List<EnvoyYearExpensesDTO> Years;

    public EnvoyExpensesDTO(EnvoyPersonalDataDTO _PersonalData, HashMap<String, Integer> _Types, List<EnvoyYearExpensesDTO> _Years)
    {
        PersonalData = _PersonalData;
        Years = _Years;
        Types = _Types;
    }
}
