package urlshortener.controller;
import org.springframework.web.bind.annotation.*;
import urlshortener.model.AnalyticsResponse;
import urlshortener.model.Url;
import urlshortener.model.UrlRequest;
import urlshortener.service.UrlService;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public Url shortenUrl(
            @RequestBody UrlRequest request) {

        return service.createShortUrl(
                request.getUrl()
        );
    }

    @GetMapping("/analytics/{shortCode}")
    public AnalyticsResponse getAnalytics(
            @PathVariable String shortCode) {

        Url url =
                service.getByShortCode(shortCode);

        if(url == null){
            throw new RuntimeException("Short URL not found");
        }

        return new AnalyticsResponse(
                url.getShortCode(),
                url.getOriginalUrl(),
                url.getClickCount()
        );
    }
}