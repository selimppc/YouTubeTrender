package comparator;

import entity.YouTubeVideo;

import java.util.Comparator;

public class YouTubeVideoViewComparator implements Comparator<YouTubeVideo> {
    @Override
    public int compare(YouTubeVideo video1, YouTubeVideo video2) {
        return Integer.compare(video1.getViewCount(), video2.getViewCount());
    }
}
