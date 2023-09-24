package entity;

/**
 * Represents a YouTube video with various attributes.
 */
public class YouTubeVideo {
    private String channelId;
    private String channelTitle;
    private String publishedAt; // You can use java.util.Date or java.time.LocalDate based on your needs
    private String title;
    private String description;
    private long viewCount;

    public YouTubeVideo() {}

    /**
     * Gets the channel ID of the video.
     * @return the channel ID.
     */
    public String getChannelId() {
        return channelId;
    }


    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }
}

