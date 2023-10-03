package service;

import entity.YouTubeVideo;

import java.util.HashSet;
import java.util.Set;

public class YoutTubeWordItem {
    private String word;
    private int count;
    private Set<YouTubeVideo> videos = new HashSet<>();

    public YoutTubeWordItem(String word) {
        this.word = word;
        this.count = 0;
    }

    public void incrementCount() {
        this.count++;
    }

    public void addVideo(YouTubeVideo video) {
        videos.add(video);
    }

    public int getCount() {
        return count;
    }

    public Set<YouTubeVideo> getVideos() {
        return videos;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "YoutTubeWordItem [word=" + word + ", count=" + count + ", videos=" + videos + "]";
    }
}
