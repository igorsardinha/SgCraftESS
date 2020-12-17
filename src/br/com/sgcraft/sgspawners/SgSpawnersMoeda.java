package br.com.sgcraft.sgspawners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import br.com.sgcraft.GlowEnchant;
import br.com.sgcraft.Main;
import br.com.sgcraft.utils.MySQL;

public class SgSpawnersMoeda implements CommandExecutor, Listener {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(final CommandSender s, final Command cmd, final String lbl, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("givemoeda")) {
			if (!(s instanceof Player)) {
				if (args.length != 2) {
					s.sendMessage("§cComando incorreto, use /givemoeda [player] [quantia]");
					return false;
				}
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null || args[1] == null) {
					s.sendMessage("§cJogador não encontrado, ou quantidade não informada.");
				} else {
					int quantidade;
					try {
						quantidade = Integer.valueOf(args[1]);
					} catch (NumberFormatException e) {
						s.sendMessage("§cQuantidade invalida!");
						return false;
					}
					final PlayerInventory inv = p.getInventory();
					inv.addItem(getMoeda(quantidade));
					s.sendMessage("§aMoeda adicionada ao inventario de " + p.getName());
				}
			} else {
				if (args.length != 2) {
					s.sendMessage("§cComando incorreto, use /givemoeda [player] [quantia]");
				}
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null || args[1] == null) {
					s.sendMessage("§cJogador não encontrado, ou quantidade não informada.");
				} else {
					int quantidade;
					try {
						quantidade = Integer.valueOf(args[1]);
					} catch (NumberFormatException e) {
						s.sendMessage("§cQuantidade invalida!");
						return false;
					}
					final PlayerInventory inv = p.getInventory();
					inv.addItem(getMoeda(quantidade));
					s.sendMessage("§aMoeda adicionada ao inventario de " + p.getName());
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("moeda")) {
			if ((s instanceof Player)) {
					Player p = ((Player) s).getPlayer();
					this.topMoedas(p);
					return true;
			}
		}
		return true;
	}
	
	public void topMoedas(final Player p) {
		try {
			p.sendMessage("§6§lTOP MOEDAS §f- §7(Atualiza Manualmente)");
			Class.forName("org.sqlite.JDBC");
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("SELECT * FROM SgSpawners_Clans ORDER BY Moedas DESC LIMIT 10");
			final ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				if (++i == 0) {
					p.sendMessage("§cNinguém tem moedas ainda...");
				} else {
					p.sendMessage("§b>> §f" + i + "º " + rs.getString("ClanTag").toUpperCase() + " - §e" + rs.getInt("Moedas") + " moedas.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public static ItemStack getMoeda(final int amount) {
		final ItemStack item = Main.pl.moeda.clone();
		item.setAmount(amount);
		if (Main.pl.suport) {
			return GlowEnchant.addGlow(item);
		}
		return item;
	}

	@SuppressWarnings("static-access")
	public static void loadMoeda() {
		final List<String> lore = new ArrayList<String>();
		lore.add("§7Usado para Desbloquear Spawners.");
		String nome = "§eMoeda do Clan";
		final ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1);
		final ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore((List<String>) lore);
		item.setItemMeta(meta);
		if (Main.pl.suport) {
			Main.pl.moeda = GlowEnchant.addGlow(item);
		} else {
			Main.pl.moeda = item;
		}
	}
}
