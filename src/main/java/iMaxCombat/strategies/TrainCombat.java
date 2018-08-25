package iMaxCombat.strategies;

import iMaxCombat.core.Core;
import iMaxCombat.data.Constants;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;

import static org.rev317.min.api.methods.Players.getMyPlayer;


/**
 * Created by Tristan on 14/03/2018.
 */

public class TrainCombat implements Strategy, MessageListener {

    //VARIABLES
    public org.rev317.min.api.wrappers.Npc Crab;

    @Override
    public boolean activate() {
        return Game.isLoggedIn();
    }

    @Override
    public void execute() {
        try {

            Core.CurrentStatus = "Killing Rock Crab";

/*            if (!Core.CrabsArea.contains(getMyPlayer().getLocation()))
            {
                Methods.TeleportToTrain();

            }*/

            /*Methods.ChangeCombatStyles();*/

            while (Core.CRAB == null && !getMyPlayer().isInCombat())
            {
                Logger.addMessage("iMaxCombat: Searching for crabs", true);
                Core.CRAB = Npcs.getNearest(Constants.CRAB_ID);
                Time.sleep(2000, 2450);
            }

            if (Core.CRAB != null && !getMyPlayer().isInCombat())
            {
                Logger.addMessage("iMaxCombat: Found crab, attacking...", true);

                Core.CRAB[0].interact(Npcs.Option.ATTACK);

                Core.CRAB = null;
                Time.sleep(1500, 1711);

            }

            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getInteractingCharacter() == null;
                }
            }, 5000);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    @Override
    public void messageReceived(MessageEvent messageEvent)
    {
        if (messageEvent.getMessage().contains("I am already under attack."))
        {
            Time.sleep(5000);
        }
        else if (messageEvent.getMessage().contains("This monster is already in combat."))
        {
            Core.CRAB = null;
            Time.sleep(Random.between(1500,3000));
        }
    }
}