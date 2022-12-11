package org.heavenmc.heavenmc.server.commands.player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.heavenmc.heavenmc.MainClass;

public class ForumCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        Player p = (Player) sender;
        TextComponent tc = new TextComponent(MainClass.getPlugin().prefix + " §bhttps://heavenmc.fr/forum");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://heavenmc.fr/forum"));
        p.spigot().sendMessage(tc);

        return false;
    }
}
