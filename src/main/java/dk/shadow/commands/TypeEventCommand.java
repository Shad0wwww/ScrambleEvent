package dk.shadow.commands;

import dk.shadow.Events;
import dk.shadow.listeners.ChatListener;
import dk.shadow.utils.Chat;
import dk.shadow.utils.Wins;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TypeEventCommand implements CommandExecutor {

    Events plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("events.scramble")){
            sender.sendMessage(Chat.colored(Events.configYML.getString("permissionDenied")));
            return true;
        }

        String _command = (label == null) ? String.valueOf(command) : label;
        if(args.length == 0) {
            sendDefaultCommand(sender, _command);


        //START SCRABLEEVENT
        } else if (args[0].equalsIgnoreCase("start")) {
            ChatListener chatListener = new ChatListener(plugin);

            chatListener.startEvent();
            return true;
        } else if (args[0].equalsIgnoreCase("wins")) {
            OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);
            sender.sendMessage(Chat.colored("&a" + offlinePlayer.getName() + " &fhar " + "&c" + Wins.getBalance(offlinePlayer) + " &aWins"));
            Wins.getBalance(offlinePlayer);
            return true;



        //RELOAD
        } else if (args[0].equalsIgnoreCase("reload")) {
            boolean reloadSucces;
            try {
                Events.config.reloadConfig();
                Events.configYML = Events.config.getConfig();
                Events.startScrambleEventAuto();
                reloadSucces = true;
            } catch(Exception e){
                e.printStackTrace();
                reloadSucces = false;
            }
            if(reloadSucces) {
                sender.sendMessage(Chat.colored("&aReload successfully completed"));
            } else {
                sender.sendMessage(Chat.colored("&cAn error occurred. Please check the console."));
            }
            return true;
        } else {
            return false;
        }


        return false;
    }


    private void sendDefaultCommand(CommandSender sender, String command){
        String sb = "";
        sb = sb + "\n";
        sb = sb + "&7/" + command + " reload &8- " + "&fReloading &aconfig.yml" + "\n ";
        sb = sb + "&7/" + command + " wins <player> &8- " + "&fget selected palyers wins" + "\n ";
        sender.sendMessage(Chat.colored(sb));
    }
}
