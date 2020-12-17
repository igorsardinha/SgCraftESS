package br.com.sgcraft.utils;

import java.sql.*;

import br.com.sgcraft.Main;
public class MySQL
{
    private String user;
    private String host;
    private String database;
    private String password;
    public static Connection connection;
    static Statement statement;
    
    public MySQL(final String user, final String password, final String host, final String database, final Main plugin) {
        this.user = user;
        this.password = password;
        this.host = host;
        this.database = database;
    }
    
    
	public void startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            MySQL.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.database, this.user, this.password);
            (MySQL.statement = MySQL.connection.createStatement()).execute("CREATE TABLE IF NOT EXISTS SgSpawners_Clans (ClanTag VARCHAR(5), Moedas INT(3), SpawnersLevel INT (2), PRIMARY KEY(ClanTag))");
        }
        catch (SQLException | ClassNotFoundException ex4) {
            ex4.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            MySQL.statement.close();
            MySQL.connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
