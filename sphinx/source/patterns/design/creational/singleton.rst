.. _singleton-design-pattern-label:

Singleton
=========

Intent
------
- ensure a class has a finite number of instances and provide a global point of access to it
- most singleton classes are used to provide only one instance

Types of singleton
------------------

- Eager initialization
    - the instance is created at the time of class loading

    .. image:: ../../../images/patterns/design/creational/singleton/eager-initialization.png
        :align: center

    - drawback :
        - the instance is created even through the client application might not be using it
        - does not provide any way of exception handling

- Static block initialization
    - is similar to eager initialization, except that instance is created in the static block
    - provides option for exception handling

    .. image:: ../../../images/patterns/design/creational/singleton/static-block-initialization.png
        :align: center

- Lazy initialization
    - the instance is created only when the method is called

    .. image:: ../../../images/patterns/design/creational/singleton/lazy-initialization.png
        :align: center

Ullink example:
---------------

    .. image:: ../../../images/patterns/design/creational/singleton/ullink-example.png
        :align: center


:ref:`Go Back <design-patterns-creational-label>`.