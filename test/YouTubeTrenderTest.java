import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TrendingTopicAnalyzer;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class YouTubeTrenderTest {

    private YouTubeTrender trender;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        mockScanner = mock(Scanner.class);
        trender = new YouTubeTrender(mockScanner);
    }

    @Test
    public void testInputYouTubeData() {
        when(mockScanner.nextLine()).thenReturn("data/youtubedata_15_50.json");
        trender.inputYouTubeData();
        assertEquals("data/youtubedata_15_50.json", trender.filename);
    }

    @Test
    public void testGetUserChoiceValidInput() {
        when(mockScanner.nextLine()).thenReturn("invalid", "invalid", "3");
        int choice = trender.getUserChoice(1, 5);
        assertEquals(3, choice);
    }

    @Test
    public void testGetUserChoiceInvalidInput() {
        when(mockScanner.nextLine()).thenReturn("6", "6", "3");
        int choice = trender.getUserChoice(1, 5);
        assertEquals(3, choice);
    }

    @Test
    public void testGetUserChoiceNonNumericInput() {
        when(mockScanner.nextLine()).thenReturn("abc", "3");
        int choice = trender.getUserChoice(1, 5);
        assertEquals(3, choice);
    }

    @Test
    public void testDisplayVideosForWord() {
        TrendingTopicAnalyzer mockAnalyzer = mock(TrendingTopicAnalyzer.class);
        when(mockAnalyzer.getWordInfo(anyString())).thenReturn(null);
        when(mockScanner.nextLine()).thenReturn("testword");
        trender.displayVideosForWord(mockAnalyzer);
        verify(mockAnalyzer).getWordInfo("testword");
    }

    @AfterEach
    public void resetSystemInStream() {
        System.setIn(System.in);
    }
}
