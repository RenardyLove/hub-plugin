package org.heavenmc.heavenmc.server.commands.player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.heavenmc.heavenmc.MainClass;
import org.jetbrains.annotations.NotNull;

public class ReseauxCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        Player p = (Player) sender;

        //ALL
        p.sendMessage(MainClass.getPlugin().prefix + " §6§nVoici la list des réseaux :");
        p.sendMessage(" ");


        //DISCORD
        TextComponent tc = new TextComponent(" §eDiscord : §bhttps://discord.gg/R4cnwHSetb");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/R4cnwHSetb"));
        p.spigot().sendMessage(tc);

        //FORUM
        TextComponent tc2 = new TextComponent(" §eForum : §bhttps://heavenmc.fr/forum");
        tc2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://heavenmc.fr/forum"));
        p.spigot().sendMessage(tc2);

        //TIKTOK
        TextComponent tc3 = new TextComponent(" §eTikTok :  §bhttps://tiktok.fr");
        tc3.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://tiktok.fr"));
        p.spigot().sendMessage(tc3);

        //INSTAGRAM
        TextComponent tc4 = new TextComponent(" §eInstagram :  §bhttps://instagram.fr");
        tc4.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://instagram.fr"));
        p.spigot().sendMessage(tc4);

        return false;
    }
}
