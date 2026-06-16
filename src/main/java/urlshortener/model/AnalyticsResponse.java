package urlshortener.model;

public class AnalyticsResponse {

    private String shortCode;
    private String originalUrl;
    private int clickCount;

    public AnalyticsResponse(
            String shortCode,
            String originalUrl,
            int clickCount) {

        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.clickCount = clickCount;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public int getClickCount() {
        return clickCount;
    }
}