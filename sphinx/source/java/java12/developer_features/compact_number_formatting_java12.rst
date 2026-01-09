.. _java12_compact_number_formatting:

Compact Number Formatting
=========================

    - a new number formatting was added : CompactNumberFormat
    - it’s designed to represent a number in a shorter form, based on the patterns provided by a given locale

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




:ref:`Go Back <java12-developer-features-label>`.