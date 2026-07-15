.. _java-development-class-object-methods-label:

Methods
=======
- a method is a block of code which is refered to by name and can be called any time we want
- components of methods:
    - access modifiers
    - determine the scope (static or not)
    - return type
    - name
    - the list of parameters
    - the list of exceptions thrown
    - body of the method

- overloading vs overwriting

Parameter Passing Mechanism
---------------------------
- you can pass an object:
    - by value
        - the value of the parameter is copied into another location of the memory
        - in case the function is changing that value, it will change only the copy and NOT the original variable
        - primitives and reference types of data are passed by value
    - by reference:
        - it is passed the reference of the parameter
        - in case the functon is changing the value for that reference, it will change also the original variable

Recursive methods
-----------------
- are methods which calls itself continuously
- it should have a stop condition to exit the method, otherwise StackOverflowError exception is thrown
- advantage:
    - are more clear and have less code
- disadvantage:
    - in case there will be al lot of methods invocations, or stop condition will be written incorretly, your program will be stopped because of StackOverflowError

Variable Length Arguments
-------------------------
- is used to pass arguments to a methods, but you don't know the number of them
- variable lentgth arguments can be seen as an Array of elements
- should be declared as last parameter of the methods

   .. code-block:: python
        :linenos:

        public static void main(String... args) {
            System.out.println("Total sum is " + sum(1, 2, 3, 4))
            System.out.println("Total sum is " + sum(1, 2, 3, 4, 5))
        }

        private int sum(int... ints) {
            int sum = 0;
            for (int i : ints){
                sum +=i
            }
            return sum;
        }


:ref:`Go Back <java-development-class-object-label>`.