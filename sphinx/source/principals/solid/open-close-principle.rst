.. _open-close-principle-label:

Open Close principle
====================
    - a class should be open for extensibility but close for modification
    - in other words it means when you are written a class and put the class into production or other clients rely on that class, you are no longer
      allowed to make changes to that class. Instead, if you want to redefine the behavior of that class it should be open for extensibility

    - the strategy pattern allows to open a class for extensibility, but close it for modification
    - close for modification means that you can't really update or delete the class

    - open for extension:
      - means the module is available for extension
      - how to violate this principal: final classes, final methods, static fields and methods
    - close for modification:
      - means the class is defined with enough level of abstraction to extend if and create new types on the basis of it
      - means that extending the behavior of a module doesn't resultin changes in the source code of the module

:ref:`Go Back <principals-solid-label>`.