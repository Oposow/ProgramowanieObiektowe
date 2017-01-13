package Lab9;

import Lab9.Consts.DataTypeToBeShown;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class LineArgs {
    public final DataTypeToBeShown DataType;
    public final int Term;
    public final String EnvoyName;

    public LineArgs(DataTypeToBeShown _DataType, int _Term, String _EnvoyName) {
        DataType = _DataType;
        Term = _Term;
        EnvoyName = _EnvoyName;
    }
}
