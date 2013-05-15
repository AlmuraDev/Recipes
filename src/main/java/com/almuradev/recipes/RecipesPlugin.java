package com.almuradev.recipes;

import com.almuradev.recipes.io.Loader;
import com.almuradev.recipes.io.RecipesRegistry;

import org.bukkit.plugin.java.JavaPlugin;

public class RecipesPlugin extends JavaPlugin {
	private final RecipesRegistry REGISTRY;
	private Loader loader;

	public RecipesPlugin() {
		REGISTRY = new RecipesRegistry();
	}

	@Override
	public void onEnable() {
		loader = new Loader(this);
		loader.onEnable();
	}

	public RecipesRegistry getBackend() {
		return REGISTRY;
	}
}
