package cloud.foliamc.inventoryshare.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryTask extends BukkitRunnable {

    private Inventory inventory;

    public InventoryTask runTask(JavaPlugin plugin) {
        this.inventory = Bukkit.createInventory(null, InventoryType.PLAYER);
        this.runTaskTimer(plugin, 0L, 10L);
        return this;
    }

    public void stopTask() {
        this.cancel();
        this.inventory = null;
    }

    public void run() {
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());

        for(int i = 0; i < playerList.size(); ++i) {
            Player player = playerList.get(i);
            if (player != null && !inventoryEqual(player.getInventory(), this.inventory)) {
                for(int j = 0; j < playerList.size(); ++j) {
                    Player target = playerList.get(j);
                    if (target != null && i != j) {
                        target.getInventory().setContents(player.getInventory().getContents());
                        this.inventory.setContents(player.getInventory().getContents());
                    }
                }
            }
        }
    }

    public static boolean inventoryEqual(Inventory player, Inventory player1) {
        for (int i = 0; i < 41; ++i) {
            if (i == 36) {
                i = 40;
            }

            if (!Objects.equals(player.getItem(i), player1.getItem(i))) {
                return false;
            }
        }
        return true;
    }

}