package dk.shadow.listeners;

import dk.shadow.Events;
import dk.shadow.utils.Chat;
import dk.shadow.utils.Econ;
import dk.shadow.utils.Format;
import dk.shadow.utils.Wins;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class ChatListener implements Listener {
    private List<String> words = Events.configYML.getStringList("words");
    private List<String> startscrambleevent = new ArrayList<>();
    private static boolean eventStarted = false;
    private static boolean eventWon = false;

    private static String wordtype = null;
    Events plugin;
    Econ econ = new Econ();
    Random random = new Random();
    public ChatListener(Events plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        if (eventStarted && event.getMessage().equalsIgnoreCase(wordtype) && !eventWon) {
            event.setCancelled(true);
            eventWon = true;
            eventStarted = false;

            //Wins
            Wins wins = new Wins();
            Wins.createAccount(event.getPlayer());
            wins.addBalance(event.getPlayer(), 1);
            //Economy
            boolean check = Events.configYML.getBoolean("beløning.random.boolean");
            int min = Events.configYML.getInt("beløning.random.penge.min");
            int max = Events.configYML.getInt("beløning.random.penge.max");
            int defaultt = Events.configYML.getInt("beløning.random.penge.default");



            if (check) {
                int randomNumber = this.random.nextInt((max - min) + 1) + min;
                econ.addMoney(event.getPlayer().getName(), randomNumber);
                List<String> messages = Events.config.getConfig().getStringList("vandtScrambleEvent");
                for (String message : messages) {
                    message = message.replace("%player%", event.getPlayer().getName());
                    message = message.replace("%penge%", Format.formatNum(randomNumber));
                    getServer().broadcastMessage(Chat.colored(message));
                }
            } else {
                List<String> messages = Events.config.getConfig().getStringList("vandtScrambleEvent");
                for (String message : messages) {
                    message = message.replace("%player%", event.getPlayer().getName());
                    message = message.replace("%penge%", Format.formatNum(defaultt));
                    getServer().broadcastMessage(Chat.colored(message));
                }

                econ.addMoney(event.getPlayer().getName(), defaultt);
            }
        }
    }

    public void startEvent() {
        eventStarted = true;
        eventWon = false;
        Collections.shuffle(words);
        wordtype = words.get(0);


        List<String> messages = Events.config.getConfig().getStringList("startScrambleEvent");
        for (String message : messages) {
            message = message.replace("%word%", scrambleWord(wordtype).toLowerCase());
            getServer().broadcastMessage(Chat.colored(message));
        }




    }

    public static String scrambleWord(String obj) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < obj.length(); i++) {
            list.add(obj.charAt(i));
        }
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder(list.size());
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }


}
