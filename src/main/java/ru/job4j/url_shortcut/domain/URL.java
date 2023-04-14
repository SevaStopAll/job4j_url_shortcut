package ru.job4j.url_shortcut.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class URL {
    private String url;
    private String code;
}
