package org.heavenmc.heavenmc.server.visual.scoreboard.main;

import fr.mrmicky.fastboard.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.heavenmc.heavenmc.MainClass;

public class Events implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FastBoard board = new FastBoard(player);
        board.updateTitle("§e§lHeavenMC");
        MainClass.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        FastBoard board = MainClass.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    public static void boardUpdateLoop()
    {
        MainClass.getPlugin().getServer().getScheduler().runTaskTimer(MainClass.getPlugin(), () -> {
            for (FastBoard board : MainClass.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    private static void updateBoard(FastBoard board) {
        board.updateLines(
                "",
                " §6▶ Profil",
                "  §e◆ Joueur : §7"+board.getPlayer().getName(),
                "  §e◆ Grade : "+ ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(board.getPlayer(), "%luckperms_prefix%")),
                "  §e◆ Crystals : §7"+PlaceholderAPI.setPlaceholders(board.getPlayer(), "%economy_crystals%")+" ✦",
                "  §e◆ StarDust : §7"+PlaceholderAPI.setPlaceholders(board.getPlayer(), "%economy_stardust%")+" ☆",
                "",
                " §6▶ Serveur",
                "  §e◆ Joueurs total : §7"+ Bukkit.getOnlinePlayers().size(),
                "",
                " §6play.heavenmc.com"
        );
    }
}
