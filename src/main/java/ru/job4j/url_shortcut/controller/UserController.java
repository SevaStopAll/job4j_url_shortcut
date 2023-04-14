package ru.job4j.url_shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.domain.Site;
import ru.job4j.url_shortcut.service.SiteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private SiteService users;
    private BCryptPasswordEncoder encoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());

    private final ObjectMapper objectMapper;

    public UserController(SiteService users,
                          BCryptPasswordEncoder encoder,
                          ObjectMapper objectMapper) {
        this.users = users;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/registration")
    public void signUp(@Valid @RequestBody Site site, HttpServletResponse response) throws IOException {
        var siteName = site.getSite();
        if (siteName == null) {
            throw new NullPointerException("Sitename mustn't be empty");
        }
        if (users.findBySite(siteName).isEmpty()) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setContentType("application/json");
            site.setLogin("TEST");
            site.setPassword("TEST");
            response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
                put("login", site.getLogin());
                put("password", site.getPassword());
            }}));
            site.setPassword(encoder.encode(site.getPassword()));
            users.save(site);
        } else {
            throw new IllegalArgumentException("This site is already in the base");
        }
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
