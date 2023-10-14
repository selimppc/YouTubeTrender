package service;

import entity.WordInfo;
import entity.YouTubeVideo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TrendingTopicAnalyzer {

    private final Map<String, WordInfo> wordInfoMap = new HashMap<>();

    public Map<String, Integer> indexWordUsage(List<YouTubeVideo> videos) {
        for (YouTubeVideo video : videos) {
            indexWordsFromText(video.getTitle().toUpperCase(), video);
            indexWordsFromText(video.getDescription().toUpperCase(), video);
        }

        Map<String, Integer> wordCountMap = new HashMap<>();
        for (Map.Entry<String, WordInfo> entry : wordInfoMap.entrySet()) {
            wordCountMap.put(entry.getKey(), entry.getValue().getCount());
        }

        return wordCountMap;
    }

    private void indexWordsFromText(String text, YouTubeVideo video) {
        String[] words = text.split("\\W+"); // Split by non-word characters

        for (String word : words) {
            WordInfo wordInfo = wordInfoMap.getOrDefault(word, new WordInfo(word));
            wordInfo.incrementCount();
            wordInfo.addVideo(video);
            wordInfoMap.put(word, wordInfo);
        }
    }

    public WordInfo getWordInfo(String word) {
        return wordInfoMap.get(word);
    }

    public List<String> getSortedWordsByCount() {
        List<String> sortedWords = new ArrayList<>(wordInfoMap.keySet());
        sortedWords.sort((w1, w2) -> Integer.compare(wordInfoMap.get(w2).getCount(), wordInfoMap.get(w1).getCount()));
        return sortedWords;
    }

    public String getMostUsedWord() {
        return getSortedWordsByCount().get(0);
    }
}

