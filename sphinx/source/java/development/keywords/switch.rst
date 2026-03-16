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



:ref:`Go Back <java-development-keywords-label>`.