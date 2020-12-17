package br.com.sgcraft.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.sgcraft.Main;

public class CmdFDP implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		
		if (sender instanceof org.bukkit.command.ConsoleCommandSender) {

			sender.sendMessage("§cDesculpe, somente jogadores podem executar este comando!");

			return true;
		}
		
		Player player = (Player) sender;
		if (!cmd.getName().equalsIgnoreCase("fdp")) {
			return false;
		}
		else if (!sender.hasPermission("sgcraft.admin")) {
			sender.sendMessage(ChatColor.RED + "Voc\u00ea n\u00e3o tem permiss\u00e3o.");
			return true;
		}
		else if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Digite: /fdp <player>");
			return true;
		}
		final Player p = Main.pl.getServer().getPlayer(args[0]);
		if (p == null) {
			sender.sendMessage(ChatColor.RED + "Player n\u00e3o encontrado.");
			return true;
		}
		else if (p == player) {
			sender.sendMessage(ChatColor.RED + "Você não pode castigar você mesmo.");
		} else {
			Bukkit.broadcastMessage("§4§m---------------------------------------------------");
			Bukkit.broadcastMessage("  §c>> §6" + player.getName() + " §festá castigando §e" + p.getName());
			Bukkit.broadcastMessage("§4§m---------------------------------------------------");
			CmdFDP.aplicarFDP(p);

			return true;
		}
		return false;
	}

	public static void aplicarFDP(final Player p) {
		p.getWorld().strikeLightningEffect(p.getLocation().add(1.0, 0.0, 1.0));
		p.getWorld().strikeLightningEffect(p.getLocation().add(-1.0, 0.0, -1.0));
		p.getWorld().strikeLightningEffect(p.getLocation().add(1.0, 0.0, -1.0));
		p.getWorld().strikeLightningEffect(p.getLocation().add(-1.0, 0.0, 1.0));
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
	}
}
