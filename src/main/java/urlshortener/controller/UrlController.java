package urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import urlshortener.model.Url;
import urlshortener.model.UrlRequest;
import urlshortener.service.UrlService;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("/shorten")
    public Url shortenUrl(@RequestBody UrlRequest request) {

        return service.createShortUrl(
                request.getUrl()
        );
    }
}