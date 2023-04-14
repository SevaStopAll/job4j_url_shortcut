package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url_shortcut.domain.Site;

@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {
    Site findBySite(String site);

    Site findByLogin(String login);
}
