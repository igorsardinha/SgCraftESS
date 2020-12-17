package br.com.sgcraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockCmds implements Listener {
    @SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (e.getMessage().equalsIgnoreCase("/loja")) {
            e.getPlayer().chat("/warp loja");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/minerar")) {
            e.getPlayer().chat("/warp minerar");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/mina")) {
            e.getPlayer().chat("/warp minerar");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/arena1")) {
            e.getPlayer().chat("/warp arena1");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/arena2")) {
            e.getPlayer().chat("/warp arena2");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/mutar")) {
            e.getPlayer().chat("/litebans:mute");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/desmutar")) {
            e.getPlayer().chat("/litebans:unmute");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/scoreboard")) {
            e.getPlayer().chat("/featherboard toggle");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/score")) {
            e.getPlayer().chat("/featherboard toggle");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/alternar")) {
            e.getPlayer().chat("/featherboard toggle");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/cidade")) {
            e.getPlayer().chat("/warp cidade");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/regras")) {
            e.getPlayer().chat("/warp regras");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/minapvp")) {
            e.getPlayer().chat("/warp minapvp");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/minavip")) {
            e.getPlayer().chat("/warp minavip");
            e.setCancelled(true);
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/dc")) {
            e.getPlayer().chat("/discord");
            e.setCancelled(true);
            return;
        }
    }

}
