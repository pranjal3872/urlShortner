package urlshortener.service;

import java.util.Random;
import org.springframework.stereotype.Service;
import java.net.URI;
import urlshortener.model.Url;
import urlshortener.repository.UrlRepository;

@Service
public class UrlService {

    private final UrlRepository repository;

    private boolean isValidUrl(String url) {

        try {

            URI uri = new URI(url);

            return uri.getScheme() != null
                    && (uri.getScheme().equals("http")
                    || uri.getScheme().equals("https"));

        } catch (Exception e) {

            return false;
        }
    }

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public Url createShortUrl(
            String originalUrl,
            String alias) {

        if (!isValidUrl(originalUrl)) {
            throw new RuntimeException(
                    "Invalid URL"
            );
        }

        String shortCode;

        if (alias != null &&
            !alias.isBlank()) {

            if (repository.existsByShortCode(alias)) {

                throw new RuntimeException(
                        "Alias already exists"
                );
            }

            shortCode = alias;

        } else {

            shortCode = generateCode();
        }

        Url url =
                new Url(
                        shortCode,
                        originalUrl
                );

        return repository.save(url);
    }

    private String generateCode() {

        String chars =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {

            code.append(
                    chars.charAt(
                            random.nextInt(chars.length())
                    )
            );
        }

        return code.toString();
    }

    public Url getByShortCode(String shortCode) {
        return repository.findByShortCode(shortCode);
    }

    public Url save(Url url) {
        return repository.save(url);
    }
}

