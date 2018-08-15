.. _object:

Object class
============
    - is the super class of all classes in java
    - provides different methods:
        - boolean equals(Object obj)
            - decides whether two objects are meaningfully equivalent
        - void finalize()
            - called by garbage collector when the GB sees that the object cannot be referenced
        - int hashCode()
            - returns a hashcode int value for an object so that the object can be used in Collection classes
        - final void notify()
            - wakes up a thread that is waiting for this object’s lock
        - final void notifyAll()
            - wakes up all threads that are waiting for the object’s lock
        - final void wait()
            - causes the current thread to wait until another thread calls notify() or notifyAll() on this object
        - String toString()
            - return a “text representation” of this object

Overriding equals()
-------------------
    - Each time you want to sort a collection of objects, the equals() and hashCode() methods are essential.
    - Using operator == is evaluating the references of the objects.
    - Operator == simply looks at the bits in the variables
    - What means if you don’t override equals()
        - you will not use those object as a key in a hashtable
        - probably you won’t get accurate Sets such that there is not conceptual about duplication

The equals() contract
---------------------
    - it is reflexive : for any reference value x, x.equals(x) -> true
    - it is symmetric, x.equals(y) -> y.equals(x);
    - it is transitive, x.equals(y) and y,equals(z) -> x.equals(z)
    - it is consistent, multiple calls of x.equals(y) with the same value will return  the same boolean value 

Overriding hashCode()
---------------------
    - Hashcodes are typically used to increase the performance of large collections of data.
    - The hashcode value of an object is used by some collections.
    - Collections such a HashSet or HashMap use the hashcode to determine how the object should be stored and
      how to retrieve the corresponding value from the collection.
    - Understanding hash codes
        - The hashcode tell only which bucket to go into and not how to locate the name once we’re in that bucket
        - Hashing retrieval is a two-step process:
            - find the right bucket ( using hashcode())
            - search the bucket for the right element ( using equals())
    - always use the same fields values for both equals & hashCode implementations

The hashcode contract()
-----------------------
    - invoked many times on the same object, should return consistently the same integer
    - if two objects are equals according to the equals ( Object) method, then calling the hashCode() method on each of the two objects must produce the same integer result
    - it is not required that if two object are unequal according to the equal() method, then calling hashcode() method will produce different values.


:ref:`Go Back <java-label>`.