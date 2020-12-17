package br.com.sgcraft.perfil;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import br.com.sgcraft.Main;
import br.com.sgcraft.VaultAPI;

public class PerfilEventos implements Listener {
	
    List<String> message;
    
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerJoin(final PlayerJoinEvent evt) {
        final Player p = evt.getPlayer();
		double targetMoney = VaultAPI.getEconomy().getBalance(p.getName());
        BigDecimal moneyFormat = new BigDecimal(targetMoney).setScale(2, RoundingMode.HALF_EVEN);
        final String filename = p.getName();
        final File yourFile = new File(Main.pl.getDataFolder() + "/players/" + File.separator + filename + ".yml");
        final YamlConfiguration yamlYourFile = YamlConfiguration.loadConfiguration(yourFile);
        yamlYourFile.addDefault("player.uuid", (Object)p.getUniqueId().toString());
        if (yamlYourFile.get("player.status") == null) {
            yamlYourFile.addDefault("player.status", (Object)true);
        }
        else {
            yamlYourFile.set("player.status", (Object)true);
        }
        //PerfilPrincipal
        yamlYourFile.addDefault("player.money", (Object)moneyFormat.doubleValue());
        yamlYourFile.addDefault("player.discord", (Object)"Não Adicionado");
        yamlYourFile.addDefault("player.youtube", (Object)"Não Adicionado");
        yamlYourFile.addDefault("player.twitch", (Object)"Não Adicionado");
        yamlYourFile.addDefault("player.instagram", (Object)"Não Adicionado");
        yamlYourFile.addDefault("player.facebook", (Object)"Não Adicionado");
        //Dados_Minecraft
        yamlYourFile.options().copyDefaults(true);
        try {
            yamlYourFile.save(yourFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
