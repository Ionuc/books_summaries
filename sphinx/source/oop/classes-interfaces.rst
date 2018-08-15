.. _oop-classes-interfaces-label:

Classes vs Interfaces
=====================

Classes
-------
- from the class declaration you can find:
    - its access modifier
    - the class name
    - the name of the class's parent (super class)
    - the list of interfaces which implements
- there are 4 access modifiers: public / private / protected package / default packaged
- defines state (fields) and behavior (methods) for an instance
- there are several kind of variables:
    - fields -> member variables
    - local variables -> variables in a method of block of code
    - parameters -> variable in method declaration
- method declaration consist of:
    - modifiers
    - return type
    - method name
    - parameter list
    - exception list
    - method body
- method signature consist of : method's name + parameter types
- constructors:
    - is used to specify how the instance is created
    - each class provides the default constructor without arguments
    - if you add one, the default will be removed (unless you added the signature for the default one)
    - you can use access modifiers in a constructor's declaration to control which other classes can call it
    - you can use the constructor of the super class by calling
- passing information to a method:
    - parameters -> refers to the list of variables in a method declaration
    - arguments -> are the exact values passed when the method is invoked
    - arbitrary number of arguments:
        - you can pass to the same method a different number of parameter, if those parameters are of the same type
        - it is done using a construct called varargs

        .. image:: ../images/oop/varargs.png
            :align: center

    - primitives are passed by value:
        - any changes to the values of the parameters exist only within the scope of the method
        - when the method returns, the parameters are gone and the changes are lost

        .. image:: ../images/oop/passing-pritimive-data-type.png
            :align: center

    - reference data parameters are passed by reference:
        - when method returns, the passed-in references the same object as before
        - the values of the object can be changed

        .. image:: ../images/oop/passing-reference-data-type.png
            :align: center

- returning a value from a method:
    - a method return to the code that invoked it when it:
        - completes all the statements in the method
        - reaches a return statement
        - throws an exception
- returning a class or interface:
    - when a method uses a class name as its return type, then the type of the returned object must:
        - exact class
        - subclass if it

        .. image:: ../images/oop/returning-class-from-method.png
            :align: center

        - in the example above:
            - if you have a method returning Number
            - the object return can be of type Number or ImaginaryNumber because ImaginaryNumber is a Number but Object it may not be

Abstract class
--------------
- is a class that is declared abstract
- it may or may not include abstract methods
- abstract classes cannot be instantiated, but only subclassed

Interfaces
----------
- is a reference type which contains only constants, method signatures, default methods, static methods, nested types
- cannot be instantiated, they can be only implemented by class or extended by other interfaces
- it has the declaration of the method
- with JDK 8, you can have default methods
- adding new method to interface will force the caller t provide an implementation to it
- how to enhance an interface without forcing the callers to change the code ?
    - add default methods
    - add static methods
    - create new interface with the new method and the caller will use it if needed
- default methods:
    - enable you to add new functionality of your libraries
    - ensure binary compatibility with code written for older version of those interfaces
- extending interfaces that contain default methods
    - when you extend an interface that contains a default method, you can do following:
        - not mention the default method at all, which will inherit the default method
        - redeclare the default method, which makes it abstract
        - redefine the default method, which overrides it

Abstract class vs Interface
---------------------------
- with abstract class:
    - you can declare fields that are not static and final
    - define public, protected, private concrete methods
    - can extend only one other class
    - can have a state

- with interfaces:
    - all fields are automatically public, static and final
    - all methods declared are public
    - can have only default / static / abstract method
    - can extend multiple interfaces
    - cannot have state

When to use ?
-------------
- consider using abstract classes if any of these statements apply:
    - you want to share code among several closely related classes
    - you expect that classes that extend your abstract class:
        - have many common methods or fields
        - requires access modifiers other than public ( such as protected )
    - you want to declare non-static or non-final fields:
        - this enables you to define methods that can access and modify the state of the object to which they belong
    - example:
        - AbstractMap. It subclasses (HashMap, TreeMap, ... ) share many methods (get, put, ..) defined in AsbtractMap
- consider using interfaces if any of these statements apply:
    - you expect that unrelated classes would implement your interface:
        - example: Comparable, Cloneable are implemented by many unrelated classes
    - you want to specify the behavior of a particular data type, but you don't care about who implemented it
        - example : define an API ( contract )
    - you want to take advantage of multiple inheritance of type
    - example -HashMap :
        - implements multiple interfaces : Serializable, Cloneable
        - by reading the list of interface you can infer that an instance of HashMap:
            - can be cloned
            - is serializable
            - has the functionality of a map

:ref:`Go Back <oop-label>`.