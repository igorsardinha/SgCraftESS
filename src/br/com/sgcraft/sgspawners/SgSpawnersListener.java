package br.com.sgcraft.sgspawners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import br.com.sgcraft.Main;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.events.CreateClanEvent;
import net.sacredlabyrinth.phaed.simpleclans.events.DisbandClanEvent;

public class SgSpawnersListener implements Listener {

	private String clanTag;
	private int AddMoedas;
	private int AddSPLevels;

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onClanCreate(CreateClanEvent event) {
		clanTag = event.getClan().getTag();
		AddMoedas = 0;
		AddSPLevels = 0;
		if (!SgSpawnersManager.clanExiste(clanTag)) {
			SgSpawnersManager.novoClan(clanTag, AddMoedas, AddSPLevels);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onClanDisband(DisbandClanEvent event) {
		clanTag = event.getClan().getTag();
		if (SgSpawnersManager.clanExiste(clanTag)) {
			SgSpawnersManager.deleteClan(clanTag);
		}
	}

	@SuppressWarnings("static-access")
	@EventHandler
	public void addBanco(final PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getItem() == null) {
			return;
		}
		if (event.getAction().equals((Object) Action.RIGHT_CLICK_AIR) && event.getPlayer().getItemInHand().isSimilar(Main.pl.moeda)
				|| event.getAction().equals((Object) Action.RIGHT_CLICK_BLOCK)
						&& event.getPlayer().getItemInHand().isSimilar(Main.pl.moeda)) {
			// Verifica se ta no clan
			if (Main.pl.sc != null) {
				ClanPlayer cp = Main.pl.sc.getClanManager().getClanPlayer(player.getUniqueId());
				if (cp != null) {
					Clan clan = cp.getClan();
					String tag = clan.getTag();
					if (SgSpawnersManager.clanExiste(tag)) {
						SgSpawnersManager.addMoedaBanco(tag);
						this.removerMoeda(player);
						player.sendMessage("§aVocê adicionou (1) moeda ao Banco do seu Clan. §7Total: "
								+ SgSpawnersManager.pegarNumeroMoedas(tag) + "§7.");
					}
				} else {
					player.sendMessage("§cNão rolou! Você deve estar em um Clan para usar as Moedas.");
				}
				event.setCancelled(true);
			}
		}
	}

	private void removerMoeda(final Player p) {
		final ItemStack ap = p.getItemInHand();
		if (ap.getAmount() == 1) {
			p.setItemInHand(new ItemStack(Material.AIR));
		} else {
			ap.setAmount(ap.getAmount() - 1);
			p.setItemInHand(ap);
		}
	}

}
