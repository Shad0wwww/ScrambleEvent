package dk.shadow;

import dk.shadow.commands.TypeEventCommand;
import dk.shadow.listeners.ChatListener;
import dk.shadow.utils.Chat;
import dk.shadow.utils.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Type;
import java.util.logging.Level;

public class TypeEvent extends JavaPlugin {

    public static TypeEvent instance;
    private static PluginManager pluginManager;
    public static Config config;
    public static FileConfiguration configYML;



    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();
        instance = this;
        getLogger().log(Level.INFO, "Loading... Please report any errors on discord: Shad0w#2143!");

        //WebSockett


        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);


        config = new Config(this, null, "config.yml");

        configYML = config.getConfig();

        getCommand("TypeEvent").setExecutor(new TypeEventCommand());
        //getCommand("buy").setExecutor(new BuyCommand());
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);



    }

    public static TypeEvent getInstance(){
        return instance;
    }



}
