package Models;

/**
 * Created by Filip i Paulinka on 26.11.2016.
 */

public class ArticleModel {
    public int ChaptersNumber;
    public String Header;
    public java.util.List<String> TextPoints;

    public ArticleModel()
    {
        TextPoints = new java.util.LinkedList<String>();
    }
}
