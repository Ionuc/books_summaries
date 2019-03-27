.. _javascript-null-label:

Null
====
    - One of the Javascript primitive types
    - Represents the absence of a value
    - Evaluates to false in boolean expressions. The same with the empty string and zero value

    .. code-block:: python
       :linenos:

       var mtvalue = null;
       if (!null){
           write("null evaluates to false");
       }
       var hasAValue = 1;
       if (hasAValue){
         write(hasAValue + " has a value")
       }
       Output
       null evaluates to false
       1 has a value

:ref:`Go Back <javascript-types-label>`.