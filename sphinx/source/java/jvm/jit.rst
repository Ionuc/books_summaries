.. _jit:

JIT
===
    - just-in-time compiler
    - A JIT compiler partially or fully converts a program into native machine code so that it doesn’t need to be interpreted by the JVM and thus runs much faster
    - When a class must be loaded (typically, the first time you want to create an object of that class),
      the .class file is located, and the bytecodes for that class are brought into memory
    - At this point, one approach is to simply JIT compile all the code:
        - drawbacks:
           - It takes a little more time,
           - it increases the size of the executable

:ref:`Go Back <java-jvm-label>`.