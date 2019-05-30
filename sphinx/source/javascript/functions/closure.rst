.. _javascript-functions-closure-label:

Closure
=======
    - a closer is a function that remembers its outer variables and can access them
    - It is possible to have nested functions, a function within function
    - The inner function have access to variables defined outside of the inner function
    - The inner function have access to all variables that are in scope when is declared
    - nested functions can be returned

    .. code-block:: python
        :linenos:

       Var a = “a”;
       Var outer = function(){
           Var b = “b”;
         Var middle = function(){
             Var c  = “c”;
             // the function ‘inner’ can access all variables in the outer scopes
            Var inner function(){
                  Write (a+ b+ c);
             inner();
          };
          middle();
       };
       outer(); // abc

    - in JavaScript, all functions are naturally closures. There is one exception with the "new function" syntax
    - they automatically remember where they were created using a hidden [[Environment]] property.

:ref:`Go Back <javascript-functions-label>`.