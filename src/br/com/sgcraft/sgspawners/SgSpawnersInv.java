package br.com.sgcraft.sgspawners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import br.com.sgcraft.GlowEnchant;
import br.com.sgcraft.Main;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;

public class SgSpawnersInv implements CommandExecutor, Listener {

	private String ClanTag;
	private String ClanTagColor;
	private String ClanTagPrinc;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("spawners")) {
			Player player = (Player) sender;
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cEste comando so pode ser usado por players.");
			}
			if (Main.pl.sc != null) {
				ClanPlayer cp = Main.pl.sc.getClanManager().getClanPlayer(player.getUniqueId());
				if (cp != null) {
					Clan clan = cp.getClan();
					String tag = clan.getTag();
					String tagColor = clan.getColorTag();
					int qtd = SgSpawnersManager.pegarNumeroMoedas(tag);
					SgSpawnersInv.spawnersMain(player, tag, tagColor, qtd);
				} else {
					player.closeInventory();
					player.sendMessage("§cNão rolou! Você deve estar em um Clan para fazer isso.");

				}
			}
		}
		return false;
	}

	public static void spawnersMain(final Player player, final String tag, final String tagColor, final int qtd) {
		Inventory sgspawners_princ = Bukkit.createInventory((InventoryHolder) null, 27,
				"§8Geradores - " + tag.toUpperCase());

		final ItemStack moedas_total = new ItemStack(Material.DOUBLE_PLANT, 1);
		final ItemMeta moedas_meta = (ItemMeta) moedas_total.getItemMeta();
		moedas_meta.setDisplayName("§eMoedas do Clan");
		ArrayList<String> moedas_lore = new ArrayList<>();
		moedas_lore.add("§r");
		moedas_lore.add("§aNome do Clan: §8" + tagColor.toUpperCase());
		moedas_lore.add("§aTotal de Moedas no Banco: " + qtd + ".");
		moedas_lore.add("§7Clique com §fBotão Direito §7do mouse para sacar as moedas.");
		moedas_lore.add("§8OBS: §cSerá cobrada uma taxa de §b20% §c para fazer o saque!");
		moedas_lore.add("§r");
		moedas_meta.setLore(moedas_lore);
		moedas_total.setItemMeta((ItemMeta) moedas_meta);
		GlowEnchant.addGlow(moedas_total);
		sgspawners_princ.setItem(11, moedas_total);

		final ItemStack upgrades_item = new ItemStack(Material.DIAMOND, 1);
		final ItemMeta upgrade_meta = (ItemMeta) upgrades_item.getItemMeta();
		upgrade_meta.setDisplayName("§bUpgrades");
		ArrayList<String> upgrades_lore = new ArrayList<>();
		upgrades_lore.add("§r");
		upgrades_lore.add("§7Clique aqui para Desbloquear os Spawners.");
		upgrades_lore.add("§r");
		upgrade_meta.setLore(upgrades_lore);
		upgrades_item.setItemMeta((ItemMeta) upgrade_meta);
		sgspawners_princ.setItem(13, upgrades_item);

		final ItemStack geradores_total = new ItemStack(Material.MOB_SPAWNER, 1);
		final ItemMeta geradores_meta = (ItemMeta) geradores_total.getItemMeta();
		geradores_meta.setDisplayName("§6Loja de Geradores");
		ArrayList<String> geradores_lore = new ArrayList<>();
		geradores_lore.add("§r");
		geradores_lore.add("§aÉ necessário desbloquear o gerador para compra-lo.");
		geradores_lore.add("§r");
		geradores_meta.setLore(geradores_lore);
		geradores_total.setItemMeta((ItemMeta) geradores_meta);
		sgspawners_princ.setItem(15, geradores_total);
		player.openInventory(sgspawners_princ);
	}

	public static void menuUpgrades(final Player player, final String clanTag) {
		Inventory sgspawners_upgrades = Bukkit.createInventory((InventoryHolder) null, 27, "§8Desbloquear Geradores");

		// Coelho
		ItemStack coelho_item;
		ArrayList<String> coelho_lore = new ArrayList<>();
		if (!(SgSpawnersManager.pegarSpawnersLvl(clanTag) >= 1)) {
			coelho_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
			coelho_lore.add("§r");
			coelho_lore.add("§cSPAWNER BLOQUEADO");
			coelho_lore.add("§eValor: §f50 §emoedas. §aClique para comprar");
			coelho_lore.add("§r");
		} else {
			coelho_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			coelho_lore.add("§r");
			coelho_lore.add("§aSPAWNER DEBLOQUEADO");
			coelho_lore.add("§eVocê pode compra-lo usando /spawners.");
			coelho_lore.add("§r");
		}
		final ItemMeta coelho_meta = (ItemMeta) coelho_item.getItemMeta();
		coelho_meta.setDisplayName("§fGerador de Coelho");
		coelho_meta.setLore(coelho_lore);
		coelho_item.setItemMeta((ItemMeta) coelho_meta);
		sgspawners_upgrades.setItem(10, coelho_item);

		// Zombie
		ItemStack zombie_item;
		ArrayList<String> zombie_lore = new ArrayList<>();
		if (!(SgSpawnersManager.pegarSpawnersLvl(clanTag) >= 2)) {
			zombie_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
			zombie_lore.add("§r");
			zombie_lore.add("§cSPAWNER BLOQUEADO");
			zombie_lore.add("§eValor: §f100 §emoedas. §aClique para comprar");
			zombie_lore.add("§r");
		} else {
			zombie_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			zombie_lore.add("§r");
			zombie_lore.add("§aSPAWNER DEBLOQUEADO");
			zombie_lore.add("§eVocê pode compra-lo usando /spawners");
			zombie_lore.add("§r");
		}
		final ItemMeta zombie_meta = (ItemMeta) zombie_item.getItemMeta();
		zombie_meta.setDisplayName("§fGerador de Zombie");
		zombie_meta.setLore(zombie_lore);
		zombie_item.setItemMeta((ItemMeta) zombie_meta);
		sgspawners_upgrades.setItem(11, zombie_item);

		// Skeleton
		ItemStack skeleton_item;
		ArrayList<String> skeleton_lore = new ArrayList<>();
		if (!(SgSpawnersManager.pegarSpawnersLvl(clanTag) >= 3)) {
			skeleton_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
			skeleton_lore.add("§r");
			skeleton_lore.add("§cSPAWNER BLOQUEADO");
			skeleton_lore.add("§eValor: §f150 §emoedas. §aClique para comprar");
			skeleton_lore.add("§r");
		} else {
			skeleton_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			skeleton_lore.add("§r");
			skeleton_lore.add("§aSPAWNER DEBLOQUEADO");
			skeleton_lore.add("§eVocê pode compra-lo usando /spawners");
			skeleton_lore.add("§r");
		}
		final ItemMeta skeleton_meta = (ItemMeta) skeleton_item.getItemMeta();
		skeleton_meta.setDisplayName("§fGerador de Esqueleto");
		skeleton_meta.setLore(skeleton_lore);
		skeleton_item.setItemMeta((ItemMeta) skeleton_meta);
		sgspawners_upgrades.setItem(12, skeleton_item);

		// Skeleton
		ItemStack creeper_item;
		ArrayList<String> creeper_lore = new ArrayList<>();
		if (!(SgSpawnersManager.pegarSpawnersLvl(clanTag) >= 4)) {
			creeper_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
			creeper_lore.add("§r");
			creeper_lore.add("§cSPAWNER BLOQUEADO");
			creeper_lore.add("§eValor: §f250 §emoedas. §aClique para comprar");
			creeper_lore.add("§r");
		} else {
			creeper_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			creeper_lore.add("§r");
			creeper_lore.add("§aSPAWNER DEBLOQUEADO");
			creeper_lore.add("§eVocê pode compra-lo usando /spawners");
			creeper_lore.add("§r");
		}
		final ItemMeta creeper_meta = (ItemMeta) creeper_item.getItemMeta();
		creeper_meta.setDisplayName("§fGerador de Creeper");
		creeper_meta.setLore(creeper_lore);
		creeper_item.setItemMeta((ItemMeta) creeper_meta);
		sgspawners_upgrades.setItem(13, creeper_item);

		// Slime
		ItemStack slime_item;
		ArrayList<String> slime_lore = new ArrayList<>();
		if (!(SgSpawnersManager.pegarSpawnersLvl(clanTag) >= 5)) {
			slime_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
			slime_lore.add("§r");
			slime_lore.add("§cSPAWNER BLOQUEADO");
			slime_lore.add("§eValor: §f325 §emoedas. §aClique para comprar");
			slime_lore.add("§r");
		} else {
			slime_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			slime_lore.add("§r");
			slime_lore.add("§aSPAWNER DEBLOQUEADO");
			slime_lore.add("§eVocê pode compra-lo usando /spawners");
			slime_lore.add("§r");
		}
		final ItemMeta slime_meta = (ItemMeta) slime_item.getItemMeta();
		slime_meta.setDisplayName("§fGerador de Slime");
		slime_meta.setLore(slime_lore);
		slime_item.setItemMeta((ItemMeta) slime_meta);
		sgspawners_upgrades.setItem(14, slime_item);

		// PigMan
		ItemStack pigman_item;
		ArrayList<String> pigman_lore = new ArrayList<>();
		if (!(SgSpawnersManager.pegarSpawnersLvl(clanTag) >= 6)) {
			pigman_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
			pigman_lore.add("§r");
			pigman_lore.add("§cSPAWNER BLOQUEADO");
			pigman_lore.add("§eValor: §f400 §emoedas. §aClique para comprar");
			pigman_lore.add("§r");
		} else {
			pigman_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			pigman_lore.add("§r");
			pigman_lore.add("§aSPAWNER DEBLOQUEADO");
			pigman_lore.add("§eVocê pode compra-lo usando /spawners");
			pigman_lore.add("§r");
		}
		final ItemMeta pigman_meta = (ItemMeta) pigman_item.getItemMeta();
		pigman_meta.setDisplayName("§fGerador de PigZombie");
		pigman_meta.setLore(pigman_lore);
		pigman_item.setItemMeta((ItemMeta) pigman_meta);
		sgspawners_upgrades.setItem(15, pigman_item);

		// Golem
		ItemStack golem_item;
		ArrayList<String> golem_lore = new ArrayList<>();
		if (!(SgSpawnersManager.pegarSpawnersLvl(clanTag) >= 7)) {
			golem_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
			golem_lore.add("§r");
			golem_lore.add("§cSPAWNER BLOQUEADO");
			golem_lore.add("§eValor: §f500 §emoedas. §aClique para comprar");
			golem_lore.add("§r");
		} else {
			golem_item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			golem_lore.add("§r");
			golem_lore.add("§aSPAWNER DEBLOQUEADO");
			golem_lore.add("§eVocê pode compra-lo usando /spawners");
			golem_lore.add("§r");
		}
		final ItemMeta golem_meta = (ItemMeta) golem_item.getItemMeta();
		golem_meta.setDisplayName("§fGerador de IronGolem");
		golem_meta.setLore(golem_lore);
		golem_item.setItemMeta((ItemMeta) golem_meta);
		sgspawners_upgrades.setItem(16, golem_item);
		player.openInventory(sgspawners_upgrades);
	}

	public static void menuSpawners(final Player player, final String clanTag) {
		Inventory loja_spawners = Bukkit.createInventory((InventoryHolder) null, 27, "§8Loja de Spawners");

		// Coelho
		final ItemStack skull_coelho = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skull_coelho_meta = (SkullMeta) skull_coelho.getItemMeta();
		skull_coelho_meta.setOwner("MHF_Rabbit");
		skull_coelho_meta.setDisplayName("§eGerador de Coelho");
		ArrayList<String> sklore_coelho = new ArrayList<>();
		sklore_coelho.add("");
		sklore_coelho.add("§aValor: §7100.000 coins.");
		sklore_coelho.add("");
		skull_coelho_meta.setLore(sklore_coelho);
		skull_coelho.setItemMeta((ItemMeta) skull_coelho_meta);
		loja_spawners.setItem(10, skull_coelho);
		player.openInventory(loja_spawners);
		
		// Zombie
		final ItemStack skull_zombie = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
		final SkullMeta skull_zombie_meta = (SkullMeta) skull_zombie.getItemMeta();
		skull_zombie_meta.setDisplayName("§eGerador de Zombie");
		ArrayList<String> sklore_zombie = new ArrayList<>();
		sklore_zombie.add("");
		sklore_zombie.add("§aValor: §7150.000 coins.");
		sklore_zombie.add("");
		skull_zombie_meta.setLore(sklore_zombie);
		skull_zombie.setItemMeta((ItemMeta) skull_zombie_meta);
		loja_spawners.setItem(11, skull_zombie);
		player.openInventory(loja_spawners);
		
		// Skeleton
		final ItemStack skull_skeleton = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
		final SkullMeta skull_skeleton_meta = (SkullMeta) skull_skeleton.getItemMeta();
		skull_skeleton_meta.setDisplayName("§eGerador de Esqueleto");
		ArrayList<String> sklore_skeleton = new ArrayList<>();
		sklore_skeleton.add("");
		sklore_skeleton.add("§aValor: §7200.000 coins.");
		sklore_skeleton.add("");
		skull_skeleton_meta.setLore(sklore_skeleton);
		skull_skeleton.setItemMeta((ItemMeta) skull_skeleton_meta);
		loja_spawners.setItem(12, skull_skeleton);
		player.openInventory(loja_spawners);
		
		// Creeper
		final ItemStack skull_creeper = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
		final SkullMeta skull_creeper_meta = (SkullMeta) skull_creeper.getItemMeta();
		skull_creeper_meta.setDisplayName("§eGerador de Creeper");
		ArrayList<String> sklore_creeper = new ArrayList<>();
		sklore_creeper.add("");
		sklore_creeper.add("§aValor: §7300.000 coins.");
		sklore_creeper.add("");
		skull_creeper_meta.setLore(sklore_creeper);
		skull_creeper.setItemMeta((ItemMeta) skull_creeper_meta);
		loja_spawners.setItem(13, skull_creeper);
		player.openInventory(loja_spawners);
		
		// Slime
		final ItemStack skull_slime = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skull_slime_meta = (SkullMeta) skull_slime.getItemMeta();
		skull_coelho_meta.setOwner("MHF_Slime");
		skull_slime_meta.setDisplayName("§eGerador de Slime");
		ArrayList<String> sklore_slime = new ArrayList<>();
		sklore_slime.add("");
		sklore_slime.add("§aValor: §7450.000 coins.");
		sklore_slime.add("");
		skull_slime_meta.setLore(sklore_slime);
		skull_slime.setItemMeta((ItemMeta) skull_slime_meta);
		loja_spawners.setItem(14, skull_slime);
		player.openInventory(loja_spawners);
		
		// PigZombie
		final ItemStack skull_pigzombie = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skull_pigzombie_meta = (SkullMeta) skull_pigzombie.getItemMeta();
		skull_pigzombie_meta.setOwner("MHF_PigZombie");
		skull_pigzombie_meta.setDisplayName("§eGerador de Zombie Pigman");
		
		ArrayList<String> sklore_pigman = new ArrayList<>();
		sklore_pigman.add("");
		sklore_pigman.add("§aValor: §7600.000 coins.");
		sklore_pigman.add("");
		skull_pigzombie_meta.setLore(sklore_pigman);
		skull_pigzombie.setItemMeta((ItemMeta) skull_pigzombie_meta);
		loja_spawners.setItem(15, skull_pigzombie);
		player.openInventory(loja_spawners);
		
		// Golem
		final ItemStack skull_ironGolem = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skull_ironGolem_meta = (SkullMeta) skull_ironGolem.getItemMeta();
		skull_ironGolem_meta.setOwner("MHF_Golem");
		skull_ironGolem_meta.setDisplayName("§eGerador de Iron Golem");
		ArrayList<String> sklore_golem = new ArrayList<>();
		sklore_golem.add("");
		sklore_golem.add("§aValor: §71.000.000 coins.");
		sklore_golem.add("");
		skull_ironGolem_meta.setLore(sklore_golem);
		skull_ironGolem.setItemMeta((ItemMeta) skull_ironGolem_meta);
		loja_spawners.setItem(16, skull_ironGolem);
		player.openInventory(loja_spawners);
	}

	@EventHandler
	public void OnMenuPrincipal(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			if (e.getWhoClicked() instanceof Player) {
				Player player = (Player) e.getWhoClicked();
				ClanPlayer cp = Main.pl.sc.getClanManager().getClanPlayer(player.getUniqueId());
				if (cp != null) {
					ClanTagPrinc = cp.getClan().getTag();
				}
				if (e.getInventory().getTitle().contains("§8Geradores - ")) {
					e.setCancelled(true);
					if (e.getCurrentItem() == null)
						return;
					if (e.getCurrentItem().getType() == Material.AIR)
						return;
					if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
						return;
					else {
						ItemStack itemOpt = e.getCurrentItem();
						switch (itemOpt.getItemMeta().getDisplayName()) {
						case "§eMoedas do Clan":
							if (e.getClick() == ClickType.RIGHT) {
								if (cp != null) {
									SgSpawnersManager.sacarMoedas(player, ClanTagPrinc);
								}
							}
							if (e.getClick() == ClickType.LEFT) {
								player.sendMessage("§cPara sacar, clique usando o §fBotão Direito §cdo seu Mouse.");
							}
							break;
						case "§bUpgrades":
							SgSpawnersInv.menuUpgrades(player, ClanTagPrinc);
							break;
						case "§6Loja de Geradores":
							SgSpawnersInv.menuSpawners(player, ClanTagPrinc);
							break;
						default:
							return;
						}
					}
					return;
				}
			}
		} else {
			return;
		}
	}

	@EventHandler
	public void onMenuUpgrades(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			if (e.getWhoClicked() instanceof Player) {
				Player player = (Player) e.getWhoClicked();
				ClanPlayer cp = Main.pl.sc.getClanManager().getClanPlayer(player.getUniqueId());
				if (cp != null) {
					ClanTag = cp.getClan().getTag();
					ClanTagColor = cp.getClan().getColorTag();
				}
				if (e.getInventory().getTitle().contains("§8Desbloquear Geradores")) {
					e.setCancelled(true);
					if (e.getCurrentItem() == null)
						return;
					if (e.getCurrentItem().getType() == Material.AIR)
						return;
					if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
						return;
					else {
						ItemStack itemOpt = e.getCurrentItem();
						switch (itemOpt.getItemMeta().getDisplayName()) {
						case "§fGerador de Coelho":
							if (ClanTag != null || ClanTagColor != null) {
								SgSpawnersManager.uparNivel(player, ClanTag, ClanTagColor, 50, 0, "Coelho");
							}
							break;
						case "§fGerador de Zombie":
							SgSpawnersManager.uparNivel(player, ClanTag, ClanTagColor, 100, 1, "Zombie");
							break;
						case "§fGerador de Esqueleto":
							SgSpawnersManager.uparNivel(player, ClanTag, ClanTagColor, 150, 2, "Skeleton");
							break;
						case "§fGerador de Creeper":
							SgSpawnersManager.uparNivel(player, ClanTag, ClanTagColor, 250, 3, "Creeper");
							break;
						case "§fGerador de Slime":
							SgSpawnersManager.uparNivel(player, ClanTag, ClanTagColor, 325, 4, "Slime");
							break;
						case "§fGerador de PigZombie":
							SgSpawnersManager.uparNivel(player, ClanTag, ClanTagColor, 400, 5, "PigZombie");
							break;
						case "§fGerador de IronGolem":
							SgSpawnersManager.uparNivel(player, ClanTag, ClanTagColor, 500, 6, "IronGolem");
							break;
						default:
							return;
						}
						return;
					}
				}
			}
		} else {
			return;
		}
	}
	
	@EventHandler
	public void onMenuSpawners(InventoryClickEvent e) {
		if (e.getInventory() != null) {
			if (e.getWhoClicked() instanceof Player) {
				Player player = (Player) e.getWhoClicked();
				ClanPlayer cp = Main.pl.sc.getClanManager().getClanPlayer(player.getUniqueId());
				if (cp != null) {
					ClanTag = cp.getClan().getTag();
					ClanTagColor = cp.getClan().getColorTag();
				}
				if (e.getInventory().getTitle().contains("§8Loja de Spawners")) {
					e.setCancelled(true);
					if (e.getCurrentItem() == null)
						return;
					if (e.getCurrentItem().getType() == Material.AIR)
						return;
					if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
						return;
					else {
						ItemStack itemOpt = e.getCurrentItem();
						switch (itemOpt.getItemMeta().getDisplayName()) {
						case "§eGerador de Coelho":
							SgSpawnersManager.SpawnerBuy(player, ClanTag, 100000, 1, "Coelho");
							break;
						case "§eGerador de Zombie":
							SgSpawnersManager.SpawnerBuy(player, ClanTag, 150000, 2, "Zombie");
							break;
						case "§eGerador de Esqueleto":
							SgSpawnersManager.SpawnerBuy(player, ClanTag, 200000, 3, "Skeleton");
							break;
						case "§eGerador de Creeper":
							SgSpawnersManager.SpawnerBuy(player, ClanTag, 300000, 4, "Creeper");
							break;
						case "§eGerador de Slime":
							SgSpawnersManager.SpawnerBuy(player, ClanTag, 450000, 5, "Slime");
							break;
						case "§eGerador de Zombie Pigman":
							SgSpawnersManager.SpawnerBuy(player, ClanTag, 600000, 6, "PigMan");
							break;
						case "§eGerador de Iron Golem":
							SgSpawnersManager.SpawnerBuy(player, ClanTag, 1000000, 7, "IronGolem");
							break;
						default:
							return;
						}
						return;
					}
				}
			}
		} else {
			return;
		}
	}
}