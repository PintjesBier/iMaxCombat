package iMaxCombat.strategies;

import iMaxCombat.core.Core;
import iMaxCombat.data.Constants;
import iMaxCombat.data.Methods;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Equipment;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;


/**
 * Created by Tristan on 21/03/2018.
 */
public class DataGathering implements Strategy
{
    private static boolean DataGathered;

    @Override
    public boolean activate() {
        return !DataGathered && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {

        Core.CurrentStatus = "Gathering data";

        //TURN ON AUTO-RETALIATE
        if (Game.getSetting(172) == 0)
        {
            Menu.sendAction(169,0,515,150);
        }
        else if (Game.getSetting(172) == 1)
        {
            System.out.println("Auto retaliate already on");
        }

        //don't run this class again
        Core.CurrentStatus = "Data gathered";
        DataGathered = true;
    }
}
