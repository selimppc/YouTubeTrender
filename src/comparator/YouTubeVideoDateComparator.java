package comparator;

import entity.YouTubeVideo;

import java.util.Comparator;

public class YouTubeVideoDateComparator implements Comparator<YouTubeVideo> {
    @Override
    public int compare(YouTubeVideo video1, YouTubeVideo video2) {
        return video1.getDate().compareTo(video2.getDate());
    }
}
