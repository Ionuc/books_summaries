.. _java-development-data-structures-data-time-new-date-label:

ZoneId
======
- it was added in Java 8
- is the new implementation for old TimeZone
- methods:
    - getAvailableZoneIds():
        - returns all zone ids


Creating instances
------------------
- it can be created from:
    - its constructor
    - retrieving from a SimpleDateFormat instance by parsing a String using parse() method:
        - ParseException will be thrown if the object can't be aplied to the String passed in the parse() method
    - retrieving from a Calendar instance using getTime() method

  .. code-block:: python
        :linenos:

        private static void dateExample() throws ParseException {
            Date date = new Date(1988, 2, 1);
            Date date2 = new Date();
            Date date3 = new Date(1895215322137621581L);
        
            System.out.println(date3);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            
            Date dateParsed = sdf.parse("1999/01/10 10:02:02");
            System.out.println(dateParsed);
            
            String dateString = sdf.format(dateParsed);
            System.out.println(dateString);
            
        }


:ref:`Go Back <java-development-data-structures-data-time-new-label>`.