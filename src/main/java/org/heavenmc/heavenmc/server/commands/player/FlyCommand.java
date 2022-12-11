package org.heavenmc.heavenmc.server.commands.player;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.utils.Prefixer;
import org.jetbrains.annotations.NotNull;

public class FlyCommand implements CommandExecutor {

    private final String prefix = Prefixer.getPrefix();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String[] args) {

        if(sender instanceof Player)
        {
            Player player = (Player) sender;

            if(!player.getAllowFlight())
            {
                player.setAllowFlight(true);
                player.sendMessage(prefix+" §7Vous §apouvez§7 dès maintenant voler !");
            } else if(player.getAllowFlight())
            {
                player.setAllowFlight(false);
                player.sendMessage(prefix+" §7Vous ne pouvez §cplus§7 voler à présent !");
            }

            return true;

        }

        return false;
    }
}
