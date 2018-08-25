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

        //GET STARTING MONEY FOR PAINT
        if (Inventory.contains(Constants.MONEY_ID)) {
            Core.StartMoney = Inventory.getItem(Constants.MONEY_ID).getStackSize();}
        else {
            Core.StartMoney = 0;}

        //SET ATTACK STYLE TO ATTACK
        Methods.AttackStyle();
        Core.CurrentAttackStyle = "Attack";

        //TURN ON AUTO-RETALIATE
        if (Game.getSetting(172) == 0)
        {
            Menu.sendAction(169,0,515,150);
        }
        else if (Game.getSetting(172) == 1)
        {
            System.out.println("Auto retaliate already on");
        }

        Core.DragonScimitarBUpgraded = Inventory.contains(Constants.DRAGSCIM_ID) || Equipment.isWearing(Constants.DRAGSCIM_ID);

        //don't run this class again
        Core.CurrentStatus = "Data gathered";
        DataGathered = true;
    }
}
