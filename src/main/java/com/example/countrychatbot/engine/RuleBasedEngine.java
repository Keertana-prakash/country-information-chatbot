package com.example.countrychatbot.engine;

import com.example.countrychatbot.model.ConversationContext;
import com.example.countrychatbot.model.CountryInfo;
import com.example.countrychatbot.service.ChatbotService;
import org.springframework.stereotype.Component;

@Component
public class RuleBasedEngine {

    private final ChatbotService chatbotService;

    public RuleBasedEngine(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    public String processMessage(String message, ConversationContext context) {
        if (message == null || message.isBlank()) {
            return "Please type a message. Try asking: \"What is the capital of Japan?\"";
        }

        String input = message.trim().toLowerCase();

        if (isGreeting(input)) {
            return "Hello! I'm a country info chatbot. You can ask me things like:\n" +
                   "- \"What is the capital of France?\"\n" +
                   "- \"What is India's national animal?\"\n" +
                   "- \"Tell me about Brazil\"";
        }

        // Try to detect a country from the current message
        CountryInfo detectedCountry = detectCountry(input);

        if (detectedCountry != null) {
            // Update context with the newly mentioned country
            context.setSelectedCountry(detectedCountry.getCountryName());
            context.setCurrentStep("COUNTRY_SELECTED");
        }

        // Resolve which country to use: detected in this message, or remembered from context
        CountryInfo country = detectedCountry;
        if (country == null && context.getSelectedCountry() != null) {
            country = chatbotService.getCountryInfo(context.getSelectedCountry());
        }

        // If a topic keyword is present but no country is known at all, ask the user
        boolean hasTopic = input.contains("capital") || input.contains("animal")
                || input.contains("flower") || input.contains("about")
                || input.contains("info") || input.contains("tell me");

        if (country == null && hasTopic) {
            return "Please tell me which country you'd like to know about. " +
                   "For example: \"India\" or \"What is the capital of Japan?\"";
        }

        if (country == null) {
            return "I'm sorry, I don't have information about that country. " +
                   "Try asking about countries like India, Japan, Brazil, or Canada.";
        }

        if (input.contains("capital")) {
            return "The capital of " + country.getCountryName() + " is " + country.getCapital() + ".";
        }

        if (input.contains("national animal") || input.contains("animal")) {
            return "The national animal of " + country.getCountryName() + " is " + country.getNationalAnimal() + ".";
        }

        if (input.contains("national flower") || input.contains("flower")) {
            return "The national flower of " + country.getCountryName() + " is " + country.getNationalFlower() + ".";
        }

        if (input.contains("about") || input.contains("info") || input.contains("tell me")) {
            return formatGeneralInfo(country);
        }

        // User typed only a country name — confirm selection and prompt for a topic
        if (detectedCountry != null) {
            return "You selected " + country.getCountryName() + ". What would you like to know?\n" +
                   "You can ask about the capital, national animal, or national flower.";
        }

        return "I'm not sure what you're asking. Try questions like:\n" +
               "- \"What is the capital of France?\"\n" +
               "- \"What is India's national animal?\"\n" +
               "- \"Tell me about Brazil\"";
    }

    private boolean isGreeting(String input) {
        return input.equals("hi") || input.equals("hello") || input.equals("hey")
                || input.startsWith("hi ") || input.startsWith("hello ") || input.startsWith("hey ");
    }

    private CountryInfo detectCountry(String input) {
        for (CountryInfo country : chatbotService.getAllCountries()) {
            if (input.contains(country.getCountryName().toLowerCase())) {
                return country;
            }
        }
        return null;
    }

    private String formatGeneralInfo(CountryInfo country) {
        return "Here is some information about " + country.getCountryName() + ":\n" +
               "- Capital: " + country.getCapital() + "\n" +
               "- National Animal: " + country.getNationalAnimal() + "\n" +
               "- National Flower: " + country.getNationalFlower();
    }
}
