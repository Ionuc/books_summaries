.. _java-tools-memory-management-memory-foreign-api-access-label:

Foreign Api Memory Access
=========================
- implemented with Java 14

Motivation
----------
- Many existing Java libraries and programs access foreign memory, for example Ignite, mapDB, memcached, and Netty's ByteBuf API.
- By doing so they can:
    - Avoid the cost and unpredictability associated with garbage collection (especially when maintaining large caches),
    - Share memory across multiple processes, and
    - Serialize and deserialize memory content by mapping files into memory (via, e.g., mmap).
- Foreign memory generally refers to memory that lives outside the managed JVM heap. Because of this, it’s not subject to garbage collection and can typically handle incredibly large memory segments.
- allows Java programs to access foreign memory, such as native memory, outside the heap in a safe and efficient manner
    - built upon three main abstractions of MemorySegment, MemoryAddress and MemoryLayout, this API is a safe way to access both heap and non-heap memory.

Old ways to access native memory
--------------------------------

    - there are 3 ways to access native memory:
        - 1) ByteBuffer API
            - allows the creation of direct, off-heap byte buffers
            - can be directly accessed from a Java program
            - incorrect use of a ByteBuffer can cause a memory leak and OutOfMemory errors because an unused memory reference can prevent the garbage collector from deallocating the memory
            - limtattions:
                - the buffer size can’t be more than two gigabytes
                - the garbage collector is responsible for memory deallocation


        - 2) Unsafe API
            - is extremely efficient due to its addressing model
            - is unsave
            - drawbacks:
                - It often allows the Java programs to crash the JVM due to illegal memory usage
                - It’s a non-standard Java API

        - 3) JNI API
            - limitations:
                - Difficulties in interoperability with different calling conventions
                - Laborious data unpacking

New way: Foreign Function and Memory API
----------------------------------------
    - provides a supported, safe, and efficient API to access both heap and native memory and invoke native code
    - It provides several key components:
        - Arena: controls the lifecycle of native memory segments
        - MemorySegment: represents a contiguous region of memory, either on-heap or off-heap
        - MemoryLayout: describes the structure of memory segments
        - FunctionDescriptor: models the signature of foreign functions
        - Linker: facilitates linking Java code with native functions
        - SymbolLookup: looks up native symbols (functions, variables) by name

Allocating Native Memory
------------------------
- there are 2 components which can be used:
    - Arena
    - MemorySegment

Arena
-----
- is responsible for managing the lifecycle of native memory segments
- offers flexible memory allocation
- ensures proper deallocation when no longer needed.
- types of arena:
    - global: The global arena has an unbounded lifetime and cannot be closed manually.



    .. code-block:: python
           :linenos:

            Arena globalArena = Arena.global();
            MemorySegment segment = globalArena.allocate(100);



    - auto: An automatic arena has a bounded lifetime managed by the garbage collector and will be cleared when the arena and all its allocated segments become unreachable



    .. code-block:: python
            :linenos:

            Arena arena = Arena.ofAuto();
            MemorySegment segment = arena.allocate(100);



    - confined: A confined arena has a bounded lifetime and restricts access to the creating thread.


    .. code-block:: python
            :linenos:

            Arena arena = Arena.ofConfined();
            MemorySegment segment = arena.allocate(100);



    - shared: A shared arena has a bounded lifetime and allows access from multiple threads.


    .. code-block:: python
            :linenos:

            Arena arena = Arena.ofShared();
            MemorySegment segment = arena.allocate(100);


MemorySegment
-------------
- is a contiguous region of memory
- can be either heap or off-heap memory
- A memory segment backed by:
    - native memory is known as a native memory segment.


    .. code-block:: python
            :linenos:

            Arena arena = Arena.ofShared();
            MemorySegment segment = arena.allocate(100);


    - an existing heap-allocated Java array


    .. code-block:: python
            :linenos:

            MemorySegment memorySegment = MemorySegment.ofArray(new long[100]);


    - an existing Java ByteBuffer. This is known as a buffer memory segment:


    .. code-block:: python
            :linenos:

            MemorySegment memorySegment = MemorySegment.ofBuffer(ByteBuffer.allocateDirect(200));


    - an memory-mapped file. This is known as a mapped memory segment.


    .. code-block:: python
            :linenos:

            Arena arena = Arena.ofConfined();
            RandomAccessFile file = new RandomAccessFile("/tmp/memory.txt", "rw");
            FileChannel fc = file.getChannel();
            MemorySegment memorySegment = fc.map(READ_WRITE, 0, 200, arena);


