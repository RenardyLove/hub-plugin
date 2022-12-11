package org.heavenmc.heavenmc.server.visual.inventorys;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.heavenmc.heavenmc.utils.ItemBuilder;

import java.awt.*;

public class MenuDesJeuxGUI {

    public static Inventory getInventory(Player player) {

        Inventory inv = Bukkit.createInventory(null, 54, "§2Menu des jeux");

        Integer[] BlackGlass = { 1, 10, 19, 28, 37, 46 };
        Integer[] LimeGlass = { 2,3,11,7,8,17,44,52,53,38,47,48 };
        Integer[] Barrier = { 23, 31, 33 };
        Integer[] LightGrayGlass = { 0, 4, 5, 6, 12, 13, 14, 15, 16, 20, 21, 25, 26, 39, 40, 41, 42, 43, 45, 49, 50, 51 };

        for(Integer i : BlackGlass) {
            inv.setItem(i, ItemBuilder.createItem(Material.BLACK_STAINED_GLASS_PANE, " ", 1, null, false, null, 0, false));
        }
        for(Integer i : LimeGlass) {
            inv.setItem(i, ItemBuilder.createItem(Material.LIME_STAINED_GLASS_PANE, " ", 1, null, false, null, 0, false));
        }
        for(Integer i : Barrier) {
            inv.setItem(i, ItemBuilder.createItem(Material.BARRIER, "§cProchainement", 1, null, false, null, 0, false));
        }
        for(Integer i : LightGrayGlass) {
            inv.setItem(i, ItemBuilder.createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", 1, null, false, null, 0, false));
        }

        inv.setItem(22, ItemBuilder.createItem(Material.BRICKS, "§aFreeCube", 1, null, false, null, 0, false));
        inv.setItem(24, ItemBuilder.createItem(Material.FEATHER, "§cSkyWars", 1, null, false, null, 0, false));
        inv.setItem(32, ItemBuilder.createItem(Material.WOODEN_SWORD, "§4Murder Mystery", 1, null, false, null, 0, false));

        inv.setItem(9, ItemBuilder.createHead("§dTikTok", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmNmMjEwNWJiNzM3NjM4ODMzMDMzZGQ4MjQ0MDcxZTc1ODcwZTJlMTFjMjYxN2U1NDJlODkyNGZiMmI5MDE4MCJ9fX0=", 1, null, false, null, 0, false));
        inv.setItem(18, ItemBuilder.createHead("§aVote", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDczZTk4M2ViZmRmM2QwMzdiZjUxN2JlNmIxZjY2ZWExNjAzZjdjNWJkMmRhNjQ4NGM4ZGFiMTk1OTc5NjJiNiJ9fX0=", 1, null, false, null, 0, false));
        inv.setItem(27, ItemBuilder.createHead("§bDiscord", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg3M2MxMmJmZmI1MjUxYTBiODhkNWFlNzVjNzI0N2NiMzlhNzVmZjFhODFjYmU0YzhhMzliMzExZGRlZGEifX19", 1, null, false, null, 0, false));
        inv.setItem(36, ItemBuilder.createHead("§5Instagram", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBkNDY0MTg2ZTFhNTBkZGFhMTRiZTIyNTk2MTFhNGU4NDU4NTE1YTUzNjdhOTM4OWE5Y2M3Yzg5Yzk0YTkzYiJ9fX0=", 1, null, false, null, 0, false));


        return inv;
    }


}
