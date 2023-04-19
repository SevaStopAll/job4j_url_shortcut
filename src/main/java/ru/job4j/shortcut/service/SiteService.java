package ru.job4j.shortcut.service;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.SiteDto;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.repository.SiteRepository;
import ru.job4j.shortcut.utils.Generator;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;

@AllArgsConstructor
@Service
public class SiteService implements UserDetailsService {
    private final SiteRepository sites;
    private BCryptPasswordEncoder encoder;

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
        Site site = sites.findByLogin(login).get();
        if (site == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(site.getLogin(), site.getPassword(), emptyList());
    }

    public <T> String toJson(T object) {
        var gson = new Gson();
        return gson.toJson(object);
    }

    public HashMap setData(Site site)  {
        String login;
        do {
            login = Generator.generate();
        } while (sites.findByLogin(login).isPresent());
        var body = new SiteDto(login, Generator.generate(), true);
        site.setLogin(body.getLogin());
        site.setPassword(encoder.encode(body.getPassword()));
        sites.save(site);
        return (HashMap) Map.of(
                "registation", body.isRegistration(),
                "country", body.getLogin(),
                "city", body.getPassword()
        );
    }

}
