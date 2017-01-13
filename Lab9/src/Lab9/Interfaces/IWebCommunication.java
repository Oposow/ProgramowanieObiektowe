package Lab9.Interfaces;

import Lab9.DTO.EnvoyExpensesDTO;
import Lab9.DTO.EnvoyJourneysDTO;
import Lab9.DTO.EnvoyPersonalDataDTO;
import Lab9.DTO.JourneyDTO;


import java.util.LinkedList;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public interface IWebCommunication {
    LinkedList<EnvoyPersonalDataDTO> GetAllEnvoysPersonalData(int term);
    EnvoyExpensesDTO GetEnvoyExpenses(int term, EnvoyPersonalDataDTO envoy) throws Exception;
    EnvoyJourneysDTO GetEnvoyJournes(int term, EnvoyPersonalDataDTO envoy) throws Exception;
    EnvoyExpensesDTO GetEnvoyExpenses(String envoyName, int term) throws Exception;
    LinkedList<EnvoyExpensesDTO> GetAllEnvoysExpenses(int term) throws Exception;
    LinkedList<EnvoyJourneysDTO> GetAllEnvoysJourneys(int term) throws Exception;
}
