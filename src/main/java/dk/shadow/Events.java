package dk.shadow;

import dk.shadow.commands.TypeEventCommand;
import dk.shadow.commands.TypeEventTabComplete;
import dk.shadow.listeners.ChatListener;
import dk.shadow.task.AutoStartScramble;
import dk.shadow.utils.Config;

import dk.shadow.utils.Wins;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class Events extends JavaPlugin {
    public static Economy econ = null;
    public static Events instance;
    private static PluginManager pluginManager;
    public static Config config, wins;
    public static FileConfiguration configYML, winsYML;

    private Wins winss;

    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();
        instance = this;
        getLogger().log(Level.INFO, "Loading... Please report any errors on discord: Shad0w#2143!");

        //CONFIGS -------------------------------
        //Config.yml
        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();

        //Wins.yml
        if (!(new File(getDataFolder(), "wins.yml")).exists())
            saveResource("wins.yml", false);

        wins = new Config(this, null, "wins.yml");
        winsYML = wins.getConfig();

        //Command executers
        getCommand("scrambleEvent").setExecutor(new TypeEventCommand());
        getCommand("scrambleEvent").setTabCompleter(new TypeEventTabComplete());
        //getCommand("buy").setExecutor(new BuyCommand());
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

        if (!setupEconomy() ) {
            Bukkit.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            Bukkit.getLogger().severe(String.format(String.valueOf(getServer().getPluginManager().getPlugin("Vault"))));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        startScrambleEventAuto();
        setupEconomy();

        //Loader din wins
        winss = new Wins();
        for (Player player : Bukkit.getOnlinePlayers()) {
            winss.loadBalances(player);
        }

        winss.saveBalances();

    }
    @Override
    public void onDisable() {
        wins.saveConfig();
        winss.saveBalances();
    }

    public static Events getInstance(){
        return instance;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static void startScrambleEventAuto() {
        AutoStartScramble autoStartScramble = new AutoStartScramble();
        //System.out.println("config.getConfig().getBoolean(autoscramblestart.enabled) " + config.getConfig().getBoolean("autoscramblestart.enabled"));
        if (config.getConfig().getBoolean("autoscramblestart.enabled")) {
            //
            autoStartScramble.runTaskTimer(instance, 0L, config.getConfig().getInt("autoscramblestart.delay") * 20L);
        }
    }

}
