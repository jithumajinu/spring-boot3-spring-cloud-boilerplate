package com.openapi.cloud.core.util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class JsonToObject {

    public JSONObject read() throws IOException {
        String file = "src/main/resources/static/response.json";
        String content = new String(Files.readAllBytes(Paths.get(file)));
        return new JSONObject(content);
    }
}
