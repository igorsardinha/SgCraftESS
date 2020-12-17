package br.com.sgcraft.comandos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import br.com.sgcraft.Main;
import br.com.sgcraft.VaultAPI;

public class ComandoX9 implements CommandExecutor, Listener {

	private String grupoPlayer;

	@SuppressWarnings("deprecation")
	public void abrirInventario(Player target, Player player) {
		Main.open.put(player, target);
		final Inventory inv = Bukkit.createInventory((InventoryHolder) null, 54, "§8Inventário de " + target.getName());
		inv.setItem(0, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(2, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(3, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(4, target.getInventory().getHelmet());
		inv.setItem(5, target.getInventory().getChestplate());
		inv.setItem(6, target.getInventory().getLeggings());
		inv.setItem(7, target.getInventory().getBoots());
		inv.setItem(8, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta sk = (SkullMeta) skull.getItemMeta();
		sk.setOwner(target.getName());
		sk.setDisplayName("§b" + target.getName());
		double targetMoney = VaultAPI.getEconomy().getBalance(target.getName());
		DecimalFormat df = new DecimalFormat("###,###,###,###,###.##");
		String grupo = VaultAPI.getChat().getPrimaryGroup(target.getPlayer());
		if (grupo.equalsIgnoreCase("default")) {
			grupoPlayer = "Membro";
		} else {
			grupoPlayer = grupo;
		}
		ArrayList<String> sklore = new ArrayList<>();
		sklore.add("");
		sklore.add("§7Grupo: §6" + grupoPlayer.toUpperCase());
		sklore.add("§7Money: §6 R$" + df.format(targetMoney));
		sklore.add("");
		sklore.add("§aClique para ver o");
		sklore.add("§aperfil deste jogador!");
		sk.setLore(sklore);
		skull.setItemMeta((ItemMeta) sk);
		inv.setItem(1, skull);
		inv.setItem(9, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(10, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(11, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(12, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(13, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(14, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(15, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(16, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));
		inv.setItem(17, GiveItem(Material.STAINED_GLASS_PANE, "§0--", 1, 15));

		new BukkitRunnable() {
			int inv_timeout = 40;

			@Override
			public void run() {
				inv_timeout--;
				int a = 18;
				for (int i = 0; i < target.getInventory().getContents().length; ++i) {
					inv.setItem(a, target.getInventory().getItem(i));
					inv.setItem(4, target.getInventory().getHelmet());
					inv.setItem(5, target.getInventory().getChestplate());
					inv.setItem(6, target.getInventory().getLeggings());
					inv.setItem(7, target.getInventory().getBoots());
					++a;
					player.updateInventory();
				}
				if (inv_timeout == 0) {
					cancel();
					player.closeInventory();
					player.sendMessage(Main.prefixX9 + " §cInventário fechado pois atingiu o limite de tempo!");
				}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 2, 3);
		player.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (sender instanceof org.bukkit.command.ConsoleCommandSender) {

			sender.sendMessage("§cDesculpe, somente jogadores podem executar este comando!");

			return true;
		}
		if (cmd.getName().equalsIgnoreCase("x9")) {
			final Player p = (Player) sender;
			if (p.hasPermission("sgcraft.invsee")) {
				if (args.length == 0) {
					p.sendMessage(String.valueOf(Main.prefixX9) + " §bUtilize: §c/x9 <nick>");
				} else if (args.length == 1) {
					final Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						p.sendMessage(String.valueOf(Main.prefixX9) + Main.playerNotOnline);
						return true;
					}
					if (target == p) {
						p.sendMessage(String.valueOf(Main.prefixX9) + " §cVocê não pode abrir seu inventario!");
					} else {
						p.sendMessage(String.valueOf(Main.prefixX9) + Main.use);
						abrirInventario(target, p);
					}
				} else {
					p.sendMessage(String.valueOf(Main.prefixX9) + Main.no_perm);
				}
			}
		}
		return false;
	}

	public static ItemStack GiveItem(final Material mat, final String Name, final int amo, final int subid) {
		final ItemStack is = new ItemStack(mat, amo, (short) subid);
		final ItemMeta im = is.getItemMeta();
		im.setDisplayName(Name);
		is.setItemMeta(im);
		return is;
	}

	@EventHandler
	public void onInventoryClickEvent(final InventoryClickEvent e) {
		if (e.getInventory().getTitle().contains("Inventário de")) {
			e.setCancelled(true);
		}
	}

}