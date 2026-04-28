.. _java-development-data-structures-data-time-old-formater-label:

SimpleDateFormat
================

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

- you can parse a Date object retrived from a Calendar.getTime() instance or created by constructor


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


- you can set a specific Timezone


   .. code-block:: python
        :linenos:

        public static void timeZoneExample() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
            sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
            Date date = new Date(1090215021581L);
            System.out.println(sdf.format(date));
            
            sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("+1")));
            System.out.println(sdf.format(date));
        }


:ref:`Go Back <java-development-data-structures-data-time-old-label>`.