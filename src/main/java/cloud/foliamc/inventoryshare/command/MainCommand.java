package cloud.foliamc.inventoryshare.command;

import cloud.foliamc.inventoryshare.InventoryShare;
import cloud.foliamc.inventoryshare.task.InventoryTask;
import cloud.foliamc.inventoryshare.version.VersionController;
import io.slib.library.util.ColorUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;
        if (!player.hasPermission("is.admin") || args.length == 0) return true;

        switch (args[0]) {
            default:
                player.playSound(player.getLocation(), VersionController.ENTITY_VILLAGER_NO(), 1, 2);
                player.sendMessage(ColorUtil.getColor("&a[인벤공유] &7존재하지 않는 명령어입니다."));
                break;
            case "on":
                if (InventoryShare.isRunning()) {
                    player.playSound(player.getLocation(), VersionController.ENTITY_VILLAGER_NO(), 1, 2);
                    player.sendMessage(ColorUtil.getColor("&a[인벤공유] &7인벤토리 공유가 이미 &a활성화&7되어 있습니다."));
                    return true;
                }

                InventoryShare.getTask().runTask(InventoryShare.getPlugin());
                player.playSound(player.getLocation(), VersionController.BLOCK_NOTE_BLOCK_PLING(), 1, 2);
                player.sendMessage(ColorUtil.getColor("&a[인벤공유] &e인벤토리 공유&f가 &a활성화&f되었습니다!"));
                break;
            case "off":
                if (!InventoryShare.isRunning()) {
                    player.playSound(player.getLocation(), VersionController.ENTITY_VILLAGER_NO(), 1, 2);
                    player.sendMessage(ColorUtil.getColor("&a[인벤공유] &7인벤토리 공유가 이미 &c비활성화&7되어 있습니다."));
                    return true;
                }

                InventoryShare.getTask().stopTask();
                InventoryShare.setTask(new InventoryTask());
                player.playSound(player.getLocation(), VersionController.BLOCK_NOTE_BLOCK_PLING(), 1, 2);
                player.sendMessage(ColorUtil.getColor("&a[인벤공유] &e인벤토리 공유&f가 &c비활성화&f되었습니다!"));
                break;
        }
        return false;
    }

}