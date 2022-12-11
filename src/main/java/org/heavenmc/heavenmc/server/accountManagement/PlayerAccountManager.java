package org.heavenmc.heavenmc.server.accountManagement;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class PlayerAccountManager implements Listener {
    /*
        When the player joins, we create an account for him, for the server economy
        and others things for utilities.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        final UUID uuid = e.getPlayer().getUniqueId();
        final DBConnection playerDataConnection = MainClass.getPlayerDataConnection();

        try {
            final Connection connection = playerDataConnection.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, player_name FROM playerdata WHERE uuid = ?");

            preparedStatement.setString(1, uuid.toString());

            final ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
                {
                    String pr = "%luckperms_prefix%";
                    pr = PlaceholderAPI.setPlaceholders(e.getPlayer(), pr);
                    if(e.getPlayer().hasPermission("message.on.join"))
                    {
                        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', pr + e.getPlayer().getDisplayName()) + " a rejoint le serveur !");
                    } else { e.setJoinMessage(""); }
                } else {
                    if(e.getPlayer().hasPermission("message.on.join"))
                    {
                        e.setJoinMessage(String.format("§f[§a+§f] %s", e.getPlayer().getName()));
                    } else { e.setJoinMessage(""); }
                }
            } else {
                createUserAccount(connection, uuid, e.getPlayer().getName());

                if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
                {
                    String pr = "%luckperms_prefix%";
                    pr = PlaceholderAPI.setPlaceholders(e.getPlayer(), pr);
                    if(e.getPlayer().hasPermission("message.on.join"))
                    {
                        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', pr + e.getPlayer().getDisplayName()) + " a rejoint le serveur !");
                    } else { e.setJoinMessage(""); }
                    Bukkit.broadcastMessage(String.format("§eBienvenue à %s sur le serveur !", e.getPlayer().getName()));
                } else {
                    if(e.getPlayer().hasPermission("message.on.join"))
                    {
                        e.setJoinMessage(String.format("§f[§a+§f] %s", e.getPlayer().getName()));
                    } else { e.setJoinMessage(""); }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createUserAccount(Connection connection, UUID uuid, String playerName)
    {
        try
        {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `playerdata`(`uuid`, `player_name`) VALUES (?, ?)");

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, playerName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
