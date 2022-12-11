package org.heavenmc.heavenmc.server.worldguard.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Events implements Listener {

    /*
        Check if player can break block
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        if(!e.getPlayer().hasPermission("worldguardian.block.break"))
        {
            e.setCancelled(true);
        }
    }

    /*
        Check if player can place block
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        if(!e.getPlayer().hasPermission("worldguardian.block.place"))
        {
            e.setCancelled(true);
        }
    }
}
