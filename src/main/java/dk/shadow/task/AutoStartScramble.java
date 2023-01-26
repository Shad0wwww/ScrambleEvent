package dk.shadow.task;

import dk.shadow.Events;
import dk.shadow.listeners.ChatListener;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoStartScramble extends BukkitRunnable {

    private boolean stop = false;
    Events plugin;
    @Override
    public void run() {
        //stop = Events.config.getConfig().getBoolean("autoscramblestart.enabled");
        if (stop) {
            cancel();
            return;
        }
        ChatListener chatListener = new ChatListener(plugin);
        chatListener.startEvent();
    }
}
