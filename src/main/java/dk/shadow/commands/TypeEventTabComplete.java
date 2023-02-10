package dk.shadow.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.Arrays;
import java.util.List;

public class TypeEventTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equals("scr") || command.getName().equals("scrambleEvent")) {
            List<String> commands = Arrays.asList("start", "reload", "stop", "wins");
            return commands;
        }
        return null;
    }
}
