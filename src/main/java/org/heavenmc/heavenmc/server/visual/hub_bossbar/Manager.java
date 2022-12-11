package org.heavenmc.heavenmc.server.visual.hub_bossbar;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.server.configs.ActionBarConfig;
import org.heavenmc.heavenmc.utils.FileManager;

import java.util.List;
import java.util.Random;

public class Manager implements Listener {
    private static final FileManager.Config actionBarConfig = ActionBarConfig.getLocation();

    private static String nmsver;
    private static boolean canChange = true;
    private static boolean init = false;
    private static int lastChosen;

    private static BossBar bossBar;

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Audience p = MainClass.adventure().player(e.getPlayer());
        p.showBossBar(bossBar);
    }

    public static void loopBossBar()
    {
        if(canChange) {
            int r = 0;
            List<String> s = actionBarConfig.get().getStringList("actionbar");
            while (r==lastChosen)
            {
                r = new Random().nextInt(s.size());
            }
            String chosen = s.get(r);
            lastChosen=r;
            if (isBossBar()) {
                updateBossBar(chosen, BossBar.Color.BLUE);
            } else {
                setBossBar("Loading..", BossBar.Color.RED);
            }
            canChange = false;
        }

        if(!init)
        {
            for(Player player : Bukkit.getOnlinePlayers())
            {
                Audience p = MainClass.adventure().player(player);
                p.showBossBar(bossBar);
            }
            init=true;
            return;
        }
    }

    public static void setBossBar(final String startMessage, BossBar.Color color) {
        final Component name = Component.text(startMessage);
        bossBar = BossBar.bossBar(name, 1.0f, color, BossBar.Overlay.PROGRESS);
        canChange=true;
        loopBossBar();
    }

    public static void updateBossBar(final String message, BossBar.Color color)
    {
        if(isBossBar())
        {
            bossBar.name(Component.text(message));
            bossBar.color(color);
            final int[] countDown = {1000};
            new BukkitRunnable(){
                @Override
                public void run() {
                    countDown[0]--;
                    if (countDown[0] <= 0 || bossBar.progress() - 0.01f <= 0.00f) {
                        canChange=true;
                        bossBar.progress(1.0f);
                        loopBossBar();
                        cancel();
                    }
                    bossBar.progress(bossBar.progress() - 0.01f);
                }
            }.runTaskTimerAsynchronously(MainClass.getPlugin(), 0, 2);
        } else {
            setBossBar("Loading", BossBar.Color.WHITE);
        }
    }

    public static boolean isBossBar()
    {
        if(bossBar==null) return false;
        return true;
    }
}
