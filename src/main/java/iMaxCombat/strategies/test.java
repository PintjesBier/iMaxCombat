package iMaxCombat.strategies;

import iMaxCombat.core.Core;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;

import java.util.Arrays;


/**
 * Created by Tristan on 13/04/2018.
 */
public class test implements Strategy, MessageListener
{
    //VARIABLES
    String[] Staff = new String[]{"Bbc", "Huy", "TEST2"};

    //TIMER
    public static org.parabot.environment.api.utils.Timer Timer = new org.parabot.environment.api.utils.Timer();

    @Override
    public boolean activate() {
        return Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Time.sleep(800);
        Core.CurrentStatus = String.valueOf(Timer.getElapsedTime());

        for (Player player : Players.getNearest())
        {
            Core.CurrentStatus = String.valueOf(Timer.getElapsedTime());
            System.out.println(player.getName());
            if (Arrays.asList(Staff).contains(player.getName()))
            {
                Core.CurrentStatus = "STAFF DETECTED";
                Core.StaffDetected = true;
                Mouse.getInstance().click(748,6,true);
                Time.sleep(800);
                Mouse.getInstance().click(604,376,true);

                Core.CurrentStatus = "Logged out";
                Time.sleep(5000);
            }
        }
    }

    @Override
    public void messageReceived(MessageEvent messageEvent)
    {
        if (Arrays.asList(Staff).contains(messageEvent.getSender()))
        {
            Core.CurrentStatus = "STAFF DETECTED";
            Mouse.getInstance().click(748,6,true);
            Time.sleep(800);
            Mouse.getInstance().click(604,376,true);
            Timer = new Timer();
            if (Timer.getElapsedTime() > 500000000)
            {
                Time.sleep(800);
            }

            Core.CurrentStatus = "Logged out";

        }
    }
}
