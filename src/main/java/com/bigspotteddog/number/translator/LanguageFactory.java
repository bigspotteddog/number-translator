package com.bigspotteddog.number.translator;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

public class LanguageFactory {
    private Map<String, Language> languages = new ConcurrentHashMap<String, Language>();

    private static final class LanguageFactoryHolder {
        private static final LanguageFactory INSTANCE = new LanguageFactory();
    }

    private LanguageFactory() {}

    public static LanguageFactory instance() {
        return LanguageFactoryHolder.INSTANCE;
    }

    public Language getLanguage(String name) {
        Language language = languages.get(name);
        if (language == null) {
            language = getLanguageFromResource(name);
        }
        return language;
    }

    private Language getLanguageFromResource(String name) {
        Language language = null;
        String json = getJsonFromResource(name);
        if (json != null) {
            language = new Gson().fromJson(json, Language.class);
            register(name, language);
        }
        return language;
    }

    private String getJsonFromResource(String name) {
        String json = null;
        InputStream is = this.getClass().getResourceAsStream(MessageFormat.format("/{0}.json", name));
        if (is != null) {
            final Scanner s = new Scanner(is);
            try {
                s.useDelimiter("\\A");
                json = s.next();
            } finally {
                s.close();
            }
        }
        return json;
    }

    public void register(String name, Language language) {
        languages.put(name, language);
    }

    public void register(String name, String json) {
        Language language = new Gson().fromJson(json, Language.class);
        languages.put(name, language);
    }

    protected void clearLanguages() {
        languages.clear();
    }
}
