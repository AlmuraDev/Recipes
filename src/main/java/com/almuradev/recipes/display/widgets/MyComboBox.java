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
		form.onSelectionChanged(i, text);
	}
}
