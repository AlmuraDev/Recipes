package com.almuradev.recipes.display.widgets;

import com.almuradev.recipes.display.RecipesPopup;

import org.getspout.spoutapi.gui.GenericComboBox;

public class MyComboBox extends GenericComboBox {
	private RecipesPopup form;

	public MyComboBox(RecipesPopup form) {
		this.form = form;
	}

	@Override
	public void onSelectionChanged(int i, String text) {
		super.onSelectionChanged(i, text);
		form.onSelected(text);
	}
}
