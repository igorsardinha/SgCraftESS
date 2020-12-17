package br.com.sgcraft.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import br.com.sgcraft.Main;

public class ChatCmds implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		
		if (sender instanceof org.bukkit.command.ConsoleCommandSender) {

			sender.sendMessage("§cDesculpe, somente jogadores podem executar este comando!");

			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("discord")) {
			player.sendMessage("§r");
			player.sendMessage("§cEm manutenção...");
        	player.sendMessage("§r");
        }
		if (cmd.getName().equalsIgnoreCase("site")) {
			player.sendMessage("§r");
			player.sendMessage("§cEm manutenção...");
        	player.sendMessage("§r");
        }
		return false;
    }

}
