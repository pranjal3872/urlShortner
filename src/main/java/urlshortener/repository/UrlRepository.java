package urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import urlshortener.model.Url;

public interface UrlRepository extends MongoRepository<Url, String> {

    Url findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);
}