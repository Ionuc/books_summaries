.. _java12_pattern_matching:

Pattern Matching for instanceof (Preview)
==========================================

    - in previous Java versions, when using, for example, if statements together with instanceof, we would have to explicitly typecast the object to access its features::

    .. code-block:: python
           :linenos:

            Object obj = "Hello World!";
            if (obj instanceof String) {
                String s = (String) obj;
                int length = s.length();
            }


    - new way of using instanceof:


    .. code-block:: python
           :linenos:

            typeOfDay = switch (dayOfWeek) {
                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day";
                case SATURDAY, SUNDAY -> "Day Off";
            };



:ref:`Go Back <java12-preview-label>`.