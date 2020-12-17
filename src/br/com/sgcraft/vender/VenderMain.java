package br.com.sgcraft.vender;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import br.com.sgcraft.VaultAPI;

public class VenderMain implements CommandExecutor, Listener {

	public static void openVender(Player player) {
		Inventory vender = Bukkit.createInventory((InventoryHolder) null, 27, "§8Vendas - Menu Principal");
		final ItemStack inkSack = new ItemStack(Material.INK_SACK, 1, (byte) 4);
		final ItemStack item_lapis = inkSack;
		final ItemMeta lapis = (ItemMeta) item_lapis.getItemMeta();
		lapis.setDisplayName("§9Vender Lápis");
		ArrayList<String> lapis_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			lapis_lore.add("§eInventário por R$3000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			lapis_lore.add("§eInventário por R$3800.");
		}
		lapis.setLore(lapis_lore);
		item_lapis.setItemMeta((ItemMeta) lapis);
		vender.setItem(11, item_lapis);

		final ItemStack item_farms = new ItemStack(Material.WHEAT, 1);
		final ItemMeta farms = (ItemMeta) item_farms.getItemMeta();
		farms.setDisplayName("§6Menu de Farms");
		ArrayList<String> farms_lore = new ArrayList<>();
		farms_lore.add("§eVender Itens de Farm");
		farms.setLore(farms_lore);
		item_farms.setItemMeta((ItemMeta) farms);
		vender.setItem(13, item_farms);

		final ItemStack item_drops = new ItemStack(Material.BONE, 1);
		final ItemMeta drops = (ItemMeta) item_drops.getItemMeta();
		drops.setDisplayName("§7Menu de Drops");
		ArrayList<String> drops_lore = new ArrayList<>();
		drops_lore.add("§eVender Drops de Mobs");
		drops.setLore(drops_lore);
		item_drops.setItemMeta((ItemMeta) drops);
		vender.setItem(15, item_drops);
		player.openInventory(vender);
	}

	private double precoLapis;

