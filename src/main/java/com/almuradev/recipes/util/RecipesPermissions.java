package com.almuradev.recipes.util;

public enum RecipesPermissions {
	VIEW("recipes.view");

	private final String permission;

	private RecipesPermissions(final String permission) {
		this.permission = permission;
	}

	public String get() {
		return permission;
	}
}
