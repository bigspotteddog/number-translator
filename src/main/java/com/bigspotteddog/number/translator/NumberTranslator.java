package com.bigspotteddog.number.translator;

import java.io.IOException;

public class NumberTranslator {

    public String translate(long number, String languageCode) throws IOException {
        Language language = LanguageFactory.instance().getLanguage(languageCode);

        StringBuilder buf = new StringBuilder();

        int scale = getScale(number);
        if (scale == -1) {
            append(buf, language.getNumber(0));
        } else {
            translate(number, scale, language, buf);
        }

        return buf.toString();
    }

    private StringBuilder translate(long number, int scale, Language language, StringBuilder buf) {
        String words = null;

        long divisor = (long) Math.pow(1000, scale);
        long digits = number / divisor;

        if (words == null) {
            if (digits >= 100) {
                words = language.getNumber((int) digits / 100);
                if (words != null) {
                    append(buf, words);
                    append(buf, language.getScale(0));
                }
            }

            if (buf.length() > 0) {
                digits = digits % 100;
                if (digits > 0) {
                    append(buf, "and");
                }
            }

            if (digits > 0) {
                if (digits > 20) {
                    words = language.getNumber((int) digits / 10 * 10);
                    append(buf, words);

                    digits %= 10;
                }

                if (digits > 0) {
                    words = language.getNumber((int) digits);
                    append(buf, words);
                }
            }
        }

        if (scale > 0) {
            if (digits > 0) {
                words = language.getScale(scale);
                append(buf, words);
            }

            translate(number % divisor, scale - 1, language, buf);
        }

        return buf;
    }

    protected int getScale(long number) {
        int scale = -1;
        while (number > 0) {
            number /= 1000;
            scale++;
        }
        return scale;
    }

    private StringBuilder append(StringBuilder buf, String words) {
        if (words != null) {
            if (buf.length() > 0) {
                buf.append(' ');
            }

            if (buf.length() == 0) {
                char[] c = words.toCharArray();
                c[0] = Character.toUpperCase(c[0]);
                words = new String(c);
            }

            buf.append(words);
        }
        return buf;
    }
}
