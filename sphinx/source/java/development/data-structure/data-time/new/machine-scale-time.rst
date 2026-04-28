.. _java-development-data-structures-data-time-new-machine-scaletime-label:

Machine-Scale Time
===================
- is using a single continually incremented number
- the rules that determine how the scale is measured and communicated are typically defned by international scientific standards oganizations
- 2 classes are defined to represent machine scale time:
  - instant : an instant of the time-line
  - duratio : a duration of time
- these classes usage nanosecond precision
- unix epoch is used as a starting point for time calculation


Instant
-------
- introduced with Java 8
- is used to represent a specific moment on the timeline
- has nanoseconds precision, comparing to Date, which has millisecond precision
- it stores 2 fields:
    - seconds: long => represents the seconds from Unix epoch, which is January 1st, 1970
    - nanos: int    => represetns the nanoseconds of second

- methods:
    - now():
        -> returns the instant that is equal to the current moment of time
    - now(Clock):
        -> returns the instant that is equal to the current moment of time using a Clock object
    - ofEpochMilli(long):
        -> returns the instant from the millisecond provided
    - ofEpochSecond(second):
        -> return the instant from the second provided
    - ofEpochSecond(second, nano):
        -> return the instant from the second provided with overrided nano
    - parse(String):
        -> returns the instant from the String which must be a valid DateTimeFormatter.ISO_INSTANT
    - toEpochMilli():
        -> returns the milliseconds from the unix epoch
    - getSeconds():
        -> returns the nr of seconds from the Unix epoch
    - getNano():
        -> returns te nanoseconds of the second


  .. code-block:: python
        :linenos:

        public static void main(String[] args) {
            Instant instant1 = Instant.now();
            System.out.println("Instant1: " + instant1);

            Instant instant2 = Instant.now(Clock.systemUTC());
            System.out.println("Instant2: " + instant2);

            long now = System.currentTimeMillis();
            Instant instant3 = Instant.ofEpochMilli(now);
            System.out.println("Instant3: " + instant3);

            now = now / 1000;
            Instant instant4 = Instant.ofEpochSecond(now);
            System.out.println("Instant4: " + instant4);

            Instant instant5 = Instant.ofEpochSecond(now, 345000000);
            System.out.println("Instant5: " + instant5);

            // DateTimeFormatter.ISO_INSTANT - is used for parsing
            Instant instant6 = Instant.parse("1994-07-08T11:16:42.08Z");
            System.out.println("Instant6: " + instant6);

            Instant instant7 = new Date().toInstant();

            System.out.println("Convert instant to milliseconds: " + instant6.toEpochMilli());
            System.out.println("getEpochSecond(): " + instant1.getEpochSecond());
            System.out.println("getNano(): " + instant1.getNano());
            System.out.println("EpochSecond: " + instant1.getLong(ChronoField.INSTANT_SECONDS));
            System.out.println("Nano: " + instant1.get(ChronoField.NANO_OF_SECOND));

            addSubtractDemo();
            dateComparison();
            instantConversionExample();

        }

- adding / substracting:
    - you can do operations on Instant, like adding and substracting :


  .. code-block:: python
        :linenos:

        private static void addSubtractDemo() {
            Instant instant = Instant.parse("1984-08-13T10:15:30.345Z");
            System.out.println("Instant             : " + instant);

            // Adding/subtracting seconds
            System.out.println("15 seconds before   : " + instant.minusSeconds(15));
            System.out.println("10 seconds after    : " + instant.plusSeconds(10));

            // Adding/subtracting millis
            System.out.println("Minus 45000 millis  : " + instant.minusMillis(45000));
            System.out.println("Plus 10000 millis   : " + instant.plusMillis(10000));

            // Adding/subtracting nanos
            System.out.println("Minus 45123456 nanos: " + instant.minusNanos(45123456));
            System.out.println("Plus 111234567 nanos: " + instant.plusNanos(111234567));

            // Using MINUTES
            System.out.println("45 minutes before   : " + instant.minus(45, ChronoUnit.MINUTES));
            // Using HOURS
            System.out.println("3 hours before      : " + instant.minus(3, ChronoUnit.HOURS));
            // Using MILLIS also supported
            System.out.println("30000 millis later  : " + instant.plus(30000, ChronoUnit.MILLIS));
            // Using DAYS
            System.out.println("10 days later       : " + instant.plus(10, ChronoUnit.DAYS));

            // Using TemporalAmount - Duration
            System.out.println("10 seconds before   : " + instant.minus(Duration.ofSeconds(10)));
            // Using TemporalAmount - Period
            System.out.println("5 days later        : " + instant.plus(Period.ofDays(5)));
        }

