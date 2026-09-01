.. _java-development-class-object-value-based-class-label:

Value-Based Class
=================
- since java 8
Overview
--------
- before value based class, there were 2 types: primitives and value types
    - primitives:
        - represent a single value and are not objects
        - there 8 such primitives: bytes, short, int, long, float, double, char, boolean
        - are store din stack
    - objects:
        - are stored in heap
        - are referenced by a reference address
        - Java provides wrapper over primitives: String, Integer, Doulble, Boolean

- Project Valhalla introduced a new type in the Java ecosystem that is somewhat between an object and a primitive, and it is termed a value-type.
    - Values types are not addressed by their reference but by their values, just like primitives
    - Value types are immutable types, and they do not have any identity.
    - These value types also do not support inheritance
    - Value types are not addressed by their reference but by their values, just like primitives.

Value-Based class
-----------------
- are classes that are desined to behave like a class and encapsuate value-types in Java
- are classes that represent simple immutable values
- JVM can freely switch between types and its value-based class, much like auto-boxing and unboxing
- properties:
    - immutability:
        - value-based classes are meant to represent immutabla data, similar to primitives
        - a value-based class is always final
        - it contains only final fields
        - the class can extend the Object class or a hierarchy of abstract classes that declare no instance field
    - object creation:
        - the class does not decalre any accesible constructor
        - the class should be instantiated only though factory methods
    - identity and equals(), hashCode(), toString() methods:
        - value-based class are identity-free
        - the implementations of equals(), hashCode(), toString() are defined solely based on the values of its instance members and not from their identities
        - we consider 2 objects to be equal solely on the objects; equals() cehck and not on reference-based equality, for example "=="
        - we can use 2 eual objects interchangeably and they should produce the same result on any computaton


Values-Bases class examples
---------------------------
- examples of :
    - java.util.Optiona;
    - java.utils.DataTime
    - Byte, Short, Integer, Long, Float, Doule, Char, Boolean (since Java 16)

Custom Value-based class
------------------------
- an implementation of Point is a good candidate for a value-based class because a specific point in space is unique and can be referred to only by its value


    .. code-block:: python
       :linenos:

        @ValueBased
        public final class Point {
            private static Point ORIGIN = new Point(0, 0, 0);
            private final int x;
            private final int y;
            private final int z;

            // inaccessible constructor
            private Point(int x, int y, int z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }

            public static Point valueOfPoint(int x, int y, int z) {
                // returns a cached instance if it is the origin, or a new instance
                if (isOrigin(x, y, z)) {
                    return ORIGIN;
                }
                return new Point(x, y, z);
            }

            // checking if a point is the origin
            private static boolean isOrigin(int x, int y, int z) {
                return x == 0 && y == 0 && z == 0;
            }
            @Override
            public boolean equals(Object other) {
                if (other == null || getClass() != other.getClass()) {
                    return false;
                }
                Point point = (Point) other;
                return x == point.x && y == point.y && z == point.z;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y, z);
            }
        }

        @Test
        public void givenValueBasedPoint_whenCompared_thenReturnEquals() {
            Point p1 = Point.valueOfPoint(1,2,3);
            Point p2 = Point.valueOfPoint(1,2,3);

            Assert.assertEquals(p1, p2);
        }

        @Test
        public void givenValueBasedPoint_whenOrigin_thenReturnCachedInstance() {
            Point p1 = Point.valueOfPoint(0, 0, 0);
            Point p2 = Point.valueOfPoint(0, 0, 0);

            // the following should not be assumed for value-based classes
            Assert.assertTrue(p1 == p2);
        }


Advantages
----------
- value-based classes are more memory efficnet as they do not have reference-based identity
- JVM can reuse the existing instances or create new ones based on the requirements
- value-based classes do not require synchronization

Value-Bases class vs other types
--------------------------------
- immutable classes in Java share a lot of common ground with Value-based classes:
    - value-based classes are, be definition, immutable but not vice-versa
- immutable classes are not identity-free like value-based classes:
    - instances of immutable classes having the same state are distinct and we can compare them based on reference equality
    - instances of value-based classes do not have the notion of refecente-based equality
- complex behavior:
    - immutable classes are free to provide accesible constructors and can have muliple attributes and complex behavior
    - value-based classes represent simple values and do not define complex behavior with dependen attributes

- comparing to records:
    - records have public constructors


Java 16 Improvements
--------------------
-  Byte, Short, Integer, Long, Float, Doule, Char, Boolean are not Value-based classes with the corresponding annotation

:ref:`Go Back <java-development-class-object-label>`.