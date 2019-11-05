.. _java9-module-system-classpath-problems-label:

Classpath problems
==================
    - there is no guarantee the jar is complete, meaning it contains all the needed extra jars, which
      can lead to NoClassDefFoundError
    - there is no separation between the jars at all, meaning different jars can use the same class, but
      with different version
    - there is no encapsulation, meaning any class from the classpath can access other classes

:ref:`Go Back <java9-module-system-label>`.