package org.heavenmc.heavenmc.server.visual.tab.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = (Player) e.getPlayer();

        p.setPlayerListHeaderFooter("\n§b-- §f§l[ §b§lHeavenMC§f§l ] §b--\n\n  §7---------------------------  \n", "\n  §7---------------------------  \n\n§7Notre discord : §b/discord\n§7Une question ? §e/forum§7\n");

    }

}
