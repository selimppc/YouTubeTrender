import entity.YouTubeVideo;
import exception.YouTubeDataParserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.YouTubeDataParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Carl
 */
public class YouTubeDataParserTest {

    private final YouTubeDataParser parser = new YouTubeDataParser();

    /**
     * Test of parse method, of class YouTubeDataParser.
     */
    @DisplayName("Testing the parse method")
    @Test
    public void testParse() throws YouTubeDataParserException {
        String fileName = "data/youtubedata.json";
        YouTubeDataParser instance = new YouTubeDataParser();
        List<YouTubeVideo> result = instance.parse(fileName);
        assertNotNull(result);
    }


    @DisplayName("Testing the parse method with valid data")
    @Test
    public void testParseValidData() throws YouTubeDataParserException {
        String fileName = "data/youtubedata.json";
        List<YouTubeVideo> result = parser.parse(fileName);
        assertEquals(1, result.size());
        YouTubeVideo video = result.get(0);
        assertEquals("UCehf4850q1L3ng7s7L54ATA", video.getChannel());
        assertEquals("2016-04-20T23:15:17.000Z", video.getDate());
        assertEquals("This should have a really useful title", video.getTitle());
        assertEquals("This should have a really useful description.  However lots of youtubers put links and other promotional material.", video.getDescription());
        assertEquals(14180950, video.getViewCount());
    }

    @DisplayName("Testing the parse method with invalid file")
    @Test
    public void testParseInvalidFile() {
        String fileName = "data/youtubedata_malformed.json";
        assertThrows(YouTubeDataParserException.class, () -> parser.parse(fileName));
    }

    @DisplayName("Testing the parse method with null values")
    @Test
    public void testParseWithNullValues() throws YouTubeDataParserException {
        String fileName = "data/youtubedata_15_50.json";
        List<YouTubeVideo> result = parser.parse(fileName);

        // Assuming the file contains 1 video with null values
        assertEquals(50, result.size());
        YouTubeVideo video = result.get(0);
        assertNotNull(video.getChannel());
        assertNotNull(video.getDate());
        assertNotNull(video.getTitle());
        assertNotNull(video.getDescription());
        assertEquals(14187775, video.getViewCount());
    }

    @DisplayName("Testing the parse method with non-numeric view count")
    @Test
    public void testParseWithNonNumericViewCount() throws YouTubeDataParserException {
        String fileName = "data/youtubedata_15_50.json";
        List<YouTubeVideo> result = parser.parse(fileName);

        // Assuming the file contains 1 video with a non-numeric view count
        assertEquals(50, result.size());
        YouTubeVideo video = result.get(0);
        assertEquals(14187775, video.getViewCount());
    }

}
