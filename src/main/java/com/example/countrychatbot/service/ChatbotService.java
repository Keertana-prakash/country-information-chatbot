package com.example.countrychatbot.service;

import com.example.countrychatbot.config.CountryDataLoader;
import com.example.countrychatbot.model.CountryInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChatbotService {

    private final CountryDataLoader countryDataLoader;

    public ChatbotService(CountryDataLoader countryDataLoader) {
        this.countryDataLoader = countryDataLoader;
    }

    public CountryInfo getCountryInfo(String countryName) {
        return countryDataLoader.getCountryByName(countryName);
    }

    public Collection<CountryInfo> getAllCountries() {
        return countryDataLoader.getAllCountries().values();
    }

    public boolean countryExists(String countryName) {
        return countryDataLoader.getCountryByName(countryName) != null;
    }
}
