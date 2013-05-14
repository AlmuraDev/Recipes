/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almuramc.recipemanager.widgets;

import org.getspout.spoutapi.gui.GenericListWidget;

import com.almuramc.recipemanager.RecipeGUI;

/**
 *
 * @author ZNickq
 */
public class MyListWidget extends GenericListWidget{
	RecipeGUI base;
	
	public MyListWidget(RecipeGUI base) {
		this.base = base;
	}

	@Override
	public void onSelected(int item, boolean doubleClick) {
		super.onSelected(item, doubleClick);
		base.onListSelected(item);
	}
	
}
