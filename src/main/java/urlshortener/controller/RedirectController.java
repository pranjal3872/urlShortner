package urlshortener.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;

import urlshortener.model.Url;
import urlshortener.service.UrlService;

@RestController
public class RedirectController {

    private final UrlService service;

    public RedirectController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/{shortCode}")
    public void redirect(
            @PathVariable String shortCode,
            HttpServletResponse response)
            throws IOException {

        Url url = service.getByShortCode(shortCode);

        if (url == null) {
            response.sendError(404, "Short URL not found");
            return;
        }

        url.setClickCount(url.getClickCount() + 1);

        service.save(url);

        response.sendRedirect(url.getOriginalUrl());
    }
}
