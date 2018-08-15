.. _javascript-error-handling-label:

Error handling
==============
    - Throw an exception when an unusual error condition occurs
    - Exceptions are thrown using the throw statement
    - The exception object can be accessed when the exception is caught


    .. code-block:: python
       :linenos:

       If (somethingGoesWrong){
           throw {
               name : “name”,
               message : “message”
           };
       }


:ref:`Go Back <javascript-label>`.