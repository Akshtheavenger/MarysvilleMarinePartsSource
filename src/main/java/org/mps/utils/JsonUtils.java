package org.mps.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mps.constants.FrameworkConstants;
import org.mps.enums.JsonProperties;
import org.mps.exceptions.JsonFileUsageException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class JsonUtils {

    private JsonUtils(){}

    private static Map<String, String> CONFIGMAP = new HashMap<>();

    static {
        try {
            CONFIGMAP = new ObjectMapper().readValue(new File(FrameworkConstants.getJsonconfigfilepath()),
                    new TypeReference<HashMap<String, String>>() {});
//			property.entrySet().forEach(entry -> CONFIGMAP.put(String.valueOf(entry.getKey()) , String.valueOf(entry.getValue())));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getData(JsonProperties key) {
        if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new JsonFileUsageException("Test Data name " + key + " does not exist. Please add the designated value into the config.json file");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }

}
