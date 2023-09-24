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

    public static List<YouTubeVideo> parseFromFile(String filePath) throws YouTubeDataParserException {
        List<YouTubeVideo> videos = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject videoObject = jsonArray.getJSONObject(i);
                YouTubeVideo video = new YouTubeVideo();
                video.setChannelId(videoObject.getString("channelId"));
                video.setChannelTitle(videoObject.getString("channelTitle"));
                video.setPublishedAt(videoObject.getString("publishedAt"));
                video.setTitle(videoObject.getString("title"));
                video.setDescription(videoObject.getString("description"));
                video.setViewCount(videoObject.getLong("viewCount"));
                videos.add(video);
            }
        } catch (Exception e) {
            throw new YouTubeDataParserException("Error parsing YouTube data", e);
        }

        return videos;
    }
}

