.. _java-tools-memory-management-garbage-collector-gc-zgc-gc-label:

Zgc Garbage Collector
============================
- was introduced in Java 11 and become production ready in Java 15
- ZGC intends to provide stop-the-world phases as short as possible
- It achieves it in such a way that the duration of these pause times doesn’t increase with the heap size

- is a low-latency garbage collector developed by Oracle for OpenJDK and designed with goals similar to Shenandoah:
    - minimizing pause times by performing nearly all GC work—including compaction—concurrently with application threads
- Like Shenandoah, it uses read barriers to maintain reference consistency while objects are being relocated in the heap
- As of JDK 21, ZGC introduced a generational mode, which further improves performance by separating short-lived and long-lived objects. This generational version became the default in JDK 23, offering better throughput while maintaining ZGC’s hallmark low-pause behavior.

How is working
--------------
- ZGC has a phase called marking, where we find the reachable objects
- A GC can store object state information in multiple ways. For example, we could create a Map, where the keys are memory addresses, and the value is the state of the object at that address. It’s simple but needs additional memory to store this information. Also, maintaining such a map can be challenging.
- ZGC it stores the reference state as the bits of the reference.It’s called reference coloring
    -  Setting bits of a reference to store metadata about an object means that multiple references can point to the same object since the state bits don’t hold any information about the location of the object. Multimapping to the rescue!
- ZGC uses relocation to achieve this. But with a large heap, relocation is a slow process. Since ZGC doesn’t want long pause times, it does most of the relocating in parallel with the application
    - Let’s say we have a reference to an object. ZGC relocates it, and a context switch occurs, where the application thread runs and tries to access this object through its old address. ZGC uses load barriers to solve this. A load barrier is a piece of code that runs when a thread loads a reference from the heap – for example, when we access a non-primitive field of an object.
- In ZGC, load barriers check the metadata bits of the reference
    - Depending on these bits, ZGC may perform some processing on the reference before we get it. Therefore, it might produce an entirely different reference. We call this remapping.

Marking
-------
- ZGC breaks marking into three phases:
    - The first phase is a stop-the-world phase:
        - In this phase, we look for root references and mark them
        - Root references are the starting points to reach objects in the heap, for example, local variables or static fields.
        - Since the number of root references is usually small, this phase is short.
    - second phase is concurrent:
        - we traverse the object graph, starting from the root references. We mark every object we reach.
        - Also, when a load barrier detects an unmarked reference, it marks it too
    - thrid pahse is also a stop-the-world phase to handle some edge cases, like weak references.

Reference Coloring
------------------
- A reference represents the position of a byte in the virtual memory
- However, we don’t necessarily have to use all bits of a reference to do that – some bits can represent properties of the reference.
- That’s what we call reference coloring.
- With 32 bits, we can address 4 gigabytes. Because computers needs more than these as memory, it means ZGC is only available on 64-bit platforms becasue ZGC uses 64-bit references


  .. image:: ../../../../images/java/tools/memory-management/garbage-collector/zgc-gc-reference-coloring.png
        :align: center


- ZGC references use 42 bits to represent the address itself. As a result, ZGC references can address 4 terabytes of memory space.
    -  we have 4 bits to store reference states (We also called these bits metadata bits)
        - finalizable bit – the object is only reachable through a finalizer
        - remap bit – the reference is up to date and points to the current location of the object (see relocation)
        - marked0 and marked1 bits – these are used to mark reachable objects

Relocation
----------
-  relocation consists of the following phases:
    - A concurrent phase, which looks for blocks, we want to relocate and puts them in the relocation set.
    - A stop-the-world phase relocates all root references in the relocation set and updates their references.
    - A concurrent phase relocates all remaining objects in the relocation set and stores the mapping between the old and new addresses in the forwarding table.
    - The rewriting of the remaining references happens in the next marking phase. This way, we don’t have to traverse the object tree twice. - Alternatively, load barriers can do it, as well.

Remapping and Load Barriers
---------------------------
- Note that in the relocation phase, we didn’t rewrite most of the references to the relocated addresses.
- Therefore, using those references, we wouldn’t access the objects we wanted to. Even worse, we could access garbage.
- ZGC uses load barriers to solve this issue. Load barriers fix the references pointing to relocated objects with a technique called remapping.
- When the application loads a reference, it triggers the load barrier, which then follows the following steps to return the correct reference:
    1. Checks whether the remap bit is set to 1. If so, it means that the reference is up to date, so can safely we return it. 
    2. Then we check whether the referenced object was in the relocation set or not. If it wasn’t, that means we didn’t want to relocate it. To avoid this check next time we load this reference, we set the remap bit to 1 and return the updated reference.
    3. Now we know that the object we want to access was the target of relocation. The only question is whether the relocation happened or not? If the object has been relocated, we skip to the next step. Otherwise, we relocate it now and create an entry in the forwarding table, which stores the new address for each relocated object. After this, we continue with the next step.
    4. Now we know that the object was relocated. Either by ZGC, us in the previous step, or the load barrier during an earlier hit of this object. We update this reference to the new location of the object (either with the address from the previous step or by looking it up in the forwarding table), set the remap bit, and return the reference.


Java 16 improvements
--------------------
- Before JDK 16, it performed relocation by using a heap reserve.
- Starting JDK 16, ZGC got support for in-place relocation, and it helps avoid OutOfMemoryError situations when garbage collection is required on a completely filled heap.


:ref:`Go Back <java-tools-memory-management-garbage-collector-label>`.