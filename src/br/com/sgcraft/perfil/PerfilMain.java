package br.com.sgcraft.perfil;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PerfilMain implements CommandExecutor, Listener {


	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String comando, String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("Este comando esta desativado no console!");
			return false;
		} else {
			cmd.getName().equalsIgnoreCase("perfil");
			Player player = (Player) sender;
			if (player.hasPermission("sgcraft.perfil")) {
				if (args.length == 0) {
					player.sendMessage("Teste" + player.getName());
				} else if (args.length == 1) {
					final Player targetPlayer = Bukkit.getPlayer(args[0]);
					if (targetPlayer == null) {
						player.sendMessage("§cNo momento só é possível ver o perfil de jogadores online!");
					} else {
						Inventory perfilMain = Bukkit.createInventory((InventoryHolder) null, 54, "§8Perfil");
					        final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
					        final SkullMeta sk = (SkullMeta)skull.getItemMeta();
					        sk.setOwner(targetPlayer.getName());
					        sk.setDisplayName("§b"+targetPlayer.getName());
					        skull.setItemMeta((ItemMeta)sk);
					        perfilMain.setItem(1, skull);
	                    player.openInventory(perfilMain);
					}
				} else if (args.length >= 2) {
					player.sendMessage("§cComando Incorreto. Use /perfil ou /perfil <jogador>");
				}
			} else {
				player.sendMessage("§cComando Incorreto. Use /perfil ou /perfil <jogador>");
			}
		}
		return false;
	}
}
