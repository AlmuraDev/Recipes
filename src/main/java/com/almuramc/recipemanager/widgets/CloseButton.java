/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almuramc.recipemanager.widgets;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import com.almuramc.recipemanager.RecipeManager;

public class CloseButton extends GenericButton {

	private RecipeManager i;

	public CloseButton(RecipeManager i) {
		super("Close");
		this.i = i;
	}

	public CloseButton(boolean openMain) {
		super("Close");
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().closePopup();
	}
}