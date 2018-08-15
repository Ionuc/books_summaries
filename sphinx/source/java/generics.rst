.. _generics:
Generics
========
    - the JVM has no idea about generics
    - the type information does not exists at runtime
    - all the generic code is strictly for the compiler
    - through a process called “type erasure”, the compiler does all of its verifications on your generics code and then strips the type
      information out of the class bytecode.
    - generics are more compile-time protection. This is different from arrays, which give you both compile-time protection and runtime protection

.. code-block:: python
   :linenos:

   private static void insert(List l)
   {
       l.add("Ionut");
   }
   public static void main(String[] args) throws IOException {
       List<Integer> l = new ArrayList<>();
       insert(l);
       l.add(3);
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
    - writing - Given the above possible assignments, what type of object could you add to List foo3 that would be legal
      for all the above possible ArrayList assignments

        - You can't add an Integer because foo3 could be pointing at a List<Double>.
        - You can't add a Double because foo3 could be pointing at a List<Integer>.
        - You can't add a Number because foo3 could be pointing at a List<Integer>.

    - You can't add any object to List<? extends T> because you can't guarantee what kind of Listit is really pointing to,
      so you can't guarantee that the object is allowed in that List
    - The only "guarantee" is that you can only read from it and you'll get a T or subclass of T

Using super
-----------
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
    - writing: Given the above possible assignments, what type of object could you add to List foo3 that would be legal for all the
      above possible ArrayList assignments

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

:ref:`Go Back <java-label>`.