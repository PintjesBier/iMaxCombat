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
    public static void TeleportToHome()
    {
        final Player CHARACTER = Players.getMyPlayer();

        //TELEPORTING TO HOME
        if (!Core.HomeArea.contains(CHARACTER.getLocation()))
        {
            System.out.println("-------------------");
            System.out.println("Teleporting to home");
            System.out.println("-------------------");
            Core.CurrentStatus = "Teleporting to home";
            Keyboard.getInstance().sendKeys("::home");
        }

        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Core.HomeArea.contains(CHARACTER.getLocation()) && CHARACTER.getAnimation() == -1;
            }
        },5000);
        Time.sleep(800);

    }

    public static void TeleportToBank()
    {
        final Player CHARACTER = Players.getMyPlayer();

        //TELEPORTING TO HOME
        if (!Core.HomeArea.contains(CHARACTER.getLocation()))
        {
            System.out.println("-------------------");
            System.out.println("Teleporting to home");
            System.out.println("-------------------");
            Core.CurrentStatus = "Teleporting to home";
            Keyboard.getInstance().sendKeys("::home");

        }

        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Core.HomeArea.contains(CHARACTER.getLocation()) && CHARACTER.getAnimation() == -1;
            }
        },5000);

        if (!Core.BankArea.contains(CHARACTER.getLocation()))
        {
            System.out.println("---------------");
            System.out.println("Walking to bank");
            System.out.println("---------------");
            Core.CurrentStatus = "Walking to bank";

            Core.BankTile.walkTo();
        }

        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Core.BankArea.contains(CHARACTER.getLocation()) && CHARACTER.getAnimation() == -1;
            }
        },10000);
        Time.sleep(800);

    }

    public static void TeleportToShops()
    {
        final Player CHARACTER = Players.getMyPlayer();

        //TELEPORTING TO HOME
        if (!Core.HomeArea.contains(CHARACTER.getLocation()))
        {
            Keyboard.getInstance().sendKeys("::home");
            Core.CurrentStatus = "Teleporting to home";
            System.out.println("-------------------");
            System.out.println("Teleporting to home");
            System.out.println("-------------------");
        }

        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Core.HomeArea.contains(CHARACTER.getLocation());
            }
        },5000);

        //WALKING TO SHOP
        if (!Core.ShopAea.contains(CHARACTER.getLocation()))
        {
            Core.ShopTile.walkTo();
            Core.CurrentStatus = "Walking to shop";
            System.out.println("---------------");
            System.out.println("Walking to shop");
            System.out.println("---------------");
        }

        //WAITING UNTIL CHARACTER IS IN SHOP
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Core.ShopAea.contains(CHARACTER.getLocation()) && CHARACTER.getAnimation() == -1;
            }
        },10000);
        Time.sleep(800);

    }

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
            },10000);
            Time.sleep(800);
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

    //LOOTING
    public static void Loot()
    {
        final Player CHARACTER = Players.getMyPlayer();
        final int[] Loot = new int[] {Constants.MONEY_ID_GROUNDITEM, Constants.LOOPHALFKEY_ID_GROUNDITEM
                , Constants.GOLDCHARM_ID_GROUNDITEM, Constants.CLUESCROLL_EASY_ID_GROUNDITEM
                , Constants.GREENCHARM_ID_GROUNDITEM, Constants.CRIMSONCHARM_ID_GROUNDITEM};
        final GroundItem CurrenLoot;

        try {
                //LOOTING BONE
                Core.CurrentStatus = "Looting bone";

                //WALK TO BONE
                Core.CRAB[0].getLocation().walkTo();
                //WAIT FOR LOCATION
                Time.sleep(new SleepCondition()
                {
                    @Override
                    public boolean isValid()
                    {
                        return Core.CRAB[0].getLocation() == CHARACTER.getLocation();
                    }
                }, 1500);
                //WAIT FOR BONE
                Time.sleep(new SleepCondition()
                {
                    @Override
                    public boolean isValid()
                    {
                        return GroundItems.getClosest(Constants.BONES_ID_GROUNDITEM).distanceTo() == 0;
                    }
                }, 2000);

                //PICK UP BONES
                Core.CurrentStatus = "Picking up bone";
                if (GroundItems.getClosest(Constants.BONES_ID_GROUNDITEM).distanceTo() == 0)
                {
                    GroundItems.getClosest(Constants.BONES_ID_GROUNDITEM).take();

                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.contains(Constants.BONES_ID);
                        }
                    }, 1500);
                    Time.sleep(800);
                }

            //LOOTING OTHER ITEMS
            while (GroundItems.getClosest(Loot) != null && GroundItems.getClosest(Loot).distanceTo() <= 1)
            {
                Core.CurrentStatus = "Looting other items";
                GroundItems.getClosest(Loot).take();

                Time.sleep(1200);
            }

            //START BURRYING
            Core.BoneLooted = true;

        }
        catch (Exception e)
        {
            Core.BoneLooted = true;
            System.out.println(e);
        }
    }

    public static void BurryBone()
    {
        try
        {
            Core.CurrentStatus = "Burrying bone";

            while (Inventory.contains(Constants.BONES_ID))
            {
                Inventory.getItem(Constants.BONES_ID).interact(Items.Option.CONSUME);

                Time.sleep(1200);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Players.getMyPlayer().getAnimation() == -1;
                    }
                }, 1500);
            }

            //WAIT
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() == -1 && !Inventory.contains(Constants.BONES_ID);
                }
            }, 1500);

                Core.CRABkilled = false;
                Core.BoneLooted = false;
        }
        catch(Exception e)
        {
            Core.CRABkilled = false;
            Core.BoneLooted = false;
            System.out.println(e);
        }
    }

    public static void EmptyInventory()
    {
        final Player CHARACTER = Players.getMyPlayer();

        Core.CurrentStatus = "Emptying inventory";
        if (Core.BankArea.contains(CHARACTER.getLocation()))
        {
            TeleportToBank();
        }

        Core.CurrentStatus = "Opening bank";
        Bank.open();
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Bank.isOpen();
            }
        }, 3000);

        Core.CurrentStatus = "Depositing items";
        Bank.depositAllExcept(Constants.MONEY_ID);
        Time.sleep(Random.between(800,1200));
        Bank.close();

        TeleportToTrain();
        Time.sleep(800);
    }

    //BUY AND EQUIP GEAR
    public static void WearStarterGear()
    {
        if (!Core.ArmourUpgraded)
        {
            if (!Equipment.isWearing(Constants.MONKROBE_ID) && Inventory.contains(Constants.MONKROBE_ID)) {
                Equipment.equip(Constants.MONKROBE_ID);
            }

            if (!Equipment.isWearing(Constants.MONKTOP_ID) && Inventory.contains(Constants.MONKTOP_ID)) {
                Equipment.equip(Constants.MONKTOP_ID);
            }

            if (!Equipment.isWearing(Constants.AMMYOFSTR_ID) && Inventory.contains(Constants.AMMYOFSTR_ID)) {
                Equipment.equip(Constants.AMMYOFSTR_ID);
            }

            if (!Equipment.isWearing(Constants.IRONSCIM_ID) && Inventory.contains(Constants.IRONSCIM_ID)) {
                Equipment.equip(Constants.IRONSCIM_ID);
            } else {
                return;
            }
        }
    }

    public static void BuyAndEquipDragonScimitar()
    {
        final Player CHARACTER = Players.getMyPlayer();

        //TELEPORT TO SHOPS
        if (!Core.ShopAea.contains(CHARACTER.getLocation()))
        {
            TeleportToShops();
        }

        //BUY A DRAGON SCIMITAR
        if (!Inventory.contains(Constants.DRAGSCIM_ID) && !Equipment.isWearing(Constants.DRAGSCIM_ID))
        {
            //OPEN SHOP WINDOW
            Npc ZEKE = Npcs.getClosest(Constants.ZEKE_ID_NPC);
            ZEKE.interact(Npcs.Option.TRADE);
            Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Interfaces.isOpen(3824);
                    }
                }, 3000);

            Time.sleep(Random.between(800, 1200));

            //BUY DRAGON SCIMITAR
            Menu.sendAction(78, 4587, 20, 3900);
            Time.sleep(800);

            //CLOSE SHOP WINDOWS
            Menu.sendAction(200, 0, 0, 3902);
            Time.sleep(800);

            //EQUIP DRAGON SCIMITAR
            if (Inventory.contains(Constants.DRAGSCIM_ID) && !Equipment.isWearing(Constants.DRAGSCIM_ID))
            {
                Equipment.equip(Constants.DRAGSCIM_ID);
            }

            Time.sleep(new SleepCondition()
            {
                @Override
                public boolean isValid()
                {
                    return Inventory.contains(Constants.IRONSCIM_ID) && Equipment.isWearing(Constants.DRAGSCIM_ID);
                }
            }, 3000);

            Inventory.getItem(Constants.IRONSCIM_ID).drop();

            //Set to not run again
            Core.DragonScimitarBUpgraded = true;
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