.. _java-development-format-date-time-format-label:

DateTimeFormat
==============


Java 16 improvement
-------------------
- A new addition to the DateTimeFormatter is the period-of-day symbol “B“, which provides an alternative to the am/pm format
- Instead of something like “3pm“, we get an output of “3 in the afternoon“.
- We can also use the “B“, “BBBB“, or “BBBBB” DateTimeFormatter pattern for short, full, and narrow styles respectively.


    .. code-block:: python
           :linenos:

            LocalTime date = LocalTime.parse("15:25:08.690791");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h B");
            assertThat(date.format(formatter)).isEqualTo("3 in the afternoon");



:ref:`Go Back <java-development-format-label>`.