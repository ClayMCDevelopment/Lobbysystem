package eu.claymc.lobbysystem.enums;

import eu.claymc.api.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ItemEnum {

    NAVIGATOR(new ItemBuilder(Material.COMPASS).setDisplayName("§6•§e● Navigator §8▎ §7Rechtsklick").toItemStack(), "§6•§e● Navigation"),
    PLAYER_HIDER(new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("§6•§e● Verstecker §8▎ §7Rechtsklick").toItemStack(), "§6•§e● Verstecker"),
    LOBBY_SWITCHER(new ItemBuilder(Material.HOPPER).setDisplayName("§6•§e● Lobbies §8▎ §7Rechtsklick").toItemStack(), "§2•§a● Lobbies"),
    GRAPPLING_HOOK(new ItemBuilder(Material.FISHING_ROD).setDisplayName("§6•§e● Enterhaken §8▎ §7Rechtsklick").setUnbrak().toItemStack(), null),
    PROFILE_HEAD(new ItemBuilder(Material.BEACON).setDisplayName("§6•§e● Profil §8▎ §7Rechtsklick").toItemStack(), "§6•§e● Profil"),

    REPORT_GUI(new ItemBuilder(Material.PAPER).setDisplayName("§6•§e● Reports §8▎ §7Rechtsklick").toItemStack(), "§6•§e● Reports"),
    ADMIN_GUI(new ItemBuilder(Material.COMMAND).setDisplayName("§6•§e● ACP §8▎ §7Rechtsklick").toItemStack(), "§6•§e● ACP"),
    SILENT_HUB(new ItemBuilder(Material.TNT).setDisplayName("§6•§e● Silent-Lobby §8▎ §7Rechtsklick").toItemStack(), null)

    ;

    ItemEnum(ItemStack itemStack, String inventoryName) {
        this.itemStack = itemStack;
        this.inventoryName = inventoryName;
    }

    private final String inventoryName;
    private final ItemStack itemStack;

    public String getInventoryName() {
        return inventoryName;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
