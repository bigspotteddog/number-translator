package com.bigspotteddog.number.translator;

import junit.framework.TestCase;

public class LanguageFactoryTest extends TestCase {

    public void testGetLanguage() {
        LanguageFactory factory = LanguageFactory.instance();

        Language language = new Language("en-US");
        factory.register("en-US", language);
        Language english = factory.getLanguage("en-US");
        assertEquals("en-US", english.getName());

        language = new Language("es-MX");
        factory.register("es-MX", language);
        Language spanish = factory.getLanguage("es-MX");
        assertEquals("es-MX", spanish.getName());
    }

    public void testLoadLanguage() {
        LanguageFactory factory = LanguageFactory.instance();

        String json = "{ \"name\": \"en-US\", \"numbers\": { 1: \"one\" } }";
        factory.register("en-US", json);

        Language english = factory.getLanguage("en-US");
        assertEquals("en-US", english.getName());

        String words = english.getNumber(1);
        assertEquals("one", words);
    }
}
