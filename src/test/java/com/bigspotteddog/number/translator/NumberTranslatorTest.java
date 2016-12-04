package com.bigspotteddog.number.translator;

import java.io.IOException;

import junit.framework.TestCase;

public class NumberTranslatorTest extends TestCase {

    private LanguageFactory factory;

    @Override
    protected void setUp() throws Exception {
        factory = LanguageFactory.instance();
        factory.clearLanguages();
    }

    public void test() throws IOException {
        NumberTranslator translator = new NumberTranslator();
        assertNotNull(translator);

        String words = translator.translate(1, "en-US");
        assertNotNull(words);
        assertEquals("one", words);

        words = translator.translate(0, "en-US");
        assertNotNull(words);
        assertEquals("zero", words);

        words = translator.translate(13, "en-US");
        assertNotNull(words);
        assertEquals("thirteen", words);

        words = translator.translate(85, "en-US");
        assertNotNull(words);
        assertEquals("eighty five", words);

        words = translator.translate(237, "en-US");
        assertNotNull(words);
        assertEquals("two hundred and thirty seven", words);
    }

    // Spike: Get numbers by scale (thousands, hundreds)
    public void testScale() {
        long number = 5237;

        long value = number;
        int scale = 0;
        while (value > 0) {
            value /= 1000;
            scale++;
        }
        assertEquals(2, scale);

        int divisor = (int) Math.pow(1000, scale - 1);
        assertEquals(1000, divisor);

        int digits = (int) number / divisor;
        assertEquals(5, digits);

        number %= divisor;
        scale--;

        divisor = (int) Math.pow(1000, scale - 1);
        assertEquals(1, divisor);

        digits = (int) number / divisor;
        assertEquals(237, digits);
    }
}
