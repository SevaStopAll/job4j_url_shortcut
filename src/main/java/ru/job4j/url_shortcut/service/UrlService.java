package ru.job4j.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url_shortcut.domain.Site;
import ru.job4j.url_shortcut.domain.URL;
import ru.job4j.url_shortcut.repository.UrlRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {
    private final UrlRepository urls;

    public Optional<URL> findCodeByUrl(String url) {
        return Optional.ofNullable(urls.findByUrl(url));
    }

    public Optional<URL> findUrlByCode(String code) {
        return Optional.ofNullable(urls.findByCode(code));
    }

    @Transactional
    public void incrementByCode(String code) {
        urls.incrementByCode(code);
    }
    public Optional<URL> save(URL url) {
        return Optional.of(urls.save(url));
    }
}
