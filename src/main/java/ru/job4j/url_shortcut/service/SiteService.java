package ru.job4j.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.domain.Site;
import ru.job4j.url_shortcut.repository.SiteRepository;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class SiteService {
    private final SiteRepository sites;

    public Site findBySite(String site) {
        return sites.findBySite(site);
    }

    public Site save(Site site) {
        return sites.save(site);
    }

    @Transactional
    public boolean update(Site site) {
        if (sites.findById(site.getId()).isPresent()) {
            sites.save(site);
            return true;
        }
        return false;
    }


}
