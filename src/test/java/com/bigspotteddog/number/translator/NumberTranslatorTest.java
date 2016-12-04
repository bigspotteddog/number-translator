package com.bigspotteddog.number.translator;

import junit.framework.TestCase;

public class NumberTranslatorTest extends TestCase {

    public void test() {
        LanguageFactory factory = LanguageFactory.instance();
        Language language = new Language("en-US");
        language.addNumber(1, "one");
        factory.register("en-US", language);

        NumberTranslator translator = new NumberTranslator();
        assertNotNull(translator);

        String words = translator.translate(1, "en-US");
        assertNotNull(words);
        assertEquals("one", words);
    }
}
