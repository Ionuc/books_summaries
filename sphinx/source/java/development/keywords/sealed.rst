.. _java-development-keywords-sealed:

Sealed keyword
==============
- implemented with Java 15
- goal:
    - Allow the author of a class or interface to control which code is responsible for implementing it.
    - Provide a more declarative way than access modifiers to restrict the use of a superclass.
    - Support future directions in pattern matching by underpinning the exhaustive analysis of patterns.

- A sealed class or interface can be extended or implemented only by those classes and interfaces permitted to do so.
- A class is sealed by applying the sealed modifier to its declaration
- Then, after any extends and implements clauses, the permits clause specifies the classes that are permitted to extend the sealed class
- The classes specified by permits must be located near the superclass: either in the same module (if the superclass is in a named module) or in the same package (if the superclass is in the unnamed module)


    .. code-block:: python
            :linenos:

            package com.example.geometry;

            public abstract sealed class Shape permits Circle, Rectangle, Square {...}

- or:


    .. code-block:: python
            :linenos:

            package com.example.geometry;

            public abstract sealed class Shape 
                permits com.example.polar.Circle,
                        com.example.quad.Rectangle,
                        com.example.quad.simple.Square {...}


    .. code-block:: python
            :linenos:

            package com.example.geometry;

            abstract sealed class Shape {...}
            class Circle    extends Shape {...}
            class Rectangle extends Shape {...}
            class Square    extends Shape {...}


- A sealed class imposes three constraints on its permitted subclasses
    - The sealed class and its permitted subclasses must belong to the same module, and, if declared in an unnamed module, the same package
    - Every permitted subclass must directly extend the sealed class.
    - Every permitted subclass must choose a modifier to describe how it continues the sealing initiated by its superclass:

- A permitted subclass may be declared final to prevent its part of the class hierarchy from being extended further.
- A permitted subclass may be declared sealed to allow its part of the hierarchy to be extended further than envisaged by its sealed superclass, but in a restricted fashion.
- A permitted subclass may be declared non-sealed so that its part of the hierarchy reverts to being open for extension by unknown subclasses.


    .. code-block:: python
            :linenos:

            package com.example.geometry;

            public abstract sealed class Shape
                permits Circle, Rectangle, Square {...}

            public final class Circle extends Shape {...}

            public sealed class Rectangle extends Shape 
                permits TransparentRectangle, FilledRectangle {...}
            public final class TransparentRectangle extends Rectangle {...}
            public final class FilledRectangle extends Rectangle {...}

            public non-sealed class Square extends Shape {...}


- one and only one of the modifiers final, sealed, and non-sealed must be used by each permitted subclass
- It is not possible for a class to be:
    - both sealed (implying subclasses) and final (implying no subclasses),
    - both non-sealed (implying subclasses) and final (implying no subclasses),
    - both sealed (implying restricted subclasses) and non-sealed (implying unrestricted subclasses)

- A class which is sealed or non-sealed may be abstract, and have abstract members
- A sealed class may permit subclasses which are abstract 

Sealed interfaces
-----------------
-  an interface is sealed by applying the sealed modifier to the interface


    .. code-block:: python
            :linenos:

            package com.example.expression;

            public sealed interface Expr
                permits ConstantExpr, PlusExpr, TimesExpr, NegExpr {...}

            public final class ConstantExpr implements Expr {...}
            public final class PlusExpr     implements Expr {...}
            public final class TimesExpr    implements Expr {...}
            public final class NegExpr      implements Expr {...}


Sealed classes and Records
--------------------------
- Sealed classes work well with records


    .. code-block:: python
            :linenos:

            package com.example.expression;

            public sealed interface Expr
                permits ConstantExpr, PlusExpr, TimesExpr, NegExpr {...}

            public record ConstantExpr(int i)       implements Expr {...}
            public record PlusExpr(Expr a, Expr b)  implements Expr {...}
            public record TimesExpr(Expr a, Expr b) implements Expr {...}
            public record NegExpr(Expr e)           implements Expr {...}


- The combination of sealed classes and records is sometimes referred to as algebraic data types

JVM
---
- The Java Virtual Machine recognizes sealed classes and interfaces at runtime

:ref:`Go Back <java-development-label>`.