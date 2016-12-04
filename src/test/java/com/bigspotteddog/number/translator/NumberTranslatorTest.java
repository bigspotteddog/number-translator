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

        words = translator.translate(5237, "en-US");
        assertNotNull(words);
        assertEquals("five thousand two hundred and thirty seven", words);
    }

    public void testScaleImpl() {
        NumberTranslator translator = new NumberTranslator();
        int scale = translator.getScale(5237);
        assertEquals(1, scale);

        scale = translator.getScale(237);
        assertEquals(0, scale);

        scale = translator.getScale(1);
        assertEquals(0, scale);

        scale = translator.getScale(0);
        assertEquals(-1, scale);
    }

    // Spike: Get numbers by scale (thousands, hundreds)
    public void testScale() {
        long number = 5237;

        long value = number;
        int scale = -1;
        while (value > 0) {
            value /= 1000;
            scale++;
        }
        assertEquals(1, scale);

        int divisor = (int) Math.pow(1000, scale);
        assertEquals(1000, divisor);

        int digits = (int) number / divisor;
        assertEquals(5, digits);

        number %= divisor;
        scale--;

        divisor = (int) Math.pow(1000, scale);
        assertEquals(1, divisor);

        digits = (int) number / divisor;
        assertEquals(237, digits);
    }
}
