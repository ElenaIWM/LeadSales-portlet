package com.iwmn.a1.lead.model;

public class LanguageModel {
    private final String activeLanguage;

    private String[] supportedLanguages = new String[]{"mk_MK", "en_US", "sq_AL"};

    public LanguageModel(String activeLanguage) {
        this.activeLanguage = activeLanguage;
    }

    public String displayActive(String lang, String activePresentment, String inactivePresentment) {
        return this.isActive(lang) ? activePresentment : inactivePresentment;
    }

    public boolean isActive(String lang) {
        return this.activeLanguage.equals(lang);
    }


    public String[] getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(String[] supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }
}
