/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almuramc.recipemanager.widgets;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import com.almuramc.recipemanager.RecipeGUI;

public class ActionButton extends GenericButton {

	private int id;
	private RecipeGUI base;

	public ActionButton(String name, RecipeGUI tx, int id) {
		super(name);
		this.base = tx;
		this.id = id;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		super.onButtonClick(event);
		base.onActionClick(id);
	}
}
