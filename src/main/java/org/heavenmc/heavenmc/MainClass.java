package org.heavenmc.heavenmc;

import fr.mrmicky.fastboard.FastBoard;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.heavenmc.heavenmc.onEnableClasses.CommandsLoader;
import org.heavenmc.heavenmc.onEnableClasses.EventsLoader;
import org.heavenmc.heavenmc.placeholderexpension.Economy;
import org.heavenmc.heavenmc.server.accountManagement.PlayerAccountManager;
import org.heavenmc.heavenmc.database.DBConnection;
import org.heavenmc.heavenmc.database.DBCreditentials;
import org.heavenmc.heavenmc.server.commands.player.FlyCommand;
import org.heavenmc.heavenmc.server.configs.ActionBarConfig;
import org.heavenmc.heavenmc.server.visual.hub_bossbar.Manager;
import org.heavenmc.heavenmc.server.worldguard.events.Events;
import org.heavenmc.heavenmc.server.events.HubInstancesEvents;
import org.heavenmc.heavenmc.utils.Prefixer;
import org.heavenmc.heavenmc.server.visual.chat.events.ChatEvents;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class MainClass extends JavaPlugin implements Listener {

    private static MainClass plugin;

    public static MainClass getPlugin()
    {
        return plugin;
    }

    private static DBConnection playerDataDB;
    public final String prefix = Prefixer.getPrefix();

    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    private static BukkitAudiences adventure;

    public static @NonNull BukkitAudiences adventure()
    {
        if(adventure == null)
        {
            throw new IllegalStateException("Tried to access adventure while the plugin was disabled!");
        }
        return adventure;
    }


    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        adventure = BukkitAudiences.create(this);
        EventsLoader.enableEvents(this);
        CommandsLoader.enableCommands(this);

        /*
            BossBar default config
         */
        ActionBarConfig.loc.saveDefaultConfig();

        // Connection to the database (playerData)
        if(getConfig().getString("database.playerData.host")!=null && getConfig().getString("database.playerData.user")!=null && getConfig().getString("database.playerData.password")!=null && getConfig().getString("database.playerData.dbName")!=null && getConfig().isInt("database.playerData.port"))
        {
            playerDataDB = new DBConnection(new DBCreditentials(getConfig().getString("database.playerData.host"), getConfig().getString("database.playerData.user"), getConfig().getString("database.playerData.password"), getConfig().getString("database.playerData.dbName"), getConfig().getInt("database.playerData.port")));
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix+" §rDatabase (playerData) configuration content is invalid, can't connect to the database. (§cFAILED§r)");
        }
    }

    public static DBConnection getPlayerDataConnection()
    {
        return playerDataDB;
    }

    @Override
    public void onDisable()
    {
        if(adventure!=null)
        {
            adventure.close();
            adventure = null;
        }
    }
}
