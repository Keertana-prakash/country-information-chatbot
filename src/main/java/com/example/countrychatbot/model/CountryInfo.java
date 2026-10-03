package com.example.countrychatbot.model;

public class CountryInfo {

    private String countryName;
    private String capital;
    private String nationalAnimal;
    private String nationalFlower;

    public CountryInfo() {}

    public CountryInfo(String countryName, String capital, String nationalAnimal, String nationalFlower) {
        this.countryName = countryName;
        this.capital = capital;
        this.nationalAnimal = nationalAnimal;
        this.nationalFlower = nationalFlower;
    }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    public String getNationalAnimal() { return nationalAnimal; }
    public void setNationalAnimal(String nationalAnimal) { this.nationalAnimal = nationalAnimal; }

    public String getNationalFlower() { return nationalFlower; }
    public void setNationalFlower(String nationalFlower) { this.nationalFlower = nationalFlower; }
}
