package com.example.countrychatbot.config;

import com.example.countrychatbot.model.CountryInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryDataLoader {

    private final ObjectMapper objectMapper;
    private final Map<String, CountryInfo> countryMap = new HashMap<>();

    public CountryDataLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void loadData() {
        try {
            ClassPathResource resource = new ClassPathResource("countries_data.json");
            List<CountryInfo> countries = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<CountryInfo>>() {}
            );
            for (CountryInfo country : countries) {
                countryMap.put(country.getCountryName().toLowerCase(), country);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load country data from countries_data.json", e);
        }
    }

    public CountryInfo getCountryByName(String name) {
        return countryMap.get(name.toLowerCase());
    }

    public Map<String, CountryInfo> getAllCountries() {
        return countryMap;
    }
}
