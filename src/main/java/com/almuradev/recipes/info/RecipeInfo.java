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
package com.almuradev.recipes.info;

public final class RecipeInfo {
	private final String identifer;
	private final String inputImageLocation;
	private final String outputImageLocation;
	private final String description;
	private final String ingredients;	

	public RecipeInfo(String identifer, String inputImageLocation, String outputImageLocation, String description, String ingredients) {
		this.identifer = identifer;
		this.inputImageLocation = inputImageLocation;
		this.outputImageLocation = outputImageLocation;
		this.description = description;
		this.ingredients = ingredients;
	}

	public String getIdentifer() {
		return identifer;
	}

	public String getInputImageLocation() {
		return inputImageLocation;
	}

	public String getOutputImageLocation() {
		return outputImageLocation;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getIngredients() {
		return ingredients;
	}
}
