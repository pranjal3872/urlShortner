package urlshortener.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urlshortener.model.AnalyticsResponse;
import urlshortener.model.Url;
import urlshortener.model.UrlRequest;
import urlshortener.service.UrlService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api")
@CrossOrigin(
    origins = {
        "https://url-shortner-chi-mocha.vercel.app"
    }
)
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(
            @RequestBody UrlRequest request) {

        try {

            Url url =
                    service.createShortUrl(
                            request.getUrl(),
                            request.getAlias()
                    );

            return ResponseEntity.ok(url);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    e.getMessage()
                            )
                    );
        }
    }
    
    @GetMapping("/analytics")
    public List<Url> getAllAnalytics() {
        return service.getAllUrls();
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