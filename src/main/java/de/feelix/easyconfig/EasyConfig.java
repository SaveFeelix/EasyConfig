package de.feelix.easyconfig;

import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * Default Class to create a Config
 */
public abstract class EasyConfig {

    /**
     * the ConfigFile
     */
    protected File file;

    /**
     * The Configuration
     */
    protected YamlConfiguration configuration;

    /**
     * Default Constructor
     * @param fileName the Name of the File
     */
    public EasyConfig(@NotNull String fileName) {
        this("", fileName);
    }

    /**
     * Default Constructor
     * @param folderName the Name of the Folder
     * @param fileName the Name of the File
     */
    public EasyConfig(@NotNull String folderName, @NotNull String fileName) {
        try {
            if (!fileName.toLowerCase().endsWith(".yml"))
                fileName += ".yml";

            file = new File(folderName, fileName);
            if (!file.getParentFile().isDirectory())
                file.getParentFile().mkdirs();
            if (!file.exists())
                file.createNewFile();

            configuration = YamlConfiguration.loadConfiguration(file);
            addDefault();
        } catch (IOException e) {
            file = null;
            configuration = null;
            e.printStackTrace();
        }

    }

    /**
     * Abstract Method to add default Data
     */
    public abstract void addDefault();

    /**
     * Check if the File and the Config exists
     * @return true if the File and Config exists
     */
    protected boolean checkConfig() {
        return (configuration != null && file.exists()) || (configuration != null && file != null);
    }

    /**
     * Save the Config
     */
    protected void saveConfig() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add new Values to the Config
     *
     * @param key   the Key
     * @param value the Value
     */
    protected void add(@NotNull String key, @NotNull Object value) {
        if (this.checkConfig()) {
            if (!configuration.contains(key)) {
                configuration.set(key, value);
                saveConfig();
            }
        }
    }

    /**
     * Method to set a new Value to an existing Key
     *
     * @param key   the Key
     * @param value the new Value
     */
    protected void set(@NotNull String key, @NotNull Object value) {
        if (this.checkConfig()) {
            if (configuration.contains(key)) {
                configuration.set(key, value);
                saveConfig();
            }
        }
    }

    /**
     * Method to set a value to a (not) existing key
     *
     * @param key   the Key
     * @param value the Value
     */
    protected void addAndSet(@NotNull String key, @NotNull Object value) {
        if (this.checkConfig()) {
            configuration.set(key, value);
            saveConfig();
        }
    }

    /**
     * Method to remove a Key and a Value
     *
     * @param key the Key to remove
     */
    protected void remove(@NotNull String key) {
        if (this.checkConfig()) {
            if (configuration.contains(key)) {
                configuration.set(key, null);
                saveConfig();
            }
        }
    }

    /**
     * Get a default Data from a specific type from the Config
     * @param key the path to the stored Data
     * @param <T> the Typ of the Data
     * @return the Value of the Config based on the Specific Type
     * @throws ClassCastException if the ValueType is not the specific Type
     * @throws NullPointerException if the Value if null
     */
    protected <T> T getFromConfig(@NotNull String key) throws ClassCastException, NullPointerException {
        if (this.checkConfig()) {
            if (configuration.contains(key))
                return (T) configuration.get(key);
        }
        return null;
    }

    /**
     * Getter of the ConfigFile
     * @return the ConfigFile
     */
    public File getFile() {
        return file;
    }

    /**
     * Getter of the Configuration
     * @return the Configuration
     */
    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}
