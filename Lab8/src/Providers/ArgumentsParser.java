package Providers;

import Consts.PartType;
import Models.ArgumentsModel;
import Models.ConstitutionModel;

/**
 * Created by Filip on 2016-12-02.
 */
public class ArgumentsParser {
    public static ArgumentsModel ParseArguments(String[] args)
    {
        ArgumentsModel result = new ArgumentsModel();
        if (args.length < 3)
        {
            System.out.println("Podano za mało argumentów");
            System.exit(1);
        }
        result.FilePath = args[0];
        result.DisplayingArguments.TypeToDisplay = PartType.values()[Integer.parseInt(args[1])];
        result.DisplayingArguments.StartNum = Integer.parseInt(args[2]);
        result.DisplayingArguments.EndNum = args.length > 3 ? Integer.parseInt(args[3]) : result.DisplayingArguments.StartNum;
        return result;
    }
}
