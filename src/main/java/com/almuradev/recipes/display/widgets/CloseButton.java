package com.almuradev.recipes.display.widgets;

import com.almuradev.recipes.RecipesPlugin;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

public class CloseButton extends GenericButton {
	private RecipesPlugin plugin;

	public CloseButton(RecipesPlugin plugin) {
		super("Close");
		this.plugin = plugin;
	}

	public CloseButton(boolean openMain) {
		super("Close");
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().closePopup();
	}
}