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

Java 8 improvements
-------------------

- Lambda Expressions
    - introduced with Java 8
    - provides a way to express instances of single method interfaces (functional interfaces)
    - has 3 main components:
        - parameters (without explicit types)
            - parameters are separated by comma
        - arrow token (or lambda operator) : "->"
            - separates parameters by body
        - body

       .. code-block:: python
            :linenos:


            // Before Java 8
            Collections.sort(myList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });
            
            // After Java 8 with Lambda expression
            Collections.sort(myList, (s1, s2) -> s1.compareTo(s2));
            Collections.sort(myList, (String s1, String s2) -> s1.compareTo(s2));

    - lambda expression can reference variables from surounding scope but have a restricton that they can only capture variables that are effectively final or final:
        - this restriction is in place to ensure lambda expression can safely capture variables from the enclosing scope without unexpected changes
        - ensures the lambda expression behave predictably and don't introduce side effects

       .. code-block:: python
            :linenos:


            // Effectively final variable
            int effectivelyFinalVariable = 20;

            // Lambda expression using effectively final variable
            MyInterface myFunc = () -> {
                System.out.println("Effectively Final Variable: " + effectivelyFinalVariable);
            };


- Method Reference
    - was introduced in Java 8
    - provide a short annotation for expressing lambda expression that directly invoke a method or contrusctor
    - allows to replace lamda expression with a reference to an existing method
    - there are 4 main types of method references:
        - reference to a static method


       .. code-block:: python
            :linenos:

            Function<String, Integer> parseIntLambda = s -> Integer.parseInt(s);
            Function<String, Integer> parseIntReference = Integer::parseInt;


        - reference to an instance method of particular object


       .. code-block:: python
            :linenos:

            List<String> words = Arrays.asList("apple", "banana", "orange");
            words.forEach(s -> System.out.println(s.toUpperCase()));
            // Method reference
            words.forEach(System.out::println);


        - reference to an instance method of an arbitrary object of a particular type


       .. code-block:: python
            :linenos:

            // Lambda expression
            Comparator<String> lengthComparatorLambda = (s1, s2) -> s1.length() - s2.length();
            // Method reference
            Comparator<String> lengthComparatorReference = Comparator.comparing(String::length);


        - reference to a constructor

       .. code-block:: python
            :linenos:

            // Lambda expression
            Supplier<List<String>> listSupplierLambda = () -> new ArrayList<>();
            // Method reference
            Supplier<List<String>> listSupplierReference = ArrayList::new;


:ref:`Go Back <java-development-class-object-label>`.