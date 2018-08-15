.. _inner-classes:

Inner classes
=============
    - Inner classes let you define classes inside classes. There are 4 inner classes 
        - Inner Class
        - Method-local inner class
        - Anonymous inner class
        - Static nested class

Inner Class
-----------
    - A regular inner class cannot have static declarations of any kind
    - The only way you can access the inner class is through a live instance of the outer  class.
    - If you want to instantiate an inner class object from outside the outer class instance
    - the inner class can access methods and fields from the enclosing class as if it owned them. This is because the inner class secretly captures a reference
      to the particular object of the enclosing class that was responsible for creating it. Then, when you refer to a member of the enclosing class,
      that reference is used to select that member.

    - It doesn’t matter how deeply an inner class may be nested—it can transparently access all of the members of all the classes it is nested within

    .. code-block:: python
       :linenos:

        MyOuter.MyInner inner = mo.new MyInner();  // -> create instance of inner class
        inner.setOuter() // -> call the method from inner class
        or
        MyOuter.MyInner inner = new MyOuter() .new MyInner()

    - review of "this"
        - the keyword “this” can be used only from within instance code ( not from static code)
        - the “this” reference is a reference to the currently executing object ( the object whose reference was used to invoke the currently running method )
        - this “this” reference is the way an object can pass a reference to itself to some code / method
        - If you want to use “this” inside inner class:

    .. code-block:: python
       :linenos:

       class MyOuter{
           class MyInner{
   	       public void seeOuter(){
    	       System.out.println(“Inner class reference is ” + this);
               System.out.println(“Outer class reference is ” + MyOuter.this);
   	       }
	   }

    - extending inner class would imply to provide the constructor

    .. code-block:: python
       :linenos:

       class WithInner {
           class Inner {}
       }
       public class InheritInner extends WithInner.Inner {
           //! InheritInner() {} // Won’t compile
           InheritInner(WithInner wi) {
               wi.super();
           }
       }

Method-Local Inner Class
------------------------
    - Method-local inner classes are inner classes defined inside a method ( and not inside directly to the outer class). 
    - a method-local inner class can be instantiated only inside that method
    - a method-local inner class cannot access the variables inside that method

Anonymous Inner Class
---------------------
    - it is a inner class without name
    - it can access only final fields from outer class
    - can have static and non-static fields & methods
    - it can be in form of :
        - normal :  anonymous inner classes are declared as part of variable assignment
        - argument-definition : are actually declared, defined and instantiated within the argument

Static nested Class
-------------------
    - is an static inner class
    - it can be access without having an instance of the outer class
    - You can’t access a non-static outer-class object from an object of a nested class.
    - it does not have access to variables and method from outer class which are not static


:ref:`Go Back <java-label>`.