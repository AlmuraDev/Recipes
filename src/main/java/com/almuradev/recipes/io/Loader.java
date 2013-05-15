package com.almuradev.recipes.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.almuradev.recipes.RecipesPlugin;
import com.almuradev.recipes.info.RecipeInfo;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Loader {
	private final RecipesPlugin plugin;
	private final File dir;

	public Loader(RecipesPlugin plugin) {
		this.plugin = plugin;
		dir = new File(plugin.getDataFolder(), "recipes");
	}

	public void onEnable() {
		try {
			Files.createDirectories(dir.toPath());
		} catch (FileAlreadyExistsException fafe) {
			;
		} catch (IOException e) {
			plugin.getLogger().severe("Could not create " + dir.getPath() + "! Disabling...");
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
	}

	public void load() {
		try {
			Files.walkFileTree(dir.toPath(), new FileLoadingVisitor(plugin));
		} catch (IOException ignore) {
			plugin.getLogger().severe("Encountered a major issue while attempting to find " + dir.toPath() + ". Disabling...");
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
	}
}

class FileLoadingVisitor extends SimpleFileVisitor<Path> {
	private final RecipesPlugin plugin;

	public FileLoadingVisitor(RecipesPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public FileVisitResult visitFileFailed(Path path, IOException ioe) {
		return FileVisitResult.TERMINATE;
	}

	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
		final List<RecipeInfo> toInject = createDescriptors(path.toFile());
		if (toInject == null) {
			plugin.getLogger().severe("Could not load: " + path.getFileName() + ". Skipping...");
			return FileVisitResult.TERMINATE;
		}
		plugin.getBackend().put(path.getFileName().toString(), toInject);
		return FileVisitResult.TERMINATE;
	}

	private List<RecipeInfo> createDescriptors(File file) {
		final List<RecipeInfo> infos = new LinkedList<>();
		final YamlConfiguration reader = YamlConfiguration.loadConfiguration(file);
		final Iterator<String> iterator = reader.getKeys(false).iterator();

		while (iterator.hasNext()) {
			final String identifier = iterator.next();
			final ConfigurationSection identifierSection = reader.getConfigurationSection(identifier);
			final String recipeImageLocation = identifierSection.getString("recipe", "");
			final String resultImageLocation = identifierSection.getString("result", "");
			infos.add(new RecipeInfo(identifier, recipeImageLocation, resultImageLocation));
		}

		return infos;
	}
}
