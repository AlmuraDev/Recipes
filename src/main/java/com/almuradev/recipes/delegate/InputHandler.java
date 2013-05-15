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
package com.almuradev.recipes.delegate;

import com.almuradev.recipes.RecipesPlugin;
import com.almuradev.recipes.display.RecipesPopup;
import com.almuradev.recipes.util.RecipesPermissions;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

public class InputHandler implements BindingExecutionDelegate {
	private final RecipesPlugin plugin;

	public InputHandler(final RecipesPlugin plugin) {
		this.plugin = plugin;
	}

	public void keyPressed(KeyBindingEvent keyBindingEvent) {
		//Only toggle state when not on the gamescreen
		if (!keyBindingEvent.getScreenType().equals(ScreenType.GAME_SCREEN)) {
			return;
		}

		final SpoutPlayer sPlayer = keyBindingEvent.getPlayer();
		if (sPlayer.hasPermission(RecipesPermissions.VIEW.get())) {
			new RecipesPopup(plugin, keyBindingEvent.getPlayer());
		}
	}

	public void keyReleased(KeyBindingEvent keyBindingEvent) {
	}
}
