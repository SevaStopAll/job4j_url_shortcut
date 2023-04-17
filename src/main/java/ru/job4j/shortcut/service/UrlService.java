package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.domain.URL;
import ru.job4j.shortcut.repository.UrlRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<?, ?> mapBuUrlCount(List<URL> list) {
        var map = new HashMap<>();
        list.forEach(p -> map.put(p.getUrl(), p.getCount()));
        return map;
    }

    public List<URL> findAll() {
        return urls.findAll();
    }

    @Transactional
    public boolean findAndIncrement(String code) {
        if (!findUrlByCode(code).isPresent()) {
            return false;
        }
        incrementByCode(code);
        return true;
    }

}


