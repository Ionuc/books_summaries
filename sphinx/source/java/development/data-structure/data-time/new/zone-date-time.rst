.. _java-development-data-structures-data-time-new-zoned-date-time-label:

ZonedDateTime
=============
- it was added in Java 8

- represents a datetime with a time zone in the ISO 8601 calendar system
- is immutable and thread-sage
- stores all date and time fields to a precision of nanoseconds

Creation
--------
- overloaded methods from now(), of(), parse() can be used
- it provides also the methods ofString() which does performe validation for between the LocalDateTime, ZoneOfset & ZoneID provided


    .. code-block:: python
        :linenos:

        private static void zonedDateTimeInstantiationExample() {
            ZonedDateTime zonedDT1 = ZonedDateTime.now();
            System.out.println("ZonedDateTime1 : " + zonedDT1);

            ZonedDateTime zonedDT2 = ZonedDateTime.now(Clock.systemUTC());
            System.out.println("ZonedDateTime2 : " + zonedDT2);

            ZonedDateTime zonedDT3 = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
            System.out.println("ZonedDateTime3 : " + zonedDT3);

            ZonedDateTime zonedDT4 = ZonedDateTime.of(1980, 4, 9, 20, 15, 45, 345875000, ZoneId.systemDefault());
            System.out.println("ZonedDateTime4 : " + zonedDT4);

            ZonedDateTime zonedDT5 = ZonedDateTime.of(LocalDate.now(), LocalTime.of(15, 50, 25), ZoneId.systemDefault());
            System.out.println("ZonedDateTime5 : " + zonedDT5);

            ZonedDateTime zonedDT6 = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
            System.out.println("ZonedDateTime6 : " + zonedDT6);

            ZonedDateTime zonedDT7 = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
            System.out.println("ZonedDateTime7 : " + zonedDT7);

            ZonedDateTime zonedDT8 = ZonedDateTime.ofInstant(LocalDateTime.now(), ZoneOffset.UTC, ZoneId.systemDefault());
            System.out.println("ZonedDateTime8 : " + zonedDT8);

            ZonedDateTime zonedDT9 = ZonedDateTime.ofLocal(LocalDateTime.now(), ZoneId.systemDefault(), ZoneOffset.UTC);
            System.out.println("ZonedDateTime9 : " + zonedDT9);

            ZonedDateTime zonedDT10 = ZonedDateTime.ofStrict(LocalDateTime.now(), ZoneOffset.ofHours(8),
                    ZoneId.of("Asia/Singapore"));
            System.out.println("ZonedDateTime10: " + zonedDT10);

            ZonedDateTime zonedDT11 = ZonedDateTime.parse("2017-10-10T18:30:45+01:00[Europe/London]");
            System.out.println("ZonedDateTime11: " + zonedDT11);

            ZonedDateTime zonedDT12 = ZonedDateTime.parse("2018-04-22T08:40:15+10:00[Australia/Sydney]",
                    DateTimeFormatter.ISO_DATE_TIME);
            System.out.println("ZonedDateTime12: " + zonedDT12);
        }


:ref:`Go Back <java-development-data-structures-data-time-new-label>`.