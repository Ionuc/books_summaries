.. _java-tools-memory-management-integer-pool-label:

Integer Pool
============
- The Integer Pool is a caching mechanism in Java that optimizes memory usage and improves performance for frequently-used integer objects
- Specifically, the Integer Pool caches instances of the Integer class for values in the range -128 to 127.
- When you create an Integer object within this range, Java reuses an existing instance from the pool instead of creating a new one
- This ensures efficient memory usage and faster operations when working with small integers
- Understanding the Integer Pool is crucial in scenarios where you rely on object identity comparisons (using ==) instead of value comparisons (using .equals())


    .. code-block:: python
           :linenos:

            Integer i1 = 127;
            Integer i2 = 127;
            Integer i3 = 129;
            Integer i4 = 129;

            System.out.println("i1 == i2 " + (i1 == i2)); // i1 == i2 true
            System.out.println("i3 == i4 " + (i3 == i4)); // i3 == i4 false


How is working
--------------
- steps:
  1. When you use the Integer.valueOf(int) method to create an Integer object, the JVM checks whether the value falls within the range -128 to 127.
  2. If the value is within this range, the JVM retrieves the corresponding Integer object from the pool.
  3. If the value is outside this range, a new Integer object is created.



    .. code-block:: python
           :linenos:

            public static Integer valueOf(int i) {
                if (i >= -128 && i <= 127) {
                    return IntegerCache.cache[i + 128];
                }
                return new Integer(i);
            }


Key points
----------
- Manual creating object:
  - if you use the Integer contructor, it will alwasy create a new instance:



    .. code-block:: python
           :linenos:

            Integer i1 = new Integer(127);
            Integer i2 = new Integer(127);

            System.out.println("i1 == i2 " + (i1 == i2)); // i1 == i2 false


- Autoboxing:
  - When you assign a primitive int to an Integer, autoboxing uses the valueOf() method, which leverages the Integer Pool.
- Custom Integer Pool Range:
  - Since Java 7, you can customize the range of the Integer Pool using the JVM option -XX:AutoBoxCacheMax


    .. code-block:: python
           :linenos:

            java -XX:AutoBoxCacheMax=1000 MyApp


:ref:`Go Back <java-tools-memory-management-label>`.