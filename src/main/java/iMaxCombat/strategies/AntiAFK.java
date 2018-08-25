package iMaxCombat.strategies;

import iMaxCombat.core.Core;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;

import java.awt.event.KeyEvent;

/**
 * Created by Tristan on 13/04/2018.
 */
public class AntiAFK implements Strategy {
    @Override
    public boolean activate() {
        return Random.between(1,1) == 1;
    }

    @Override
    public void execute()
    {
        final int[]  keys   = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };
        System.out.println("ANTI AFK");

        Core.CurrentStatus = "Anti AFK";
        int keyCode = keys[Random.between(0, keys.length)];
        Keyboard.getInstance().pressKey(keyCode);
        Time.sleep(Random.between(500,1500));
        Keyboard.getInstance().releaseKey(keyCode);
        Core.CurrentStatus = "Waiting...";

    }
}
