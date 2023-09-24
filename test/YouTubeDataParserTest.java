import entity.YouTubeVideo;
import exception.YouTubeDataParserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.YouTubeDataParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Carl
 */
public class YouTubeDataParserTest {


    /**
     * Test of parse method, of class YouTubeDataParser.
     */
    @DisplayName("Testing the parse method")
    @Test
    public void testParse() throws YouTubeDataParserException {
        String fileName = "";
        YouTubeDataParser instance = new YouTubeDataParser();
        List<YouTubeVideo> expResult = null;
        List<YouTubeVideo> result = instance.parseFromFile(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
