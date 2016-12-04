package com.bigspotteddog.number.translator;

public class NumberTranslator {

    public String translate(long number, String languageCode) {
        Language language = LanguageFactory.instance().getLanguage(languageCode);
        return language.getNumber((int) number);
    }

}
