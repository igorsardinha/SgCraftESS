package br.com.sgcraft.vender;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import br.com.sgcraft.VaultAPI;

public class VenderDrops implements Listener {

	private double precoCarnePodre;
	private double precoOsso;
	private double precoEnderEye;
	private double precoTNT;
	private double precoSlime;
	private double precoPepita;

	@EventHandler
	public void OnSubControls(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			if (e.getWhoClicked() instanceof Player) {
				Player player = (Player) e.getWhoClicked();
				if (e.getInventory().getTitle().equals("§8Vendas - Drops de Mobs")) {
					e.setCancelled(true);
					if (e.getCurrentItem() == null)
						return;
					if (e.getCurrentItem().getType() == Material.AIR)
						return;
					if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
						return;
					ItemStack itemOpt = e.getCurrentItem();
					switch (itemOpt.getItemMeta().getDisplayName()) {
					case "§cVender Carne Podre":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						PlayerInventory invCarnePodre = player.getInventory();
						if (player.hasPermission("sgcraft.vender.mega")) {
							precoCarnePodre = 3.472222222;
						} else if (player.hasPermission("sgcraft.vender.epic")) {
							precoCarnePodre = 3.472222222;
						} else if (player.hasPermission("sgcraft.vender.ultra")) {
							precoCarnePodre = 3.472222222;
						} else if (player.hasPermission("sgcraft.vender.mvp")) {
							precoCarnePodre = 4.123263889;
						}
						int contagemCarnePodre = 0;
						for (ItemStack item : invCarnePodre.getContents()) {
							if (item == null)
								continue;
							if (item.getType() == Material.ROTTEN_FLESH) {
								contagemCarnePodre = contagemCarnePodre + item.getAmount();
							}
						}
						if (contagemCarnePodre == 0) {
							player.sendMessage("§cVocê não tem nenhuma Carne Podre para vender!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						} else {
							invCarnePodre.remove(Material.ROTTEN_FLESH);
							double precoFinal = contagemCarnePodre * precoCarnePodre;
							VaultAPI.getEconomy().depositPlayer(player, precoFinal);
							BigDecimal precoRound = new BigDecimal(precoFinal).setScale(2, RoundingMode.HALF_EVEN);
							player.sendMessage("§fVoce vendeu " + contagemCarnePodre + " §aCarne Podre §fpor §aR$ "
									+ precoRound.doubleValue());
							player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
						}
						break;
					case "§fVender Osso":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						PlayerInventory invOsso = player.getInventory();
						if (player.hasPermission("sgcraft.vender.mega")) {
							precoOsso = 3.6892361111;
						} else if (player.hasPermission("sgcraft.vender.epic")) {
							precoOsso = 3.6892361111;
						} else if (player.hasPermission("sgcraft.vender.ultra")) {
							precoOsso = 3.6892361111;
						} else if (player.hasPermission("sgcraft.vender.mvp")) {
							precoOsso = 4.4270833333;
						}
						int contagemOsso = 0;
						for (ItemStack item : invOsso.getContents()) {
							if (item == null)
								continue;
							if (item.getType() == Material.BONE) {
								contagemOsso = contagemOsso + item.getAmount();
							}
						}
						if (contagemOsso == 0) {
							player.sendMessage("§cVocê não tem nenhum Osso para vender!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						} else {
							invOsso.remove(Material.BONE);
							double precoFinal = contagemOsso * precoOsso;
							VaultAPI.getEconomy().depositPlayer(player, precoFinal);
							BigDecimal precoRound = new BigDecimal(precoFinal).setScale(2, RoundingMode.HALF_EVEN);
							player.sendMessage(
									"§fVoce vendeu " + contagemOsso + " §aOsso §fpor §aR$ " + precoRound.doubleValue());
							player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
						}
						break;
					case "§fVender EnderEye":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						PlayerInventory invEnderEye = player.getInventory();
						if (player.hasPermission("sgcraft.vender.mega")) {
							precoEnderEye = 5.2083333333;
						} else if (player.hasPermission("sgcraft.vender.epic")) {
							precoEnderEye = 5.2083333333;
						} else if (player.hasPermission("sgcraft.vender.ultra")) {
							precoEnderEye = 5.2083333333;
						} else if (player.hasPermission("sgcraft.vender.mvp")) {
							precoEnderEye = 6.2934027778;
						}
						int contagemEnderEye = 0;
						for (ItemStack item : invEnderEye.getContents()) {
							if (item == null)
								continue;
							if (item.getType() == Material.EYE_OF_ENDER) {
								contagemEnderEye = contagemEnderEye + item.getAmount();
							}
						}
						if (contagemEnderEye == 0) {
							player.sendMessage("§cVocê não tem nenhum EnderEye para vender!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						} else {
							invEnderEye.remove(Material.EYE_OF_ENDER);
							double precoFinal = contagemEnderEye * precoEnderEye;
							VaultAPI.getEconomy().depositPlayer(player, precoFinal);
							BigDecimal precoRound = new BigDecimal(precoFinal).setScale(2, RoundingMode.HALF_EVEN);
							player.sendMessage("§fVocê vendeu " + contagemEnderEye + " §aEnderEye §fpor §aR$ "
									+ precoRound.doubleValue());
							player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
						}
						break;
					case "§4Vender TNT":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						PlayerInventory invTNT = player.getInventory();
						if (player.hasPermission("sgcraft.vender.mega")) {
							precoTNT = 6.510416667;
						} else if (player.hasPermission("sgcraft.vender.epic")) {
							precoTNT = 6.510416667;
						} else if (player.hasPermission("sgcraft.vender.ultra")) {
							precoTNT = 6.510416667;
						} else if (player.hasPermission("sgcraft.vender.mvp")) {
							precoTNT = 7.8125;
						}
						int contagemTNT = 0;
						for (ItemStack item : invTNT.getContents()) {
							if (item == null)
								continue;
							if (item.getType() == Material.TNT) {
								contagemTNT = contagemTNT + item.getAmount();
							}
						}
						if (contagemTNT == 0) {
							player.sendMessage("§cVocê não tem nenhuma TNT para vender!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						} else {
							invTNT.remove(Material.TNT);
							double precoFinal = contagemTNT * precoTNT;
							VaultAPI.getEconomy().depositPlayer(player, precoFinal);
							BigDecimal precoRound = new BigDecimal(precoFinal).setScale(2, RoundingMode.HALF_EVEN);
							player.sendMessage("§fVocê vendeu " + contagemTNT + " §aTNT §fpor §aR$ "
									+ precoRound.doubleValue());
							player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
						}
						break;
					case "§aVender Slime":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						PlayerInventory invSlime = player.getInventory();
						if (player.hasPermission("sgcraft.vender.mega")) {
							precoSlime = 8.6805555556;
						} else if (player.hasPermission("sgcraft.vender.epic")) {
							precoSlime = 8.6805555556;
						} else if (player.hasPermission("sgcraft.vender.ultra")) {
							precoSlime = 8.6805555556;
						} else if (player.hasPermission("sgcraft.vender.mvp")) {
							precoSlime = 10.4166666667;
						}
						int contagemSlime = 0;
						for (ItemStack item : invSlime.getContents()) {
							if (item == null)
								continue;
							if (item.getType() == Material.SLIME_BALL) {
								contagemSlime = contagemSlime + item.getAmount();
							}
						}
						if (contagemSlime == 0) {
							player.sendMessage("§cVocê não tem nenhuma Gosma de Slime para vender!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						} else {
							invSlime.remove(Material.SLIME_BALL);
							double precoFinal = contagemSlime * precoSlime;
							VaultAPI.getEconomy().depositPlayer(player, precoFinal);
							BigDecimal precoRound = new BigDecimal(precoFinal).setScale(2, RoundingMode.HALF_EVEN);
							player.sendMessage("§fVoce vendeu " + contagemSlime + " §aGosma de Slime §fpor §aR$ "
									+ precoRound.doubleValue());
							player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
						}
						break;
					case "§6Vender Pepita de Ouro":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						PlayerInventory invPepita = player.getInventory();
						if (player.hasPermission("sgcraft.vender.mega")) {
							precoPepita = 10.8506944444;
						} else if (player.hasPermission("sgcraft.vender.epic")) {
							precoPepita = 10.8506944444;
						} else if (player.hasPermission("sgcraft.vender.ultra")) {
							precoPepita = 10.8506944444;
						} else if (player.hasPermission("sgcraft.vender.mvp")) {
							precoPepita = 13.0208333333;
						}
						int contagemPepita = 0;
						for (ItemStack item : invPepita.getContents()) {
							if (item == null)
								continue;
							if (item.getType() == Material.GOLD_NUGGET) {
								contagemPepita = contagemPepita + item.getAmount();
							}
						}
						if (contagemPepita == 0) {
							player.sendMessage("§cVocê não tem nenhum Pepita de Ouro para vender!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						} else {
							invPepita.remove(Material.GOLD_NUGGET);
							double precoFinal = contagemPepita * precoPepita;
							VaultAPI.getEconomy().depositPlayer(player, precoFinal);
							BigDecimal precoRound = new BigDecimal(precoFinal).setScale(2, RoundingMode.HALF_EVEN);
							player.sendMessage("§fVoce vendeu " + contagemPepita + " §aPepita de Ouro §fpor §aR$ "
									+ precoRound.doubleValue());
							player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
						}
						break;
					case "§4ITEM DESATIVADO":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
							player.sendMessage("§cEsta Venda não está disponível!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
					case "§3Voltar para Menu Principal":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						VenderMain.openVender(player);
						break;
					case "§cFechar Menu":
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						player.closeInventory();
						break;
					default:
						return;
					}
				}
			}
		} else {
			return;
		}
	}
}
