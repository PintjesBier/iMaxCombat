package iMaxCombat.strategies;

import iMaxCombat.core.Core;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;

import java.awt.event.KeyEvent;

import static org.rev317.min.api.methods.Players.getMyPlayer;

/**
 * Created by Tristan on 22/03/2018.
 */
public class Relog implements Strategy
{

    @Override
    public boolean activate() {
        return !Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        int i = 0;
        Core.CurrentStatus = "Relogging";

        Mouse.getInstance().click(335,202,true);
        Time.sleep(800);
        while (i < 25)
        {
            Keyboard.getInstance().pressKey(KeyEvent.VK_BACK_SPACE);
            Time.sleep(50);
            Keyboard.getInstance().releaseKey(KeyEvent.VK_BACK_SPACE);
            i++;
        }
        Time.sleep(1500);
        Keyboard.getInstance().pressKey(KeyEvent.VK_ENTER);
        Time.sleep(100);
        Keyboard.getInstance().releaseKey(KeyEvent.VK_ENTER);

        Core.CurrentStatus = "Waiting on relog";
        Time.sleep(Random.between(3000,5000));

        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Game.isLoggedIn() && getMyPlayer().getAnimation() == -1;
            }
        }, 3000);


    }
}
