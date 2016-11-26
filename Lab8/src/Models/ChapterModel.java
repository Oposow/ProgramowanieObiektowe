package Models;

/**
 * Created by Filip i Paulinka on 26.11.2016.
 */

import java.util.LinkedList;
import java.util.List;


public class ChapterModel {
    public String Header;
    public String Title;
    public List<SubChapterModel> SubChapters;

    public ChapterModel()
    {
        SubChapters = new LinkedList<SubChapterModel>();
    }
}
