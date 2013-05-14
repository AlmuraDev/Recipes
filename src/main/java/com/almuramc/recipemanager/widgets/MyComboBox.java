/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almuramc.recipemanager.widgets;

import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.GenericComboBox;

import com.almuramc.recipemanager.RecipeGUI;

/**
 *
 * @author ZNickq
 */
public class MyComboBox extends GenericComboBox{
	private RecipeGUI base;
	
	public MyComboBox(RecipeGUI base) {
		this.base = base;
	}

	@Override
	public void onSelectionChanged(int i, String text) {
		super.onSelectionChanged(i, text);
		base.onSelected(text);
	}
}
