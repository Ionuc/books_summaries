.. _java-development-error-handling-error-type-label:

Error types
===========
- there are 2 types of exceptions:
    - checked exceptions
        - are checked at compilation time
        - exists to predict errors of user interaction with program and they can be verified at compile time
    - runtime exceptions
        - are thrown at runtime
        - can't be checked beforehand


Hierarchy
---------
- Error types are all unchecked:
    - even if you handle this types of errors, you won't be able to do anything


    .. image:: ../../../images/java/development/error-handling/error-hierarchy.png
        :align: center


Error handling
--------------
- in case there are multiple exceptions that are from the same hierarchy, you have to specify hte most specific type first and only after that, specify te most generic one

Cases when finally won't be called till the end
-----------------------------------------------
- in case System.exit(0) is called inside finally, the rest of code won't be called
- in case exception is thrwn in finally, the rest of code won't be called
- in case of multi-threading program, finally block won't e executed till the end in case thread will be terminated

:ref:`Go Back <java-development-error-handling-label>`.