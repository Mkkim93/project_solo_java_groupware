package com.group.web.weather.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherApiController {

    private final String apiKey = "88e58bad8e19b7ff3783d1a6b26f9fb0";
    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric&lang=kr";

    @GetMapping("/current")
    public ResponseEntity getWeather(@RequestParam String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl.replace("{city}", city).replace("{apiKey}", apiKey);
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}
