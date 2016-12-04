package com.bigspotteddog.number.translator;

import java.util.HashMap;
import java.util.Map;

public class Language {
    private String name;

    private Map<Integer, String> numbers = new HashMap<Integer, String>();
    private Map<Integer, String> scales = new HashMap<Integer, String>();

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNumber(int number) {
        return numbers.get(number);
    }

    public String getScale(int scale) {
        return scales.get(scale);
    }
}
