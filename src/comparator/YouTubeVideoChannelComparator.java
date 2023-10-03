package comparator;

import entity.YouTubeVideo;

import java.util.Comparator;

public class YouTubeVideoChannelComparator implements Comparator<YouTubeVideo> {
    @Override
    public int compare(YouTubeVideo video1, YouTubeVideo video2) {
        return video1.getChannel().compareTo(video2.getChannel());
    }
}
