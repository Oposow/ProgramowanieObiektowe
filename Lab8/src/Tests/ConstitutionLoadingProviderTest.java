package Tests;

import Models.ArticleModel;
import Models.ChapterModel;
import Models.ConstitutionModel;
import Models.SubChapterModel;
import Providers.ConstitutionLoadingProvider;
import Providers.ConstitutionProvider;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Filip on 2016-11-26.
 */
class ConstitutionLoadingProviderTest {

    @org.junit.jupiter.api.Test
    public void testChaptersAmount()
    {
        ConstitutionLoadingProvider _provider = new ConstitutionLoadingProvider();
        ConstitutionModel constitution = null;
        try {
            constitution = _provider.LoadConstitution("F:\\KonstytucjaRP.txt");
        }
        catch(Exception ex){
            assertTrue (false);
        }

        assertEquals(13,constitution.Chapters.size());
    }

    @org.junit.jupiter.api.Test
    public void testArticlesAmount()
    {
        ConstitutionLoadingProvider _provider = new ConstitutionLoadingProvider();
        ConstitutionModel constitution = null;
        try {
            constitution = _provider.LoadConstitution("F:\\KonstytucjaRP.txt");
        }
        catch(Exception ex){
            assertTrue (false);
        }
        int amount=0;
        for (ChapterModel ch : constitution.Chapters)
            for(SubChapterModel sub : ch.SubChapters)
                for(ArticleModel art : sub.Articles)
                    amount++;
        assertEquals(243, amount);
    }

    @org.junit.jupiter.api.Test
    public void testInvalidLines()
    {
        ConstitutionLoadingProvider _provider = new ConstitutionLoadingProvider();
        ConstitutionModel constitution = null;
        try {
            constitution = _provider.LoadConstitution("F:\\KonstytucjaRP.txt");
        }
        catch(Exception ex){
            assertTrue (false);
        }

        for (ChapterModel ch : constitution.Chapters)
            for(SubChapterModel sub : ch.SubChapters)
                for(ArticleModel art : sub.Articles)
                    for(String line : art.TextPoints)
                    {
                        assertNotEquals("©Kancelaria Sejmu", line);
                        try {
                            new SimpleDateFormat("yyyy-MM-dd").parse(line);
                            assertTrue(false);
                        }
                        catch(ParseException ex) {}
                    }
    }
}