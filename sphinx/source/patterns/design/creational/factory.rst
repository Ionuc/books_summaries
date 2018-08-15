.. _factory-design-pattern-label:

Factory
=======

- is a creational pattern

Intent
------
- provide a class which is creating instances of other class or classes

Types of factory
------------------

- Static method factory
    - the instance is created using a static method of a class
    - it is not needed an instance of the factory class in order to create the needed instances
    - real example : String.valueOf()

    .. image:: ../../../images/patterns/design/creational/factory/static-method.png
        :align: center

    - ullink example:
        - http://git-viewer/cgit/ul-confirm-odisys-api-helpers.git/tree/src/main/java/com/ullink/oms/ulconfirm/apihelper/models/details/RecipientFactory.java
        - http://git-viewer/cgit/ul-confirm-odisys-api-helpers.git/tree/src/main/java/com/ullink/oms/ulconfirm/apihelper/models/details/MediumDetailsContextFactory.java

- Simple factory
    - is similar to static method factory, but the instance is created using a non-static method
    - an instance of the factory method is needed

    .. image:: ../../../images/patterns/design/creational/factory/simple.png
        :align: center

    - ullink example:

        - http://git-viewer/cgit/ul-confirm-extension.git/tree/odisys/extension/src/main/java/com/ullink/oms/ulconfirm/extension/workers/request/ConfirmResourcesRequestWorkerFactory.java

    .. image:: ../../../images/patterns/design/creational/factory/ullink-simple.png
        :align: center

- Factory method
    - "define an interface for creating an object, but let subclasses decide which class to instantiate"
    - each implementation of the factory interface can produce specific subclasses of the intended instance class

    .. image:: ../../../images/patterns/design/creational/factory/factory-method.png
        :align: center

    - real example:
        - the iterator() function in the collection API. Each collection implements the interface Iterable<E> which describe a function iterator() that return
          an Iterator<E>
        - the strength of this pattern is that you don't need to know what type of collection you are using, each collection will provide an Iterator through
          the factory method iterator()

- Abstract factory method
    - provide an interface for creating families of related or dependent objects without specifying their concrete classes
    - is a generalization fo the factory method pattern expect that the factory interface have multiple factory methods that are related

    .. image:: ../../../images/patterns/design/creational/factory/abstract-factory-method.png
        :align: center

:ref:`Go Back <design-patterns-creational-label>`.