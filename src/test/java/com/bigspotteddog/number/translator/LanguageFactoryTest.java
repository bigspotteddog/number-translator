package com.bigspotteddog.number.translator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.google.gson.Gson;

import junit.framework.TestCase;

public class LanguageFactoryTest extends TestCase {

    private LanguageFactory factory;

    @Override
    protected void setUp() throws Exception {
        factory = LanguageFactory.instance();
        factory.clearLanguages();
    }

    public void testGetLanguage() throws IOException {
        Language language = new Language("en-US");
        factory.register("en-US", language);
        Language english = factory.getLanguage("en-US");
        assertEquals("en-US", english.getName());

        language = new Language("es-MX");
        factory.register("es-MX", language);
        Language spanish = factory.getLanguage("es-MX");
        assertEquals("es-MX", spanish.getName());
    }

    public void testLoadLanguage() throws IOException {
        String json = "{ \"name\": \"en-US\", \"numbers\": { 1: \"one\" } }";
        factory.register("en-US", json);

        Language english = factory.getLanguage("en-US");
        assertEquals("en-US", english.getName());

        String words = english.getNumber(1);
        assertEquals("one", words);
    }

    public void testLoadResource() {
        InputStream is = this.getClass().getResourceAsStream("/en-US.json");
        assertNotNull(is);

        String json = null;

        final Scanner s = new Scanner(is);
        try {
            s.useDelimiter("\\A");
            assertTrue(s.hasNext());
            json = s.next();
            assertTrue(json.length() > 0);
        } finally {
            s.close();
        }

        Language language = new Gson().fromJson(json, Language.class);
        assertEquals("en-US", language.getName());
    }

    public void testRegisterFromResource() throws IOException {
        Language language = factory.getLanguage("en-US");
        assertNotNull(language);

        language = factory.getLanguage("Does not exist");
        assertNull(language);
    }
}
