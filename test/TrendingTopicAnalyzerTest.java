import entity.YouTubeVideo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.TrendingTopicAnalyzer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrendingTopicAnalyzerTest {

    private TrendingTopicAnalyzer analyzer;
    private List<YouTubeVideo> videos;

    @BeforeEach
    public void setUp() {
        analyzer = new TrendingTopicAnalyzer();

        // Sample YouTube videos for testing
        YouTubeVideo video1 = new YouTubeVideo();
        video1.setTitle("Test Video 1");
        video1.setDescription("This is a description with the word TEST.");

        YouTubeVideo video2 = new YouTubeVideo();
        video2.setTitle("Another Test Video");
        video2.setDescription("Another description with TEST and EXAMPLE.");

        YouTubeVideo video3 = new YouTubeVideo();
        video3.setTitle("Example Video");
        video3.setDescription("Just an example description.");

        videos = Arrays.asList(video1, video2, video3);
    }

    @DisplayName("Testing word indexing")
    @Test
    public void testIndexWordUsage() {
        Map<String, Integer> wordCounts = analyzer.indexWordUsage(videos);

        assertEquals(4, wordCounts.get("TEST"));
        assertEquals(3, wordCounts.get("EXAMPLE"));
    }

    @DisplayName("Testing retrieval of word info")
    @Test
    public void testGetWordInfo() {
        analyzer.indexWordUsage(videos);

        assertEquals(4, analyzer.getWordInfo("TEST").getCount());
        assertEquals(3, analyzer.getWordInfo("EXAMPLE").getCount());
    }

    @DisplayName("Testing sorted words by count")
    @Test
    public void testGetSortedWordsByCount() {
        analyzer.indexWordUsage(videos);

        List<String> sortedWords = analyzer.getSortedWordsByCount();
        assertEquals("TEST", sortedWords.get(0));
        assertEquals("VIDEO", sortedWords.get(1));
    }

    @DisplayName("Testing most used word retrieval")
    @Test
    public void testGetMostUsedWord() {
        analyzer.indexWordUsage(videos);

        assertEquals("TEST", analyzer.getMostUsedWord());
    }
}
