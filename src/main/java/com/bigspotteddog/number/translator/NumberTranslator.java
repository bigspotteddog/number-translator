package com.bigspotteddog.number.translator;

import java.io.IOException;

public class NumberTranslator {

    private static final int NO_SCALE = -1;
    private static final int MAX_SCALE = 6;

    public String translate(final long number, final String languageCode) throws IOException {
        Language language = LanguageFactory.instance().getLanguage(languageCode);

        StringBuilder buf = new StringBuilder();

        int scale = getScale(number);
        if (scale == NO_SCALE) {
            append(buf, language.getNumber(0));
        } else {
            if (number < 0) {
                append(buf, "negative");
                translate(number, scale, language, buf);
            } else {
                translate(number, scale, language, buf);
            }
        }

        return buf.toString();
    }

    private StringBuilder translate(final long number, final int scale, final Language language, final StringBuilder buf) {
        String words = null;

        long digits = number;

        long pow = 0;
        if (scale < MAX_SCALE) {
            pow = (long) Math.pow(1000, scale + 1);
            digits = number % pow;
        }

        digits = digits / (long) Math.pow(1000, scale);

        if (digits < 0) {
            digits = Math.abs(digits);
        }

        final long segment = digits;

        if (digits >= 100) {
            words = language.getNumber((int) digits / 100);
            if (words != null) {
                append(buf, words);
                append(buf, language.getScale(0));
            }

            digits = digits % 100;

            if (digits > 0) {
                append(buf, "and");
            }
        } else {
            if ((number > 1000 || number < -1000) && scale == 0 && digits > 0) {
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

        if (scale > 0) {
            if (segment > 0) {
                words = language.getScale(scale);
                append(buf, words);
            }

            translate(number, scale - 1, language, buf);
        }

        return buf;
    }

    protected int getScale(final long number) {
        long value = number;

        int scale = NO_SCALE;
        while (value / 1 != 0) {
            value /= 1000;
            scale++;
        }
        return scale;
    }

    private StringBuilder append(final StringBuilder buf, final String words) {
        if (words != null) {
            if (buf.length() > 0) {
                buf.append(' ');
            }

            if (buf.length() == 0) {
                char[] c = words.toCharArray();
                c[0] = Character.toUpperCase(c[0]);
                buf.append(c);
            } else {
                buf.append(words);
            }
        }
        return buf;
    }
}
