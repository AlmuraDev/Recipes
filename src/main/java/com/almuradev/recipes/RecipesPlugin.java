/*
 * This file is part of Recipes.
 *
 * © 2013 AlmuraDev <http://www.almuradev.com/>
 * Recipes is licensed under the GNU General Public License.
 *
 * Recipes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Recipes is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License. If not,
 * see <http://www.gnu.org/licenses/> for the GNU General Public License.
 */
package com.almuradev.recipes;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.almuradev.recipes.delegate.InputHandler;
import com.almuradev.recipes.info.RecipeInfo;
import com.almuradev.recipes.io.Loader;
import com.almuradev.recipes.io.RecipesRegistry;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RecipesPlugin extends JavaPlugin {
	private final RecipesRegistry REGISTRY;
	private Loader loader;

	public RecipesPlugin() {
		REGISTRY = new RecipesRegistry();
	}

	@Override
	public void onEnable() {
		//Setup loader
		loader = new Loader(this);
		loader.onEnable();
		loader.load();
		//Setup config
		FileConfiguration config = this.getConfig();
		config.addDefault("PromptTitle", "Recipe Browser");
		config.addDefault("TitleX", 190);
		config.addDefault("Hot_Key", "KEY_U");
		config.addDefault("GUITexture", "http://www.pixentral.com/pics/1duZT49LzMnodP53SIPGIqZ8xdKS.png");
		config.options().copyDefaults(true);
		saveConfig();
		//Setup bindings
		try {
			SpoutManager.getKeyBindingManager().registerBinding("Recipes", Keyboard.valueOf(getConfig().getString("Hot_Key", "KEY_U")), "Opens Recipes Browser", new InputHandler(this), this);
		} catch (Exception ex) {
			SpoutManager.getKeyBindingManager().registerBinding("Recipes", Keyboard.KEY_U, "Opens Recipes Browser", new InputHandler(this), this);
		}
		//Precache files
		for (Map.Entry<String, List<RecipeInfo>> entry : REGISTRY.getAll().entrySet()) {
			for (RecipeInfo info : entry.getValue()) {
				SpoutManager.getFileManager().addToPreLoginCache(this, new File(info.getInputImageLocation()));
				SpoutManager.getFileManager().addToPreLoginCache(this, new File(info.getOutputImageLocation()));
			}
		}
	}

	public RecipesRegistry getBackend() {
		return REGISTRY;
	}
}