- comparing Instant instances:
    - 2 methods can be used: isAfter() & isBefore()
    - also, Instant is implementing Comparable<>


  .. code-block:: python
        :linenos:

        private static void dateComparison() {
            Instant instant1 = Instant.parse("1994-07-08T11:16:43.08Z");
            Instant instant2 = Instant.parse("1994-07-08T11:16:44.08Z");
            System.out.println("Instant1\t: " + instant1);
            System.out.println("Instant2\t: " + instant2);

            System.out.println("Instant1 after Instant2\t\t: " + instant1.isAfter(instant2));
            System.out.println("Instant1 before Instant2\t: " + instant1.isBefore(instant2));

            System.out.println("Instant1 compareTo Instant2:\t" + instant1.compareTo(instant2));
            System.out.println("Instant2 compareTo Instant1:\t" + instant2.compareTo(instant1));
        }


- Cnverting to other local date time
    - 


  .. code-block:: python
        :linenos:

        private static void instantConversionExample() {
            Instant instant = Instant.parse("1997-05-07T10:15:30.00Z");

            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            System.out.println("LocalDateTime : " + localDateTime);

            ZonedDateTime zonedDateTime1 = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
            System.out.println("ZonedDateTime1: " + zonedDateTime1);

            ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("Asia/Tokyo"));
            System.out.println("ZonedDateTime2: " + zonedDateTime2);

            OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
            System.out.println("OffsetDateTime: " + offsetDateTime);

        }


Duration
---------
- uses time based values
- models a quantity or amount of time in terms of seconds and nanoseconds
- duration of one day is always 24 hours
- stores 2 fields:
    - seconds: long
    - nanoseconds: int
- it can be negative



  .. code-block:: python
        :linenos:

        public static void main(String[] args) {
            Instant start = Instant.parse("1995-04-11T12:27:42.00Z");
            Instant end = Instant.parse("2000-03-18T04:37:12.00Z");
                    
            Duration duration = Duration.between(start, end);
            System.out.println("duration seconds: " + duration.getSeconds());
            
            Duration fromDays = Duration.ofDays(1);
            System.out.println(fromDays.getSeconds());
                   
            Duration fromMinutes = Duration.ofHours(60);
            System.out.println(fromMinutes.get(ChronoUnit.SECONDS));
            
            Duration fromChar1 = Duration.parse("P1DT1H10M10.5S");
            Duration fromChar2 = Duration.parse("PT10M");
            System.out.println(fromChar1.getSeconds());
            System.out.println(fromChar2.getSeconds());
            
        }


Period
------
- uses date based values
- period will try to add a conceptual day trying to maintain the local time
- the supported units if a period are:
    - years
    - months
    - days


  .. code-block:: python
        :linenos:

        public static void main(String[] args) {
            LocalDate startDate = LocalDate.of(1995, 4, 11);
            LocalDate endDate = LocalDate.of(2000, 3, 18);

            Period period = Period.between(startDate, endDate);
            System.out.println("Period years: " + period.getYears() + ", months: " 
                    + period.getMonths() + ", days: " + period.getDays());
            
            
            Period fromUnits = Period.of(3, 10, 10);
            Period fromDays = Period.ofDays(50);
            Period fromMonths = Period.ofMonths(5);
            Period fromYears = Period.ofYears(10);
            Period fromWeeks = Period.ofWeeks(40);
            
            Period years = Period.parse("P5Y");
            System.out.println("years: " + years.getYears());

            Period days = Period.parse("P5Y2M10D");
            System.out.println("days: " + days.getDays());
        }


:ref:`Go Back <java-development-data-structures-data-time-new-label>`.