package service;

import entity.YouTubeVideo;

import java.util.*;

public class YouTubeVideoIndexer {
    private final List<YoutTubeWordItem> items = new ArrayList<>();
    private final Map<String, YoutTubeWordItem> words = new HashMap<>();

    public void index(List<YouTubeVideo> list) {
        for (YouTubeVideo video : list) {
            String[] titleWords = video.getTitle().split("\\W+");
            String[] descriptionWords = video.getDescription().split("\\W+");

            indexWords(titleWords, video);
            indexWords(descriptionWords, video);
        }
    }

    private void indexWords(String[] wordArray, YouTubeVideo video) {
        for (String word : wordArray) {
            word = word.toLowerCase(); // Convert to lowercase for consistent indexing
            YoutTubeWordItem wordItem = words.get(word);
            if (wordItem == null) {
                wordItem = new YoutTubeWordItem(word);
                words.put(word, wordItem);
                items.add(wordItem);
            }
            wordItem.incrementCount();
            wordItem.addVideo(video);
        }
    }

    public List<YoutTubeWordItem> getSortedYouTubeWordItems() {
        items.sort(Comparator.comparingInt(YoutTubeWordItem::getCount).reversed());
        return items;
    }

    public Map<String, Integer> getWordCounts() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (YoutTubeWordItem item : items) {
            wordCountMap.put(item.getWord(), item.getCount());
        }
        return wordCountMap;
    }

    public List<YouTubeVideo> getVideosAssociatedWithWord(String word) {
        YoutTubeWordItem wordItem = words.get(word.toLowerCase());
        if (wordItem != null) {
            return (List<YouTubeVideo>) wordItem.getVideos();
        }
        return new ArrayList<>(); // Return empty list if word not found
    }

    public String getMostUsedWord() {
        YoutTubeWordItem mostUsedItem = Collections.max(items, Comparator.comparingInt(YoutTubeWordItem::getCount));
        return mostUsedItem.getWord();
    }
}

