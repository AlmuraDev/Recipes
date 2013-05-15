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
