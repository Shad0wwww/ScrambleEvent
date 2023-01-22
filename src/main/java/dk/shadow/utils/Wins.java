package dk.shadow.utils;

import dk.shadow.Events;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;

public class Wins {

    protected static HashMap<UUID, Number> winsData = new HashMap<>();


    public static boolean createAccount(OfflinePlayer PlayerName)
    {
        if (!winsData.containsKey(PlayerName.getUniqueId())) {
            winsData.put(PlayerName.getUniqueId(), 0);
            Events.winsYML.set("Accounts."+ PlayerName.getUniqueId(), 0);
            Events.wins.saveConfig();
            return true;
        }
        return false;
    }

    public static boolean addBalance(OfflinePlayer PlayerName, Integer Amount) {
        if (winsData.containsKey(PlayerName.getUniqueId())) {
            int result = (int) winsData.get(PlayerName.getUniqueId()) + Amount;

            winsData.put(PlayerName.getUniqueId(), result);
            Events.winsYML.set("Accounts."+ PlayerName.getUniqueId(), result);
            Events.wins.saveConfig();
            return true;
        }
        return false;
    }
    public static int getBalance(OfflinePlayer PlayerName) {
        if (winsData.containsKey(PlayerName.getUniqueId())) {
            return (int) winsData.get(PlayerName.getUniqueId());
        }
        return 0;
    }

}
