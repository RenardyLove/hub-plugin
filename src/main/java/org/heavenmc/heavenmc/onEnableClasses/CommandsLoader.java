package org.heavenmc.heavenmc.onEnableClasses;

import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.server.commands.player.DiscordCommand;
import org.heavenmc.heavenmc.server.commands.player.FlyCommand;
import org.heavenmc.heavenmc.server.commands.player.ForumCommand;
import org.heavenmc.heavenmc.server.commands.player.ReseauxCommand;

import java.util.Objects;

public class CommandsLoader {
    public static void enableCommands(MainClass plugin)
    {
        /*
            Fly command
         */
        Objects.requireNonNull(plugin.getCommand("fly")).setExecutor(new FlyCommand());

         /*
            Forum and discord command
         */
        Objects.requireNonNull(plugin.getCommand("forum")).setExecutor(new ForumCommand());
        Objects.requireNonNull(plugin.getCommand("discord")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(plugin.getCommand("reseaux")).setExecutor(new ReseauxCommand());
    }
}

