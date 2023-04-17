package ru.job4j.shortcut.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import ru.job4j.shortcut.Main;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.domain.URL;
import ru.job4j.shortcut.service.SiteService;
import ru.job4j.shortcut.service.UrlService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class SiteControllerTest {

        @MockBean
        private SiteService sites;

        @MockBean
        private UrlService urls;

        @Autowired
        private MockMvc mockMvc;

        @Test
        @WithMockUser
        public void whenSignUp() throws Exception {
            var body = new HashMap<>() {{
                put("site", "test.ru");
            }};
            var om = new ObjectMapper().writeValueAsString(body);
            this.mockMvc.perform(post("/sites/registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om))
                    .andDo(print())
                    .andExpect(status().isCreated());
            ArgumentCaptor<Site> argument = ArgumentCaptor.forClass(Site.class);
            verify(sites).save(argument.capture());
            assertThat(argument.getValue().getSite()).isEqualTo("test.ru");
        }

        @Test
        @WithMockUser
        public void whenConvert() throws Exception {
            var body = new HashMap<>() {{
                put("url", "test.ru");
            }};
            var om = new ObjectMapper().writeValueAsString(body);
            this.mockMvc.perform(post("/sites/convert")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om))
                    .andDo(print())
                    .andExpect(status().isCreated());
            ArgumentCaptor<URL> argument = ArgumentCaptor.forClass(URL.class);
            verify(urls).save(argument.capture());
            assertThat(argument.getValue().getUrl()).isEqualTo("test.ru");
        }

    @Test
    @WithMockUser
    public void whenRedirectButNotFound() throws Exception {
        when(urls.findAndIncrement("testCode")).thenReturn(false);
        this.mockMvc.perform(get("/sites/redirect/{code}", "testCode"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void whenRedirect() throws Exception {
            URL url = new URL();
            url.setUrl("site.test");
        when(urls.findAndIncrement("testCode")).thenReturn(true);
        when(urls.findUrlByCode("testCode")).thenReturn(Optional.of(url));
        this.mockMvc.perform(get("/sites/redirect/{code}", "testCode"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void whenGetStatistics() throws Exception {
        HashMap body = new HashMap<>() {{
            put("test.com", "total : 2");
        }};
        var list = List.of(new URL());
        var json = new ObjectMapper().writeValueAsString(body);
        when(urls.findAll()).thenReturn(list);
        when(urls.mapBuUrlCount(list)).thenReturn(body);
        when(sites.toJson(body)).thenReturn(json);
        this.mockMvc.perform(get("/sites/statistic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }
}
