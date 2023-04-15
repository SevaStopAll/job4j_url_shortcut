package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.shortcut.domain.URL;

import java.util.List;

@Component
public interface UrlRepository extends CrudRepository<URL, Integer> {
    URL findByUrl(String url);

    URL findByCode(String code);

    @Modifying
    @Query(value = """
            UPDATE URL u
            SET u.count = u.count + 1
            WHERE u.code = ?1
            """)
    void incrementByCode(String code);

    List<URL> findAll();
}