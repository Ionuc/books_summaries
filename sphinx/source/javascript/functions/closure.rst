.. _javascript-functions-closure-label:

Closure
=======
    - It is possible to have nested functions, a function within function
    - The inner function have access to variables defined outside of the inner function
    - The inner function have access to all variables that are in scope when is declared


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
       outer();


    .. code-block:: python
       :linenos:

       //Output:
       abc

:ref:`Go Back <javascript-functions-label>`.