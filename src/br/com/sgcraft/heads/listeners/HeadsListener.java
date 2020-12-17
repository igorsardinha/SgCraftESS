package br.com.sgcraft.heads.listeners;

import org.bukkit.SkullType;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import br.com.sgcraft.heads.HeadsMain;

public class HeadsListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onInteract(PlayerInteractEvent event) {
		if (event.getPlayer().getItemInHand() != null && event.getItem() != null) {
		} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (event.getClickedBlock().getState() instanceof Skull) {
				Skull skull = (Skull) event.getClickedBlock().getState();
				if (skull.getSkullType() == SkullType.PLAYER && skull.hasOwner()
						&& !HeadsMain.isSkullCustom(skull.getOwner())) {
					event.getPlayer()
							.sendMessage("§f§l[§6Heads§f§l] §bCabeça do Jogador: §e" + skull.getOwner() + "§b.");

				}
			}
		}
	}
}
