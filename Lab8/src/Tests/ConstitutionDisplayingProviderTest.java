package Tests;

import Models.ConstitutionModel;
import Providers.ConstitutionLoadingProvider;
import Providers.ConstitutionDisplayingProvider;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Filip on 2016-11-27.
 */
class ConstitutionDisplayingProviderTest {

    @org.junit.jupiter.api.Test
    public void testGettingArcticles()
    {
        ConstitutionLoadingProvider _provider = new ConstitutionLoadingProvider();
        ConstitutionDisplayingProvider _constitutionProvider = new ConstitutionDisplayingProvider();
        ConstitutionModel constitution = null;
        try {
            constitution = _provider.LoadConstitution("F:\\KonstytucjaRP.txt");
            for (int i = 1; i < 244; i++)
                assertNotNull(_constitutionProvider.GetArticleAsString(constitution, i));
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @org.junit.jupiter.api.Test
    public void testGettingChapters()
    {
        ConstitutionLoadingProvider _provider = new ConstitutionLoadingProvider();
        ConstitutionDisplayingProvider _constitutionProvider = new ConstitutionDisplayingProvider();
        ConstitutionModel constitution = null;
        try {
            constitution = _provider.LoadConstitution("F:\\KonstytucjaRP.txt");
            for (int i = 1; i < 13; i++)
                assertNotNull(_constitutionProvider.GetChapterAsString(constitution, i));
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

}