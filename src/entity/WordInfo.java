package entity;

import java.util.ArrayList;
import java.util.List;

public class WordInfo {
    private String word;
    private int count;
    private List<YouTubeVideo> videos;

    public WordInfo(String word) {
        this.word = word;
        this.count = 0;
        this.videos = new ArrayList<>();
    }

    public void incrementCount() {
        this.count++;
    }

    public void addVideo(YouTubeVideo video) {
        if (!videos.contains(video)) {
            videos.add(video);
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<YouTubeVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<YouTubeVideo> videos) {
        this.videos = videos;
    }

}
