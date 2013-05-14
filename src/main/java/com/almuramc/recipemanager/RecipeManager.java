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
		config.addDefault("ForceDefaultCape", true);
		config.addDefault("DefaultCape", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/spoutcape.png");
		config.options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getDataFolder().mkdir();
		for (AccessoryType ttype : AccessoryType.values()) {
			List<WebAccessory> aval = getAvailable(ttype);
			for (WebAccessory wa : aval) {
				SpoutManager.getFileManager().addToPreLoginCache(this, wa.getUrl());
			}
		}
			
		hotkeys = config.getString("Hot_Key");
		SpoutManager.getKeyBindingManager().registerBinding("PlayerPlus", Keyboard.valueOf(RecipeManager.hotkeys), "Opens Player Plus Accessories", new InputHandler(), RecipeManager.getInstance());
	}

	public List<WebAccessory> getAvailable(AccessoryType type) {
		// Accessories
		List<WebAccessory> toRet = new ArrayList<WebAccessory>();		
		File adr = new File(getDataFolder(), type.toString().toLowerCase() + ".yml");
		if (!adr.exists()) {
			try {
				adr.createNewFile();
				YamlConfiguration temp = YamlConfiguration.loadConfiguration(adr);				
				if (type.toString().equalsIgnoreCase("bracelet")) {					
					temp.addDefault("Bracelet - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-4.png");
					temp.addDefault("Bracelet - 2", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-7.png");
					temp.addDefault("Bracelet - 3", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-24.png");
					temp.addDefault("Bracelet - 4", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-28.png");
					temp.addDefault("Bracelet - 5", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-31.png");
					temp.addDefault("Bracelet - 6", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-92.png");
					temp.addDefault("Bracelet - 7", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-97.png");
					temp.addDefault("Bracelet - 8", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-102.png");
					temp.addDefault("Bracelet - 9", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-204.png");
					temp.addDefault("Bracelet - 10", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-296.png");
					temp.addDefault("Bracelet - 11", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-504.png");
					temp.addDefault("Bracelet - 12", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-508.png");
					temp.addDefault("Bracelet - 13", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-524.png");
					temp.addDefault("Bracelet - 14", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-597.png");
					temp.addDefault("Bracelet - 15", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-608.png");
					temp.addDefault("Bracelet - 16", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-618.png");
					temp.addDefault("Bracelet - 17", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-651.png");
					temp.addDefault("Bracelet - 18", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-738.png");
					temp.addDefault("Bracelet - 19", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-856.png");
					temp.addDefault("Bracelet - 20", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-960.png");
					temp.addDefault("Bracelet - 21", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1110.png");
					temp.addDefault("Bracelet - 22", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1256.png");
					temp.addDefault("Bracelet - 23", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1287.png");
					temp.addDefault("Bracelet - 24", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1314.png");
					temp.addDefault("Bracelet - 25", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1407.png");
					temp.addDefault("Bracelet - 26", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1448.png");
					temp.addDefault("Bracelet - 27", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1501.png");
					temp.addDefault("Bracelet - 28", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1513.png");
					temp.addDefault("Bracelet - 29", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1576.png");
					temp.addDefault("Bracelet - 30", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1617.png");
					temp.addDefault("Bracelet - 31", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1728.png");
					temp.addDefault("Bracelet - 32", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1756.png");
					temp.addDefault("Bracelet - 33", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1775.png");
					temp.addDefault("Bracelet - 34", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1777.png");
					temp.addDefault("Bracelet - 35", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1786.png");
					temp.addDefault("Bracelet - 36", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1796.png");
					temp.addDefault("Bracelet - 37", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1805.png");
					temp.addDefault("Bracelet - 38", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1806.png");
					temp.addDefault("Bracelet - 39", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1808.png");
					temp.addDefault("Bracelet - 40", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1810.png");
					temp.addDefault("Bracelet - 41", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1815.png");
					temp.addDefault("Bracelet - 42", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1816.png");
					temp.addDefault("Bracelet - 43", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1817.png");
					temp.addDefault("Bracelet - 44", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1819.png");
					temp.addDefault("Bracelet - 45", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1821.png");
					temp.addDefault("Bracelet - 46", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1826.png");
					temp.addDefault("Bracelet - 47", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1832.png");
					temp.addDefault("Bracelet - 48", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1833.png");
					temp.addDefault("Bracelet - 49", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1835.png");
					temp.addDefault("Bracelet - 50", "http://dl.dropbox.com/u/37060654/AlmuraDev/bracelet/pp-bc-1836.png");										
				}
				if (type.toString().equalsIgnoreCase("ears")) {					
					temp.addDefault("Earring - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-21.png");
					temp.addDefault("Earring - 2", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-23.png");
					temp.addDefault("Earring - 3", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-27.png");
					temp.addDefault("Earring - 4", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-46.png");
					temp.addDefault("Earring - 5", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-224.png");
					temp.addDefault("Earring - 6", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-229.png");
					temp.addDefault("Earring - 7", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-442.png");
					temp.addDefault("Earring - 8", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-467.png");
					temp.addDefault("Earring - 9", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-525.png");
					temp.addDefault("Earring - 10", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-571.png");
					temp.addDefault("Earring - 11", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-708.png");
					temp.addDefault("Earring - 12", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-749.png");
					temp.addDefault("Earring - 13", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-778.png");
					temp.addDefault("Earring - 14", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-810.png");
					temp.addDefault("Earring - 15", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-821.png");
					temp.addDefault("Earring - 16", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1120.png");
					temp.addDefault("Earring - 17", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1154.png");
					temp.addDefault("Earring - 18", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1165.png");
					temp.addDefault("Earring - 19", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1167.png");
					temp.addDefault("Earring - 20", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1202.png");
					temp.addDefault("Earring - 21", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1239.png");
					temp.addDefault("Earring - 22", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1257.png");
					temp.addDefault("Earring - 23", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1286.png");
					temp.addDefault("Earring - 24", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1454.png");
					temp.addDefault("Earring - 25", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1463.png");
					temp.addDefault("Earring - 26", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1568.png");
					temp.addDefault("Earring - 27", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1576.png");
					temp.addDefault("Earring - 28", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1599.png");
					temp.addDefault("Earring - 29", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1601.png");
					temp.addDefault("Earring - 30", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1602.png");
					temp.addDefault("Earring - 31", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1604.png");
					temp.addDefault("Earring - 32", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1613.png");
					temp.addDefault("Earring - 33", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1619.png");
					temp.addDefault("Earring - 34", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1621.png");
					temp.addDefault("Earring - 35", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1628.png");
					temp.addDefault("Earring - 36", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1636.png");
					temp.addDefault("Earring - 37", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1637.png");
					temp.addDefault("Earring - 38", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1640.png");
					temp.addDefault("Earring - 39", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1645.png");
					temp.addDefault("Earring - 40", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1649.png");
					temp.addDefault("Earring - 41", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1654.png");
					temp.addDefault("Earring - 42", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1658.png");
					temp.addDefault("Earring - 43", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1661.png");
					temp.addDefault("Earring - 44", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1667.png");
					temp.addDefault("Earring - 45", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1668.png");
					temp.addDefault("Earring - 46", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1669.png");
					temp.addDefault("Earring - 47", "http://dl.dropbox.com/u/37060654/AlmuraDev/ears/pp-er-1670.png");								
				}
				if (type.toString().equalsIgnoreCase("notchhat")) {
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-1.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-48.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-59.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-182.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-339.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-401.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-505.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-583.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-610.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-680.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-754.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-797.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-801.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-813.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-817.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-819.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-823.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-827.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-839.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-841.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-842.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-844.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-852.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-853.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-858.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-1487.png");
					temp.addDefault("Notch Hat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/notchhat/pp-nh-1496.png");					
				}
				if (type.toString().equalsIgnoreCase("sunglasses")) {
					temp.addDefault("Black - Sunglass", "http://dl.dropbox.com/u/37060654/AlmuraDev/sunglasses/sun_black_1.png");
					temp.addDefault("Blue - Sunglass", "http://dl.dropbox.com/u/37060654/AlmuraDev/sunglasses/sun_blue_1.png");
					temp.addDefault("Green - Sunglass", "http://dl.dropbox.com/u/37060654/AlmuraDev/sunglasses/sun_green_1.png");
					temp.addDefault("Red - Sunglass", "http://dl.dropbox.com/u/37060654/AlmuraDev/sunglasses/sun_red_1.png");
					temp.addDefault("White - Sunglass", "http://dl.dropbox.com/u/37060654/AlmuraDev/sunglasses/sun_white_1.png");
				}
				if (type.toString().equalsIgnoreCase("tail")) {
					temp.addDefault("Tail - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-39.png");
					temp.addDefault("Tail - 2", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-41.png");
					temp.addDefault("Tail - 3", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-49.png");
					temp.addDefault("Tail - 4", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-63.png");
					temp.addDefault("Tail - 5", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-79.png");
					temp.addDefault("Tail - 6", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-98.png");
					temp.addDefault("Tail - 7", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-210.png");
					temp.addDefault("Tail - 8", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-307.png");
					temp.addDefault("Tail - 9", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-347.png");
					temp.addDefault("Tail - 10", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-349.png");
					temp.addDefault("Tail - 11", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-552.png");
					temp.addDefault("Tail - 12", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-613.png");
					temp.addDefault("Tail - 13", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-629.png");
					temp.addDefault("Tail - 14", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-733.png");
					temp.addDefault("Tail - 15", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-776.png");
					temp.addDefault("Tail - 16", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-813.png");
					temp.addDefault("Tail - 17", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-838.png");
					temp.addDefault("Tail - 18", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-841.png");
					temp.addDefault("Tail - 19", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-915.png");
					temp.addDefault("Tail - 20", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-975.png");
					temp.addDefault("Tail - 21", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1047.png");
					temp.addDefault("Tail - 22", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1083.png");
					temp.addDefault("Tail - 23", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1165.png");
					temp.addDefault("Tail - 24", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1169.png");
					temp.addDefault("Tail - 25", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1170.png");
					temp.addDefault("Tail - 26", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1171.png");
					temp.addDefault("Tail - 27", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1182.png");
					temp.addDefault("Tail - 28", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1187.png");
					temp.addDefault("Tail - 29", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1191.png");
					temp.addDefault("Tail - 30", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1197.png");
					temp.addDefault("Tail - 31", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1198.png");
					temp.addDefault("Tail - 32", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1202.png");
					temp.addDefault("Tail - 33", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1205.png");
					temp.addDefault("Tail - 34", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1206.png");
					temp.addDefault("Tail - 35", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1217.png");
					temp.addDefault("Tail - 36", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1227.png");
					temp.addDefault("Tail - 37", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1229.png");
					temp.addDefault("Tail - 38", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1235.png");
					temp.addDefault("Tail - 39", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1282.png");
					temp.addDefault("Tail - 40", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1297.png");
					temp.addDefault("Tail - 41", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1301.png");
					temp.addDefault("Tail - 42", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1320.png");
					temp.addDefault("Tail - 43", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1322.png");
					temp.addDefault("Tail - 44", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1379.png");
					temp.addDefault("Tail - 45", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1394.png");
					temp.addDefault("Tail - 46", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1400.png");
					temp.addDefault("Tail - 47", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1411.png");
					temp.addDefault("Tail - 48", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1431.png");
					temp.addDefault("Tail - 49", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1434.png");
					temp.addDefault("Tail - 50", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1453.png");
					temp.addDefault("Tail - 51", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1458.png");
					temp.addDefault("Tail - 52", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1569.png");
					temp.addDefault("Tail - 53", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1572.png");
					temp.addDefault("Tail - 54", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1575.png");
					temp.addDefault("Tail - 55", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1576.png");
					temp.addDefault("Tail - 56", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1577.png");
					temp.addDefault("Tail - 57", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1579.png");
					temp.addDefault("Tail - 58", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1584.png");
					temp.addDefault("Tail - 59", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1593.png");
					temp.addDefault("Tail - 60", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1594.png");
					temp.addDefault("Tail - 61", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1612.png");
					temp.addDefault("Tail - 62", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1614.png");
					temp.addDefault("Tail - 63", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1618.png");
					temp.addDefault("Tail - 64", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1622.png");
					temp.addDefault("Tail - 65", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1628.png");
					temp.addDefault("Tail - 66", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1629.png");
					temp.addDefault("Tail - 67", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1631.png");
					temp.addDefault("Tail - 68", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1632.png");
					temp.addDefault("Tail - 69", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1633.png");
					temp.addDefault("Tail - 70", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1635.png");
					temp.addDefault("Tail - 71", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1637.png");
					temp.addDefault("Tail - 72", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1644.png");
					temp.addDefault("Tail - 73", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1646.png");
					temp.addDefault("Tail - 74", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1648.png");
					temp.addDefault("Tail - 75", "http://dl.dropbox.com/u/37060654/AlmuraDev/tail/pp-tl-1654.png");					
				}
				if (type.toString().equalsIgnoreCase("tophat")) {
					temp.addDefault("Tophat - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-9.png");
					temp.addDefault("Tophat - 2", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-16.png");
					temp.addDefault("Tophat - 3", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-23.png");
					temp.addDefault("Tophat - 4", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-46.png");
					temp.addDefault("Tophat - 5", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-63.png");
					temp.addDefault("Tophat - 6", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-75.png");
					temp.addDefault("Tophat - 7", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-98.png");
					temp.addDefault("Tophat - 8", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-127.png");
					temp.addDefault("Tophat - 9", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-145.png");
					temp.addDefault("Tophat - 10", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-227.png");
					temp.addDefault("Tophat - 11", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-304.png");
					temp.addDefault("Tophat - 12", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-347.png");
					temp.addDefault("Tophat - 13", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-593.png");
					temp.addDefault("Tophat - 14", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-595.png");
					temp.addDefault("Tophat - 15", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-667.png");
					temp.addDefault("Tophat - 16", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-824.png");
					temp.addDefault("Tophat - 17", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1102.png");
					temp.addDefault("Tophat - 18", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1173.png");
					temp.addDefault("Tophat - 19", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1222.png");
					temp.addDefault("Tophat - 20", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1274.png");
					temp.addDefault("Tophat - 21", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1310.png");
					temp.addDefault("Tophat - 22", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1389.png");
					temp.addDefault("Tophat - 23", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1455.png");
					temp.addDefault("Tophat - 24", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1457.png");
					temp.addDefault("Tophat - 25", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1461.png");
					temp.addDefault("Tophat - 26", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1466.png");
					temp.addDefault("Tophat - 27", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1476.png");
					temp.addDefault("Tophat - 28", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1488.png");
					temp.addDefault("Tophat - 29", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1490.png");
					temp.addDefault("Tophat - 30", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1493.png");
					temp.addDefault("Tophat - 31", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1497.png");
					temp.addDefault("Tophat - 32", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1502.png");
					temp.addDefault("Tophat - 33", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1503.png");
					temp.addDefault("Tophat - 34", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1504.png");
					temp.addDefault("Tophat - 35", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1506.png");
					temp.addDefault("Tophat - 36", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1507.png");
					temp.addDefault("Tophat - 37", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1510.png");
					temp.addDefault("Tophat - 38", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1516.png");
					temp.addDefault("Tophat - 39", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1517.png");
					temp.addDefault("Tophat - 40", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1518.png");
					temp.addDefault("Tophat - 41", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1521.png");
					temp.addDefault("Tophat - 42", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1524.png");
					temp.addDefault("Tophat - 43", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1525.png");
					temp.addDefault("Tophat - 44", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1528.png");
					temp.addDefault("Tophat - 45", "http://dl.dropbox.com/u/37060654/AlmuraDev/tophat/pp-th-1666.png");				
				}
				if (type.toString().equalsIgnoreCase("wings")) {
					temp.addDefault("Wings - 1", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-9.png");
					temp.addDefault("Wings - 2", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-10.png");
					temp.addDefault("Wings - 3", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-12.png");
					temp.addDefault("Wings - 4", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-30.png");
					temp.addDefault("Wings - 5", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-40.png");
					temp.addDefault("Wings - 6", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-42.png");
					temp.addDefault("Wings - 7", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-122.png");
					temp.addDefault("Wings - 8", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-148.png");
					temp.addDefault("Wings - 9", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-202.png");
					temp.addDefault("Wings - 10", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-275.png");
					temp.addDefault("Wings - 11", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-356.png");
					temp.addDefault("Wings - 12", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-389.png");
					temp.addDefault("Wings - 13", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-424.png");
					temp.addDefault("Wings - 14", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-442.png");
					temp.addDefault("Wings - 15", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-818.png");
					temp.addDefault("Wings - 16", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-924.png");
					temp.addDefault("Wings - 17", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-1204.png");
					temp.addDefault("Wings - 18", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-1237.png");
					temp.addDefault("Wings - 19", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-1421.png");
					temp.addDefault("Wings - 20", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-1458.png");
					temp.addDefault("Wings - 21", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-1630.png");
					temp.addDefault("Wings - 22", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-1976.png");
					temp.addDefault("Wings - 23", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2111.png");
					temp.addDefault("Wings - 24", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2124.png");
					temp.addDefault("Wings - 25", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2546.png");
					temp.addDefault("Wings - 26", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2581.png");
					temp.addDefault("Wings - 27", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2591.png");
					temp.addDefault("Wings - 28", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2594.png");
					temp.addDefault("Wings - 29", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2614.png");
					temp.addDefault("Wings - 30", "http://dl.dropbox.com/u/37060654/AlmuraDev/wings/pp-wg-2615.png");					
				}
				temp.options().copyDefaults(true);
				temp.save(adr);
			} catch (IOException ex) {
				Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		YamlConfiguration ycf = YamlConfiguration.loadConfiguration(adr);
		for (String name : ycf.getKeys(false)) {
			toRet.add(new WebAccessory(name, ycf.getString(name)));
		}

		return toRet;
	}

	public List<WebAccessory> getCapes() {
		// Capes, to be used until Capes are moved into the AccessoryType Class
		List<WebAccessory> toRet = new ArrayList<WebAccessory>();		
		File adr = new File(getDataFolder(), "capes.yml");
		if (!adr.exists()) {
			try {
				adr.createNewFile();
				YamlConfiguration temp = YamlConfiguration.loadConfiguration(adr);				
				temp.addDefault("Blue Cow", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/bluecow.png");
				temp.addDefault("Diamond Pick", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/diamondpick.png");
				temp.addDefault("Flame Creeper", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/flamecreeper.png");
				temp.addDefault("Gold Sword", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/goldsward.png");
				temp.addDefault("Grassy", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/grass.png");
				temp.addDefault("Happy Face", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/happyface.png");
				temp.addDefault("Mario Cape", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/mariocape.png");
				temp.addDefault("Pig King", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/pigking.png");
				temp.addDefault("Shield", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/shield.png");
				temp.addDefault("Shroomhead", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/shroomhead.png");
				temp.addDefault("Spout Cape", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/spoutcape.png");
				temp.addDefault("Striped", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/striped.png");
				temp.addDefault("Stripe Dot", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/stripedot.png");
				temp.addDefault("Torch", "http://dl.dropbox.com/u/37060654/AlmuraDev/capes/torch.png");
				temp.options().copyDefaults(true);
				temp.save(adr);
			} catch (IOException ex) {
				Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		YamlConfiguration ycf = YamlConfiguration.loadConfiguration(adr);
		for (String name : ycf.getKeys(false)) {
			toRet.add(new WebAccessory(name, ycf.getString(name)));
		}

		return toRet;
	}

	@EventHandler
	public void onSpoutcraftAuth(SpoutCraftEnableEvent event) {
		SpoutPlayer sPlayer = event.getPlayer();
	
		if (!sPlayer.hasPermission("PlayerPlus.use")) {
			for(AccessoryType ttype : AccessoryType.values()) {
				String url = get(event.getPlayer().getName(), ttype);		
				sPlayer.removeAccessory(ttype);
			}
		}

		if (sPlayer.hasPermission("PlayerPlus.use")) {
			for(AccessoryType ttype : AccessoryType.values()) {
				String url = get(event.getPlayer().getName(), ttype);
				if (event.getPlayer().hasPermission("PlayerPlus.use." + ttype.toString())) {
					if(url != null) {
						event.getPlayer().addAccessory(ttype, url);				
					}	
				} else {
					sPlayer.removeAccessory(ttype);					
				}			
			}
			
			if (event.getPlayer().hasPermission("PlayerPlus.use.capes")) {				
				String url = getCape(event.getPlayer().getName(), "CAPES");
				if (url != null) {
					sPlayer.setCape(url);
				} else {
					if (RecipeManager.getInstance().getConfig().getBoolean("ForceDefaultCape")) {
						sPlayer.setCape(RecipeManager.getInstance().getConfig().getString("DefaultCape"));
					}
				}
			} else {
				sPlayer.resetCape();
			}
		}	
	}

	public String get(String player, AccessoryType type) {
		File saveFile = new File(getDataFolder(), "saved.yml");
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		YamlConfiguration yFile = YamlConfiguration.loadConfiguration(saveFile);
		return yFile.getString(player+"."+type.name());
	}

	public String getCape(String player, String type) {
		File saveFile = new File(getDataFolder(), "saved.yml");
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		YamlConfiguration yFile = YamlConfiguration.loadConfiguration(saveFile);
		return yFile.getString(player+"."+"CAPES");
	}

	public void save(SpoutPlayer player, AccessoryType type) {
		File saveFile = new File(getDataFolder(), "saved.yml");
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		YamlConfiguration yFile = YamlConfiguration.loadConfiguration(saveFile);
		yFile.set(player.getName()+"."+type.name()+"", player.getAccessoryURL(type));
		try {
			yFile.save(saveFile);
		} catch (IOException ex) {
			Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void saveCape(SpoutPlayer player) {
		File saveFile = new File(getDataFolder(), "saved.yml");
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		YamlConfiguration yFile = YamlConfiguration.loadConfiguration(saveFile);
		yFile.set(player.getName()+"."+"CAPES", player.getCape());
		try {
			yFile.save(saveFile);
		} catch (IOException ex) {
			Logger.getLogger(RecipeManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
