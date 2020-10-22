.. _java9-try-with-resource:

Try with Resource Improvements
==============================

    - With java 7, try with resource was introduce to have a better exception handler
    - the problem is that we cannot use variables declared outside the Try-With-Resource
      within try block

    .. code-block:: python
        :linenos:

        void testARM_Before_Java9() throws IOException{
            BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"));
            try (BufferedReader reader2 = reader1) {
                System.out.println(reader2.readLine());
            }
        }

    - with Java 9, variables which are final or effectively final can be used inside try
      block of the Try-With-Resource

    .. code-block:: python
        :linenos:

        void testARM_Java9() throws IOException{
            BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"));
            try (reader1) {
                System.out.println(reader1.readLine());
            }
        }

:ref:`Go Back <java9-label>`.