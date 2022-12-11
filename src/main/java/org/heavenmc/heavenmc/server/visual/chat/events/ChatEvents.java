package org.heavenmc.heavenmc.server.visual.chat.events;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {

    /*
        When player chats, rename rank,(...)
     */
    @EventHandler
    public void onChatAsync(AsyncPlayerChatEvent e)
    {
        String prefix = "%luckperms_prefix%";
        prefix = PlaceholderAPI.setPlaceholders(e.getPlayer(), prefix);
        String suffix = "%luckperms_suffix%";
        suffix = PlaceholderAPI.setPlaceholders(e.getPlayer(), suffix);
        e.setFormat(ChatColor.translateAlternateColorCodes('&', prefix + e.getPlayer().getDisplayName() + suffix + ":§f " + e.getMessage()));
    }

    /*
        When player joins, we cancel the quit message
        for bypassing spams.
     */
    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent e)
    {
        e.setQuitMessage(""); // Cancel leave message event
    }
}
