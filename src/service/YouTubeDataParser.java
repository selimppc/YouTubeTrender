package service;

import entity.YouTubeVideo;
import exception.YouTubeDataParserException;

import javax.json.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class YouTubeDataParser {

    public List<YouTubeVideo> parse(String filename) throws YouTubeDataParserException {
        List<YouTubeVideo> videos = new ArrayList<>();
        try {
            JsonReader jsonReader = Json.createReader(new FileInputStream(filename));
            JsonObject jobj = jsonReader.readObject();

            // read the values of the item field
            JsonArray items = jobj.getJsonArray("items");

            for (JsonObject videoObject : items.getValuesAs(JsonObject.class)) {
                JsonObject snippet = videoObject.getJsonObject("snippet");
                JsonObject statistics = videoObject.getJsonObject("statistics");

                YouTubeVideo video = new YouTubeVideo();

                // Handle potential null values using the JSONP library
                video.setChannel(getStringValue(snippet, "channelId"));
                video.setDate(getStringValue(snippet, "publishedAt"));
                video.setTitle(getStringValue(snippet, "title"));
                video.setDescription(getStringValue(snippet, "description"));
                video.setViewCount(getIntValue(statistics, "viewCount"));

                videos.add(video);
            }
        } catch (Exception e) {
            throw new YouTubeDataParserException("Error parsing YouTube data", e);
        }
        return videos;
    }

    private String getStringValue(JsonObject jsonObject, String key) {
        JsonValue.ValueType value = jsonObject.get(key).getValueType();
        if (value == JsonValue.ValueType.NULL) {
            return null;
        } else {
            return jsonObject.getString(key);
        }
    }

    private int getIntValue(JsonObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            JsonValue value = jsonObject.get(key);
            if (value.getValueType() == JsonValue.ValueType.NUMBER) {
                return jsonObject.getInt(key);
            } else if (value.getValueType() == JsonValue.ValueType.STRING) {
                String stringValue = jsonObject.getString(key);
                try {
                    return Integer.parseInt(stringValue);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing integer from string value: " + stringValue);
                    return 0; // or some default value
                }
            }
        }
        return 0; // or some default value
    }

}


