package entity;


public class YouTubeVideo {
    private String channel;
    private String date;
    private String title;
    private String description;
    private int viewCount;
    private String id;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "YoutubeVideo [channel=" + channel + ", date=" + date + ", title=" + title + ", description=" + description
                + ", viewCount=" + viewCount + ", id=" + id + "]";
    }

}


