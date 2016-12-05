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

        String words = translator.translate(0, "en-US");
        assertNotNull(words);
        assertEquals("Zero", words);

        words = translator.translate(13, "en-US");
        assertNotNull(words);
        assertEquals("Thirteen", words);

        words = translator.translate(85, "en-US");
        assertNotNull(words);
        assertEquals("Eighty five", words);

        words = translator.translate(5237, "en-US");
        assertNotNull(words);
        assertEquals("Five thousand two hundred and thirty seven", words);
    }

    public void testOtherNumbers() throws IOException {
        NumberTranslator translator = new NumberTranslator();
        assertNotNull(translator);

        String words = translator.translate(0, "en-US");
        assertNotNull(words);
        assertEquals("Zero", words);

        words = translator.translate(1, "en-US");
        assertNotNull(words);
        assertEquals("One", words);

        words = translator.translate(2, "en-US");
        assertNotNull(words);
        assertEquals("Two", words);

        words = translator.translate(3, "en-US");
        assertNotNull(words);
        assertEquals("Three", words);

        words = translator.translate(4, "en-US");
        assertNotNull(words);
        assertEquals("Four", words);

        words = translator.translate(5, "en-US");
        assertNotNull(words);
        assertEquals("Five", words);

        words = translator.translate(6, "en-US");
        assertNotNull(words);
        assertEquals("Six", words);

        words = translator.translate(7, "en-US");
        assertNotNull(words);
        assertEquals("Seven", words);

        words = translator.translate(8, "en-US");
        assertNotNull(words);
        assertEquals("Eight", words);

        words = translator.translate(9, "en-US");
        assertNotNull(words);
        assertEquals("Nine", words);

        words = translator.translate(10, "en-US");
        assertNotNull(words);
        assertEquals("Ten", words);

        words = translator.translate(11, "en-US");
        assertNotNull(words);
        assertEquals("Eleven", words);

        words = translator.translate(12, "en-US");
        assertNotNull(words);
        assertEquals("Twelve", words);

        words = translator.translate(13, "en-US");
        assertNotNull(words);
        assertEquals("Thirteen", words);

        words = translator.translate(14, "en-US");
        assertNotNull(words);
        assertEquals("Fourteen", words);

        words = translator.translate(15, "en-US");
        assertNotNull(words);
        assertEquals("Fifteen", words);

        words = translator.translate(16, "en-US");
        assertNotNull(words);
        assertEquals("Sixteen", words);

        words = translator.translate(17, "en-US");
        assertNotNull(words);
        assertEquals("Seventeen", words);

        words = translator.translate(18, "en-US");
        assertNotNull(words);
        assertEquals("Eighteen", words);

        words = translator.translate(19, "en-US");
        assertNotNull(words);
        assertEquals("Nineteen", words);

        words = translator.translate(20, "en-US");
        assertNotNull(words);
        assertEquals("Twenty", words);

        words = translator.translate(30, "en-US");
        assertNotNull(words);
        assertEquals("Thirty", words);

        words = translator.translate(40, "en-US");
        assertNotNull(words);
        assertEquals("Fourty", words);

        words = translator.translate(50, "en-US");
        assertNotNull(words);
        assertEquals("Fifty", words);

        words = translator.translate(60, "en-US");
        assertNotNull(words);
        assertEquals("Sixty", words);

        words = translator.translate(70, "en-US");
        assertNotNull(words);
        assertEquals("Seventy", words);

        words = translator.translate(80, "en-US");
        assertNotNull(words);
        assertEquals("Eighty", words);

        words = translator.translate(90, "en-US");
        assertNotNull(words);
        assertEquals("Ninety", words);

        words = translator.translate(100, "en-US");
        assertNotNull(words);
        assertEquals("One hundred", words);

        words = translator.translate(1000, "en-US");
        assertNotNull(words);
        assertEquals("One thousand", words);

        words = translator.translate(1000000, "en-US");
        assertNotNull(words);
        assertEquals("One million", words);

        words = translator.translate(1000000000, "en-US");
        assertNotNull(words);
        assertEquals("One billion", words);

        words = translator.translate(1000000000000L, "en-US");
        assertNotNull(words);
        assertEquals("One trillion", words);

        words = translator.translate(1000000000000000L, "en-US");
        assertNotNull(words);
        assertEquals("One quadrillion", words);

        words = translator.translate(1000000000000000000L, "en-US");
        assertNotNull(words);
        assertEquals("One quintillion", words);

        words = translator.translate(1001, "en-US");
        assertNotNull(words);
        assertEquals("One thousand and one", words);

        words = translator.translate(1000001, "en-US");
        assertNotNull(words);
        assertEquals("One million and one", words);

        words = translator.translate(1234567, "en-US");
        assertNotNull(words);
        assertEquals("One million two hundred and thirty four thousand five hundred and sixty seven", words);

        words = translator.translate(100036007, "en-US");
        assertNotNull(words);
        assertEquals("One hundred million thirty six thousand and seven", words);

        words = translator.translate(Long.MAX_VALUE, "en-US");
        assertNotNull(words);
        assertEquals("Nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion thirty six billion eight hundred and fifty four million seven hundred and seventy five thousand eight hundred and seven", words);
    }

    public void testBroken() throws IOException {
        NumberTranslator translator = new NumberTranslator();
        assertNotNull(translator);

        String words = translator.translate(100036007, "en-US");
        assertNotNull(words);
        assertEquals("One hundred million thirty six thousand and seven", words);

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

    public void testScaleSpike() {
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

    public void testGetDigitsSpike() {
        long number = 123456789;

        long digits = number % (long) Math.pow(1000, 2);
        System.out.println(digits);

        digits = digits / (long) Math.pow(1000, 1);
        System.out.println(digits);
    }
}
