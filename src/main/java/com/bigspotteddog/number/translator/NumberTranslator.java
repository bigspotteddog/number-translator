package com.bigspotteddog.number.translator;

import java.io.IOException;

public class NumberTranslator {

    public String translate(long number, String languageCode) throws IOException {
        Language language = LanguageFactory.instance().getLanguage(languageCode);

        StringBuilder buf = new StringBuilder();

        String words = language.getNumber((int) number);
        append(buf, words);

        if (words == null) {
            int digits = (int) number % 1000;
            if (digits >= 100) {
                words = language.getNumber(digits / 100);
                if (words != null) {
                    append(buf, words);
                    append(buf, "hundred");
                }

                if (number >= 100) {
                    append (buf, "and");
                }
            }

            digits = (int) number % 100;

            words = language.getNumber(digits / 10 * 10);
            append(buf, words);

            words = language.getNumber(digits % 10);
            append(buf, words);
        }

        return buf.toString();
    }

    private StringBuilder append(StringBuilder buf, String words) {
        if (words != null) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append(words);
        }
        return buf;
    }
}
