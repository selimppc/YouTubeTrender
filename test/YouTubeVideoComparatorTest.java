import comparator.YouTubeVideoChannelComparator;
import comparator.YouTubeVideoDateComparator;
import comparator.YouTubeVideoViewComparator;
import comparator.YoutubeVideoDescriptionComparator;
import entity.YouTubeVideo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YouTubeVideoComparatorTest {

    @Test
    public void testYouTubeVideoChannelComparator() {
        YouTubeVideo video1 = new YouTubeVideo();
        video1.setChannel("A");
        YouTubeVideo video2 = new YouTubeVideo();
        video2.setChannel("B");

        YouTubeVideoChannelComparator comparator = new YouTubeVideoChannelComparator();
        assertTrue(comparator.compare(video1, video2) < 0);
    }

    @Test
    public void testYouTubeVideoDateComparator() {
        YouTubeVideo video1 = new YouTubeVideo();
        video1.setDate("2021-01-01");
        YouTubeVideo video2 = new YouTubeVideo();
        video2.setDate("2022-01-01");

        YouTubeVideoDateComparator comparator = new YouTubeVideoDateComparator();
        assertTrue(comparator.compare(video1, video2) < 0);
    }

    @Test
    public void testYoutubeVideoDescriptionComparator() {
        YouTubeVideo video1 = new YouTubeVideo();
        video1.setDescription("Apple");
        YouTubeVideo video2 = new YouTubeVideo();
        video2.setDescription("Banana");

        YoutubeVideoDescriptionComparator comparator = new YoutubeVideoDescriptionComparator();
        assertTrue(comparator.compare(video1, video2) < 0);
    }

    @Test
    public void testYouTubeVideoViewComparator() {
        YouTubeVideo video1 = new YouTubeVideo();
        video1.setViewCount(100);
        YouTubeVideo video2 = new YouTubeVideo();
        video2.setViewCount(200);

        YouTubeVideoViewComparator comparator = new YouTubeVideoViewComparator();
        assertTrue(comparator.compare(video1, video2) < 0);
    }
}
