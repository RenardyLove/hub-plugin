package org.heavenmc.heavenmc.placeholderexpension;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class Economy extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "HeavenMC";
    }

    @Override
    public String getIdentifier() {
        return "economy";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("crystals")) {
            return ""+getCrsytals(player); // "name" requires the player to be valid
        }
        if(params.equalsIgnoreCase("stardust")) {
            return ""+getStarDust(player);
        }

        return "§cERROR PLACEHOLDER"; // Placeholder is unknown by the Expansion
    }

    /*
        IMPERATIVEMENT A CHANGER -> TROP DE REQUETE A LA BASE DE DONNÉES
     */

    private int getCrsytals(OfflinePlayer e)
    {
        final UUID uuid = Objects.requireNonNull(e.getPlayer()).getUniqueId();
        final DBConnection playerDataConnection = MainClass.getPlayerDataConnection();

        try {
            final Connection connection = playerDataConnection.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT crystals FROM playerdata WHERE uuid = ?");

            preparedStatement.setString(1, uuid.toString());

            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("crystals");
            }
        } catch (SQLException exception) {throw new RuntimeException(exception);}

        return 0;
    }

    private int getStarDust(OfflinePlayer e)
    {
        final UUID uuid = Objects.requireNonNull(e.getPlayer()).getUniqueId();
        final DBConnection playerDataConnection = MainClass.getPlayerDataConnection();

        try {
            final Connection connection = playerDataConnection.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT stardust FROM playerdata WHERE uuid = ?");

            preparedStatement.setString(1, uuid.toString());

            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("stardust");
            }
        } catch (SQLException exception) {throw new RuntimeException(exception);}

        return 0;
    }
}