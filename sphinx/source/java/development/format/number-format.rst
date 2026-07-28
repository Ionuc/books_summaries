.. _java-development-format-number-format-label:

Number Format
=============


Java 12 improvement
-------------------
- a new number formatting was added : CompactNumberFormat
    - it’s designed to represent a number in a shorter form, based on the patterns provided by a given locale
    - extends NumberFormat class

    .. code-block:: python
           :linenos:

            public static NumberFormat getCompactNumberInstance(Locale locale, NumberFormat.Style formatStyle)



    - the locale parameter is responsible for providing proper format patterns
    - the format style can be either SHORT or LONG


    .. code-block:: python
           :linenos:

            @Test
            public void givenNumber_thenCompactValues() {
                NumberFormat likesShort = 
                    NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.SHORT);
                likesShort.setMaximumFractionDigits(2);
                assertEquals("2.59K", likesShort.format(2592));

                NumberFormat likesLong = 
                    NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.LONG);
                likesLong.setMaximumFractionDigits(2);
                assertEquals("2.59 thousand", likesLong.format(2592));
            }



Java 14 Improvements
--------------------
- currency formatting using local and currencies


    .. code-block:: python
           :linenos:

        System.out.println("===== Currency format support =====");
        // Create a Locale for the United States
        Locale usLocale = new Locale("en", "US");

        // Create a Currency instance for the US Dollar
        Currency usd = Currency.getInstance("USD");

        // Create a NumberFormat instance for accounting currency formatting
        NumberFormat accountingFormat = NumberFormat.getCurrencyInstance(usLocale);

        // Set the currency to USD and enable accounting formatting
        accountingFormat.setCurrency(usd);
        accountingFormat.setMaximumFractionDigits(2); // Set maximum fraction digits

        // Sample monetary value
        double amount = 1234567.89;

        // Format and display the amount using accounting currency format
        String formattedAmount = accountingFormat.format(amount);
        System.out.println("Accounting Currency Format: " + formattedAmount);


:ref:`Go Back <java-development-format-label>`.