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
        int result = Main.main(new String[] {});
        assertEquals(-1, result);

        result = Main.main(new String[] { "123456" });
        assertEquals(0, result);

        result = Main.main(new String[] { "123,456" });
        assertEquals(0, result);

        result = Main.main(new String[] { "123456.00" });
        assertEquals(0, result);

        result = Main.main(new String[] { "a123456" });
        assertEquals(-2, result);

    }
}
