package comparator;

import entity.YouTubeVideo;

import java.util.Comparator;

public class YoutubeVideoDescriptionComparator implements Comparator<YouTubeVideo> {
    @Override
    public int compare(YouTubeVideo video1, YouTubeVideo video2) {
        return video1.getDescription().compareTo(video2.getDescription());
    }
}
