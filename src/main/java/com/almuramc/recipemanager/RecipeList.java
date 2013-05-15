/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almuramc.recipemanager;

public class RecipeList {
	private String name, url;
	
	public RecipeList(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}
	
}
