package org.heavenmc.heavenmc.server.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.server.visual.inventorys.BoutiqueGUI;
import org.heavenmc.heavenmc.server.visual.inventorys.MenuDesJeuxGUI;
import org.heavenmc.heavenmc.utils.ItemBuilder;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class HubInstancesEvents implements Listener {

    /*
        Initialize hub instances
     */
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e)
    {
        e.setFoodLevel(20);
    }
    @EventHandler
    public void onHunger(EntityDamageEvent e)
    {
        e.setCancelled(true);
    }
    @EventHandler
    public void onHunger(EntityRegainHealthEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            Player player = (Player) e.getEntity();
            player.setHealth(20D);
        }
    }
    @EventHandler
    public void onJoinPlayerInstances(PlayerJoinEvent e)
    {
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        e.getPlayer().setHealth(20D);
        e.getPlayer().setFoodLevel(20);
    }
    @EventHandler
    public void onJoinPlayerInstances(PlayerGameModeChangeEvent e)
    {
        if(!e.getNewGameMode().equals(GameMode.SURVIVAL))
        {
            e.setCancelled(true);
        }
    }

    /*
        Give player's item on join.
     */
    @EventHandler
    public void onJoinBarManager(PlayerJoinEvent e)
    {
        e.getPlayer().getInventory().clear();
        giveHotbarItems(e.getPlayer());
    }

    /*
        Prevent player from touch inventory's items.
     */
    @EventHandler
    public void onInventoryTouch(InventoryClickEvent e)
    {
        try
        {
            if(e.getView().getTitle().equals("Crafting"))
            {
                e.setCancelled(true);
            }
        } catch (NullPointerException ignored) {}
    }

    /*
        Prevent player from dropping items.
     */
    @EventHandler
    public void onDropItem(PlayerDropItemEvent e)
    {
        e.setCancelled(true);
    }

    /*
        Prevent entity from pickup 'items'.
     */
    @EventHandler
    public void onPickupEntity(EntityPickupItemEvent e)
    {
        e.setCancelled(true);
    }

    /*
        Prevent entity from spawning.
     */
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e)
    {
        e.setCancelled(true);
    }

    /*
        Player click with held items
     */
    @EventHandler
    public void onRightClickOnItem(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        try {
            switch (Objects.requireNonNull(e.getItem()).getType())
            {
                case RED_BANNER:
                    if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§7Poudre d'escampette [§cDÉSACTIVÉ§7]") || Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§7Poudre d'escampette [§aACTIVÉ§7]"))
                    {
                        p.sendMessage("§cCette fonctionnalité n'est pas encore disponible.");
                    }
                    break;

                case GOLD_INGOT:
                    if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§eBoutique"))
                    {
                        p.openInventory(BoutiqueGUI.getInventory(p));
                    }
                    break;

                case COMPASS:
                    if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§aMenu des jeux"))
                    {
                        p.openInventory(MenuDesJeuxGUI.getInventory(p));
                    }
                    break;

                case ENDER_CHEST:
                    if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§cCosmétiques"))
                    {
                        p.sendMessage("§cCette fonctionnalité n'est pas encore disponible.");
                    }
                    break;

                case PLAYER_HEAD:
                    if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§9Profil"))
                    {
                        p.sendMessage("§cCette fonctionnalité n'est pas encore disponible.");
                    }
                    break;

                default:
                    break;
            }
        } catch (NullPointerException ignored) {}
    }

    /*
        Giver function.
     */
    private void giveHotbarItems(Player player)
    {
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§7En un seul clique");
        lore.add("§7vous vous enfuyez pour qu'il");
        lore.add("§7ne reste que §avous§7 sur le hub !");
        lore.add(" ");
        lore.add("§eClique-droit pour rendre invisible les autres joueurs");
        player.getInventory().setItem(0, ItemBuilder.createItem(Material.RED_BANNER, "§7Poudre d'escampette [§cDÉSACTIVÉ§7]", 1, lore, false, null, 0, false));

        lore.clear();
        lore.add(" ");
        lore.add("§7Boutique complète, grades, boosts");
        lore.add("§7et bien d'autres permettant");
        lore.add("§7des §aavantages de gain§7 ou autres !");
        lore.add(" ");
        lore.add("§eClique-droit pour entrer dans la boutique");
        player.getInventory().setItem(2, ItemBuilder.createItem(Material.GOLD_INGOT, "§eBoutique", 1, lore, false, null, 0, false));

        lore.clear();
        lore.add(" ");
        lore.add("§7Choisissez le jeu auquel");
        lore.add("§7vous souhaiter jouer, et partez");
        lore.add("§7en aventure pour devenir un §ajoueur pro§7 !");
        lore.add(" ");
        lore.add("§eClique-droit pour entrer dans le menu");
        player.getInventory().setItem(4, ItemBuilder.createItem(Material.COMPASS, "§aMenu des jeux", 1, lore, false, null, 0, false));

        lore.clear();
        lore.add(" ");
        lore.add("§7Soyez tout habillé, prenez vos gadgets,");
        lore.add("§7vos animaux de compagnie");
        lore.add("§7pour être le plus §abeau§7 dans le hub !");
        lore.add(" ");
        lore.add("§eClique-droit pour entrer dans le menu des cosmétiques");
        player.getInventory().setItem(6, ItemBuilder.createItem(Material.ENDER_CHEST, "§cCosmétiques", 1, lore, false, null, 0, false));

        lore.clear();
        lore.add(" ");
        lore.add("§7Prennez le controle des paramètres,");
        lore.add("§7soyez au §acourant§7 de presque");
        lore.add("§7tout sur vous !");
        lore.add(" ");
        lore.add("§eClique-droit pour entrer dans le menu de votre profil");
        player.getInventory().setItem(8, ItemBuilder.createPlayerHead("§9Profil", UUID.fromString(player.getUniqueId().toString()),1, lore, false, null, 0, false));
    }
}
