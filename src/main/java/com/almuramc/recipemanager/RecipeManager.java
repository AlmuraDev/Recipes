/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almuramc.recipemanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.player.accessories.AccessoryType;
import org.getspout.spout.Spout;
import org.getspout.spout.player.SpoutCraftPlayer;

public class RecipeManager extends JavaPlugin implements Listener {

	private static RecipeManager instance;
	public static String hotkeys = null;

	public static RecipeManager getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {	
		instance = this;
		FileConfiguration config = this.getConfig();
		config.addDefault("PromptTitle", "Recipe Manager");
		config.addDefault("TitleX", 190);
		config.addDefault("Hot_Key", "KEY_U");
		config.addDefault("GUITexture", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/playerplus.png");		
		config.options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getDataFolder().mkdir();
		
		//TODO:  This needs to be modified to read in the images from /plugin/recipemanager/images/ so that spoutplugin gets precache info
		/*
		for (AccessoryType ttype : AccessoryType.values()) {
			List<WebAccessory> aval = getAvailable(ttype);
			for (WebAccessory wa : aval) {
				SpoutManager.getFileManager().addToPreLoginCache(this, wa.getUrl());
			}
		}
		*/			
		hotkeys = config.getString("Hot_Key");
		SpoutManager.getKeyBindingManager().registerBinding("Recipe Manager", Keyboard.valueOf(RecipeManager.hotkeys), "Opens Recipe Manager", new InputHandler(), RecipeManager.getInstance());
	}
}
