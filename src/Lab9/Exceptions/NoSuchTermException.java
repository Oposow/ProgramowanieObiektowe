package Lab9.Exceptions;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public class NoSuchTermException extends Exception {
    NoSuchTermException()
    {
        super("There is no choosen term!");
    }
}
