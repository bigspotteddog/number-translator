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
    }
}
