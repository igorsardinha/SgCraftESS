package br.com.sgcraft;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;


public class GlowEnchant
{
    public static void init() {
        try {
            Class.forName("net.minecraft.server.v1_8_R3.ItemStack");
            Main.suport = true;
        }
        catch (ClassNotFoundException ex) {}
    }
    
    public static ItemStack addGlow(final ItemStack item) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) {
            tag = nmsStack.getTag();
        }
        final NBTTagList ench = new NBTTagList();
        tag.set("ench", (NBTBase)ench);
        nmsStack.setTag(tag);
        return (ItemStack)CraftItemStack.asCraftMirror(nmsStack);
    }
}
