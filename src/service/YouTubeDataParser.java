package service;

import entity.YouTubeVideo;
import exception.YouTubeDataParserException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class YouTubeDataParser {

    public List<YouTubeVideo> parseFromFile(String filePath) throws YouTubeDataParserException {
        List<YouTubeVideo> videos = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath))).trim();
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject videoObject = jsonArray.getJSONObject(i);

                // Print the entire JSONObject
                System.out.println(videoObject.toString(4));

                YouTubeVideo video = new YouTubeVideo();

                // Check if channelId exists before accessing it
                if(videoObject.has("channelId")) {
                    video.setChannelId(videoObject.getString("channelId"));
                } else {
                    System.out.println("channelId not found in object: " + videoObject);
                }

                // Similar checks for other keys...

                videos.add(video);
            }
        } catch (Exception e) {
            throw new YouTubeDataParserException("Error parsing YouTube data", e);
        }
        return videos;
    }
}

