.. _java-development-data-structures-data-time-old-calendar-label:

Calendar
========
- the Calender is an abstract class with 3 implementations:
    - GregorianCalendar
    - JapaneseImperialCalendar
    - BudhistCalendar

- the abstract class provides:
    - methods for converting between a specific instant in time ans set of calendar fields, like year, month, day of month, hour, etc
    - methods for manipulating fields such as getting the date of next week
- an instant time can be represented by a millisecond value that is an offset from January 1st 1970s, midnight, GMT:
    - this reference value is called Unix epoch time
- unit time is a system for descripbing a point in time:
    - it is the number of seconds that have elapsed since the Unix epoch minues leap seconds
    - positive number represents the date after this Unix epoch time
    - negative numbers represents tha date before this Unix epoch time



GregorianCalendar
-----------------
- 


   .. code-block:: python
        :linenos:

        Calendar calendar = new GregorianCalendar(1988, 1, 28, 13, 24, 56);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR); // 12 hour clock
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

Comparison
----------
- 2 methods can be used to compare : after() & before()
- Calendar is implementing Comparable:


    .. code-block:: python
        :linenos:

        private static void dateComparison() {
            Calendar calendar1 = new GregorianCalendar(1013, 1, 28, 13, 24, 55);
            Calendar calendar2 = new GregorianCalendar(1013, 1, 28, 13, 24, 56);
            System.out.println("Calendar1 after Calendar2:\t" + calendar1.after(calendar2));
            System.out.println("Calendar1 before Calendar2:\t" + calendar1.before(calendar2));
        }


SimpleDateFormat
----------------
- it is formating a Date object to a String based on the pattern specified
- examples:
    - "yyyy.MM.dd G 'at' HH:mm:ss z"    => 2001.07.04 AD at 12:08:56 PDT
    - "EEE, MMM d, ''yy"                => Wed, Jul 4, '01
    - "h:mm a"                          => 12:08 PM
    - "hh 'o''clock' a, zzzz"           => 12 o'clock PM, Pacific Daylight Time
    - "K:mm a, z"                       => 0:08 PM, PDT
    - "yyyyy.MMMMM.dd GGG hh:mm aaa"    => 02001.July.04 AD 12:08 PM
    - "EEE, d MMM yyyy HH:mm:ss Z"      => Wed, 4 Jul 2001 12:08:56 -0700
    - "yyMMddHHmmssZ"                   => 010704120856-0700
    - "yyyy-MM-dd'T'HH:mm:ss.SSSZ"      => 2001-07-04T12:08:56.235-0700
    - "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"    => 2001-07-04T12:08:56.235-07:00
    - "YYYY-'W'ww-u"                    => 2001-W27-3

   .. code-block:: python
        :linenos:

        public static void simpleDateFormatExample() throws ParseException {
            Calendar calendar = new GregorianCalendar(1013, 1, 28, 13, 24, 56);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
            System.out.println(sdf.format(calendar.getTime()));
            
            sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss", Locale.US);
            System.out.println(sdf.format(calendar.getTime()));
            
            sdf = new SimpleDateFormat("yy M dd HH:mm:ss", Locale.US);
            System.out.println(sdf.format(calendar.getTime()));
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