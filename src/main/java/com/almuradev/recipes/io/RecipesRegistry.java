package com.almuradev.recipes.io;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.almuradev.recipes.info.RecipeInfo;

public class RecipesRegistry {
	private final Map<String, List<RecipeInfo>> TYPES_RECIPES = new HashMap<>();

	public boolean contains(String type, String name) {
		if (type == null || type.isEmpty()) {
			throw new NullPointerException("Specified type is null!");
		}
		final List<RecipeInfo> RECIPES = TYPES_RECIPES.get(type);
		if (RECIPES != null) {
			for (RecipeInfo info : RECIPES) {
				if (info.getIdentifer().equalsIgnoreCase(name)) {
					return true;
				}
			}
		}
		return false;
	}

	public RecipeInfo get(String type, String name) {
		if (type == null || type.isEmpty() || name == null || name.isEmpty()) {
			throw new NullPointerException("Specified type or name is null!");
		}
		final List<RecipeInfo> RECIPES = TYPES_RECIPES.get(type);
		if (RECIPES != null) {
			for (RecipeInfo info : RECIPES) {
				if (info.getIdentifer().equalsIgnoreCase(name)) {
					return info;
				}
			}
		}
		return null;
	}

	public List<RecipeInfo> get(String type) {
		if (type == null || type.isEmpty()) {
			throw new NullPointerException("Specified type is null!");
		}
		return TYPES_RECIPES.get(type);
	}

	public Set<String> getTypes() {
		return TYPES_RECIPES.keySet();
	}

	public Map<String, List<RecipeInfo>> getAll() {
		return Collections.unmodifiableMap(TYPES_RECIPES);
	}

	protected void put(String type, List<RecipeInfo> infos) {
		TYPES_RECIPES.put(type, infos);
	}
}
