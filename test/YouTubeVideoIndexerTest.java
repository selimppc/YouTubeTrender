import entity.YouTubeVideo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.YouTubeVideoIndexer;
import service.YoutTubeWordItem;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class YouTubeVideoIndexerTest {

    private final YouTubeVideoIndexer indexer = new YouTubeVideoIndexer();

    @BeforeEach
    public void setUp() {
        // Sample videos for testing
        YouTubeVideo video1 = new YouTubeVideo();
        video1.setTitle("Test Video One");
        video1.setDescription("This is a test video description.");

        YouTubeVideo video2 = new YouTubeVideo();
        video2.setTitle("Test Video Two");
        video2.setDescription("Another test video description.");

        indexer.index(List.of(video1, video2));
    }

    @DisplayName("Testing sorted YouTube word items")
    @Test
    public void testSortedYouTubeWordItems() {
        List<YoutTubeWordItem> sortedItems = indexer.getSortedYouTubeWordItems();
        assertTrue(sortedItems.get(0).getCount() >= sortedItems.get(1).getCount());
    }

    @DisplayName("Testing word counts")
    @Test
    public void testWordCounts() {
        Map<String, Integer> wordCounts = indexer.getWordCounts();
        assertEquals(4, wordCounts.get("test").intValue());
        assertEquals(1, wordCounts.get("one").intValue());
    }

    @DisplayName("Testing videos associated with word")
    @Test
    public void testVideosAssociatedWithWord() {
        Set<YouTubeVideo> videos = indexer.getVideosAssociatedWithWord("test");
        System.out.println(videos);
        assertEquals(2, videos.size());
    }

    @DisplayName("Testing most used word")
    @Test
    public void testMostUsedWord() {
        String mostUsedWord = indexer.getMostUsedWord();
        assertEquals("test", mostUsedWord);
    }
}
