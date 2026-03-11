.. _java-development-error-handling-try-with-resource-label:

Try with Resource Improvements
==============================

    - With java 7, try with resource was introduce to have a better exception handler
    - is replacing try-catch-finally with try-with-resource
    - A try-with-resources block can still have the catch and finally blocks, which will work in the same way as with a traditional try block.

    .. code-block:: python
        :linenos:

        // before java 7
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("test.txt"));
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        // with java 7
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }


Multiple resources
------------------
    - you can have multiple resources, the resources will be closed in LIFO mode: last resource defined will be the first closed


    .. code-block:: python
        :linenos:

        try (Scanner scanner = new Scanner(new File("testRead.txt"));
            PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
            while (scanner.hasNext()) {
            writer.print(scanner.nextLine());
            }
        }


Custom Resource with AutoCloseable
----------------------------------
    - To construct a custom resource that will be correctly handled by a try-with-resources block, the class should implement the Closeable or AutoCloseable interfaces and override the close method


    .. code-block:: python
        :linenos:

        public class MyResource implements AutoCloseable {
            @Override
            public void close() throws Exception {
                System.out.println("Closed MyResource");
            }
        }


Java 9 improvement
------------------
    - Before Java 9, we could only use fresh variables inside a try-with-resources block:


    .. code-block:: python
        :linenos:

        try (Scanner scanner = new Scanner(new File("testRead.txt")); 
            PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) { 
            // omitted
        }


    - with Java 9, variables which are final or effectively final can be used inside try
      block of the Try-With-Resource


    .. code-block:: python
        :linenos:

        final Scanner scanner = new Scanner(new File("testRead.txt"));
        PrintWriter writer = new PrintWriter(new File("testWrite.txt"))
        try (scanner;writer) { 
            // omitted
        }


    - Put simply, a variable is effectively final if it doesn’t change after the first assignment, even though it’s not explicitly marked as final.

:ref:`Go Back <java-development-error-handling-label>`.