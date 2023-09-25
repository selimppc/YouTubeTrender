import entity.YouTubeVideo;
import exception.YouTubeDataParserException;
import service.YouTubeDataParser;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class YouTubeTrender {

    public static void test1() throws FileNotFoundException {
        System.out.println("Performing Test 1");
        String filename = "data/youtubedata_15_50.json";
        int expectedSize = 50;

        System.out.println("Testing the file: " + filename);
        System.out.println("Expecting size of: " + expectedSize);

        // Read data
        JsonReader jsonReader = Json.createReader(new FileInputStream(filename));
        JsonObject jobj = jsonReader.readObject();

        // read the values of the item field
        var items = jobj.getJsonArray("items");

        System.out.println("Size of input: " + items.size());
        System.out.println("Success: " + (expectedSize == items.size()));
    }

    public static void test2() {
        System.out.println("Performing Test 2");
        String filename = "data/youtubedata_loremipsum.json";
        int expectedSize = 10;

        System.out.println("Testing the file: " + filename);
        System.out.println("Expecting size of: " + expectedSize);

        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parseFromFile(filename);
            System.out.println("Found size: " + list.size());
        } catch (YouTubeDataParserException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("YouTube Trender Application");
        test1();
        test2();
    }
}
