package Lab9.Interfaces;

import Lab9.LineArgs;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public interface IArgsHandler {
    LineArgs ParseLineArgs(String[] args);
    void ShowChoosenData(LineArgs args) throws Exception;
}
