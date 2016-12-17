package Lab9.Exceptions;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class NoSuchEnvoyException extends Exception {
    NoSuchEnvoyException()
    {
        super("There is no such envoy!");
    }
}
