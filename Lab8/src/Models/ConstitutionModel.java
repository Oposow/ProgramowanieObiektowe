package Models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Filip i Paulinka on 26.11.2016.
 */

public class ConstitutionModel {
    public String Preamble;
    public List<ChapterModel> Chapters;

    public ConstitutionModel()
    {
        Chapters = new LinkedList<ChapterModel>();
    }
}
