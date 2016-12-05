package com.bigspotteddog.number.translator;

import java.io.IOException;

import junit.framework.TestCase;

public class MainTest extends TestCase {

    private LanguageFactory factory;

    @Override
    protected void setUp() throws Exception {
        factory = LanguageFactory.instance();
        factory.clearLanguages();
    }

    public void testMain() throws IOException {
        new Main().translate(new String[] {});
        new Main().translate(new String[] { "123456" });
        new Main().translate(new String[] { "123,456" });
        new Main().translate(new String[] { "123456.00" });
        new Main().translate(new String[] { "a123456" });
    }
}
