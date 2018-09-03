package iMaxCombat.strategies;

import iMaxCombat.core.Core;
import iMaxCombat.data.Constants;
import iMaxCombat.data.Methods;
import org.parabot.core.ui.Logger;
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

public class AttackNPC implements Strategy, MessageListener {

    //VARIABLES
    public static int Teller = 0;

    @Override
    public boolean activate() {
        return (Game.isLoggedIn() && !getMyPlayer().isInCombat() && Core.CRAB != null);
    }

    @Override
    public void execute() {
        try {
            Core.CurrentStatus = "Attacking NPC";
            Logger.addMessage("iMaxCombat: Attacking NPC", true);

           if (!Core.CrabsArea.contains(getMyPlayer().getLocation()))
            {
                Methods.TeleportToTrain();
            }

            if (Core.AutoChangeAttackStyles) {
                //SET ATTACK STYLE TO ATTACK
                Methods.AttackStyle();
                Core.CurrentAttackStyle = "Attack";

                Methods.ChangeCombatStyles();
            }

            if (Core.CRAB != null
                    && !getMyPlayer().isInCombat()
                    && Core.CrabsArea.contains(getMyPlayer().getLocation())
                    && !Core.CRAB[Teller].isInCombat()) {

                Core.CRAB[Teller].interact(Npcs.Option.ATTACK);

                Core.CRAB = null;

                //WAIT UNTIL IN COMBAT
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return getMyPlayer().isInCombat();
                    }
                }, 5000);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @Override
    public void messageReceived(MessageEvent messageEvent)
    {
        if (messageEvent.getMessage().contains("I am already under attack."))
        {
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !getMyPlayer().isInCombat();
                }
            }, 50000);
        }
        else if (messageEvent.getMessage().contains("This monster is already under attack")) {
            Core.CurrentStatus = "Skipping NPC, already in combat!";
            Logger.addMessage("iMaxCombat: Already under attack!", true);

            Core.CRAB[Teller + 1].interact(Npcs.Option.ATTACK);

            Core.CRAB = null;
            Time.sleep(1500, 1711);

        }
    }
}