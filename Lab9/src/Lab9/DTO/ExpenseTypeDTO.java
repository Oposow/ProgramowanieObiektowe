package Lab9.DTO;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class ExpenseTypeDTO {
    public final String Title;
    public final int Id;

    public ExpenseTypeDTO(String _Title, int _Id)
    {
        Id = _Id;
        Title = _Title;
    }
}
