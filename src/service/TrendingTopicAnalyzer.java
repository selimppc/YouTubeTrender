package service;

import entity.YouTubeVideo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Analyzes trending topics
 */
public class TrendingTopicAnalyzer {

    /**
     * Indexes word usage across the title and description
     * @param videos list of YouTube videos.
     * @return A map.
     */
    public Map<String, Integer> indexWordUsage(List<YouTubeVideo> videos) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        for (YouTubeVideo video : videos) {
            // Index words from the title
            indexWords(video.getTitle(), wordCountMap);

            // Index words from the description
            indexWords(video.getDescription(), wordCountMap);
        }

        return wordCountMap;
    }

    /**
     * @param text The text to index.
     * @param wordCountMap The map to update
     */
    private void indexWords(String text, Map<String, Integer> wordCountMap) {
        String[] words = text.split("\\W+"); // Split by non-word characters
        for (String word : words) {
            word = word.toLowerCase(); // Convert to lowercase for case-insensitive indexing
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
    }
}
