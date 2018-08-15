.. _builder-design-pattern-label:

Builder
=======

- was introduced to solve some of the problems with factory pattern ( when contains a lot of attributes )
    - to many arguments to pass from client program to the factory class that can be error prone because the arguments type can be the same
    - some parameters might be optional, but you are forced to provide them
    - if the object is heavy and its creation is complex, then all the complexity will be part of the Factory classes

Intent
------
- create wrapper which is enhancing the instance with different field values and provide the access to the instance in the end

Ullink example:
---------------
    - example:
        - http://git-viewer/cgit/ul-allocation-odisys-api-helpers.git/tree/src/main/java/com/ullink/oms/ulallocation/apihelper/model/MatchingRuleBuilder.java

    .. image:: ../../../images/patterns/design/creational/builder/ullink-example.png
        :align: center


:ref:`Go Back <design-patterns-creational-label>`.