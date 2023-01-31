.. _java10_local_variable_type:

Local Variable Type
===================

    - untile Java 9, the type of the variable must be explcitily set, else the compiler will not validate the code

    .. code-block:: python
           :linenos:

           String message = "Untile java 9";

    - with Java 9, the type of the variable can be replaced wtih "var"


    .. code-block:: python
           :linenos:

           var message = "With java 10";
           assertThat(message, "With java 10")

    - this feature is available only for local variables with initalization. It cannot be used for members
      of the class, method parameter, return type, etc. With the initialization, the compile will detect
      the corresponding type
    - to be backword compatible with programs who where already using word "var", in Java 10, "var" is a reserved type name,
      just like "int" or "double".
    - also, there is no runtime processing for "var" definitions

Illegal usages of "var"
-----------------------
    - "var" won't work:

    - 1) without an initialization


    .. code-block:: python
           :linenos:

            var n; // error: cannot use 'var' on variable without initializer

    - 2) when initialiazing with null

    .. code-block:: python
           :linenos:

            var n = null; // error: variable initializer is 'null'

    - 3) for non-local variables

    .. code-block:: python
           :linenos:

            public var = "hello"; // error: 'var' is not allowed here


    - 4) in lambda expression

    .. code-block:: python
           :linenos:

            var p = (String s) -> s.length() > 10; // error: lambda expression needs an explicit target-type

    - 5) in case of aray initialization

    .. code-block:: python
           :linenos:

            var arr = { 1, 2, 3 }; // error: array initializer needs an explicit target-type


Cases when "var" can be used, but it is not ok
----------------------------------------------

    - there are cases when "var" can be used (no compilation errors), but it should be avoided, when: 

    - 1) it makes the code harder to read. In the example below, is very hard to determine the returned type:


    .. code-block:: python
           :linenos:

            var result = obj.prcoess();


    - 2) in streams with long pipelines:


    .. code-block:: python
           :linenos:

            var x = emp.getProjects.stream().findFirst().map(String::length).orElse(0);


    - 3) used with diamond operation, it can return unexpected result:


       .. code-block:: python
               :linenos:

                var list = new ArrayList<>(); // list has type ArrayList<Onject> and not List<Object>


    - 4) unsing "var" with non-denotable types can result in unexpected behavior, like:

   .. code-block:: python
           :linenos:

            var obj = new Object() {};
            assertFalse(obj.getClass().equals(Object.class)); // type of obj is not Object.class
            obj = new Object(); // error: Object cannot be converted to <anonymous Object>


:ref:`Go Back <java10-label>`.