package com.almuradev.recipes.display.widgets;

import com.almuradev.recipes.display.RecipesPopup;

import org.getspout.spoutapi.gui.GenericListWidget;

public class MyListWidget extends GenericListWidget {
	RecipesPopup base;

	public MyListWidget(RecipesPopup base) {
		this.base = base;
	}

	@Override
	public void onSelected(int item, boolean doubleClick) {
		super.onSelected(item, doubleClick);
		base.onListSelectionChanged(item, doubleClick);
	}
}
