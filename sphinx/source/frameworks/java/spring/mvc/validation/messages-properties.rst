.. _frameworks-java-spring-mvc-validation-messages-properties-label:

Messages properties
===================
- is a file used by Spring MVC validation
- should be called "mesages.properties" and placed under the same root of application.properties
- is used to handle custom own error message:
    - the key is composed of:
        - error code => typeMismatch
        - model attribute  name => customer
        - field name => freePasses
    - the value is the own custom error message

- the error code can be seen on the BindingResult

    .. code-block:: python
        :linenos:

        //messages.properties
        typeMismatch.customer.freePasses=Invalid number


:ref:`Go Back <frameworks-java-spring-mvc-validation-label>`.