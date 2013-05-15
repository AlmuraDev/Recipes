package com.almuradev.recipes.display.widgets;

import com.almuradev.recipes.display.RecipesPopup;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

public class ActionButton extends GenericButton {
	private int id;
	private RecipesPopup form;

	public ActionButton(String name, RecipesPopup form, int id) {
		super(name);
		this.form = form;
		this.id = id;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		super.onButtonClick(event);
		form.onActionClick(id);
	}
}
