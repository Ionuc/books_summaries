.. _java-development-class-object-hidden-class-label:

Hidden Class
============
- implemented with Java 15

Goal
----
- Allow frameworks to define classes as non-discoverable implementation details of the framework, so that they cannot be linked against by other classes nor discovered through reflection.
- Support extending an access control nest with non-discoverable classes.
- support aggressive unloading of non-discoverable classes, so that frameworks have the flexibility to define as many as they need.
- Deprecate the non-standard API sun.misc.Unsafe::defineAnonymousClass, with the intent to deprecate it for removal in a future release.

Motivation
----------
- Many language implementations built on the JVM rely upon dynamic class generation for flexibility and efficiency.
- For example, in the case of the Java language, javac does not translate a lambda expression into a dedicated class file at compile time but, rather, emits bytecode that dynamically generates and instantiates a class to yield an object corresponding to the lambda expression when needed

- Language implementors usually intend for a dynamically generated class to be logically part of the implementation of a statically generated class.
- This intent suggests various properties that are desirable for dynamically generated classes:
    - Non-discoverability: 
        - Being independently discoverable by name is not only unnecessary but harmful
        - t undermines the goal that the dynamically generated class is merely an implementation detail of the statically generated class.
    - Access control:
        - It may be desirable to extend the access control context of the statically generated class to include the dynamically generated class
    - Lifecycle:
        - Dynamically generated classes may only be needed for a limited time, so retaining them for the lifetime of the statically generated class might unnecessarily increase memory footprint.

- Unfortunately, the standard APIs that define a class -- ClassLoader::defineClass and Lookup::defineClass -- are indifferent to whether the bytecodes of the class were generated dynamically (at run time) or statically (at compile time). 
- These APIs always define a visible class that will be used every time another class in the same loader hierarchy tries to link a class of that name.
- Consequently, the class may be more discoverable or have a longer lifecycle than desired


How is working
--------------
- The Lookup API introduced in Java 7 allows a class to obtain a lookup object that provides reflective access to classes, methods, and fields
- the reflective access always occurs in the context of the class which originally obtained the lookup object -- the lookup class
- In effect, a lookup object transmits the access rights of the lookup class to any code which receives the object.

- Java 9 enhanced the transmission capabilities of lookup objects by introducing the method Lookup::defineClass(byte[])
- From the bytes supplied, this method defines a new class in the same context as the class which originally obtained the lookup object
- That is, the newly-defined class has the same defining class loader, run-time package, and protection domain as the lookup class

- This new implementation proposes to extend the Lookup API to support defining a hidden class that can only be accessed by reflection.
- A hidden class is not discoverable by the JVM during bytecode linkage, nor by programs making explicit use of class loaders (via, e.g., Class::forName and ClassLoader::loadClass)
- A hidden class can be unloaded when it is no longer reachable, or it can share the lifetime of a class loader so that it is unloaded only when the class loader is garbage collected
- Optionally, a hidden class can be created as a member of an access control nest.

Creating a hidden class
-----------------------
- a normal class is created by invoking ClassLoader::defineClass
- a hidden class is created by invoking Lookup::defineHiddenClass
- This causes the JVM to derive a hidden class from the supplied bytes, link the hidden class, and return a lookup object that provides reflective access to the hidden class
- The invoking program should store the lookup object carefully, for it is the only way to obtain the Class object of the hidden class.

- After the hidden class is derived, it is linked as for a normal class (JVMS 5.4), except that no loading constraints are imposed.
    - After the hidden class is linked, it is initialized if the initialize argument of Lookup::defineHiddenClass is true; if the argument is false, then the hidden class will be initialized when reflective methods instantiate it or access its members.

-  A hidden class is not anonymous
- It has a name that is available via Class::getName and may be shown in diagnostics (such as the output of java -verbose:class), in JVM TI class loading events, in JFR events, and in stack traces
- However, the name has a sufficiently unusual form that it effectively makes the class invisible to all other classes


Hidden classes and class loaders
--------------------------------
- Despite the fact that a hidden class has a corresponding Class object, and the fact that a hidden class's supertypes are created by class loaders, no class loader is involved in the creation of the hidden class itself.

- a hidden class is deemed to have a defining class loader. This is necessary to resolve types used by the hidden class's own fields and methods
- In particular, a hidden class has the same defining class loader, runtime package, and protection domain as the lookup class, which is the class that originally obtained the lookup object on which Lookup::defineHiddenClass is invoked.


:ref:`Go Back <java-development-class-object-label>`.