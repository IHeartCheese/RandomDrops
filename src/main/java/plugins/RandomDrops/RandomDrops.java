package plugins.RandomDrops;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import plugins.RandomDrops.Listeners.Listeners;

public class RandomDrops extends JavaPlugin {
    private static RandomDrops instance;

    public static RandomDrops getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable() Invoked");
    }

    // COMMANDS
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("activate")) {

            Bukkit.broadcastMessage("ACTIVATED");

            return true;
        }
        return false;
    }

}
