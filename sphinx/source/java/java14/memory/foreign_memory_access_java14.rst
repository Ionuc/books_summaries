.. _java14_foreign_memory:

Foreign Memory Access API
=========================

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


:ref:`Go Back <java14-memory-label>`.