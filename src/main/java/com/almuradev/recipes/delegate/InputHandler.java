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
