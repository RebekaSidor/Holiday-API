package gr.hua.dit.holidayAPI.core.impl;

import gr.hua.dit.holidayAPI.config.HolidayApiProperties;
import gr.hua.dit.holidayAPI.core.HolidayService;
import gr.hua.dit.holidayAPI.core.model.PublicHoliday;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class HolidayServiceImpl implements HolidayService {

    private final RestTemplate restTemplate;
    private final HolidayApiProperties properties;

    // 👇 ΕΔΩ το Spring κάνει inject
    public HolidayServiceImpl(RestTemplate restTemplate,
                              HolidayApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    @Cacheable("holidays")
    public boolean isHoliday(LocalDate date) {

        String url = properties.getBaseUrl()
                + "/PublicHolidays/"
                + date.getYear()
                + "/"
                + properties.getCountry();

        PublicHoliday[] holidays =
                restTemplate.getForObject(url, PublicHoliday[].class);

        if (holidays == null) {
            return false;
        }

        return Arrays.stream(holidays)
                .anyMatch(h -> h.getDate().equals(date));
    }
}
