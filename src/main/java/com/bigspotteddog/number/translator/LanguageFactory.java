package com.bigspotteddog.number.translator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class LanguageFactory {
    private static final Logger log = Logger.getLogger(LanguageFactory.class.getName());

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
            try {
                language = getLanguageFromResource(name);
            } catch (IOException e) {
                if (log.isLoggable(Level.SEVERE)) {
                    log.severe(MessageFormat.format("Unable to load language: {0}", name));
                }
            }
        }
        return language;
    }

    private Language getLanguageFromResource(String name) throws IOException {
        Language language = null;
        String json = getJsonFromResource(name);
        if (json != null) {
            language = new Gson().fromJson(json, Language.class);
            register(name, language);
        }
        return language;
    }

    private String getJsonFromResource(String name) throws IOException {
        String json = null;
        URL resource = this.getClass().getResource(MessageFormat.format("/{0}.json", name));
        if (resource != null) {
            InputStream is = resource.openStream();
            if (is != null) {
                final Scanner s = new Scanner(is);
                try {
                    s.useDelimiter("\\A");
                    json = s.next();
                } finally {
                    s.close();
                }
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
