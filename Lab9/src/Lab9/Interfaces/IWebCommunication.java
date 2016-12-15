package Lab9.Interfaces;

import Lab9.DTO.MainEnvoyDataDTO;

import java.util.LinkedList;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public interface IWebCommunication {
    LinkedList<MainEnvoyDataDTO> GetAllEnvoysMainData(int term);
    MainEnvoyDataDTO GetEnvoyExpenses(int term, int envoyId);
    LinkedList<MainEnvoyDataDTO> GetAllEnvoysExpenses(int term);
    LinkedList<MainEnvoyDataDTO> GetAllEnvoysJournes(int term);
    int GetEnvoyId(String envoyName);
}
