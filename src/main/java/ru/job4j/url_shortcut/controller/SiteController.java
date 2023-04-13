package ru.job4j.url_shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url_shortcut.domain.Site;
import ru.job4j.url_shortcut.service.SiteService;

@RestController
@AllArgsConstructor
@RequestMapping("/sites")
public class SiteController {

    private final SiteService sites;

    @PostMapping("/registration")
    public ResponseEntity<Site> create(@RequestBody Site site) {
        var siteName = site.getSite();
        if (siteName == null) {
            throw new NullPointerException("Sitename mustn't be empty");
        }
        if (sites.findBySite(siteName).isEmpty()) {
            return new ResponseEntity<>()
        }
        sites.save(site);
        return new ResponseEntity<>();

    }

}
