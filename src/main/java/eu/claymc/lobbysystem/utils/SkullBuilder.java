package eu.claymc.lobbysystem.utils;

import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class SkullBuilder {

    private ItemStack itemStack;
    private SkullMeta skullMeta;

    @SuppressWarnings("deprecation")
    public SkullBuilder(String displayname, String skullOwner, int amount, String lore1, String lore2, String lore3){
        itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setDisplayName(displayname);
        skullMeta.setOwner(skullOwner);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(lore1);
        lore.add(lore2);
        lore.add(lore3);
        skullMeta.setLore(lore);
        itemStack.setItemMeta(skullMeta);
    }

    public ItemStack buildSkull() {
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

}
