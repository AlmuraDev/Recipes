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
package com.almuradev.recipes.display;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.almuradev.recipes.RecipesPlugin;
import com.almuradev.recipes.display.widgets.CloseButton;
import com.almuradev.recipes.display.widgets.MyComboBox;
import com.almuradev.recipes.display.widgets.MyListWidget;
import com.almuradev.recipes.info.RecipeInfo;

import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.gui.ListWidgetItem;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class RecipesPopup extends GenericPopup {
	private RecipesPlugin plugin;
	private SpoutPlayer player;
	private ListWidget listWidget;
	//private GenericButton selectButton; TODO
	private MyComboBox comboBox;
	private GenericTexture craftTexture, resultTexture;

	public RecipesPopup(RecipesPlugin plugin, SpoutPlayer player) {
		this.plugin = plugin;
		this.player = player;

		GenericTexture border = new GenericTexture(plugin.getConfig().getString("GUITexture"));
		border.setAnchor(WidgetAnchor.CENTER_CENTER);
		border.setPriority(RenderPriority.High);
		border.setWidth(420).setHeight(345);
		border.shiftXPos(0 - (border.getWidth() / 2)).shiftYPos(-120);

		GenericLabel label = new GenericLabel();
		label.setText(plugin.getConfig().getString("PromptTitle"));
		label.setAnchor(WidgetAnchor.CENTER_CENTER);
		label.setHeight(15).setWidth(GenericLabel.getStringWidth(label.getText(), label.getScale()));
		label.setScale(1.5F);
		label.shiftXPos((GenericLabel.getStringWidth(label.getText(), label.getScale()) / 2) * -1).shiftYPos(-113);

		GenericLabel label1 = new GenericLabel();
		label1.setText("Text here.");
		label1.setAnchor(WidgetAnchor.CENTER_CENTER);
		label1.shiftXPos(-195).shiftYPos(100);
		label1.setScale(1.0F).setWidth(-1).setHeight(-1);

		comboBox = new MyComboBox(this);
		comboBox.setText("Recipes Categories");
		comboBox.setAnchor(WidgetAnchor.CENTER_CENTER);
		comboBox.shiftXPos(-195).shiftYPos(-90);
		comboBox.setHeight(20).setWidth(150);
		comboBox.setSelection(0);

		listWidget = new MyListWidget(this);
		listWidget.setAnchor(WidgetAnchor.CENTER_CENTER);
		listWidget.setHeight(150).setWidth(150);
		listWidget.shiftXPos(-195).shiftYPos(-60);

		craftTexture = new GenericTexture();
		craftTexture.setAnchor(WidgetAnchor.CENTER_CENTER);
		craftTexture.setHeight(110).setWidth(190);
		craftTexture.shiftXPos(5).shiftYPos(-100);

		resultTexture = new GenericTexture();
		resultTexture.setAnchor(WidgetAnchor.CENTER_CENTER);
		resultTexture.setHeight(100).setWidth(100);
		resultTexture.shiftXPos(50).shiftYPos(20);

		CloseButton close = new CloseButton(plugin);
		close.setAnchor(WidgetAnchor.CENTER_CENTER);
		close.setHeight(20).setWidth(50);
		close.shiftXPos(145).shiftYPos(95);


		attachWidgets(plugin, border, label, label1, listWidget, comboBox, craftTexture, resultTexture, close);
		player.getMainScreen().attachPopupScreen(this);
		populateComboBox();
	}

	public void onActionClick(int id) {
		// TODO
	}

	//ListWidget
	public void onListSelectionChanged(int item, boolean doubleClick) {
		final String selectedTypeName = comboBox.getSelectedItem();
		final String selectedRecipeName = listWidget.getItem(item).getText();
		final RecipeInfo info = plugin.getBackend().get(selectedTypeName, selectedRecipeName);
		if (info == null) {
			craftTexture.setUrl("");
			resultTexture.setUrl("");
		} else {
			craftTexture.setUrl(new File(plugin.getDataFolder().getPath() + File.separator + "images", info.getInputImageLocation()).getPath());
			resultTexture.setUrl(new File(plugin.getDataFolder().getPath() + File.separator + "images", info.getOutputImageLocation()).getPath());
		}
	}

	//ComboBox
	public void onSelectionChanged(int i, String text) {
		if (listWidget == null) {
			return;
		}
		listWidget.clear();
		final List<RecipeInfo> infos = plugin.getBackend().get(text);
		if (infos == null) {
			return;
		}
		for (RecipeInfo info : plugin.getBackend().get(text)) {
			listWidget.addItem(new ListWidgetItem(info.getIdentifer(), ""));
		}
	}

	private void populateComboBox() {
		final Set<String> types = plugin.getBackend().getTypes();
		comboBox.setItems(asSortedList(types));
		comboBox.setDirty(true);
	}

	private <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
		List<T> list = new ArrayList<>(c);
		java.util.Collections.sort(list);
		return list;
	}
}
