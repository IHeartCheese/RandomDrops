package plugins.RandomDrops.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Listeners implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Choose random block to drop and remember what the old block was
        Material toDrop = Material.values()[new Random().nextInt(Material.values().length)];
        Material block = event.getBlock().getType();

        // Break block and drop item
        event.getBlock().setType(Material.AIR);
        if (block != Material.GRASS && block != Material.TALL_GRASS) {
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(toDrop));
        }
    }

}
