package iMaxCombat.strategies;

import iMaxCombat.data.Constants;
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
 * Created by Tristan on 3/09/2018.
 */
public class WaitForKill implements Strategy, MessageListener
{
    @Override
    public boolean activate() {
        return (Game.isLoggedIn() && getMyPlayer().isInCombat());
    }

    @Override
    public void execute()
    {
            AttackNPC.Teller = 0;
    }

    @Override
    public void messageReceived(MessageEvent messageEvent) {
        if (messageEvent.getMessage().contains("A Citrine Crab appears") && !getMyPlayer().isInCombat())
        {
            Npcs.getClosest(Constants.CITRINE_CRAB_ID).interact(Npcs.Option.ATTACK);
        }
    }
}
