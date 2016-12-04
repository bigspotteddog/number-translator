package com.bigspotteddog.number.translator;

import java.util.HashMap;
import java.util.Map;

public class NumberTranslator {

    private static final Map<Long, String> map = new HashMap<Long, String>();

    static {
        map.put(1L, "one");
    }

    public String translate(long number) {
        return map.get(number);
    }

}