	public void openFarms(Player player) {
		Inventory menu_farms = Bukkit.createInventory((InventoryHolder) null, 45, "§8Vendas - Itens de Farm");
		// fungo
		final ItemStack item_fungo = new ItemStack(Material.NETHER_STALK, 1);
		final ItemMeta fungo = (ItemMeta) item_fungo.getItemMeta();
		fungo.setDisplayName("§cVender Fungo");
		ArrayList<String> fungo_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			fungo_lore.add("§eInventário por R$3800.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			fungo_lore.add("§eInventário por R$4200.");
		}
		fungo.setLore(fungo_lore);
		item_fungo.setItemMeta((ItemMeta) fungo);
		menu_farms.setItem(10, item_fungo);

		// Cana_de_açucar
		final ItemStack item_cana = new ItemStack(Material.SUGAR_CANE, 1);
		final ItemMeta cana = (ItemMeta) item_cana.getItemMeta();
		cana.setDisplayName("§aVender Cana-de-Açucar");
		ArrayList<String> cana_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			cana_lore.add("§eInventário por R$5000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			cana_lore.add("§eInventário por R$5800.");
		}
		cana.setLore(cana_lore);
		item_cana.setItemMeta((ItemMeta) cana);
		menu_farms.setItem(11, item_cana);

		// cenoura
		final ItemStack item_cenoura = new ItemStack(Material.CARROT_ITEM, 1);
		final ItemMeta cenoura = (ItemMeta) item_cenoura.getItemMeta();
		cenoura.setDisplayName("§6Vender Cenoura");
		ArrayList<String> cenoura_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			cenoura_lore.add("§eInventário por R$11000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			cenoura_lore.add("§eInventário por R$11800.");
		}
		cenoura.setLore(cenoura_lore);
		item_cenoura.setItemMeta((ItemMeta) cenoura);
		menu_farms.setItem(12, item_cenoura);

		// batata
		final ItemStack item_batata = new ItemStack(Material.POTATO_ITEM, 1);
		final ItemMeta batata = (ItemMeta) item_batata.getItemMeta();
		batata.setDisplayName("§eVender Batata");
		ArrayList<String> batata_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			batata_lore.add("§eInventário por R$11000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			batata_lore.add("§eInventário por R$11800.");
		}
		batata.setLore(batata_lore);
		item_batata.setItemMeta((ItemMeta) batata);
		menu_farms.setItem(13, item_batata);

		// abobora
		final ItemStack item_abobora = new ItemStack(Material.PUMPKIN, 1);
		final ItemMeta abobora = (ItemMeta) item_abobora.getItemMeta();
		abobora.setDisplayName("§6Vender Abobora");
		ArrayList<String> abobora_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			abobora_lore.add("§eInventário por R$18000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			abobora_lore.add("§eInventário por R$19200.");
		}
		abobora.setLore(abobora_lore);
		item_abobora.setItemMeta((ItemMeta) abobora);
		menu_farms.setItem(14, item_abobora);

		// melancia
		final ItemStack item_melancia = new ItemStack(Material.MELON_BLOCK, 1);
		final ItemMeta melancia = (ItemMeta) item_melancia.getItemMeta();
		melancia.setDisplayName("§cVender Melancia");
		ArrayList<String> melancia_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			melancia_lore.add("§eInventário por R$18000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			melancia_lore.add("§eInventário por R$19200.");
		}
		melancia.setLore(melancia_lore);
		item_melancia.setItemMeta((ItemMeta) melancia);
		menu_farms.setItem(15, item_melancia);

		// cacto
		final ItemStack item_cacto = new ItemStack(Material.CACTUS, 1);
		final ItemMeta cacto = (ItemMeta) item_cacto.getItemMeta();
		cacto.setDisplayName("§2Vender cacto");
		ArrayList<String> cacto_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			cacto_lore.add("§eInventário por R$4400.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			cacto_lore.add("§eInventário por R$4800.");
		}
		cacto.setLore(cacto_lore);
		item_cacto.setItemMeta((ItemMeta) cacto);
		menu_farms.setItem(16, item_cacto);

		// voltar
		final ItemStack item_voltar = new ItemStack(Material.ARROW, 1);
		final ItemMeta voltar = (ItemMeta) item_voltar.getItemMeta();
		voltar.setDisplayName("§3Voltar para Menu Principal");
		item_voltar.setItemMeta((ItemMeta) voltar);
		menu_farms.setItem(29, item_voltar);

		// fechar
		final ItemStack item_fechar = new ItemStack(Material.BARRIER, 1);
		final ItemMeta fechar = (ItemMeta) item_fechar.getItemMeta();
		fechar.setDisplayName("§cFechar Menu");
		item_fechar.setItemMeta((ItemMeta) fechar);
		menu_farms.setItem(33, item_fechar);
		player.openInventory(menu_farms);
	}

