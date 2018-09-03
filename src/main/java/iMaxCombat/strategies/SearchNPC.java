package iMaxCombat.strategies;

import iMaxCombat.core.Core;
import iMaxCombat.data.Constants;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Npcs;

import static org.rev317.min.api.methods.Players.getMyPlayer;

/**
 * Created by Tristan on 3/09/2018.
 */
public class SearchNPC implements Strategy {
    @Override
    public boolean activate() {
        return (Game.isLoggedIn() && !getMyPlayer().isInCombat() && Core.CRAB == null);
    }

    @Override
    public void execute()
    {
        try {
            while (Core.CRAB == null
                    && !getMyPlayer().isInCombat()
                    && Core.CrabsArea.contains(getMyPlayer().getLocation())
                    ) {
                Logger.addMessage("iMaxCombat: Searching for crabs", true);
                Core.CurrentStatus = "Searching NPC...";


                Core.CRAB = Npcs.getNearest(Constants.CRAB_ID);


                Time.sleep(250, 350);
            }

            if (Core.CRAB[AttackNPC.Teller].isInCombat()) {
                Logger.addMessage("iMaxCombat: NPC already in combat, skipping...", true);
                Core.CurrentStatus = "Skipping NPC, already in combat!";
                Core.CRAB = null;
                AttackNPC.Teller = AttackNPC.Teller + 1;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
