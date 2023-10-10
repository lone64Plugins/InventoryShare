package cloud.foliamc.inventoryshare.version;

import org.bukkit.Sound;

public class VersionController {

    public static Sound BLOCK_NOTE_BLOCK_PLING() {
        try {
            return Sound.valueOf("BLOCK_NOTE_BLOCK_PLING");
        } catch (Exception e) {
            return Sound.BLOCK_NOTE_PLING;
        }
    }

    public static Sound ENTITY_VILLAGER_NO() {
        return Sound.ENTITY_VILLAGER_NO;
    }

}