	public void openDrops(Player player) {
		Inventory menu_drops = Bukkit.createInventory((InventoryHolder) null, 45, "§8Vendas - Drops de Mobs");
		// carne_podre
		final ItemStack item_carnePodre = new ItemStack(Material.ROTTEN_FLESH, 1);
		final ItemMeta carnePodre = (ItemMeta) item_carnePodre.getItemMeta();
		carnePodre.setDisplayName("§cVender Carne Podre");
		ArrayList<String> carnePodre_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			carnePodre_lore.add("§eInventário por R$8000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			carnePodre_lore.add("§eInventário por R$9500.");
		}
		carnePodre.setLore(carnePodre_lore);
		item_carnePodre.setItemMeta((ItemMeta) carnePodre);
		menu_drops.setItem(10, item_carnePodre);

		// osso
		final ItemStack item_osso = new ItemStack(Material.BONE, 1);
		final ItemMeta osso = (ItemMeta) item_osso.getItemMeta();
		osso.setDisplayName("§fVender Osso");
		ArrayList<String> osso_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			osso_lore.add("§eInventário por R$8500.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			osso_lore.add("§eInventário por R$10200.");
		}
		osso.setLore(osso_lore);
		item_osso.setItemMeta((ItemMeta) osso);
		menu_drops.setItem(11, item_osso);

		//EnderEye
		final ItemStack item_enderdeye = new ItemStack(Material.EYE_OF_ENDER, 1);
		final ItemMeta endereye = (ItemMeta) item_enderdeye.getItemMeta();
		endereye.setDisplayName("§fVender EnderEye");
		ArrayList<String> ender_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			ender_lore.add("§eInventário por R$12000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			ender_lore.add("§eInventário por R$14500.");
		}
		endereye.setLore(ender_lore);
		item_enderdeye.setItemMeta((ItemMeta) endereye);
		menu_drops.setItem(12, item_enderdeye);

		//TNT
		final ItemStack item_tnt = new ItemStack(Material.TNT, 1);
		final ItemMeta tnt = (ItemMeta) item_tnt.getItemMeta();
		tnt.setDisplayName("§4Vender TNT");
		ArrayList<String> tnt_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			tnt_lore.add("§eInventário por R$15000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			tnt_lore.add("§eInventário por R$18000.");
		}
		tnt.setLore(tnt_lore);
		item_tnt.setItemMeta((ItemMeta) tnt);
		menu_drops.setItem(13, item_tnt);
		
		// slime
		final ItemStack item_slime = new ItemStack(Material.SLIME_BALL, 1);
		final ItemMeta slime = (ItemMeta) item_slime.getItemMeta();
		slime.setDisplayName("§aVender Slime");
		ArrayList<String> slime_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			slime_lore.add("§eInventário por R$20000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			slime_lore.add("§eInventário por R$24000.");
		}
		slime.setLore(slime_lore);
		item_slime.setItemMeta((ItemMeta) slime);
		menu_drops.setItem(14, item_slime);

		// pepita
		final ItemStack item_pepita = new ItemStack(Material.GOLD_NUGGET, 1);
		final ItemMeta pepita = (ItemMeta) item_pepita.getItemMeta();
		pepita.setDisplayName("§6Vender Pepita de Ouro");
		ArrayList<String> pepita_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			pepita_lore.add("§eInventário por R$25000.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			pepita_lore.add("§eInventário por R$30000.");
		}
		pepita.setLore(pepita_lore);
		item_pepita.setItemMeta((ItemMeta) pepita);
		menu_drops.setItem(15, item_pepita);
		
		// prismarinho
		final ItemStack item_prismarinho = new ItemStack(Material.BARRIER, 1);
		final ItemMeta prismarinho = (ItemMeta) item_prismarinho.getItemMeta();
		prismarinho.setDisplayName("§4ITEM DESATIVADO");
		ArrayList<String> prismarinho_lore = new ArrayList<>();
		if (player.hasPermission("sgcraft.vender.mega") || player.hasPermission("sgcraft.vender.epic")
				|| player.hasPermission("sgcraft.vender.ultra")) {
			prismarinho_lore.add("§eInventário por XXXXX.");
		} else if (player.hasPermission("sgcraft.vender.mvp")) {
			prismarinho_lore.add("§eInventário por XXXXX.");
		}
		prismarinho.setLore(prismarinho_lore);
		item_prismarinho.setItemMeta((ItemMeta) prismarinho);
		menu_drops.setItem(16, item_prismarinho);

		// voltar
		final ItemStack item_voltar = new ItemStack(Material.ARROW, 1);
		final ItemMeta voltar = (ItemMeta) item_voltar.getItemMeta();
		voltar.setDisplayName("§3Voltar para Menu Principal");
		item_voltar.setItemMeta((ItemMeta) voltar);
		menu_drops.setItem(29, item_voltar);

		// fechar
		final ItemStack item_fechar = new ItemStack(Material.BARRIER, 1);
		final ItemMeta fechar = (ItemMeta) item_fechar.getItemMeta();
		fechar.setDisplayName("§cFechar Menu");
		item_fechar.setItemMeta((ItemMeta) fechar);
		menu_drops.setItem(33, item_fechar);
		player.openInventory(menu_drops);
	}