- Heap segments are accessible from any thread without restrictions.
- On the other hand, access to native segments is limited based on the confinement characteristics of the arena from which they were obtained.
- Also, a memory segment has spatial and temporal boundaries in terms of memory access:
    - Spatial boundary — the memory segment has lower and upper limits
    - Temporal boundary — governs creating, using, and closing a memory segment

- Slicing A Memory Segment
    - We can slice a memory segment into multiple smaller blocks.
    - This avoids allocating multiple blocks if we want to store values with different layouts.


    .. code-block:: python
          :linenos:

            Arena arena = Arena.ofAuto();
            MemorySegment memorySegment = arena.allocate(12);

            MemorySegment segment1 = memorySegment.asSlice(0, 4);
            MemorySegment segment2 = memorySegment.asSlice(4, 4);
            MemorySegment segment3 = memorySegment.asSlice(8, 4);

            VarHandle intHandle = ValueLayout.JAVA_INT.varHandle();

            intHandle.set(segment1, 0, Integer.MIN_VALUE);
            intHandle.set(segment2, 0, 0);
            intHandle.set(segment3, 0, Integer.MAX_VALUE);

            assertEquals(intHandle.get(segment1, 0), Integer.MIN_VALUE);
            assertEquals(intHandle.get(segment2, 0), 0);
            assertEquals(intHandle.get(segment3, 0), Integer.MAX_VALUE);


MemoryLayout
------------
- is a programmatic description of a memory segment's contents.
- The MemoryLayout class lets us describe the contents of a memory segment.
- Specifically, it lets us define how the memory is broken up into elements, where the size of each element is provided
- This is a bit like describing the memory layout as a concrete type, but without providing a Java class. It’s similar to how languages like C++ map their structures to memory.


    .. code-block:: python
          :linenos:

            int numberOfPoints = 10;
            MemoryLayout pointLayout = MemoryLayout.structLayout(ValueLayout.JAVA_INT.withName("x"), 
                ValueLayout.JAVA_INT.withName("y"));
            SequenceLayout pointsLayout = MemoryLayout.sequenceLayout(numberOfPoints, pointLayout);


ValueLayout
-----------
- A ValueLayout models a memory layout for basic data types such as integer and floating types
- Each ValueLayout has a size and a byte order
- Some predefined ValueLayout types include ValueLayout.JAVA_INT, ValueLayout.CHAR, ValueLayout.DOUBLE and so on.


    .. code-block:: python
          :linenos:

            ValueLayout intLayout = ValueLayout.JAVA_INT;
            ValueLayout charLayout = ValueLayout.JAVA_CHAR;

            assertEquals(intLayout.byteSize(), 4);
            assertEquals(charLayout.byteSize(), 2);


SequenceLayout
--------------
- A SequenceLayout denotes the repetition of a given layout
- In other words, this can be thought of as a sequence of elements similar to an array with the defined element layout.


    .. code-block:: python
          :linenos:

            SequenceLayout sequenceLayout = MemoryLayout.sequenceLayout(10, ValueLayout.JAVA_INT);


GroupLayout
-----------
- A GroupLayout can combine multiple member layouts, which can be similar or a combination of different types.
- There are two possible ways to define a group layout:
    - struct: when the member layouts are organized one after another


    .. code-block:: python
          :linenos:

            GroupLayout groupLayout = MemoryLayout.structLayout(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT);


    - union: if the member layouts are laid out from the same starting offset,


    .. code-block:: python
          :linenos:

            GroupLayout groupLayout = MemoryLayout.unionLayout(ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG);


- you can have complex memroy layout:


    .. code-block:: python
          :linenos:

            MemoryLayout memoryLayout1 = ValueLayout.JAVA_INT;
            MemoryLayout memoryLayout2 = MemoryLayout.structLayout(ValueLayout.JAVA_LONG);
            MemoryLayout complexLayout = MemoryLayout.structLayout(memoryLayout1, MemoryLayout.paddingLayout(4), memoryLayout2);


