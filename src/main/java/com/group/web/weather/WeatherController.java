package com.group.web.weather;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @GetMapping("/info")
    public String getWeather() {
        return "/weather/info";
    }
}
