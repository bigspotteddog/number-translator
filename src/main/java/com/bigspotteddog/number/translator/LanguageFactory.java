package com.bigspotteddog.number.translator;

import java.util.Map;
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
        return languages.get(name);
    }

    public void register(String name, Language language) {
        languages.put(name, language);
    }

    public void register(String name, String json) {
        Language language = new Gson().fromJson(json, Language.class);
        languages.put(name, language);
    }

}
