.. _java12_files:

Files
======

    - new methods were added to Files: rmismatch()
    - the method is used to compare two files and find the position of the first mismatched byte in their contents.
    - the return value will be in the inclusive range of 0L up to the byte size of the smaller file or -1L if the files are identical.


    .. code-block:: python
           :linenos:

            // Example 1
            @Test
            public void givenIdenticalFiles_thenShouldNotFindMismatch() {
                Path filePath1 = Files.createTempFile("file1", ".txt");
                Path filePath2 = Files.createTempFile("file2", ".txt");
                Files.writeString(filePath1, "Java 12 Article");
                Files.writeString(filePath2, "Java 12 Article");

                long mismatch = Files.mismatch(filePath1, filePath2);
                assertEquals(-1, mismatch);
            }


    .. code-block:: python
           :linenos:

            // Example 2
            @Test
            public void givenDifferentFiles_thenShouldFindMismatch() {
                Path filePath3 = Files.createTempFile("file3", ".txt");
                Path filePath4 = Files.createTempFile("file4", ".txt");
                Files.writeString(filePath3, "Java 12 Article");
                Files.writeString(filePath4, "Java 12 Tutorial");

                long mismatch = Files.mismatch(filePath3, filePath4);
                assertEquals(8, mismatch);
            }



:ref:`Go Back <java12-developer-features-label>`.