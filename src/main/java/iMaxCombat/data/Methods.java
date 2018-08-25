package iMaxCombat.data;

import iMaxCombat.core.Core;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Player;

import java.awt.event.KeyEvent;

public class Methods
{
    //TELEPORTS
    public static void TeleportToTrain()
    {
        final Player CHARACTER = Players.getMyPlayer();

        if (!Core.CrabsArea.contains(CHARACTER.getLocation()))
        {
            Keyboard.getInstance().sendKeys("::train");

            //WAITING UNTIL CHARACTER IS IN SHOP
            Time.sleep(new SleepCondition()
            {
                @Override
                public boolean isValid()
                {
                    return Core.CrabsArea.contains(CHARACTER.getLocation()) && CHARACTER.getAnimation() == -1;
                }
            },5000);
            Time.sleep(4751, 15874);
        }
    }


    //COMBAT
    public static void AttackStyle()
    {
        Menu.sendAction(646, 3315, 41, 5862); // Send action
        Time.sleep(800);
    }

    private static void DefenceStyle()
    {
        Menu.sendAction(646, 637, 513, 2430); // Send action
        Time.sleep(800);
    }

    private static void StrengthStyle()
    {
        Menu.sendAction(646, 637, 513, 2432); // Send action
        Time.sleep(800);
    }

    public static void ChangeCombatStyles()
    {
        //CHANGE COMBAT STYLE
        if (Skill.ATTACK.getRealLevel() < 60 && Skill.STRENGTH.getRealLevel() <60  && Skill.DEFENSE.getRealLevel() <60 && Skill.ATTACK.getRealLevel() != 99 &&Skill.DEFENSE.getRealLevel() != 99 && Skill.STRENGTH.getRealLevel() != 99 && Core.CurrentAttackStyle != "Attack")
        {
            Methods.AttackStyle();
            Core.CurrentAttackStyle = "Attack";
        }
        else if (Skill.ATTACK.getRealLevel() >= 60 && Skill.STRENGTH.getRealLevel() < 60 && Skill.DEFENSE.getRealLevel() < 60 && Skill.ATTACK.getRealLevel() != 99 &&Skill.DEFENSE.getRealLevel() != 99 && Skill.STRENGTH.getRealLevel() != 99 && Core.CurrentAttackStyle != "Strength")
        {
            Methods.StrengthStyle();
            Core.CurrentAttackStyle = "Strength";
        }
        else if (Skill.STRENGTH.getRealLevel() >= 60 && Skill.ATTACK.getRealLevel() >= 60 && Skill.DEFENSE.getRealLevel() < 60 && Skill.ATTACK.getRealLevel() != 99 &&Skill.DEFENSE.getRealLevel() != 99 && Skill.STRENGTH.getRealLevel() != 99 && Core.CurrentAttackStyle != "Defence")
        {
            Methods.DefenceStyle();
            Core.CurrentAttackStyle = "Defence";
        }
        else if (Skill.DEFENSE.getRealLevel() >= 60 && Skill.ATTACK.getRealLevel() >= 60 && Skill.STRENGTH.getRealLevel() >= 60 && Skill.ATTACK.getRealLevel() != 99 &&Skill.DEFENSE.getRealLevel() != 99 && Skill.STRENGTH.getRealLevel() != 99 && Core.CurrentAttackStyle != "Attack")
        {
            Methods.AttackStyle();
            Core.CurrentAttackStyle = "Attack";
        }
        else if (Skill.ATTACK.getRealLevel() == 99 && Skill.STRENGTH.getRealLevel() != 99 && Skill.DEFENSE.getRealLevel() != 99 && Core.CurrentAttackStyle != "Strength")
        {
            Methods.StrengthStyle();
            Core.CurrentAttackStyle = "Strength";
        }
        else if (Skill.STRENGTH.getRealLevel() == 99 && Skill.ATTACK.getRealLevel() == 99 && Skill.DEFENSE.getRealLevel() != 99 && Core.CurrentAttackStyle != "Defence")
        {
            Methods.DefenceStyle();
            Core.CurrentAttackStyle = "Defence";
        }
        else if (Skill.DEFENSE.getRealLevel() == 99 && Skill.ATTACK.getRealLevel() == 99 && Skill.STRENGTH.getRealLevel() == 99 && Core.CurrentAttackStyle != "Attack")
        {
            Methods.AttackStyle();
            Core.CurrentAttackStyle = "Attack";
        }
    }

    //ANTI AFK
    public static void ANTIAFK()
    {
        final int[]  keys   = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };
        System.out.println("ANTI AFK");


        if (Random.between(0,4) == 2)
        {
            Core.CurrentStatus = "Anti AFK";
            int keyCode = keys[Random.between(0, keys.length)];
            Keyboard.getInstance().pressKey(keyCode);
            Time.sleep(Random.between(500,1500));
            Keyboard.getInstance().releaseKey(keyCode);
            Core.CurrentStatus = "Waiting...";
        }
    }

}