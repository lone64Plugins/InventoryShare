package cloud.foliamc.inventoryshare;

import cloud.foliamc.inventoryshare.command.MainCommand;
import cloud.foliamc.inventoryshare.command.tab.MainTab;
import cloud.foliamc.inventoryshare.task.InventoryTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryShare extends JavaPlugin {

    private static InventoryShare plugin;
    private static InventoryTask task;

    @Override
    public void onLoad() {
        plugin = this;
        task = new InventoryTask();
    }

    @Override
    public void onEnable() {
        getCommand("inventoryshare").setExecutor(new MainCommand());
        getCommand("inventoryshare").setTabCompleter(new MainTab());

        if (getTask() != null) {
            getTask().runTask(this);
        }
    }

    @Override
    public void onDisable() {
        if (getTask() != null && isRunning()) {
            getTask().stopTask();
        }
    }

    public static InventoryShare getPlugin() {
        return plugin;
    }

    public static void setTask(InventoryTask task) {
        InventoryShare.task = task;
    }

    public static InventoryTask getTask() {
        return task;
    }

    public static boolean isRunning() {
        int taskId;
        try {
            taskId = getTask().getTaskId();
        } catch (IllegalStateException e) {
            return false;
        }

        return getTask() != null && (Bukkit.getScheduler().isCurrentlyRunning(taskId) || Bukkit.getScheduler().isQueued(taskId));
    }

}