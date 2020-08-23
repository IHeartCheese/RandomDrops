package plugins.RandomDrops.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import plugins.RandomDrops.RandomDrops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Listeners implements Listener {

    private static HashMap<Material, Material> dropMap = new HashMap<>();
    private static ArrayList<Material> blackList = new ArrayList<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Select random drop methods
        if (RandomDrops.totalRandom) {

            // Choose random block to drop and remember what the old block was
            Material toDrop;
            do {
                toDrop = Material.values()[new Random().nextInt(Material.values().length)];
            } while (toDrop == Material.AIR);
            Material block = event.getBlock().getType();

            // Break block and drop item
            event.getBlock().setType(Material.AIR);
            if (block != Material.GRASS && block != Material.TALL_GRASS) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(toDrop));
            }

        } else if (RandomDrops.blockRandom) {

            // Process block
            Material block = event.getBlock().getType();

            if (dropMap.containsKey(block)) {
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(dropMap.get(block)));
            } else {
                Material toDrop;
                do {
                    toDrop = Material.values()[new Random().nextInt(Material.values().length)];
                } while (toDrop == Material.AIR || dropMap.containsValue(toDrop) || blackList.contains(toDrop));
                dropMap.put(block, toDrop);
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(dropMap.get(block)));
            }

        }

    }

    public static void updateBlackList() {
        // Add materials to blacklist
        for (Material mat : Material.values()) {
            if (mat.isRecord()) {
                blackList.add(mat);
            } else if (mat.name().toLowerCase().contains("uncraftable")) {
                blackList.add(mat);
            } else if (mat.name().toLowerCase().contains("command")) {
                blackList.add(mat);
            } else if (mat.name().toLowerCase().contains("spawn")) {
                blackList.add(mat);
            }
        }
    }

    public static HashMap<Material, Material> getDropMap() {
        return dropMap;
    }

    public static void setDropMap(HashMap<Material, Material> dropMap) {
        Listeners.dropMap = dropMap;
    }

    public static void resetDropMap() {
        dropMap.clear();
    }
}
