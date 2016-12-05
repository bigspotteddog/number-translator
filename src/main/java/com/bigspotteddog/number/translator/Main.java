package com.bigspotteddog.number.translator;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.NumberFormat;

public class Main {
    private static final String USAGE = getUsage();

    public static final void main(String[] args) throws IOException {
        if (args.length == 0) {
            String max = NumberFormat.getInstance().format(Long.MAX_VALUE);
            System.out.println(MessageFormat.format(USAGE, max));
            return;
        }

        long number = 0;
        try {
            String input = args[0];

            String numberToTranslate = input;
            numberToTranslate = numberToTranslate.replaceAll(",", "");
            numberToTranslate = numberToTranslate.split("\\.")[0];

            String reformattedNumberToTranslate = NumberFormat.getInstance().format(Long.parseLong(numberToTranslate));
            if (!input.equals(reformattedNumberToTranslate)) {
                System.out.println(MessageFormat.format("Translating: {0}", reformattedNumberToTranslate));
            }

            number = Long.parseLong(numberToTranslate);
        } catch (NumberFormatException e) {
            System.out.println("The number entered is not valid.");
            return;
        }

        NumberTranslator translator = new NumberTranslator();
        String words = translator.translate(number, "en-US");

        System.out.println(words);
    }

    private static String getUsage() {
        return
                new StringBuilder()
                .append("\nThis utility translates numbers into their english word representations.\n")
                .append("The maximum number that can be transalted is {0}.\n\n")
                .append("Usage:\n\n")
                .append("$ java -jar number-translator-<version>.jar <number to translate>\n")
                .toString();
    }
}
