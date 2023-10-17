# YouTubeTrender
YouTubeTrender


# Questions:

### Reflect on any challenges or difficulties faced during the development process.

    During the development process, several challenges arose. One of the primary challenges was handling and parsing the data from YouTube videos, especially when dealing with JSON structures. Ensuring that the data was correctly parsed and mapped to our entities was crucial. Additionally, there were instances where certain methods or constructors were missing or not aligned with the expected functionality, leading to errors during testing.

### Describe how you resolved these challenges or implemented workarounds.

    To address the data parsing challenge, we utilized the JSON-P library, which provided a robust way to handle JSON data. We also implemented helper methods to handle potential null values and different data types within the JSON. For issues related to missing methods or constructors, we either modified the main classes to include the necessary functionality or adjusted our testing approach to work with the existing structure. For instance, when faced with a constructor mismatch in the YouTubeVideo class during testing, we opted to use setter methods to populate the video objects.

### Related to sets, lists, and maps, explain the choice of data structures used in your application and justify why specific data structures were chosen for certain tasks.

    In our application, we made extensive use of lists, sets, and maps to manage and process the data:

    Lists: We used lists, specifically ArrayList, to maintain a collection of YouTube videos and word items. Lists were chosen because they provide ordered collections and allow for easy iteration, which was essential when parsing and indexing words from video titles and descriptions.

    Sets: In the YouTubeVideoIndexer class, a HashSet was used to store videos associated with specific words. Sets were chosen for this task because they ensure that each video is unique, preventing any duplicates. This is especially important when indexing words from both titles and descriptions, where a video might be added multiple times for different words.

    Maps: The HashMap data structure was extensively used, especially in the TrendingTopicAnalyzer and YouTubeVideoIndexer classes. Maps provide a key-value pair relationship, which was ideal for associating specific words with their corresponding information (like word count or associated videos). The constant-time complexity for retrieval operations in HashMap made it an excellent choice for tasks where we needed to quickly access word information based on a given key.

    In summary, the choice of data structures was driven by the specific requirements of each task, ensuring efficient data management and processing throughout the application.
