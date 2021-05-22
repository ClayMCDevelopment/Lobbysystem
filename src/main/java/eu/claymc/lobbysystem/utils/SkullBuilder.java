package eu.claymc.lobbysystem.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder {

    private ItemStack itemStack;
    private SkullMeta skullMeta;

    @SuppressWarnings("deprecation")
    public SkullBuilder(String displayname, String skullOwner, int amount) {
        itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setDisplayName(displayname);
        skullMeta.setOwner(skullOwner);
    }

    public ItemStack buildSkull() {
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

}
