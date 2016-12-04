package com.bigspotteddog.number.translator;

import java.io.IOException;

public class NumberTranslator {

    public String translate(long number, String languageCode) throws IOException {
        Language language = LanguageFactory.instance().getLanguage(languageCode);
        return language.getNumber((int) number);
    }

}
