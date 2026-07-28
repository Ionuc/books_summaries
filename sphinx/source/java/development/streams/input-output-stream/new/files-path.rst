.. _java-development-streams-input-output-new-files-path-label:

Files Path class
================
- it is inside package java.nio (new input output), which is newest version
- java.io is build on Path abstraction

Paths class
-----------
- is used to create instances of Path interface


    .. code-block:: python
       :linenos:

        Path path = Paths.get("testDirectoryNio", "innerTestDirectoryNio");



Files class
-----------
- provides corresponding methods from old File class
- methods:
    - isDirectory(path)
        - returns if the path is a directory
    - isRegularFile(path)
        - returns if the Path is a normal file
    - createDirectories
        - creates the directories and subdirectories from the path name
    - createFile
        - creates the corresponding file
    - exists(path):
        - return if the file exists on the file system
    - delete(path)
        - it removes the files from the file system

Reading from file
-----------------
- methods to read characters:
    - lines():
        - return a Stream<String> where each entry is a line
    - readAllLines():
        - returns a List<String> where each entry is a line
        - all file content is stored in memory


    .. code-block:: python
       :linenos:

        public static void printFileToConsole(String path) throws IOException {
            try (Stream<String> fStream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
                fStream.forEach(System.out::println);
            }

            List<String> readAllLines = Files.readAllLines(Paths.get(path));
        }

        private static void printFileToConsoleWithCustomEncoding(String path) throws IOException {
            try (Stream<String> stream = Files.lines(Paths.get(path), Charset.forName("windows-1251"))) {
                SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
                stream.forEach(System.out::println);
            }
        }


Writing to a file
-----------------
- write() method can be used to write byte content to a source
- OpenOptions can be passed:
    - 


    .. code-block:: python
       :linenos:

        private static void writeNio(String path, String textToWrite) throws IOException {
            Files.write(Paths.get(path), textToWrite.getBytes(), CREATE, APPEND);
            List<String> lines = Arrays.asList("a", "s", "d");
            Files.write(Paths.get(path), lines, StandardCharsets.UTF_8);
        }


Searching for file
------------------
- methods:
    - find() method can be used to iterate file from a directory, including subdirectory (based on the maxDepth)


    .. code-block:: python
       :linenos:

        private static void findMethodDemo(String path, int maxDepth) throws IOException {
            Path start = Paths.get(path);
            try (Stream<Path> stream = Files
                    .find(start, maxDepth, (specificPath, attr) -> String.valueOf(specificPath)
                    .endsWith(".java"))) {
                String joined = stream
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining(";"));
                if (joined != null && !joined.isEmpty()) {
                    System.out.println("Found: " + joined);
                }
            }
        }


    - walk:
        - read all files from a path


    .. code-block:: python
       :linenos:


        private static void walkMethodDemo(String path) throws IOException {
            Files.walk(Paths.get(path))
                .filter(p -> p.toString().endsWith(".ext"))
                .map(p -> p.getParent().getParent())
                .distinct()
                .forEach(System.out::println);
        }



Java 11 Improvements
--------------------

 - new methods were added to Files: 
    - readString()
        - uses UTF 8 by default for decoding from bytes to characters
        - an overloaded method was added to specify also the Charset used for decoding
        - handles the opening and closing of the unerdlying file stream internally => no need to explicitly close the resources


    .. code-block:: python
       :linenos:

        Path filePath = Path.of("test_text.txt");
        try {
            String content = Files.readString(filePath);
            System.out.println("File Content:\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }


    - writeString()
        - writes a sequence of characters to a file
        - uses UTF 8 by default for encoding the characters into bytes
        - an overloaded method was added to specify also:
            - the Charset used for encoding
            - OpenOptions => provide more flexibility when writing to a file
        - handles the opening and closing of the unerdlying file stream internally => no need to explicitly close the resources


    .. code-block:: python
           :linenos:

            Files.writeString(Paths.get("Java_11_test.txt"), "Java 11 - Demo Lesson", 
                StandardOpenOption.CREATE, StandardOpenOption.DSYNC, StandardOpenOption.APPEND);


Java 12 improvments
-------------------
- new methods were added to Files: mismatch()
    - the method is used to compare two files and find the position of the first mismatched byte in their contents.
    - the return value will be in the inclusive range of 0L up to the byte size of the smaller file or -1L if the files are identical.
        - first it is checking if it is the path.
        - then it reads files using Files.newInputStream() method, which uses a buffer to read chunks of bytes from both files


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

:ref:`Go Back <java-development-streams-input-output-old-label>`.