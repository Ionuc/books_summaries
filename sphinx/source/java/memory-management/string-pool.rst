.. _string-pool:

String Pool
===========
    - String pool helps in saving a lot of space for java runtime although it takes more time to create the String.
    - When we use double quotes to create String, it first looks for string with same value in the String pool,
      if found it just returns the reference else it created a new String in the pool and then returns the reference

    - However using new operator, we force String class to create a new String object in heap space, We can use intern()
      method to put it into the pool or fere to other String object from string pool having same value


    .. code-block:: python
       :linenos:

       String str = new String("Cat");

    - In the above statement, either 1 or 2 string will be created, IF there is already a string literal “Cat” in the pool,
      then only one string str will be created. If there is no string literal “Cat” in the pool, then it will be first 
      created in the pool and then in the heap space, so total 2 string objects will be created

:ref:`Go Back <java-memory-management-label>`.