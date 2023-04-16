package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.service.SiteService;
import ru.job4j.shortcut.utils.Generator;
import ru.job4j.shortcut.domain.URL;
import ru.job4j.shortcut.service.UrlService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/sites")
public class SiteController {
    private final ObjectMapper objectMapper;
    private final UrlService urls;

    private final SiteService sites;

    private BCryptPasswordEncoder encoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class.getSimpleName());

    @PostMapping("/registration")
    public ResponseEntity<String> signUp(@Valid @RequestBody Site site) {
        var siteName = site.getSite();
        if (siteName == null) {
            throw new NullPointerException("Sitename mustn't be empty");
        }
        if (sites.findBySite(siteName).isEmpty()) {
            site.setLogin(Generator.generate());
            site.setPassword(Generator.generate());
            var body = new HashMap<>() {{
                put("registration", true);
                put("password", site.getPassword());
                put("login", site.getLogin());
            }}.toString();
            site.setPassword(encoder.encode(site.getPassword()));
            sites.save(site);
            return ResponseEntity.
                    status(HttpStatus.CREATED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(body.length())
                    .body(body);
        } else {
            var body = new HashMap<>() {{
                put("registration", false);
                put("password", "");
                put("login", "");
            }}.toString();
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(body.length())
                    .body(body);
        }
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@RequestBody URL url) {
        var code = Generator.generate();
        url.setCode(code);
        urls.save(url);
        var body = new HashMap<>() {{
            put("code", code);
        }}.toString();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(body.length())
                .body(body);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity redirect(@PathVariable String code) {
        Optional<URL> url = urls.findUrlByCode(code);
        if (url.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
        urls.incrementByCode(code);
        var body =  url.get().getUrl();
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Status", "HTTP CODE - 302 REDIRECT URL")
                .body(body);
    }

    @GetMapping("/statistic")
    public ResponseEntity<String> getStatistic() {

        var body = sites.toJson(urls.mapBuUrlCount(urls.findAll()));
        return ResponseEntity.status(HttpStatus.OK)
                .header("Statistic", "Successful")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(body.length())
                .body(body);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }
}