	// comando vender
	public boolean onCommand(CommandSender sender, Command cmd, String comando, String[] args) {
		if (!(sender instanceof Player)) {

			Bukkit.getConsoleSender().sendMessage("» Este comando esta desativado no console!");
			return false;
		}
		comando.equalsIgnoreCase("vender");
		Player p = (Player) sender;
		if (p.hasPermission("sgcraft.vender")) {
			VenderMain.openVender(p);
		} else {
			p.sendMessage("§bApenas jogadores §6§lVIP §bpodem utilizar o /vender!");
		}
		return false;
	}

	@SuppressWarnings("unused")
	@EventHandler
	public void OnVenderLapis(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			if (e.getWhoClicked() instanceof Player) {
				Player player = (Player) e.getWhoClicked();
				if (e.getInventory().getTitle().equals("§8Vendas - Menu Principal")) {
					e.setCancelled(true);
					if (e.getCurrentItem() == null) return;
					if (e.getCurrentItem().getType() == Material.AIR) return;
					if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
					ItemStack itemOpt = e.getCurrentItem();
					if (itemOpt.getItemMeta().getDisplayName() == "§9Vender Lápis") {
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						if (player.hasPermission("sgcraft.vender.mega")) {
							precoLapis = 1.3392857143;
						} else if (player.hasPermission("sgcraft.vender.epic")) {
							precoLapis = 1.3392857143;
						} else if (player.hasPermission("sgcraft.vender.ultra")) {
							precoLapis = 1.3392857143;
						} else if (player.hasPermission("sgcraft.vender.mvp") || player.isOp()) {
							precoLapis = 1.6964285714;
						}
						int contagem = 0;
						PlayerInventory inv = player.getInventory();
						for (ItemStack item : inv.getContents()) {
							ItemStack inkSack = new ItemStack(Material.INK_SACK, 1, (byte) 4);
							Material matInkSack = inkSack.getType();
							if (item == null)
								continue;
							if (item.getType() == matInkSack && item.getDurability() == 4) {
								contagem = contagem + item.getAmount();
							}
						}
						if (contagem == 0) {
							player.sendMessage("§cVocê não tem nenhum Lapis Lazulli para vender!");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
						} else {

							ItemStack lapis = new ItemStack(Material.INK_SACK, contagem, (byte) 4);
							inv.removeItem(lapis);
							double precoFinal = contagem * precoLapis;
							VaultAPI.getEconomy().depositPlayer(player, precoFinal);
							BigDecimal precoRound = new BigDecimal(precoFinal).setScale(0, RoundingMode.HALF_EVEN);
							player.sendMessage("§fVocê vendeu " + contagem + " Lapis Lazulli §fpor §aR$ "
									+ precoRound.doubleValue());
							player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
						}
					} else if (itemOpt == null) {
						return;
					}
				}
			}
		} else {
			return;
		}
	}

	@EventHandler
	public void OnMenuOpcoes(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			if (e.getWhoClicked() instanceof Player) {
				Player player = (Player) e.getWhoClicked();
				if (e.getInventory().getTitle().equals("§8Vendas - Menu Principal")) {
					e.setCancelled(true);
					e.setCancelled(true);
					if (e.getCurrentItem() == null) return;
					if (e.getCurrentItem().getType() == Material.AIR) return;
					if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
					if (e.getCurrentItem().getType() == Material.WHEAT) {
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						this.openFarms(player);
					} else if (e.getCurrentItem().getType() == Material.BONE) {
						player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
						player.sendMessage("§cNão disponível no momento. Por favor aguarde.");
						player.closeInventory();
					}
				}
			}
		} else {
			return;
		}
	}
}