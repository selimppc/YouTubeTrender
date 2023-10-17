import comparator.YouTubeVideoChannelComparator;
import comparator.YouTubeVideoDateComparator;
import comparator.YouTubeVideoViewComparator;
import comparator.YoutubeVideoDescriptionComparator;
import entity.WordInfo;
import entity.YouTubeVideo;
import exception.YouTubeDataParserException;
import service.TrendingTopicAnalyzer;
import service.YouTubeDataParser;

import java.util.List;
import java.util.Scanner;

public class YouTubeTrender {

    protected Scanner scanner;
    protected String filename;

    public YouTubeTrender(Scanner scanner) {
        this.scanner = scanner;
    }

    protected void inputYouTubeData() {
        System.out.print("Enter the absolute path of the JSON file: ");
        filename = scanner.nextLine();
    }

    protected void test1() {
        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parse(filename);
            System.out.println("Size of input: " + list.size());

            System.out.print("Want to print the output (Y/N): ");
            char printChoice = scanner.nextLine().charAt(0);
            if (printChoice == 'Y' || printChoice == 'y') {
                for (YouTubeVideo video : list) {
                    System.out.println(video.getTitle());
                }
            }

        } catch (YouTubeDataParserException e) {
            System.out.println("Error parsing the file: " + e.getMessage());
            filename = null;
        }
    }

    protected void test2(int sortChoice) {
        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parse(filename);

            switch (sortChoice) {
                case 1 -> list.sort(new YouTubeVideoChannelComparator());
                case 2 -> list.sort(new YouTubeVideoDateComparator());
                case 3 -> list.sort(new YouTubeVideoViewComparator());
                case 4 -> list.sort(new YoutubeVideoDescriptionComparator());
                default -> {
                    System.out.println("Invalid sorting choice.");
                    return;
                }
            }

            System.out.print("View sorted result (Y/N): ");
            char viewChoice = scanner.nextLine().charAt(0);
            if (viewChoice == 'Y' || viewChoice == 'y') {
                switch (sortChoice) {
                    case 1 -> System.out.println("\n== Sorted by channel title: ==");
                    case 2 -> System.out.println("\n== Sorted by published date: ==");
                    case 3 -> System.out.println("\n== Sorted by views: ==");
                    case 4 -> System.out.println("\n== Sorted by description length: ==");
                }
                for (YouTubeVideo video : list) {
                    System.out.println(video.getTitle());
                }
            }

        } catch (YouTubeDataParserException e) {
            e.printStackTrace();
        }
    }


    protected void test3() {
        try {
            YouTubeDataParser parser = new YouTubeDataParser();
            List<YouTubeVideo> list = parser.parse(filename);

            TrendingTopicAnalyzer analyzer = new TrendingTopicAnalyzer();
            analyzer.indexWordUsage(list);

            while (true) {
                System.out.println("\nAnalyze Trending Topics:");
                System.out.println("1. Find word and its count");
                System.out.println("2. Find all videos that use a specific word");
                System.out.println("3. Find the word that is used the most");
                System.out.println("4. Display list of words sorted by their counts");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = getUserChoice(1, 5);
                switch (choice) {
                    case 1:
                        System.out.print("Enter the word: ");
                        String word = scanner.nextLine().toUpperCase();
                        WordInfo wordInfo = analyzer.getWordInfo(word);
                        if (wordInfo != null) {
                            System.out.println("Count of '" + word + "': " + wordInfo.getCount());
                        } else {
                            System.out.println("Word not found.");
                        }
                        break;
                    case 2:
                        displayVideosForWord(analyzer);  // Use the refactored method here
                        break;
                    case 3:
                        String mostUsedWord = analyzer.getMostUsedWord();
                        System.out.println("The most used word is: " + mostUsedWord);
                        break;
                    case 4:
                        List<String> sortedWords = analyzer.getSortedWordsByCount();
                        System.out.println("Words sorted by their counts:");
                        for (String sortedWord : sortedWords) {
                            System.out.println(sortedWord + ": " + analyzer.getWordInfo(sortedWord).getCount());
                        }
                        break;
                    case 5:
                        return;  // Back to Main Menu
                }
            }
        } catch (YouTubeDataParserException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        while (true) {
            displayMainMenu();
            int mainChoice = getUserChoice(1, 5);
            if ((mainChoice == 2 || mainChoice == 3 || mainChoice == 4) && filename == null) {
                System.out.println("You must input the YouTube data file path first.");
                inputYouTubeData();
            }

            switch (mainChoice) {
                case 1 -> inputYouTubeData();
                case 2 -> test1();
                case 3 -> {
                    while (true) {
                        displaySortMenu();
                        int sortChoice = getUserChoice(1, 5);
                        if (sortChoice == 5) {
                            break;  // Exit the sorting menu
                        }
                        test2(sortChoice);
                    }
                }
                case 4 -> test3();
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Unexpected error occurred.");
            }
        }
    }

    protected void displayVideosForWord(TrendingTopicAnalyzer analyzer) {
        System.out.print("Enter the word: ");
        String word = scanner.nextLine().trim();  // Ensure the word is read correctly
        WordInfo wordInfo = analyzer.getWordInfo(word);
        if (wordInfo != null) {
            System.out.println("Videos containing the word '" + word + "':");
            for (YouTubeVideo video : wordInfo.getVideos()) {
                System.out.println(video.getTitle());
            }
        } else {
            System.out.println("Word not found.");
        }
    }


    protected int getUserChoice(int min, int max) {
        int choice;
        int attempts = 0;
        int maxAttempts = 3;  // Set a maximum number of attempts

        while (attempts < maxAttempts) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
            attempts++;
            if (attempts < maxAttempts) {
                System.out.print("Enter your choice: ");  // Prompt the user again for their choice
            }
        }
        throw new RuntimeException("Maximum number of attempts reached.");
    }


    protected void displayMainMenu() {
        System.out.println("\nYoutube Trender Menu:");
        System.out.println("1. Input Youtube data from File");
        System.out.println("2. Parse data from the File");
        System.out.println("3. Sort Videos");
        System.out.println("4. Analyze Trending Topics");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }


    protected void displaySortMenu() {
        System.out.println("\nSort Videos:");
        System.out.println("1. Sort by Channel Title");
        System.out.println("2. Sort by Published Date");
        System.out.println("3. Sort by View Count");
        System.out.println("4. Sort by Description Length");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your sorting choice: ");
    }


    public static void main(String[] args) {
        YouTubeTrender trender = new YouTubeTrender(new Scanner(System.in));
        trender.run();
    }

}

