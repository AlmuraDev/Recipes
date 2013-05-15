package com.almuradev.recipes.info;

public final class RecipeInfo {
	private final String identifer;
	private final String inputImageLocation;
	private final String outputImageLocation;

	public RecipeInfo(String identifer, String inputImageLocation, String outputImageLocation) {
		this.identifer = identifer;
		this.inputImageLocation = inputImageLocation;
		this.outputImageLocation = outputImageLocation;
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
}
