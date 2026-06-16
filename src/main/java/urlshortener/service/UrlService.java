package urlshortener.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import urlshortener.model.Url;
import urlshortener.repository.UrlRepository;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public Url createShortUrl(String originalUrl) {

        String shortCode = generateCode();

        Url url = new Url(shortCode, originalUrl);

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

