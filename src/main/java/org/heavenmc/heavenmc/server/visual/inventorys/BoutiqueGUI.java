package org.heavenmc.heavenmc.server.visual.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.heavenmc.heavenmc.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class BoutiqueGUI {
    public static Inventory getInventory(Player player)
    {
        Inventory gui = Bukkit.createInventory(null, 45, "§eBoutique");
        List<String> lore = new ArrayList<String>();

        Integer[] blue_part = {0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44};
        Integer[] black_part = {2, 3, 4, 5, 6, 10, 16, 18, 26, 28, 34, 38, 39, 40, 41, 42};

        for(int i : blue_part)
        {
            gui.setItem(i, ItemBuilder.createItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, " ", 1, null, false, null, 0, false));
        }
        for(int i : black_part)
        {
            gui.setItem(i, ItemBuilder.createItem(Material.BLACK_STAINED_GLASS_PANE, " ", 1, null, false, null, 0, false));
        }

        if(!player.hasPermission("mvp"))
        {
            lore.add(" ");
            lore.add("§7Prix : §b375 ✦ §7(§f§l-25%§7)");
            lore.add("§7Aperçu : §9MVP " + player.getName());
            lore.add(" ");
            lore.add("§eClique-droit pour acheter ce grade");
            gui.setItem(20, ItemBuilder.createItem(Material.LAPIS_LAZULI, "§7Grade : §9MVP", 1, lore, false, null, 0, false));
        } else {
            lore.add(" ");
            lore.add("§7Prix : §b375 ✦ §7(§f§l-25%§7)");
            lore.add("§7Aperçu : §9MVP " + player.getName());
            lore.add(" ");
            lore.add("§cVous possèdez déjà ce grade !");
            gui.setItem(20, ItemBuilder.createItem(Material.LAPIS_LAZULI, "§7Grade : §9MVP", 1, lore, false, null, 0, false));
        }
        if(!player.hasPermission("vip"))
        {
            lore.clear();
            lore.add(" ");
            lore.add("§7Prix : §b750 ✦ §7(§f§l-25%§7)");
            lore.add("§7Aperçu : §eVIP " + player.getName());
            lore.add(" ");
            lore.add("§eClique-droit pour acheter ce grade");
            gui.setItem(21, ItemBuilder.createItem(Material.GOLD_INGOT, "§7Grade : §eVIP", 1, lore, false, null, 0, false));
        } else {
            lore.clear();
            lore.add(" ");
            lore.add("§7Prix : §b750 ✦ §7(§f§l-25%§7)");
            lore.add("§7Aperçu : §eVIP " + player.getName());
            lore.add(" ");
            lore.add("§cVous possèdez déjà ce grade !");
            gui.setItem(21, ItemBuilder.createItem(Material.GOLD_INGOT, "§7Grade : §eVIP", 1, lore, false, null, 0, false));
        }
        if(!player.hasPermission("hero"))
        {
            lore.clear();
            lore.add(" ");
            lore.add("§7Prix : §b1500 ✦ §7(§f§l-25%§7)");
            lore.add("§7Aperçu : §aHéro " + player.getName());
            lore.add(" ");
            lore.add("§eClique-droit pour acheter ce grade");
            gui.setItem(23, ItemBuilder.createItem(Material.EMERALD, "§7Grade : §aHéro", 1, lore, false, null, 0, false));
        } else {
            lore.clear();
            lore.add(" ");
            lore.add("§7Prix : §b1500 ✦ §7(§f§l-25%§7)");
            lore.add("§7Aperçu : §aHéro " + player.getName());
            lore.add(" ");
            lore.add("§cVous possèdez déjà ce grade !");
            gui.setItem(23, ItemBuilder.createItem(Material.EMERALD, "§7Grade : §aHéro", 1, lore, false, null, 0, false));
        }
        if(!player.hasPermission("hero+"))
        {
            if(player.hasPermission("hero"))
            {
                lore.clear();
                lore.add(" ");
                lore.add("§7Prix : §675000 ☆ §a+ grade Héro §7(§f§l-25%§7)");
                lore.add("§7Aperçu : §aHéro§2+§a " + player.getName());
                lore.add(" ");
                lore.add("§eClique-droit pour acheter ce grade");
                gui.setItem(24, ItemBuilder.createItem(Material.EMERALD, "§7Grade : §aHéro§2+§a", 1, lore, false, Enchantment.KNOCKBACK, 1, true));
            } else {
                lore.clear();
                lore.add(" ");
                lore.add("§7Prix : §675000 ☆ §a+ grade Héro §7(§f§l-25%§7)");
                lore.add("§7Aperçu : §aHéro§2+§a " + player.getName());
                lore.add(" ");
                lore.add("§cVous devez possèder le grade Héro pour acheter ce grade !");
                gui.setItem(24, ItemBuilder.createItem(Material.EMERALD, "§7Grade : §aHéro§2+§a", 1, lore, false, Enchantment.KNOCKBACK, 1, true));
            }
        } else {
            lore.clear();
            lore.add(" ");
            lore.add("§7Prix : §675000 ☆ §a+ grade Héro §7(§f§l-25%§7)");
            lore.add("§7Aperçu : §aHéro§2+§a " + player.getName());
            lore.add(" ");
            lore.add("§cVous possèdez déjà ce grade !");
            gui.setItem(24, ItemBuilder.createItem(Material.EMERALD, "§7Grade : §aHéro§2+§a", 1, lore, false, Enchantment.KNOCKBACK, 1, true));
        }

        return gui;
    }
}
