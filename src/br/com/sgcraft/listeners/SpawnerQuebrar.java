package br.com.sgcraft.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SpawnerQuebrar implements Listener {

	@EventHandler
	public void quebrar(final BlockBreakEvent e) {
		final Player p = e.getPlayer();
		final Block b = e.getBlock();
		if (b.getType() == Material.MOB_SPAWNER) {
			if (p.hasPermission("sgcraft.quebrarspawner") && !p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) && !p.isOp()) {
				e.setExpToDrop(0);
				e.setCancelled(true);
				p.sendMessage("§cUse uma picareta com Silk Touch para não perder seu Spawner!");
			}
			if (p.hasPermission("sgcraft.quebrarspawner.not") && p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) && !p.isOp()) {
				e.setExpToDrop(0);
				e.setCancelled(true);
				p.sendMessage("§cVocê precisa ser do grupo Mega ou Superior para quebrar Spawners!");
			}
		}
	}
}
