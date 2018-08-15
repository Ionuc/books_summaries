.. _strategy-design-pattern-label:

Strategy
========

- is used when we have multiple algorithms for a specific task and client decides the actual implementation to be used at runtime

Intent
------
- define a family of algorithms and make them interchangeable
- capture the abstraction in an interface and hide the implementation details in derived classes

Example
-------

    .. image:: ../../../images/patterns/design/behavioral/strategy/class-diagram.png
        :align: center


When to use it ?
----------------
- when is like 1:many relationship : there is only one type of object to which there will be applied multiple operations

- The strategy is like a marriage - you create the object, it lives in the class that uses it, takes memory, has a room and makes
  itself a coffee in the morning :) . Of course they can get a divorce and switch to another class but that class would also live
  in its owner's context.

Ullink example:
---------------
    - example:
        - http://git-viewer/cgit/ul-confirm-extension.git/tree/odisys/extension/src/main/java/com/ullink/oms/ulconfirm/extension/reporting/filestrategy

:ref:`Go Back <design-patterns-behavioral-label>`.