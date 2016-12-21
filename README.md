# A Number Translator
[![Build Status](https://travis-ci.org/bigspotteddog/number-translator.svg)](https://travis-ci.org/bigspotteddog/number-translator)[![Code Coverage](https://img.shields.io/codecov/c/github/bigspotteddog/number-translator/develop.svg)](https://codecov.io/github/bigspotteddog/number-translator?branch=develop)

## Demo

https://bigspotteddog.github.io/number-translator

## Description

This utility translates numbers into their english word representations.
The maximum number that can be translated is +/- 9,223,372,036,854,775,807.

## Sample Output
```
$ java -jar number-translator-1.0-SNAPSHOT.jar

This utility translates numbers into their english word representations.
The maximum number that can be translated is +/- 9,223,372,036,854,775,807.

Usage:

$ java -jar number-translator-<version>.jar <number to translate>

$ java -jar number-translator-1.0-SNAPSHOT.jar 0
Zero

$ java -jar number-translator-1.0-SNAPSHOT.jar 13
Thirteen

$ java -jar number-translator-1.0-SNAPSHOT.jar 85
Eighty five

$ java -jar number-translator-1.0-SNAPSHOT.jar 5237
Translating: 5,237
Five thousand two hundred and thirty seven

$ java -jar number-translator-1.0-SNAPSHOT.jar 5,237
Five thousand two hundred and thirty seven

$ java -jar number-translator-1.0-SNAPSHOT.jar 9,223,372,036,854,775,807
Nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion thirty six billion eight hundred and fifty four million seven hundred and seventy five thousand eight hundred and seven

```