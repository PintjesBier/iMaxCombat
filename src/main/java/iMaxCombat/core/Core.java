package iMaxCombat.core;


import iMaxCombat.antiban.WhitelistAntiBan;
import iMaxCombat.data.Area;
import iMaxCombat.strategies.*;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by Tristan on 14/03/2018.
 */

@ScriptManifest(author = "iTunes",
        category = Category.COMBAT,
        description = "Will train all melee skills to 99 at rock crabs",
        name = "iMaxCombat",
        servers = { "Revival" },
        version = 0.1)

public class Core extends Script implements Paintable {
    //VARIABLES
    private final ArrayList<Strategy> strategies = new ArrayList<>();
    public static String CurrentStatus = "Starting...";
    public static String CurrentAttackStyle;
    public static boolean AutoChangeAttackStyles = false;

    //TIMER
    private static org.parabot.environment.api.utils.Timer Timer = new org.parabot.environment.api.utils.Timer();

    //AREAS
    public static Area CrabsArea = new Area(new Tile(3700, 2665, 0), new Tile(3700, 2700, 0), new Tile(3730, 2700, 0), new Tile(3730, 2665, 0));

    //AttackNPC Strategy
    private static Integer KILLCOUNT = 0;
    public static Npc[] CRAB;

    @Override
    public boolean onExecute() {

        //START TIMER
        Timer = new Timer();

        //LOAD STRATEGIES
        strategies.add(new Relog());
        strategies.add(new WhitelistAntiBan());
        strategies.add(new DataGathering());
        strategies.add(new SearchNPC());
        strategies.add(new AttackNPC());
        strategies.add(new WaitForKill());

        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {

        super.onFinish();
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(7, 7, 184, 178);
    private final Color color2 = new Color(255, 255, 255, 178);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 1, 12);

    private String FormatNumber(double number) {
        if (number >= 1000 && number < 1000000) {
            return new DecimalFormat("#,###.0").format(number / 1000) + "K";
        } else if (number >= 1000000 && number < 1000000000) {
            return new DecimalFormat("#,###.0").format(number / 1000000) + "M";
        } else if (number >= 1000000000) {
            return new DecimalFormat("#,###.00").format(number / 1000000000) + "B";
        }

        return "" + number;
    }

    public static boolean isPresentINT(int[] a, int Target)
    {
        for (int i : a)
        {
            if (Target == i)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g1)
    {
        Graphics2D g = (Graphics2D)g1;

        //PAINT
        g.setColor(color1);
        g.fillRect(277, 195, 235, 139);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(277, 195, 235, 139);
        g.setFont(font1);
        g.drawString("Status: ", 285, 210);
        g.drawString(Core.CurrentStatus, 380, 210);
        g.drawString("Killcount:  ", 285, 225);
        g.drawString(String.valueOf(Core.KILLCOUNT), 380, 225);
        g.drawString("Kills p/h: ", 285, 240);
        g.drawString(String.valueOf(Timer.getPerHour(Core.KILLCOUNT)), 380, 240);
        g.drawString("Time elapsed:  ", 285, 255);
        g.drawString(Timer.toString(), 380, 255);
/*        g.drawString("Current attackstyle:  ", 285, 270);
        g.drawString(CurrentAttackStyle, 380, 270);*/
    }


}
