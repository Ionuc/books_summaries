.. _java14_npe:

Helpfull NULLPointerException
=============================

    - previously, the stack trace for a NullPointerException didn’t have much of a story to tell except that some value was null at a given line in a given file
    - now Java has made this easier by adding the capability to point out what exactly was null in a given line of code

    .. code-block:: python
           :linenos:

            int[] arr = null;
            arr[0] = 1;

            // Old
            Exception in thread "main" java.lang.NullPointerException
            at com.baeldung.MyClass.main(MyClass.java:27)

            // New
            java.lang.NullPointerException: Cannot store to int array because "a" is null



:ref:`Go Back <java14-developer-features-label>`.