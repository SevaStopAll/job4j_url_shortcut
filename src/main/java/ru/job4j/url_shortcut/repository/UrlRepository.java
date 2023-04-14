package ru.job4j.url_shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.url_shortcut.domain.URL;

@Component
public interface UrlRepository extends CrudRepository<URL, Integer> {
    URL findByUrl(String url);
    URL findByCode(String code);

    @Modifying
    @Query(value = """
            UPDATE Url u
            SET u.count = u.count + 1
            WHERE u.code = ?1
            """)
    void incrementByCode(String code);
}