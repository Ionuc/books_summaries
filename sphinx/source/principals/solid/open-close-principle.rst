.. _open-close-principle-label:

Open Close principle
====================
    - a class should be open for extensibility but close for modification
    - in other words it means when you are written a class and put the class into production or other clients rely on that class, you are no longer
      allowed to make changes to that class. Instead, if you want to redefine the behavior of that class it should be open for extensibility

    - the strategy pattern allows to open a class for extensibility, but close it for modification
    - close for modification means that you can't really update or delete the class

:ref:`Go Back <solid-principals-label>`.