package Interfaces;

import Models.ConstitutionModel;
import Models.DisplayingArgumentsModel;

import javax.naming.NoPermissionException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Filip i Paulinka on 26.11.2016.
 */
public interface IConstitutionDisplayingProvider {
    void DisplayConstitution(ConstitutionModel constitution, DisplayingArgumentsModel arguments);
    String GetChapterAsString(ConstitutionModel constitution, int chapterNumber);
    String GetArticleAsString(ConstitutionModel constitution, int articleNumber) throws IllegalArgumentException;
}
