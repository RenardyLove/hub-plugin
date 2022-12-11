package org.heavenmc.heavenmc.database;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.utils.Prefixer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private DBCreditentials dbCreditentials;
    private Connection connection;
    private final String prefix = Prefixer.getPrefix();

    public DBConnection(DBCreditentials dbCreditentials)
    {
        this.dbCreditentials = dbCreditentials;
        this.connect();
    }

    private void connect()
    {
        Bukkit.getConsoleSender().sendMessage(prefix+" §rConnecting to the database.. (§cLOADING§r)");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.dbCreditentials.toURL(), this.dbCreditentials.getUser(), this.dbCreditentials.getPass());
            Bukkit.getConsoleSender().sendMessage(prefix+" §rConnected to the database! (§aSUCCESS§r)");
        } catch (SQLException | ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage(prefix+" §rFailed to connect to the database. (§cFAILED§r)");
            throw new RuntimeException(e);
        }
    }

    private void close() throws SQLException
    {
        if(this.connection!=null)
        {
            if(!this.connection.isClosed())
            {
                this.connection.close();
            }
        }
    }

    public Connection getConnection() throws SQLException
    {
        if(this.connection!=null)
        {
            if(!this.connection.isClosed())
            {
                return this.connection;
            }
        }
        connect();
        return this.connection;
    }
}
