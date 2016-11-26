package Models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Filip i Paulinka on 26.11.2016.
 */
public class SubChapterModel {
    public String Title;
    public List<ArticleModel> Articles;

    public SubChapterModel()
    {
        Articles = new LinkedList<ArticleModel>();
    }
}
