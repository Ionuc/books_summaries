.. _java-development-streams-input-output--new-path-label:

Path class
==========

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


:ref:`Go Back <java-development-streams-input-output-old-label>`.