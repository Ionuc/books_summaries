.. _frameworks-java-spring-mvc-validation-annotations-label:

Validation annotations
======================
- there are multiple annotations which can be used to performe Java field validations


NotNull
-------
- @NotNull annotation will forme validation regarding null field
- message property is the error send back to the form validation

    .. code-block:: python
        :linenos:

        public class Customer {
            private String firstName;
            
            @NotNull("message" = "is required")
            @Size(min=1, message = "is required")
            private String lastName;
        }

Size
----
- @Size annotation will peform validation on field regarding the lenght
- in our case, it will check if String value has the minum or maximum lenght size
- message property is the error send back to the form validation


    .. code-block:: python
        :linenos:

        public class Customer {
            private String firstName;
            
            @NotNull("message" = "is required")
            @Size(min=1, max=100, message = "is required")
            private String lastName;
        }


Min
---
- @Min annotation will peform validation on number field to be higher then the specified value
- message property is the error send back to the form validation


    .. code-block:: python
        :linenos:

        public class Customer {
            private String firstName;
            
            @Min(value=0, message = "must be greater then zero")
            private int age;
        }


Max
---
- @Max annotation will peform validation on number field to be lower then the specified value
- message property is the error send back to the form validation


    .. code-block:: python
        :linenos:

        public class Customer {
            private String firstName;
            
            @Max(value=100, message = "must be less then 100")
            private int age;
        }


Regular Expression
------------------
- regular expression is a sequence of characters that define a search pattern
- @Pattern annotation can be used

- example of applying regular expression for postal code: to have only 5 chars/digits

    .. code-block:: python
        :linenos:

        public class Customer {
            private String firstName;
            
            @Pattern(regexp="^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
            private String postalCode;
        }


:ref:`Go Back <frameworks-java-spring-mvc-validation-label>`.