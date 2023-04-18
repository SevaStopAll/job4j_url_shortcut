package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.domain.Site;

import java.util.Optional;

@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {
    Site findBySite(String site);

    Optional<Site> findByLogin(String login);
}
