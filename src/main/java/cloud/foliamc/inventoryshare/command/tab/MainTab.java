package cloud.foliamc.inventoryshare.command.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("is.admin")) {
                if (args.length == 1) {
                    return Arrays.asList("on", "off");
                }
            }
        }
        return null;
    }

}