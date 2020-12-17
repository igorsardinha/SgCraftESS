package br.com.sgcraft.sgspawners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import br.com.sgcraft.Main;
import br.com.sgcraft.VaultAPI;
import br.com.sgcraft.utils.MySQL;
import net.milkbowl.vault.economy.EconomyResponse;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;

public class SgSpawnersManager {

	private static int qtdMoeda;
	private static int SpLevel;

	public static boolean clanExiste(final String ClanTag) {
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("SELECT * FROM SgSpawners_Clans WHERE ClanTag=?");
			ps.setString(1, ClanTag);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void novoClan(final String ClanTag, final int AddMoedas, final int AddSPLevels) {
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("INSERT INTO SgSpawners_Clans (ClanTag, Moedas, SpawnersLevel) VALUES (?,?,?);");
			ps.setString(1, ClanTag);
			ps.setInt(2, AddMoedas);
			ps.setInt(3, AddSPLevels);
			ps.executeUpdate();
		} catch (SQLException ev) {
			ev.printStackTrace();
		}
	}

	public static void deleteClan(final String ClanTag) {
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("DELETE FROM SgSpawners_Clans WHERE ClanTag = ?;");
			ps.setString(1, ClanTag.toLowerCase());
			ps.executeUpdate();
		} catch (SQLException ev) {
			ev.printStackTrace();
		}
	}

	public static void pegarMoedaAndLevel(String clanTag) {
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("SELECT * FROM SgSpawners_Clans WHERE ClanTag=?;");
			ps.setString(1, clanTag.toLowerCase());
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				qtdMoeda = rs.getInt("Moedas");
				SpLevel = rs.getInt("SpawnersLevel");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addMoedaBanco(final String ClanTag) {
		SgSpawnersManager.pegarMoedaAndLevel(ClanTag);
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE SgSpawners_Clans SET Moedas = ? WHERE ClanTag = ?;");
			ps.setInt(1, qtdMoeda + 1);
			ps.setString(2, ClanTag);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeMoedaBanco(final String ClanTag, final int qtd) {
		SgSpawnersManager.pegarMoedaAndLevel(ClanTag);
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE SgSpawners_Clans SET Moedas = ? WHERE ClanTag = ?;");
			ps.setInt(1, qtdMoeda - qtd);
			ps.setString(2, ClanTag);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void sacarMoedas(final Player player, final String ClanTag) {
		SgSpawnersManager.pegarMoedaAndLevel(ClanTag);
		if (qtdMoeda < 9) {
			player.sendMessage("§cNecessário no minimo 10 Moedas para o Saque ser efetuado!");
		} else {
			//verfica se existe SimpleClans
			if (Main.pl.sc != null) {
				ClanPlayer cp = Main.pl.sc.getClanManager().getClanPlayer(player.getUniqueId());
				if (cp != null) {
					// Verifica se é Lider
					if (cp.isLeader()) {
						double qtdDesconto = qtdMoeda * 0.2;
						double qtdFinal = qtdMoeda - qtdDesconto;
						Main.pl.getServer().dispatchCommand((CommandSender) Main.pl.getServer().getConsoleSender(),
								"givemoeda " + player.getName() + " " + (int) qtdFinal);
						try {
							final PreparedStatement ps = MySQL.connection
									.prepareStatement("UPDATE SgSpawners_Clans SET Moedas = ? WHERE ClanTag = ?;");
							ps.setInt(1, 0);
							ps.setString(2, ClanTag);
							ps.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						player.closeInventory();
						player.sendMessage("§aVocê sacou §f" + (int) qtdFinal + " §aMoedas do Banco do seu Clan");
						cp.getClan().addBb("§b" + player.getDisplayName() + " §cEfetou um Saque de §f" + (int)qtdFinal + " §cMoedas.");
					//Caso nao for lider
					} else {
						player.sendMessage("§cSomente Líderes podem efetuar esse tipo de Saque.");
					}
				} else {
					//caso nao esta no clan
					player.sendMessage("§cNão rolou! Você não está em nenhum Clan.");
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void uparNivel(final Player player, final String ClanTag, final String ClanTagColor, final int ValorSpawner,
			final int LevelMinimo, final String SpawnerName) {
		SgSpawnersManager.pegarMoedaAndLevel(ClanTag);
		// verfica se existe SimpleClans
		if (SpLevel < LevelMinimo) {
			player.sendMessage("§cVocê deve desbloquear os Spawners em Ordem.");
		}
		else if(SpLevel > LevelMinimo) {
			player.sendMessage("§aEsse Spawner já foi desbloqueado!");
		}
		else {
			if (qtdMoeda < ValorSpawner) {
				player.sendMessage("§cVocê não tem a quantide de Moedas Necessária para isto.");
			} else {
				if (Main.pl.sc != null) {
					ClanPlayer cp = Main.pl.sc.getClanManager().getClanPlayer(player.getUniqueId());
					if (cp != null) {
						// Verifica se é Lider
						if (cp.isLeader()) {
							try {
								final PreparedStatement ps = MySQL.connection.prepareStatement(
										"UPDATE SgSpawners_Clans SET Moedas = ?, SpawnersLevel = ?  WHERE ClanTag = ?;");
								ps.setInt(1, qtdMoeda - ValorSpawner);
								ps.setInt(2, SpLevel + 1);
								ps.setString(3, ClanTag);
								ps.executeUpdate();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							player.sendMessage("§aVocê liberou o Spawner de §f"+ SpawnerName + " §apor §7" + ValorSpawner + " §aMoedas." );
							player.closeInventory();
							cp.getClan().addBb("§b" + player.getDisplayName() + " §aliberou o Spawner de§f "+ SpawnerName);
							// Caso nao for lider
						} else {
							player.sendMessage("§cSomente Líderes podem fazer Upgrades.");
						}
					} else {
						// caso nao esta no clan
						player.sendMessage("§cNão rolou! Você não está em nenhum Clan.");
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void SpawnerBuy(final Player player, final String ClanTag, final double PrecoSpawner, final int LevelMin, final String tipo) {
		SgSpawnersManager.pegarMoedaAndLevel(ClanTag);
	if (tipo == "Coelho" && SpLevel >= LevelMin) {
		final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
		if (moneyQtd >= PrecoSpawner) {
			@SuppressWarnings("unused")
			final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), PrecoSpawner);
			Main.pl.getServer().dispatchCommand((CommandSender)Main.pl.getServer().getConsoleSender(), "sgive " + player.getName() + " Rabbit 1" );
			player.sendMessage("§aVocê comprou §71 §aspawner de §7" + tipo + " §apor §7"+ PrecoSpawner +" §acoins.");
		}
		else {
			player.sendMessage("§cVocê não tem Money suficiente para isso!");
		}
	}
	else if (tipo == "Zombie" && SpLevel >= LevelMin) {
		final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
		if (moneyQtd >= PrecoSpawner) {
			@SuppressWarnings("unused")
			final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), PrecoSpawner);
			Main.pl.getServer().dispatchCommand((CommandSender)Main.pl.getServer().getConsoleSender(), "sgive " + player.getName() + " Zombie 1" );
			player.sendMessage("§aVocê comprou §71 §aspawner de §7" + tipo + " §apor §7"+ PrecoSpawner +" §acoins.");
		}
		else {
			player.sendMessage("§cVocê não tem Money suficiente para isso!");
		}
	}
	else if (tipo == "Skeleton" && SpLevel >= LevelMin) {
		final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
		if (moneyQtd >= PrecoSpawner) {
			@SuppressWarnings("unused")
			final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), PrecoSpawner);
			Main.pl.getServer().dispatchCommand((CommandSender)Main.pl.getServer().getConsoleSender(), "sgive " + player.getName() + " Skeleton 1" );
			player.sendMessage("§aVocê comprou §71 §aspawner de §7" + tipo + " §apor §7"+ PrecoSpawner +" §acoins.");
		}
		else {
			player.sendMessage("§cVocê não tem Money suficiente para isso!");
		}
	}
	else if (tipo == "Creeper" && SpLevel >= LevelMin) {
		final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
		if (moneyQtd >= PrecoSpawner) {
			@SuppressWarnings("unused")
			final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), PrecoSpawner);
			Main.pl.getServer().dispatchCommand((CommandSender)Main.pl.getServer().getConsoleSender(), "sgive " + player.getName() + " Creeper 1" );
			player.sendMessage("§aVocê comprou §71 §aspawner de §7" + tipo + " §apor §7"+ PrecoSpawner +" §acoins.");
		}
		else {
			player.sendMessage("§cVocê não tem Money suficiente para isso!");
		}
	}

	else if (tipo == "Slime" && SpLevel >= LevelMin) {
		final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
		if (moneyQtd >= PrecoSpawner) {
			@SuppressWarnings("unused")
			final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), PrecoSpawner);
			Main.pl.getServer().dispatchCommand((CommandSender)Main.pl.getServer().getConsoleSender(), "sgive " + player.getName() + " Slime 1" );
			player.sendMessage("§aVocê comprou §71 §aspawner de §7" + tipo + " §apor §7"+ PrecoSpawner +" §acoins.");
		}
		else {
			player.sendMessage("§cVocê não tem Money suficiente para isso!");
		}
	}
	
	else if (tipo == "PigMan" && SpLevel >= LevelMin) {
		final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
		if (moneyQtd >= PrecoSpawner) {
			@SuppressWarnings("unused")
			final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), PrecoSpawner);
			Main.pl.getServer().dispatchCommand((CommandSender)Main.pl.getServer().getConsoleSender(), "sgive " + player.getName() + " PIG_ZOMBIE 1" );
			player.sendMessage("§aVocê comprou §71 §aspawner de §7" + tipo + " §apor §7"+ PrecoSpawner +" §acoins.");
		}
		else {
			player.sendMessage("§cVocê não tem Money suficiente para isso!");
		}
	}
	
	else if (tipo == "IronGolem" && SpLevel >= LevelMin) {
		final double moneyQtd = VaultAPI.getEconomy().getBalance(player);
		if (moneyQtd >= PrecoSpawner) {
			@SuppressWarnings("unused")
			final EconomyResponse c = VaultAPI.getEconomy().withdrawPlayer(player.getName(), PrecoSpawner);
			Main.pl.getServer().dispatchCommand((CommandSender)Main.pl.getServer().getConsoleSender(), "sgive " + player.getName() + " IRON_GOLEM 1" );
			player.sendMessage("§aVocê comprou §71 §aspawner de §7" + tipo + " §apor §7"+ PrecoSpawner +" §acoins.");
		}
		else {
			player.sendMessage("§cVocê não tem Money suficiente para isso!");
		}
	}
	
	else if (SpLevel < LevelMin) {
		player.sendMessage("§cEsse Spawner está bloqueado");
	}
	}

	public static Integer pegarNumeroMoedas(final String ClanTag) {
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("SELECT * FROM SgSpawners_Clans WHERE ClanTag=?");
			ps.setString(1, ClanTag.toLowerCase());
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				final int n = rs.getInt("Moedas");
				return n;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Integer pegarSpawnersLvl(final String ClanTag) {
		try {
			final PreparedStatement ps = MySQL.connection
					.prepareStatement("SELECT * FROM SgSpawners_Clans WHERE ClanTag=?");
			ps.setString(1, ClanTag.toLowerCase());
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				final int n = rs.getInt("SpawnersLevel");
				return n;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
