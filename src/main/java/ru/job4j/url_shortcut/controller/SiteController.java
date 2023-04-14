package ru.job4j.url_shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.utils.Generator;
import ru.job4j.url_shortcut.domain.URL;
import ru.job4j.url_shortcut.service.UrlService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/sites")
public class SiteController {
    private final ObjectMapper objectMapper;
    private final UrlService urls;

    @PostMapping("/convert")
    public void convert(@RequestBody URL url, HttpServletResponse response) throws IOException {
        if (url.getUrl() == null) {
            throw new NullPointerException("You sent an empty URL");
        }
        if (urls.findCodeByUrl(url.getUrl()).isEmpty()) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setContentType("application/json");
            String code = Generator.generate();
            url.setCode(code);
            response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
                put("code", url.getCode());
            }}));
            urls.save(url);
        } else {
            throw new IllegalArgumentException("This site is already in the base");
        }
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity redirect(@PathVariable String code) {
        Optional<URL> url = urls.findUrlByCode(code);
        if (url.isEmpty()) {
            throw new IllegalArgumentException("Your code is incorrect");
        }
        urls.incrementByCode(code);
        var body =  url.get().getUrl();
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Status","HTTP CODE - 302 REDIRECT URL")
                .body(body);
    }
}
