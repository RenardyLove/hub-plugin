package org.heavenmc.heavenmc.onEnableClasses;

import org.bukkit.Bukkit;
import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.placeholderexpension.Economy;
import org.heavenmc.heavenmc.server.accountManagement.PlayerAccountManager;
import org.heavenmc.heavenmc.server.events.HubInstancesEvents;
import org.heavenmc.heavenmc.server.visual.chat.events.ChatEvents;
import org.heavenmc.heavenmc.server.visual.hub_bossbar.Manager;
import org.heavenmc.heavenmc.server.worldguard.events.Events;

public class EventsLoader {
    public static void enableEvents(MainClass plugin)
    {
         /*
            Tab events
         */
        plugin.getServer().getPluginManager().registerEvents(new org.heavenmc.heavenmc.server.visual.tab.events.Events(), plugin);

        /*
            Player account manager + join adder
         */
        plugin.getServer().getPluginManager().registerEvents(new PlayerAccountManager(), plugin);

        /*
            Register economy function placeholder api
         */
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Economy().register();
        }

        /*
            Main scoreboard manager
         */
        plugin.getServer().getPluginManager().registerEvents(new org.heavenmc.heavenmc.server.visual.scoreboard.main.Events(), plugin);
        org.heavenmc.heavenmc.server.visual.scoreboard.main.Events.boardUpdateLoop();

        /*
            WorldGuardian system
         */
        //plugin.getServer().getPluginManager().registerEvents(new Events(), plugin);

        /*
            Hub instances
         */
        plugin.getServer().getPluginManager().registerEvents(new HubInstancesEvents(), plugin);

        /*
            BossBar Manager looper
         */
        plugin.getServer().getPluginManager().registerEvents(new Manager(), plugin);
        Manager.loopBossBar();

        // VERIFYING PLACEHOLDER API
        Bukkit.getConsoleSender().sendMessage(plugin.prefix+" §rVerifying if the plugin PlaceholderAPI is installed.. (§aSEARCHING§r)");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getConsoleSender().sendMessage(plugin.prefix+" §rPlugin founded and loaded, loading events.. (§cLOADING§r)");
            plugin.getServer().getPluginManager().registerEvents(new ChatEvents(), plugin); // Chat events (rank, suffix, ..) - REQUIRE PLACEHOLDER API
            Bukkit.getConsoleSender().sendMessage(plugin.prefix+" §rPlaceholderAPI events loaded! (§aSUCCESS§r)");
        } else {
            Bukkit.getConsoleSender().sendMessage(plugin.prefix+" §rPlaceholderAPI not found, disabling events using this api. (§eWARNING§r)");
        }
    }
}
