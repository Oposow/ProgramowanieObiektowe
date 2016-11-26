package Interfaces;

import Models.ConstitutionModel;

import javax.naming.NoPermissionException;
import java.io.IOException;

/**
 * Created by Filip on 2016-11-26.
 */
public interface IConstitutionLoadingProvider {
    ConstitutionModel LoadConstitution(String filePath) throws IOException, NoPermissionException;
}
