package br.com.sgcraft.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import br.com.sgcraft.VaultAPI;
import net.milkbowl.vault.economy.EconomyResponse;

public class ComandoBuff implements CommandExecutor {
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		
		if (sender instanceof org.bukkit.command.ConsoleCommandSender) {

			sender.sendMessage("§cDesculpe, somente jogadores podem executar este comando!");

			return true;
		}
		
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("buff")) {
			if (!player.hasPermission("sgcraft.buff")) {
				player.sendMessage("§cVocê nao tem permissao para usar o BUFF!");
			} else {
				final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
				if (moneyQtd >= 30000) {
					final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), 30000.0);
					for (final PotionEffect effect : player.getActivePotionEffects()) {
						player.removePotionEffect(effect.getType());
					}
					player.sendMessage("§cFoi deduzido 30k de sua conta por usar o BUFF!");
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4800, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3600, 4));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4800, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 4800, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3600, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 200, 0));
					Bukkit.broadcastMessage("§f[§6BUFF§f] §fO jogador " + player.getName() + " §futilizou um §lBUFF§f!");
				} else {
					player.sendMessage("§cVoce precisa de 30k para usar o BUFF!");
					return false;
				}
			}
		}
		return false;
	}
}
