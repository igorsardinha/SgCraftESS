package br.com.sgcraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;

public class RepairSign implements Listener{
	
	 @SuppressWarnings("unused")
	@EventHandler
	    public void onPlayerInteract(PlayerInteractEvent event) {
	        Player player = event.getPlayer();
	        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
	            if (event.getClickedBlock().getState() instanceof Sign) {
	                Sign sign = (Sign) event.getClickedBlock().getState();
	                if(sign.getLine(0).contains("[REPARAR]")) {
	                    event.getPlayer().chat("/repair all");
	                }
	            }
	        }
	    }

}
