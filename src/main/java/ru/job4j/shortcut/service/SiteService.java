package ru.job4j.shortcut.service;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.repository.SiteRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SiteService {
    private final SiteRepository sites;

    public Optional<Site> findBySite(String site) {
        return Optional.ofNullable(sites.findBySite(site));
    }

    public Optional<Site> save(Site site) {
        return Optional.of(sites.save(site));
    }

    @Transactional
    public boolean update(Site site) {
        if (sites.findBySite(site.getSite()) != null) {
            sites.save(site);
            return true;
        }
        return false;
    }

    public <T> String toJson(T object) {
        var gson = new Gson();
        return gson.toJson(object);
    }

}
