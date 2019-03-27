.. _javascript-undefined-label:

Undefined
=========
    - Also a JavaScript primitive type
    - Represents an unknown value, which is a variable or property which is not assigned
    - Is returned when a non-existing object property is called
    - Evaluates to false in boolean expressions

    .. code-block:: python
       :linenos:

       var notAssigned;
       write(notAssigned)
       if(!notAssigned){
         write("undefined evaluates to false");
       }
       var person = {
         name: "Frank"
       };
       write("non-existent object property: "+ person.age);
       write(typeof person)
       Output:
       undefined
       undefined evaluates to false
       non-existent object property: undefined
       Object

:ref:`Go Back <javascript-types-label>`.