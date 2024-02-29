package org.mps.utils;

import org.mps.constants.FrameworkConstants;
import org.mps.enums.ConfigProperties;
import org.mps.exceptions.PropertyFileUsageException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class PropertyUtils {

    private PropertyUtils() {
    }
    private static Properties property = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getConfigFilePath());
            property.load(fileInputStream);
//            property.entrySet().forEach(entry -> CONFIGMAP.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue())));

            property.forEach((key, value) -> CONFIGMAP.put(String.valueOf(key), String.valueOf(value).trim()));

        } catch (IOException e) {
            System.exit(0);
        }

    }
    //Hashtable -> Little slow, but thread safe
    //Converting a property file to a hashmap needs some time.

    public static String get(ConfigProperties key){
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new PropertyFileUsageException("Property name " + key + " does not exist. Please add the designated value into the config.properties file");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }
}
