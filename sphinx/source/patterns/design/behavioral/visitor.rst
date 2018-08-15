.. _visitor-design-pattern-label:

Visitor
========

- is used when we have to perform an operation on a group of similar kind of Objects.
- with the help of this pattern, we can move the operational logic from the objects to another class

Intent
------
- represent an operation to be performed on the elements of an object structure
- visitor let you define new operation without changing the class of the elements on which it operates

Example
-------

    .. image:: ../../../images/patterns/design/behavioral/visitor/class-diagram.png
        :align: center

- example: https://sourcemaking.com/design_patterns/visitor/java/1

When to used it
---------------
- when is like many:many relationship: there are multiple types of objects to which there will be applied multiple operations
- when similar operations have to be performed on objects of different types grouped in a structure
- new operations have to be added without chnaging object structure
- gather related operations into a single class rather than force you to change or derive classes

- The visitor is like a one-night stand - you create it when you call the accept function and then they get separated and the
  visitor can be cleaned from the memory, it doesn't take any room for the class that use it.

Ullink example:
---------------
    - example:
        - http://git-viewer/cgit/ul-fees-odisys-api.git/tree/src/main/java/com/ullink/oms/fees/api/model/calculation

:ref:`Go Back <design-patterns-behavioral-label>`.