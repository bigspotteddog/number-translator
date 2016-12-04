package com.bigspotteddog.number.translator;

import junit.framework.TestCase;

public class NumberTranslatorTest extends TestCase {

    public void test() {
        NumberTranslator translator = new NumberTranslator();
        assertNotNull(translator);

        long number = 1;

        String words = translator.translate(number);
        assertNotNull(words);
    }
}
