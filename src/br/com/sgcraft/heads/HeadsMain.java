package br.com.sgcraft.heads;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public final class HeadsMain {

	private HeadsMain() {

	}

	public static ItemStack getCustomSkull(MobsHeads type) {
		final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		final SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(type.getSkinName());
		meta.setDisplayName(ChatColor.RESET + type.getDisplayName());
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getSkinnedHead(String playerName) {
		final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		final SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(playerName);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean isSkullCustom(String input) {
		for (MobsHeads t : MobsHeads.values()) {
			if (input.equalsIgnoreCase(t.getSkinName()))
				return true;
		}
		return false;
	}

}