VarHandle
---------
- is a versatile and immutable reference to variables that supports different access modes, allowing read/write operations on various types of variables, including static fields, non-static fields, array elements, and off-heap data structure components.
-  A VarHandle allows access to a memory segment
- VarHandle can be constructed using different subtypes of MemoryLayout


    .. code-block:: python
          :linenos:

            int value = 10;
            MemoryLayout pointLayout = MemoryLayout.structLayout(
                    ValueLayout.JAVA_INT.withName("x"),
                    ValueLayout.JAVA_INT.withName("y")
            );
            VarHandle xHandle = pointLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
            Arena arena = Arena.ofAuto();
            MemorySegment segment = arena.allocate(pointLayout);
            xHandle.set(segment, 0, (int) value);
            int xValue = (int) xHandle.get(segment, 0);

            assertEquals(xValue, value);


- it can be used also with offset, in conjunction with a MemorySegment to access specific elements, similar to using an index in an array:


    .. code-block:: python
          :linenos:

            int numberOfPoints = 10;
            MemoryLayout pointLayout = MemoryLayout.structLayout(ValueLayout.JAVA_INT.withName("x"),
                    ValueLayout.JAVA_INT.withName("y"));
            SequenceLayout pointsLayout = MemoryLayout.sequenceLayout(numberOfPoints, pointLayout);
            VarHandle xHandle = pointsLayout.varHandle(MemoryLayout.PathElement.sequenceElement(),
                    MemoryLayout.PathElement.groupElement("x"));
            Arena arena = Arena.ofAuto();
            MemorySegment segment = arena.allocate(pointsLayout);

            for (int i = 0; i < numberOfPoints; i++) {
                xHandle.set(segment, 0, i, i);
            }

            for (int i = 0; i < numberOfPoints; i++) {
                assertEquals(i, xHandle.get(segment, 0, i));
            }

Invoking Native Functions
-------------------------
- Foreign function and memory API provides a simple and streamlined approach for integrating native code and offering a safer and more efficient alternative for invoking foreign functions in Java applications.

- FunctionDescriptor:
    - The FunctionDescriptor class describes the signature of a foreign function, specifying the return type, parameter types, and relevant information.


    .. code-block:: python
          :linenos:

            FunctionDescriptor fd = FunctionDescriptor.of(JAVA_LONG, ADDRESS);


    - An address is a ValueLayout used to represent the location or reference to a specific region of memory.

- Linker and SymbolLookup:
    - The Linker class manages the loading and unloading of native libraries
    - the SymbolLookup class resolves function symbols within those libraries, enabling the establishment of a connection between Java code and foreign functions defined in native libraries.


    .. code-block:: python
          :linenos:

            Linker linker = Linker.nativeLinker();
            var symbol = linker.defaultLookup().find("strlen").orElseThrow();

- MethodHandle
    - The MethodHandle class serves as a bridge between Java and foreign functions, representing a reference to a function that can be invoked from Java code.
    - It allows dynamic binding and invocation of foreign functions with appropriate arguments and return values.
    - We can create a MethodHandle for the strlen function and use it to calculate the length of a string:



    .. code-block:: python
          :linenos:

            Linker linker = Linker.nativeLinker();
            var symbol = linker.defaultLookup().find("strlen").orElseThrow();

            MethodHandle strlen = linker.downcallHandle(symbol, FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS));

            Arena arena = Arena.ofAuto();
            MemorySegment str = arena.allocateFrom("Hello");
            long len = (long) strlen.invoke(str);

            assertEquals(5, len);



Java 15 Improvements
--------------------
- A rich VarHandle combinator API, to customize memory access var handles;
- Targeted support for parallel processing of a memory segment via the Spliterator interface;
- Enhanced support for mapped memory segments (e.g., MappedMemorySegment::force);
- Safe API points to support serial confinement (e.g., to transfer thread ownership between two threads); and
- Unsafe API points to manipulate and dereference addresses coming from, e.g., native calls, or to wrap such addresses into synthetic memory segments.

:ref:`Go Back <java-tools-memory-management-label>`.