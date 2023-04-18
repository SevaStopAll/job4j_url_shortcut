package ru.job4j.shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SiteDto {
    @Setter
    @Getter
    private String login;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private boolean registration;
}
