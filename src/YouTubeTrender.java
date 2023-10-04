import comparator.YouTubeVideoChannelComparator;
import comparator.YouTubeVideoDateComparator;
import comparator.YouTubeVideoViewComparator;
import comparator.YoutubeVideoDescriptionComparator;
import entity.YouTubeVideo;
import exception.YouTubeDataParserException;
import service.TrendingTopicAnalyzer;
import service.YouTubeDataParser;
import service.YouTubeVideoIndexer;

import java.util.List;
import java.util.Map;

public class YouTubeTrender {

    public static void test1() {
        System.out.println("Performing Test 1");
        String filename = "data/youtubedata_15_50.json";
        int expectedSize = 50;

        System.out.println("Testing the file: " + filename);
        System.out.println("Expecting size of: " + expectedSize);

        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parse(filename);
            System.out.println("Size of input: " + list.size());
            System.out.println("Success: " + (expectedSize == list.size()));
        } catch (YouTubeDataParserException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        System.out.println("Performing Test 2");
        String filename = "data/youtubedata_loremipsum.json";
        int expectedSize = 10;

        System.out.println("Testing the file: " + filename);
        System.out.println("Expecting size of: " + expectedSize);

        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parse(filename);
            System.out.println("Found size: " + list.size());
        } catch (YouTubeDataParserException e) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        System.out.println("Performing Test 3: Sorting videos by different properties");
        String filename = "data/youtubedata_loremipsum.json";

        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parse(filename);

            // Sort the videos by number of views using the YouTubeVideoViewComparator
            list.sort(new YouTubeVideoViewComparator());
            System.out.println("Sorted by views:");
            for (YouTubeVideo video : list) {
                System.out.println(video.getTitle() + " - " + video.getViewCount() + " views");
            }

            // Sort by channel title using the YouTubeVideoChannelComparator
            list.sort(new YouTubeVideoChannelComparator());
            System.out.println("\nSorted by channel title:");
            for (YouTubeVideo video : list) {
                System.out.println(video.getTitle() + " - " + video.getTitle());
            }

            // Sort by date using the YouTubeVideoDateComparator
            list.sort(new YouTubeVideoDateComparator());
            System.out.println("\nSorted by date:");
            for (YouTubeVideo video : list) {
                System.out.println(video.getDate() + " - " + video.getTitle());
            }

            // Sort by description using the YouTubeVideoDescriptionComparator
            list.sort(new YoutubeVideoDescriptionComparator());
            System.out.println("\nSorted by description length:");
            for (YouTubeVideo video : list) {
                System.out.println(video.getDescription().length() + " - " + video.getTitle());
            }

        } catch (YouTubeDataParserException e) {
            e.printStackTrace();
        }
    }

    public static void test4() {
        System.out.println("Performing Test 4: Indexing word usage for trending topics");
        String filename = "data/youtubedata_loremipsum.json";

        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parse(filename);

            TrendingTopicAnalyzer analyzer = new TrendingTopicAnalyzer();
            Map<String, Integer> wordCountMap = analyzer.indexWordUsage(list);

            // Print the indexed words and their counts
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Use the YouTubeVideoIndexer to demonstrate its functionality
            YouTubeVideoIndexer indexer = new YouTubeVideoIndexer();

            // Index the videos
            indexer.index(list);

            // Retrieve word counts using the indexer
            System.out.println("\nWord Counts using YouTubeVideoIndexer:");
            Map<String, Integer> indexerWordCounts = indexer.getWordCounts();
            for (Map.Entry<String, Integer> entry : indexerWordCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Get videos associated with a specific word
            String wordToSearch = "example"; // Replace with any word you want to search for
            List<YouTubeVideo> videosWithWord = indexer.getVideosAssociatedWithWord(wordToSearch);
            System.out.println("\nVideos containing the word '" + wordToSearch + "':");
            for (YouTubeVideo video : videosWithWord) {
                System.out.println(video.getTitle());
            }

            // Find the most used word
            String mostUsedWord = indexer.getMostUsedWord();
            System.out.println("\nThe most used word is: " + mostUsedWord);

        } catch (YouTubeDataParserException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}

