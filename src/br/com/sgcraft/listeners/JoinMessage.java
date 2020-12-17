package br.com.sgcraft.listeners;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class JoinMessage implements Listener
{
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoinClearChat(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        for (int i = 1; i < 80; ++i) {
            p.sendMessage("                ");
        }
    }
}
