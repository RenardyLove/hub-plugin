package org.heavenmc.heavenmc.server.commands.player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.heavenmc.heavenmc.MainClass;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        Player p = (Player) sender;
        TextComponent tc = new TextComponent(MainClass.getPlugin().prefix + " §bhttps://discord.gg/R4cnwHSetb");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/R4cnwHSetb"));
        p.spigot().sendMessage(tc);

        return false;
    }
}
