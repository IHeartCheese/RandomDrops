package plugins.RandomDrops;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import plugins.RandomDrops.Listeners.Listeners;

import java.util.HashMap;

public class RandomDrops extends JavaPlugin {
    private static RandomDrops instance;
    public static Boolean totalRandom = false;
    public static Boolean blockRandom = false;

    public static RandomDrops getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable() Invoked");
    }

    // COMMANDS
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("activate") && !blockRandom && !totalRandom) {
            String randType = args[0];

            // Activate corresponding random drop method
            if (randType.equalsIgnoreCase("block")) {
                blockRandom = true;
                totalRandom = false;
                Listeners.resetDropMap();
            } else if (randType.equalsIgnoreCase("total")) {
                totalRandom = true;
                blockRandom = false;
            }
            getServer().getPluginManager().registerEvents(new Listeners(), this);
            Listeners.updateBlackList();

            return true;
        } else if (cmd.getName().equalsIgnoreCase("activate") && (!blockRandom || !totalRandom)) {

            sender.sendMessage("Must use /deactivate to disable currently running method.");

        } else if (cmd.getName().equalsIgnoreCase("deactivate")) {
            // Deactivate both random drop methods
            totalRandom = false;
            blockRandom = false;
            HandlerList.unregisterAll(new Listeners());
            return true;
        }
        return false;
    }

}
