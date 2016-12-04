package com.bigspotteddog.number.translator;

import java.util.HashMap;
import java.util.Map;

public class Language {
    private String name;

    private Map<Integer, String> numbers = new HashMap<Integer, String>();

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addNumber(int i, String string) {
        numbers.put(i, string);
    }

    public String getNumber(int number) {
        return numbers.get(number);
    }
}
