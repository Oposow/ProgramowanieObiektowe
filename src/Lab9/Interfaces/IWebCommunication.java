package Lab9.Interfaces;

import Lab9.DTO.MainEnvoyDataDTO;

import java.util.LinkedList;

/**
 * Created by Filip i Paulinka on 11.12.2016.
 */
public interface IWebCommunication {
    LinkedList<EnvoyDataDTO> GetAllEnvoysMainData(int term);
    EnvoyDataDTO FillEnvoyExpenses(int term, EnvoyDataDTO model);
    LinkedList<EnvoyDataDTO> FillAllEnvoysExpenses(int term);
    LinkedList<EnvoyDataDTO> FillAllEnvoysJournes(int term);
    int GetEnvoyId(String envoyName);
}
