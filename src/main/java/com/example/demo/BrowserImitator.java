package com.example.demo;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
//import ru.psu.digitalcurrency.aspect.TimeLogging;

import java.util.Optional;

@Component
@Slf4j
public class BrowserImitator {
    private final RestTemplate restTemplate = new RestTemplate();
    private final static HttpEntity<?> HEADERS = new HttpEntity<>(new HttpHeaders() {
        {
            add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0");
        }
    });

//    @TimeLogging
    public <T> Optional<T> get(String url, Class<T> type, User user) {
        try {
            log.info(String.format("Получение данных по адресу: %s", url));
//            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, null, type);

            // set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<User> entity = new HttpEntity<>(user, headers);

            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, entity, type);
            if (response.getStatusCode() != HttpStatus.OK) {
                log.error(String.format("Статус ответа: %d", response.getStatusCodeValue()));
                return Optional.empty();
            }
            if (response.getBody() == null) {
                log.error("В ответе нет данных");
                return Optional.empty();
            }
            return Optional.of(response.getBody());
        } catch (RestClientException e) {
            log.error("Ошибка при получении данных", e);
            return Optional.empty();
        }
    }
}
