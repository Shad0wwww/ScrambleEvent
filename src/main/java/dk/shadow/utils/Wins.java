package dk.shadow.utils;

import dk.shadow.Events;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Wins {

    private static HashMap<UUID, Integer> winsData = new HashMap<>();


    public static boolean createAccount(OfflinePlayer PlayerName)
    {
        if (!winsData.containsKey(PlayerName.getUniqueId())) {
            System.out.println("Har ikke en account");
            winsData.put(PlayerName.getUniqueId(), 0);
            Events.winsYML.set("Accounts."+ PlayerName.getUniqueId(), 0);
            Events.wins.saveConfig();
            return true;
        }
        System.out.println("Har en account");
        return false;
    }

    public boolean addBalance(OfflinePlayer PlayerName, Integer Amount) {
        if (winsData.containsKey(PlayerName.getUniqueId())) {
            int result = (int) winsData.get(PlayerName.getUniqueId()) + Amount;

            winsData.put(PlayerName.getUniqueId(), result);
            Events.winsYML.set("Accounts."+ PlayerName.getUniqueId(), result);
            Events.wins.saveConfig();
            return true;
        }
        loadBalances(PlayerName);
        return false;
    }
    public static int getBalance(OfflinePlayer PlayerName) {
        if (winsData.containsKey(PlayerName.getUniqueId())) {
            return winsData.get(PlayerName.getUniqueId());
        }
        return 0;
    }


    public Boolean loadBalances(OfflinePlayer player) {
        int balance = Events.wins.getConfig().getInt("Accounts." + player);
        winsData.put(player.getUniqueId(), balance);
        return true;
    }

    public void saveBalances() {
        for (Map.Entry<UUID, Integer> entry : winsData.entrySet()) {
            Events.wins.getConfig().set("Accounts." + entry.getKey().toString(), entry.getValue());
        }
        Events.wins.saveConfig();
    }

}
