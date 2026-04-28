.. _java-development-data-structures-data-time-old-date-label:

Date
====

- Date class represents a specific instant in time with milliseconds precision
- has different constructors to allows setting year, month, day, etc ...
- it is better to use Calendar class


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

Comparison
----------
- 2 methods can be used to compare : after() & before()
- Date is implementing Comparable:


    .. code-block:: python
        :linenos:

        private static void dateComparison() {
            Date date1 = new Date(1090215021581L);
            Date date2 = new Date(1090215021582L);
            System.out.println("Date1 after Date2:\t" + date1.after(date2));
            System.out.println("Date1 before Date2:\t" + date1.before(date2));

        }

Disadvantages
-------------
- thread safety:
    - is not thread safe
    - the new API is immutable and thread safe
- zone date and time:
    - additional logic for handling time zone
    - the new API handles zone using local DateTime and zoned DateTime 

:ref:`Go Back <java-development-data-structures-data-time-old-label>`.