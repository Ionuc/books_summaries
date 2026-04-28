.. _java-development-data-structures-data-time-new-local-date-time-label:

Local Date and Time
===================
- it was added in Java 8
- are in java.time package
- is the new implementation for old Calendar and Date
- represets a date time without a timezone
- is a combination betweeb LocaDate & LocalTime
- is immutable and thread-safe
- stores all date and time fields to a precision of nanoseconds

Creation
--------
- overloaded methods from now(), of(), parse() can be used:


    .. code-block:: python
        :linenos:

        private static void localDateTimeInstantiationExample() {
            LocalDateTime localDT1 = LocalDateTime.now();
            System.out.println("LocalDateTime1 : " + localDT1);

            LocalDateTime localDT2 = LocalDateTime.now(Clock.systemUTC());
            System.out.println("LocalDateTime2 : " + localDT2);

            LocalDateTime localDT3 = LocalDateTime.now(ZoneId.systemDefault());
            System.out.println("LocalDateTime3 : " + localDT3);

            LocalDateTime localDT4 = LocalDateTime.of(1980, 4, 9, 20, 15);
            System.out.println("LocalDateTime4 : " + localDT4);

            LocalDateTime localDT5 = LocalDateTime.of(1979, 12, 9, 18, 5, 50);
            System.out.println("LocalDateTime6 : " + localDT5);

            LocalDateTime localDT6 = LocalDateTime.of(1983, 7, 12, 20, 15, 50, 345678900);
            System.out.println("LocalDateTime8 : " + localDT6);

            LocalDateTime localDT7 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 50));
            System.out.println("LocalDateTime10: " + localDT7);

            LocalDateTime localDT8 = LocalDateTime.ofEpochSecond(1555552018, 456789500, ZoneOffset.UTC);
            System.out.println("LocalDateTime11: " + localDT8);

            LocalDateTime localDT9 = LocalDateTime.ofInstant(Instant.ofEpochMilli(324142255123L), ZoneId.systemDefault());
            System.out.println("LocalDateTime12: " + localDT9);

            LocalDateTime localDT10 = LocalDateTime.parse("1945-08-17T10:20:45");
            System.out.println("LocalDateTime13: " + localDT10);

            LocalDateTime localDT11 = LocalDateTime.parse("20190824155025", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            System.out.println("LocalDateTime14: " + localDT11);
        }


Comparison
----------
- 2 methods can be used: isAfter() & isBefore()
- also, LocalDateTime is implementing Comparable<>


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


:ref:`Go Back <java-development-data-structures-data-time-new-label>`.