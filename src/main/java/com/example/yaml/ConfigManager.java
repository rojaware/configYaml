package com.example.yaml;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

/**
 * @author giwon
 *
 */
public class ConfigManager {
	public static final String CONFIG_FILE = "config.yml";
	private static ConfigManager configManager;
	private Configuration config;

	/**
	 * 
	 */
	public ConfigManager() {
		loadConfig();

	}

	public Configuration getConfig() {
		return config;
	}

	public void loadConfig() {
		Yaml yaml = new Yaml();
		String in = getFileWithUtil(CONFIG_FILE);
		config = yaml.loadAs(in, Configuration.class);
		System.out.println(config.toString());

	}

	public static ConfigManager instance() {
		if (configManager == null)
			configManager = new ConfigManager();
		return configManager;
	}

	private String getFileWithUtil(String fileName) {

		String result = "";

		ClassLoader classLoader = getClass().getClassLoader();
		try {
			result = IOUtils
					.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		ConfigManager app = ConfigManager.instance();
		Configuration config = app.getConfig();
		System.out.println("URL = " + config.getConnection().getUrl());

	}
}
