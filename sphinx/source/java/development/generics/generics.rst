.. _java-development-generics-generics-label:
Generics
========
- the JVM has no idea about generics
- the type information does not exists at runtime
- all the generic code is strictly for the compiler
- through a process called “type erasure”, the compiler does all of its verifications on your generics code and then strips the type information out of the class bytecode.
- generics are more compile-time protection. This is different from arrays, which give you both compile-time protection and runtime protection

- problems solved by generics:
    - to make sure one collection contains only expected types:

    .. code-block:: python
       :linenos:

       private static void insert(List l)
       {
           l.add("Ionut");
       }

       public static void main(String[] args) throws IOException {
           List<Integer> l = new ArrayList<>();
           insert(l);
           l.add(3); // insert an Integer, even if previously it was inserted an String
       }

   - solution:

    .. code-block:: python
       :linenos:

       private static void print(List<Integer> l)
       {
           l.add("Ionut"); // => will result in compilation error
       }

       public static void main(String[] args) throws IOException {
           List<Integer> l = new ArrayList<>();
           insert(l);
           l.add(3);
       }


Generic Methods
---------------
- generic metods is a method that introduces its own type parameters:
    - you could use any letter as long as the same latter is used in paramter argument and method body
    - using just a letter without bounderies (which is not extends or super other class) which is doing extend or super other types, the parameter will be seen as Object type and only objects methods can be used (except the case of casting)
    - this letter can be used only inside the method paramters, method return type and method body


    .. code-block:: python
       :linenos:

        public static void main(String[] args) {
            String[] strings = {"one", "two", "three"};
            
            printArray(strings);
            
            Integer[] ints = {1, 2,  
            printArray(ints); // same method can be used with array with different types
        }
        
        private static <E> void printArray(E[] arr) {
            // Display array elements
            for (E element : arr) {
                System.out.println("Element: " + element);
            }
        }


    - if you want more informatio regarding the Generic type, you should use generic with bounderies, meaninig a type which extends or super other class


    .. code-block:: python
       :linenos:

        public static void main(String args[]) {
            System.out.printf("Max of %d, %d and %d is %d\n\n", 1, 2, 3, 
                    maxValue(1, 2, 3));

            System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n", 
                    1.1, 2.2, 3.3, maxValue(1.1, 2.2, 3.3));

            System.out.printf("Max of %s, %s and %s is %s\n", "Audi", 
                    "Acura", "Aston Martin", maxValue("Audi", "Acura", "Aston Martin"));
        }

        public static <T extends Comparable<T>> T maxValue(T x, T y, T z) {
            T max = x; // assume x is initially the largest

            if (y.compareTo(max) > 0) {
                max = y; // y is the largest so far
            }

            if (z.compareTo(max) > 0) {
                max = z; // z is the largest now
            }
            return max; // returns the largest object
        }

Generic Class
-------------
- you can parameterized the class with a generic type which can be used anywhere in the class

.. code-block:: python
    :linenos:


    public class GenericDemo3<T> {
        
        private T t;

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }

        public static void main(String[] args) {
            GenericDemo3<Integer> integerBox = new GenericDemo3<Integer>();
            GenericDemo3<String> stringBox = new GenericDemo3<>();

            integerBox.set(10);
    //      integerBox.set("ten"); // compilation error
            
            stringBox.set("Hello World");
    //      stringBox.set(10); // compilation error
            
            Integer integer = integerBox.get();
            String string = stringBox.get();

            System.out.printf("Integer Value : %d\n\n", integer);
            System.out.printf("String Value : %s\n", string);
        }
    }


List vs Array
-------------
- if you declare a List<Foo>, then you MUST put also ArrayList<Foo>
- with array you can change the generics type 

.. code-block:: python
   :linenos:

   List<Parent> parents = new ArrayList<Foo>(); // GOOD
   List<Parent> parents = new ArrayList<Child>() ; // BAD
   Parent[] = new Child[4]; // GOOD

Wildcard vs Object
------------------
- with ? you can put any type you want as generics
- with Object, you are forced to put only Object


.. code-block:: python
   :linenos:

   List<?> list = new ArrayList<Dog>();   -> GOOD
   List<? extends Animal> list = new ArrayList<Dog>(); -> GOOD
   List<?> foo = new ArrayList<? extends Animal>(); -> WRONG, you cannot use ? in declaration
   List<? extends Dog> list = new ArrayList<Integer>(); -> WRONG
   List<? super Dog> list = new ArrayList<Animal>(); -> GOOD
   List<? super Animal> list = new ArrayList<Dog>(); -> WRONG


