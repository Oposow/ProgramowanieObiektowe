package Models;

import Consts.PartType;

/**
 * Created by Filip on 2016-12-02.
 */
public class ArgumentsModel {
    public String FilePath;
    public DisplayingArgumentsModel DisplayingArguments;

    public ArgumentsModel(){
        DisplayingArguments = new DisplayingArgumentsModel();
    }
}
