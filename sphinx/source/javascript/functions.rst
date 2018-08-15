.. _javascript-functions-label:

Functions
=========
    - Functions can be invoked
    - There are multiple ways to declare a function:
        - Standard declaration: 
            - function square(x){ return x*x;}
            - square(7) => 49
        - Named and assigned function
            - Var squareFunc = function square(x) {return x*x;}
            - The function it will be called by the variable name and not by the function name
            - squareFunc(7) => 49
        - Anonymous and assigned function
            - Var square = function (x) {return x*x’}
            - It debug mode there will be no name on the stack trace
        - Anonymous and immediately invoked:
            - (function(x){return x*x;})(numberToSquare);
    - Passing more parameters to a function will ignore the extra parameters
    - It does not allow the function overloading. The last function will remain

    .. code-block:: python
       :linenos:

       Function add(num, num2, num3) {
           Return num + num2 + num3;
       }
       Function add(num, num2) {
           Return num + num2;
       }
       write (add(6,7));
       write(add(6,7,8);


    .. code-block:: python
       :linenos:

       // Output:
       13
       13

Function Overriding
-------------------
    - Functions cannot be overloaded
    - Provide parameter flexibility, passing too many or too few will not cause an error:
        - extra parameters are ignored, missing parameters are initialized with undefined
    - Object parameters are passed by reference and primitive parameters are passed by value

The Arguments Object
--------------------
    - Local variable available within all functions
    - Contains the functions parameters
    - Indexed like an array
    - Has a length property

    .. code-block:: python
       :linenos:

       Function argumentsType(){
           write(“lengts of arguments : “ + arguments.length);
          write(“arguments[0]: “+arguments[0]);
          Return typeof arguments
       }

       write(“Type of arguments:” + argumetnsType(“a”) + <<br/>”);
       // function that can be called with any number of parameters
       Function add(){
         Var total = 0;
         For (var i = 0 ; i < arguments.length; i++){
          Total += arguments[i];
         }
         Return total;
       }
       write(add());
       write(add(5));
       write(add(2,3,8,2));


    .. code-block:: python
       :linenos:

       //Output
       Length of arguments: 1
       Arguments[0]: a
       Type of arguments: object
       0
       5
       15 


:ref:`Go Back <javascript-label>`.