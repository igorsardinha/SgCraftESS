package br.com.sgcraft;

import org.bukkit.plugin.java.*;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import java.util.*;
import org.bukkit.entity.*;
import net.milkbowl.vault.economy.*;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import br.com.sgcraft.listeners.*;
import br.com.sgcraft.sgspawners.SgSpawnersInv;
import br.com.sgcraft.sgspawners.SgSpawnersListener;
import br.com.sgcraft.sgspawners.SgSpawnersMoeda;
import br.com.sgcraft.utils.MySQL;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.command.*;
import br.com.sgcraft.comandos.*;
import br.com.sgcraft.heads.listeners.HeadsListener;
import br.com.sgcraft.vender.*;

public class Main extends JavaPlugin implements Listener {
	public static Main pl;
	public static Plugin plugin;
	public static MySQL sql;
	public static String prefixX9;
	public static String prefix;
	public static HashMap<Player, Player> open;
	public static String playerNotOnline;
	public static String use;
	public static String no_perm;
	public static int continuar;
	public static Economy econ;
	public static ArrayList<String> abririnv;
	public static ArrayList<String> mensagemknock;
	public static HashMap<String, ItemStack[]> saveinv;
	public static HashMap<String, ItemStack[]> armadura;
	public static boolean suport;
	public static ItemStack moeda;

	static {
		Main.prefixX9 = "§9[X9]";
		Main.prefix = "Config.";
		Main.open = new HashMap<Player, Player>();
		Main.playerNotOnline = "§c Esse Jogador esta Offline!";
		Main.use = " §aVoce abriu o invent\u00e1rio do jogador!";
		Main.no_perm = " §cVoce nao tem permissao para fazer isso!";
	}
	
	public SimpleClans sc;

	@SuppressWarnings("unused")
	public void onEnable() {
		final Server server = this.getServer();
		final PluginManager pm = Bukkit.getPluginManager();
		this.onComandos();
		this.setupWorldEdit();
		this.setupSimpleClans();
		this.onCraft();
		this.onCraft2();
		this.setupSQL();
		SgSpawnersMoeda.loadMoeda();
        if (Bukkit.getServer().getClass().getPackage().toString().contains("v1_8")) {
            GlowEnchant.init();
        }
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§b[SgCraftEssential] §aATIVADO COM SUCESSO!");
		Bukkit.getConsoleSender().sendMessage("");
		this.saveDefaultConfig();
		pm.registerEvents((Listener) new ComandoX9(), (Plugin) this);
		pm.registerEvents((Listener) new VipZeroFix(), (Plugin) this);
		pm.registerEvents((Listener) new BlockCmds(), (Plugin) this);
		pm.registerEvents((Listener) new FixBuff(), (Plugin) this);
		pm.registerEvents((Listener) new MobsFix(), (Plugin) this);
		pm.registerEvents((Listener) new SpawnerQuebrar(), (Plugin) this);
		pm.registerEvents((Listener) new VenderMain(), (Plugin) this);
		pm.registerEvents((Listener) new VenderFarms(), (Plugin) this);
		pm.registerEvents((Listener) new VenderDrops(), (Plugin) this);
		pm.registerEvents((Listener) new JoinMessage(), (Plugin) this);
		pm.registerEvents((Listener) new HeadsListener(), (Plugin) this);
		pm.registerEvents((Listener) new RepairSign(), (Plugin) this);
		pm.registerEvents((Listener) new SgSpawnersListener(), (Plugin) this);
		pm.registerEvents((Listener) new SgSpawnersMoeda(), (Plugin) this);
		pm.registerEvents((Listener) new SgSpawnersInv(), (Plugin) this);
		// pm.registerEvents((Listener) new PerfilEventos(), (Plugin) this);
		Main.pl = this;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§b[SgCraftEssential] §cDESATIVADO COM SUCESSO!");
		Bukkit.getConsoleSender().sendMessage("");
		HandlerList.unregisterAll((Plugin) this);
		this.saveDefaultConfig();
	}

	private void setupWorldEdit() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldEdit");
		if (plugin == null || !(plugin instanceof WorldEditPlugin))
			return;
	}
	
	private void setupSimpleClans(){
		
	    Plugin plug = getServer().getPluginManager().getPlugin("SimpleClans");

	    if (plug != null)
	    {
	        sc = ((SimpleClans) plug);
	    }
		
	}
	
	public void setupSQL() {
		final String user = this.getConfig().getString("MySQL.Usuario");
		final String password = this.getConfig().getString("MySQL.Senha");
		final String host = this.getConfig().getString("MySQL.Host");
		final String database = this.getConfig().getString("MySQL.Database");
		(Main.sql = new MySQL(user, password, host, database, Main.pl)).startConnection();
	}

	private void onComandos() {
		this.getCommand("desencantar").setExecutor((CommandExecutor) new Desencantar());
		this.getCommand("anunciar").setExecutor((CommandExecutor) new Anunciar());
		// this.getCommand("perfil").setExecutor((CommandExecutor) new PerfilMain());
		this.getCommand("x9").setExecutor((CommandExecutor) new ComandoX9());
		this.getCommand("buff").setExecutor((CommandExecutor) new ComandoBuff());
		this.getCommand("fdp").setExecutor((CommandExecutor) new CmdFDP());
		this.getCommand("vender").setExecutor((CommandExecutor) new VenderMain());
		this.getCommand("discord").setExecutor((CommandExecutor) new ChatCmds());
		this.getCommand("site").setExecutor((CommandExecutor) new ChatCmds());
		this.getCommand("givemoeda").setExecutor((CommandExecutor) new SgSpawnersMoeda());
		this.getCommand("moeda").setExecutor((CommandExecutor) new SgSpawnersMoeda());
		this.getCommand("spawners").setExecutor((CommandExecutor) new SgSpawnersInv());
	}

	public void onCraft() {
		ItemStack enderchest = new ItemStack(Material.ENDER_CHEST);
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		Recipe recipe;
		while (recipes.hasNext()) {
			recipe = recipes.next();
			if (recipe != null && recipe.getResult().isSimilar(enderchest)) {
				recipes.remove();
			}
		}
	}

	public void onCraft2() {
		ItemStack nugget = new ItemStack(Material.GOLD_NUGGET);
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		Recipe recipe;
		while (recipes.hasNext()) {
			recipe = recipes.next();
			if (recipe != null && recipe.getResult().isSimilar(nugget)) {
				recipes.remove();
			}
		}
	}
}