Using extends
-------------
- is called lower bounded parameterization because we set boundary from the lower type
- you can only read values from that collection, you cannot add new elements except of NULL value


    .. code-block:: python
       :linenos:


        public static void main(String[] args) {
            List<Child> children = new ArrayList<>();
            
        //  processParentElements(children); // compilation error
            
            processElements(children);
            
            Child child = children.get(0);
            child.childSpecificMethod();
            
        }

        public static void processElements(List<? extends Parent> elements) {
            Parent parent = elements.get(0);
        //  Child child = elements.get(0); // compilation error
        //      
        //  elements.add(new Parent()); // compilation error
        //  elements.add(new Child()); // compilation error
        //  elements.add(new Grandchild()); // compilation error
            
            elements.add(null);
        }


- you can set multiple bounderies for the same generics:

    .. code-block:: python
       :linenos:

        // Example of multiple bounds
        public static <T extends Comparable<T> & Comparator<T>> T maxValue(T x, T y, T z) {
            T max = x; // assume x is initially the largest

            if (y.compareTo(max) > 0) {
                max = y; // y is the largest so far
            }

            if (z.compareTo(max) > 0) {
                max = z; // z is the largest now
            }
            return max; // returns the largest object
        }


- the wildcard declaration of List<? extends Number> foo3 means that any of these are legal assignments:


    .. code-block:: python
       :linenos:

       List<? extends Number> foo3 = new ArrayList<Number>(); // Number "extends" Number (in this context)
       List<? extends Number> foo3 = new ArrayList<Integer>(); // Integer extends Number
       List<? extends Number> foo3 = new ArrayList<Double>(); // Double extends Number


- reading: Given the above possible assignments, what type of object are you guarenteed to read from List foo3
    - You can read a Number because any of the lists that could be assigned to foo3 contain a Number or a subclass of Number.
    - You can't read an Integer because foo3 could be pointing at a List<Double>.
    - You can't read a Double because foo3 could be pointing at a List<Integer>.
- writing - Given the above possible assignments, what type of object could you add to List foo3 that would be legal for all the above possible ArrayList assignments

    - You can't add an Integer because foo3 could be pointing at a List<Double>.
    - You can't add a Double because foo3 could be pointing at a List<Integer>.
    - You can't add a Number because foo3 could be pointing at a List<Integer>.

- You can't add any object to List<? extends T> because you can't guarantee what kind of List it is really pointing to, so you can't guarantee that the object is allowed in that List
- only NULL can be added to that list
- The only "guarantee" is that you can only read from it and you'll get a T or subclass of T

Using super
-----------
- is called lower bounded paramterization because we set boundery from the 
- you can read values only from the parent -> Object

    .. code-block:: python
       :linenos:


        public static void main(String[] args) {
            List<Parent> parents = new ArrayList<>();
            List<Child> children2 = new ArrayList<>();
            processElements2(parents);
            processElements2(children2);
            
            List<Grandchild> grandChildren = new ArrayList<>();
        //  processElements2(grandChildren); // compilation error

        }

        public static void processElements2(List<? super Child> elements) {
            Object element = elements.get(0);
        //      Child parentElement = elements.get(0); // compilation error
            
            elements.add(new Child());
            elements.add(new Grandchild());
            elements.add(null);
            
        //  elements.add(new Parent());
        //  elements.add(new Object());
        }


- The wildcard declaration of List<? super Integer> foo3 means that any of these are legal assignments:


    .. code-block:: python
       :linenos:

       List<? super Integer> foo3 = new ArrayList<Integer>(); // Integer is a "superclass" of Integer (in this context)
       List<? super Integer> foo3 = new ArrayList<Number>(); // Number is a superclass of Integer 
       List<? super Integer> foo3 = new ArrayList<Object>(); // Object is a superclass of Integer
 

- reading: Given the above possible assignments, what type of object are you guaranteed to receive when you read from List foo3
    - You aren't guaranteed an Integer because foo3 could be pointing at a List<Number>or List<Object>.
    - You aren't guaranteed an Number because foo3 could be pointing at a List<Object>.
    - The only guarantee is that you will get an instance of an Object or subclass of Object(but you don't know what subclass).
- writing: Given the above possible assignments, what type of object could you add to List foo3 that would be legal for all the above possible ArrayList assignments

    - You can add an Integer because an Integer is allowed in any of above lists.
    - You can add an instance of a subclass of Integer because an instance of a subclass of Integer is allowed in any of the above lists.
    - You can't add a Double because foo3 could be pointing at a ArrayList<Integer>.
    - You can't add a Number because foo3 could be pointing at a ArrayList<Integer>.
    - You can't add a Object because foo3 could be pointing at a ArrayList<Integer>.

PECS
-----
- Producer Extends, Consumer Super
- Producer Extends:
    - If you need a List to produce T values (you want to read Ts from the list), you need to declare it with ? extends T, e.g. List<? extends Integer>.
    - But you cannot add to this list.
    - Consumer Super:
    - If you need a List to consume T values (you want to write Ts into the list), you need to declare it with ? super T, e.g. List<? super Integer>
    -  But there are no guarantees what type of object you may read from this list.
- If you need to both read from and write to a list, you need to declare it exactly with no wildcards, e.g. List<Integer>.


:ref:`Go Back <java-development-generics-label>`.