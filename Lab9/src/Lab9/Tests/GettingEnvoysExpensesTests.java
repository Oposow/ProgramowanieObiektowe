package Lab9.Tests;

import Lab9.Consts.ExpensesTypesNames;
import Lab9.DTO.EnvoyExpensesDTO;
import Lab9.DTO.EnvoyYearExpensesDTO;
import Lab9.Interfaces.IWebCommunication;
import Lab9.Providers.WebCommunication;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Filip i Paulinka on 18.12.2016.
 */
public class GettingEnvoysExpensesTests {

    IWebCommunication _communicationProvider;

    Object[][] parameters = new Object[][]
            {
                    new Object[] {"Witold Pahl", ExpensesTypesNames.CorespondenceCosts, 891.0, 7},
                    new Object[] { "Maciej Wydrzyński", ExpensesTypesNames.PrivateCarCommunicationCosts, 70207.2, 7},
                    new Object[] {"Maciej Wydrzyński", ExpensesTypesNames.TranslationsCosts, 5656.8 , 7},
                    new Object[] {"Sławomir Kłosowski", ExpensesTypesNames.RentingRoomCosts, 370.0, 7},
                    new Object[] {"Sławomir Kłosowski", ExpensesTypesNames.PrivateCarCommunicationCosts, 66503.87, 7}
            };


    GettingEnvoysExpensesTests() {
        _communicationProvider = new WebCommunication();
    }

    @Test
    public void GetEnvoyExpensesTest()
    {
        try {
            for (int i=0; i<parameters.length; i++) {
                double total = 0;
                EnvoyExpensesDTO expenses = _communicationProvider.GetEnvoyExpenses((String)parameters[i][0], (int)parameters[i][3]);
                for (EnvoyYearExpensesDTO year : expenses.Years)
                    total += year.Amounts.get(expenses.Types.get((String)parameters[i][1]));

                assertEquals(total, (double)parameters[i][2]);
            }
        }
        catch(Exception ex)
        {
            assertTrue(false);
        }
    }

}
