package ru.job4j.shortcut.service;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.repository.SiteRepository;

import javax.transaction.Transactional;
import java.util.Optional;

import static java.util.Collections.emptyList;

@AllArgsConstructor
@Service
public class SiteService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Site site = sites.findByLogin(login);
        if (site == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(site.getLogin(), site.getPassword(), emptyList());
    }

    public <T> String toJson(T object) {
        var gson = new Gson();
        return gson.toJson(object);
    }

}
