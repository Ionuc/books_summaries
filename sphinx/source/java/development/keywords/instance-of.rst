.. _java-development-keywords-instance-of-label:

Instance-of keyword
====================

Java 12 Improvement (preview)
-----------------------------
     - in previous Java versions, when using, for example, if statements together with instanceof, we would have to explicitly typecast the object to access its features::


    .. code-block:: python
            :linenos:

            Object obj = "Hello World!";
            if (obj instanceof String) {
                String s = (String) obj;
                int length = s.length();
            }


    - new way of using instanceof:


    .. code-block:: python
           :linenos:

            if (obj instanceof String s) {
                int length = s.length();
            }


Java 14 improvements
--------------------
- with traditional instance-of:


    .. code-block:: python
           :linenos:

            if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                cat.meow();
               // other cat operations
            } else if (animal instanceof Dog) {
                Dog dog = (Dog) animal;
                dog.woof();
                // other dog operations
            }

            // More conditional statements for different animals

- with the new way:


    .. code-block:: python
           :linenos:

            if (animal instanceof Cat cat) {
                cat.meow();
            } else if(animal instanceof Dog dog) {
                dog.woof();
            }


Java 15 improvements
--------------------
- We can also combine the new binding variable with conditional statements:


    .. code-block:: python
           :linenos:

            if (person instanceof Employee employee && employee.getYearsOfService() > 5) {
                //...
            }


Java 16 Improvements
--------------------
- now it is part of production code


:ref:`Go Back <java-development-keywords-label>`.