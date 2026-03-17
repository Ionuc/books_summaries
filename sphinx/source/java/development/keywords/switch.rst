.. _java-development-keywords-switch-label:

Switch keyword
==============

Java 12 Improvement (preview)
-----------------------------
    - are more compact and readable
    - remove the need for break statements. The code execution will not fall through after the first match
    - we can assign a switch statement directly to the variable.
    - it’s also possible to execute code in switch expressions without returning any value:
    - old way of using switch:

    .. code-block:: python
           :linenos:

            DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
            String typeOfDay = "";
            switch (dayOfWeek) {
                case MONDAY:
                case TUESDAY:
                case WEDNESDAY:
                case THURSDAY:
                case FRIDAY:
                    typeOfDay = "Working Day";
                    break;
                case SATURDAY:
                case SUNDAY:
                    typeOfDay = "Day Off";
            }


    - new way of using switch:


    .. code-block:: python
           :linenos:

            typeOfDay = switch (dayOfWeek) {
                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day";
                case SATURDAY, SUNDAY -> "Day Off";
            };


Java 13 Improvements
--------------------

 - using yield, we can now effectively return values from a switch expression

    .. code-block:: python
           :linenos:

            @Test
            @SuppressWarnings("preview")
            public void whenSwitchingOnOperationSquareMe_thenWillReturnSquare() {
                var me = 4;
                var operation = "squareMe";
                var result = switch (operation) {
                    case "doubleMe" -> {
                        yield me * 2;
                    }
                    case "squareMe" -> {
                        yield me * me;
                    }
                    default -> me;
                };

                assertEquals(16, result);
            }

Java 14 Improvements
--------------------
- switch expressions have been standardized so that they are part and parcel of the development kit.
- What this effectively means is that this feature can now be used in production code, and not just in the preview mode to be experimented with by developers.

:ref:`Go Back <java-development-keywords-label>`.