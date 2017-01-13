package Lab9.Tests;

import Lab9.Consts.ExpensesTypesNames;
import Lab9.DTO.*;
import Lab9.Interfaces.IWebCommunication;
import Lab9.Providers.WebCommunication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Filip i Paulinka on 18.12.2016.
 */
public class GetEnvoyJourneysTests {

    IWebCommunication _communicationProvider;

    // Imię i nazwisko, ID posła, numer sprawdzanego wyjazdu, koszt tego wyjazdu, kraj docelowy
    Object[][] parameters = new Object[][]
            {
                    new Object[] {"Maciej Wydrzyński", 433, 0, 1269.2, 7, "BE"},
                    new Object[] { "Witold Pahl", 284, 0, 3781.16, 7, "GR"},
                    new Object[] {"Cezary Grabarczyk", 116, 4, 38.51, 7, "UA"},
                    new Object[] {"Cezary Grabarczyk", 116, 2, 3667.64, 7, "GE"},
                    new Object[] {"Cezary Grabarczyk", 116, 7, 7202.18, 7, "AZ"}
            };


    GetEnvoyJourneysTests() {
        _communicationProvider = new WebCommunication();
    }

    @Test
    public void GetEnvoyJourneysTest()
    {
        try {
            for (int i=0; i<parameters.length; i++) {
                EnvoyJourneysDTO envoyJourneys = _communicationProvider.GetEnvoyJournes((int)parameters[i][4], new EnvoyPersonalDataDTO((int)parameters[i][1], (String)parameters[i][0]));
                JourneyDTO journey = envoyJourneys.Journeys.get((int)parameters[i][2]);

                assertEquals(journey.Cost, (double)parameters[i][3]);
                assertTrue(journey.CountryCode.equals((String)parameters[i][5]));
            }
        }
        catch(Exception ex)
        {
            assertTrue(false);
        }
    }
}
