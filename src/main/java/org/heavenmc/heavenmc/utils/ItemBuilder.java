package org.heavenmc.heavenmc.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {


    /**
     createItem to create an item.
     @param material to choose your material.
     @param name to choose the name of your item.
     @param number to choose the number of your item.
     @param lore to add a lore.
     @param unbreakble make your item is unbreakable.
     @param enchantment add enchantement.
     @param hideEnchant to choose if your item hide or show enchantment.
     @param numberOfYourEnchantment to choose the number of your enchantment.
     */
    public static ItemStack createItem(Material material, String name, int number, List<String> lore, boolean unbreakble, Enchantment enchantment, int numberOfYourEnchantment, boolean hideEnchant) {

        ItemStack is = new ItemStack(material, number);
        ItemMeta im = is.getItemMeta();
        assert im != null;
        im.setDisplayName(name);
        im.setUnbreakable(unbreakble);
        im.setLore(lore);
        if(enchantment!=null && numberOfYourEnchantment>0) {
            im.addEnchant(enchantment, numberOfYourEnchantment, false);
        }
        if(hideEnchant) {
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        is.setItemMeta(im);

        return is;
    }

    public static ItemStack createPlayerHead(String name, UUID playerUUID, int number, List<String> lore, boolean unbreakble, Enchantment enchantment, int numberOfYourEnchantment, boolean hideEnchant) {

        ItemStack is = new ItemStack(Material.PLAYER_HEAD, number);
        SkullMeta im = (SkullMeta) is.getItemMeta();
        assert im != null;
        im.setDisplayName(name);
        im.setUnbreakable(unbreakble);
        im.setLore(lore);
        im.setOwningPlayer(Bukkit.getOfflinePlayer(playerUUID));
        if(enchantment!=null && numberOfYourEnchantment>0) {
            im.addEnchant(enchantment, numberOfYourEnchantment, false);
        }
        if(hideEnchant) {
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        is.setItemMeta(im);

        return is;
    }

    public static ItemStack createHead(String name, String base64, int number, List<String> lore, boolean unbreakble, Enchantment enchantment, int numberOfYourEnchantment, boolean hideEnchant) {

        ItemStack is = new ItemStack(Material.PLAYER_HEAD, number);
        if (base64 == null || base64.isEmpty())
            return is;
        SkullMeta skullMeta = (SkullMeta) is.getItemMeta();
        assert skullMeta != null;
        skullMeta.setDisplayName(name);
        skullMeta.setUnbreakable(unbreakble);
        skullMeta.setLore(lore);
        if(enchantment!=null && numberOfYourEnchantment>0) {
            skullMeta.addEnchant(enchantment, numberOfYourEnchantment, false);
        }
        if(hideEnchant) {
            skullMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        is.setItemMeta(skullMeta);

        return is;
    }

}
