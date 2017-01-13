package Lab9.DTO;

import java.util.List;

/**
 * Created by Filip i Paulinka on 17.12.2016.
 */
public class EnvoyJourneysDTO {
    public final EnvoyPersonalDataDTO PersonalData;
    public final List<JourneyDTO> Journeys;

    public EnvoyJourneysDTO(List<JourneyDTO> _Journeys, EnvoyPersonalDataDTO _PersonalData)
    {
        PersonalData = _PersonalData;
        Journeys = _Journeys;
    }
}
