.. _java-development-data-structures-data-time-standards-label:

Time Standards
==============
- universal time isa time standard based on Earth's rotation
- most common version used are:
    - Coordinated Universal Time
    - UTC
    - UT1


GMT
---
- Greenwich Mean Time
- it is a mean time at the Royak Observatory in Greenwich, London, counted from midnight at different times in the past
- it has been calculated in different ways, including being calculated from noon
- it cannot be used to specify a particular time unless a context is given
- GMT is now a timezone, not a time reference

UTC
---
- is based on International Atomic Time with leap seconds added
- a normal day has 24 (hours) * 60 (min) * 60 (sec) = 86400 seconds
- in UTC, about once every year, there is a extra second called a leap second
- the leap second is always added as the last second of the day and always on DEcembet 30th or June 30ts
    - for example, the last minute of year 1995 was 61 seconds thanks to the added leap second
- most computer clocks are not accurate enought to be able to reflect the leap second distinction


:ref:`Go Back <java-development-data-structures-data-type-label>`.