.. _java-development-generics-diamond-operator-label:

Diamond Operator
================

The Java 7 “Diamond” Syntax
---------------------------

    .. code-block:: python
       :linenos:

        Instead of writing :
        List<Dog> myDogs = new ArrayList<Dog>()or
        Map<String, Dog> dogMap = new HashTable<String, Dog>();

        you can write:

        List<Dog> myDogs = new ArrayList<>();
        Map<String, Dog> dogMap = new HashTable<>();


Anonymous inner class Fix with Java 9
-------------------------------------

- with Java 7, diamond operator cannot be used with anonymous inner class. This


    .. code-block:: python
       :linenos:

        // with java 7
        Handler<Integer> intHandler = new Handler<Integer>(1) {
           @Override
           public void handle() {
              System.out.println(content);
           }
        };

        // with Java 9
        Handler<Integer> intHandler = new Handler<>(1) {
            @Override
            public void handle() {
                System.out.println(content);
            }
        };



:ref:`Go Back <java-development-generics-label>`.