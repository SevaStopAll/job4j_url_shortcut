package ru.job4j.url_shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.service.SiteService;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/sites")
public class SiteController {

    private final SiteService sites;
    private final ObjectMapper objectMapper;

    @PostMapping("/convert")
    public ResponseEntity<Site> convert(@RequestBody Site site) {

        if (login == null || password == null) {
            throw new NullPointerException("Login and password mustn't be empty");
        }
        return new ResponseEntity<>(
                this.persons.save(person),
                HttpStatus.CREATED
        );
    }


}
