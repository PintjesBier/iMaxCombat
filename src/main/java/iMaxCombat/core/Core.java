package iMaxCombat.core;


import iMaxCombat.data.Area;
import iMaxCombat.strategies.AntiAFK;
import iMaxCombat.strategies.DataGathering;
import iMaxCombat.strategies.Relog;
import iMaxCombat.strategies.TrainCombat;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
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
    public static boolean CRABkilled = false;
    public static boolean BoneLooted = false;
    public static String CurrentAttackStyle;
    public static int MoneyMade = 0;
    public static int StartMoney;
    public static boolean MoneyNeeded = true;
    public static boolean ArmourUpgraded = false;
    public static boolean DragonScimitarBUpgraded = false;
    public static boolean settingUp = false;
    public static boolean StaffDetected = false;

    //TIMER
    public static org.parabot.environment.api.utils.Timer Timer = new org.parabot.environment.api.utils.Timer();

    //AREAS
    public static Area CrabsArea = new Area(new Tile(2662, 3707, 0), new Tile(2665, 3740, 0), new Tile(2689, 3740, 0), new Tile(2689, 3707, 0));
    public static Area ShopAea = new Area(new Tile(3084, 3507, 0), new Tile(3084, 3513, 0), new Tile(3076, 3513, 0), new Tile(3076, 3507, 0));
    public static Area HomeArea = new Area(new Tile(3101, 3485, 0), new Tile(3072, 3485, 0), new Tile(3072, 3519, 0), new Tile(3101, 3519, 0));
    public static Area MarketArea = new Area(new Tile(2982, 3365, 0), new Tile(2982, 3392, 0), new Tile(3008, 3392, 0), new Tile(3008, 3365, 0));
    public static Area StallArea = new Area(new Tile(3093, 3502, 0), new Tile(3093, 3499, 0), new Tile(3099, 3499, 0), new Tile(3099, 3502, 0));
    public static Area BankArea = new Area(new Tile(3091, 3499, 0), new Tile(3098, 3499, 0), new Tile(3098, 3488, 0), new Tile(3091, 3488, 0));
    public static Area GnomeCourseArea = new Area(new Tile(2468, 3440, 0), new Tile(2490, 3441), new Tile(2490, 3413, 0), new Tile(2470, 1343, 0));
    public static Area BarbarianCourseArea = new Area(new Tile(2555, 3565, 0), new Tile(2555, 3541, 0), new Tile(2528, 3541, 0), new Tile(2528, 3565, 0));

    //TILES
    public static Tile ShopTile = new Tile(3078, 3508, 0);
    public static Tile ThievingTile = new Tile(3095, 3501, 0);
    public static Tile BankTile = new Tile(3092, 3497, 0);
    public static Tile CrabTile = new Tile(2669, 3728, 0);

    //TrainCombat Strategy
    public static Integer KILLCOUNT = 0;
    public static org.rev317.min.api.wrappers.Npc[] CRAB;

    //Looting Strategy
    public static boolean Looting = true;


    @Override
    public boolean onExecute() {

        //START TIMER
        Timer = new Timer();

        //LOAD STRATEGIES
        strategies.add(new Relog());
        strategies.add(new DataGathering());
        strategies.add(new TrainCombat());
        strategies.add(new AntiAFK());

        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {
        System.out.println("------------------------");
        System.out.println("iMaxCombat shutting down");
        System.out.println("------------------------");
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
    }


}
