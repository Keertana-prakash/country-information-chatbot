package com.example.countrychatbot.model;

public class ConversationContext {

    private String selectedCountry;
    private String currentStep;

    public ConversationContext() {}

    public ConversationContext(String selectedCountry, String currentStep) {
        this.selectedCountry = selectedCountry;
        this.currentStep = currentStep;
    }

    public String getSelectedCountry() { return selectedCountry; }
    public void setSelectedCountry(String selectedCountry) { this.selectedCountry = selectedCountry; }

    public String getCurrentStep() { return currentStep; }
    public void setCurrentStep(String currentStep) { this.currentStep = currentStep; }
}
