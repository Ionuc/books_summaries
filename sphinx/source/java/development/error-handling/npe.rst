.. _java-development-error-handling-npe-label:

Try with Resource Improvements
==============================

Java 14 improvements
--------------------

- previously, the stack trace for a NullPointerException didn’t have much of a story to tell except that some value was null at a given line in a given file
    - now Java has made this easier by adding the capability to point out what exactly was null in a given line of code
    - The first part represents the failing operation, a consequence of a reference being null, while the second part identifies the reason for the null reference:


    .. code-block:: python
           :linenos:

            int[] arr = null;
            arr[0] = 1;

            // Old
            Exception in thread "main" java.lang.NullPointerException
            at com.baeldung.MyClass.main(MyClass.java:27)

            // New
            java.lang.NullPointerException: Cannot store to int array because "a" is null


- a detailed message computation is only done when the JVM itself throws a NullPointerException — the computation won’t be performed if we explicitly throw the exception in our Java code
- JEP 358 calculates the message lazily, meaning only when we print the exception message and not when the exception occurs
- Most importantly, the detailed exception message is switched off by default in JDK 14. To enable it, we need to use the command-line option:


    .. code-block:: python
           :linenos:

           -XX:+ShowCodeDetailsInExceptionMessages


Java 15 Improvement
-------------------
- now it is enabled by default

:ref:`Go Back <java-development-error-handling-label>`.