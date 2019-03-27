.. _javascript-gc-label:

Garbage Collection
==================
    - memory management in JavaScript is performed automatically

Reachability
------------
    - the main concept of memory management in JavaScript is reachability
    - "reachable" values are those that are accessible or usable somehow. They are guaranteed to be stored in memory
    - there's a base set of inherently reachable values which cannot be deleted from memory. These arecalled ROOTS:
        - local variables and parameter of the current function
        - variables and parameters of other function on the current chain of nested calls
        - global variables
        - etc
    - any other value is considered reachable if it's reachable from a root by a reference or by a chain of references
    - the background process called garbage collector is monitoring all objects and removed:
        - those that have become unreachable
        - unreachalbe island

Internal algorithms
-------------------
    - the basic carbage collection algorithm is called "mark-and-sweep"
    - steps:
        - the garbage collector takes roots and marks (remember) them
        - then it visits and marks all references from them
        - then it visit marked objects and marks their referenced. All visited objects are remembered, and is optimized to no
          visit same object twice

        - redo step above until all references are processed
        - all objects except marked one are removed
    - JavaScript engines apply many optimization to make it run faster and not affect the execution:
        - Generational collection:
            - objects are slit into 2 sets : "new ones" and "old ones"
            - many objects appera, do their job and fie fast, they can be cleaned up aggressively
            - those tht survive for long enough, become "old" and are examined less often
        - Incremental collection
            - if there are many objects, engine tries to split the GC into pieces
            - pieces are executed one by one, separately
            - that requirs some extra bookeeping between them to track changed, but we have may tiny delays instead of a big 
              one

        - Idle-time collection:
            - the GC tries to run only while the CPU is idle, to reduce the possible effect on the execution


:ref:`Go Back <javascript-label>`.