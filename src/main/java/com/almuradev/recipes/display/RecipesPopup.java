package com.almuradev.recipes.display;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.almuradev.recipes.RecipesPlugin;
import com.almuradev.recipes.display.widgets.CloseButton;
import com.almuradev.recipes.display.widgets.MyComboBox;
import com.almuradev.recipes.display.widgets.MyListWidget;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class RecipesPopup extends GenericPopup {
	private RecipesPlugin plugin;
	private SpoutPlayer player;
	private ListWidget lw;
	private GenericButton select;
	private MyComboBox cb;
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

		cb = new MyComboBox(this);
		cb.setText("Recipes Categories");
		cb.setAnchor(WidgetAnchor.CENTER_CENTER);
		cb.shiftXPos(-195).shiftYPos(-90);
		cb.setHeight(20).setWidth(150);
		cb.setSelection(0);
		updateDropdown();

		lw = new MyListWidget(this);
		lw.setAnchor(WidgetAnchor.CENTER_CENTER);
		lw.setHeight(150).setWidth(150);
		lw.shiftXPos(-195).shiftYPos(-60);

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

		attachWidgets(plugin, border, label, label1, lw, cb, select, craftTexture, resultTexture, close);
		player.getMainScreen().attachPopupScreen(this);
		updateTexture();
	}

	public void updateTexture() {
		int sel = lw.getSelectedRow();
		if (sel < 1) {
			craftTexture.setUrl("");
			resultTexture.setUrl("");
		} else {
			craftTexture.setUrl(list.get(sel - 1).getUrl());
			resultTexture.setUrl(list.get(sel - 1).getUrl());
		}
		craftTexture.setDirty(true);
		resultTexture.setDirty(true);
	}

	public void onListSelected(int item) {
		updateTexture();
	}

	public void onSelected(String text) {
		// TODO
	}

	public void onActionClick(int id) {
		// TODO
	}

	private void updateList() {
		lw.clear();
		lw.setDirty(true);
	}

	private void updateDropdown() {
		List<String> available = new ArrayList<>();
		//available.add(StringUtils.capitalize(type.name().toLowerCase()));				
		Collections.sort(available, String.CASE_INSENSITIVE_ORDER);
		cb.setItems(available);
		cb.setDirty(true);
	}
}
