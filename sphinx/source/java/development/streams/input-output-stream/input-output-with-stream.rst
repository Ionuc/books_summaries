.. _java-development-streams-input-output-stream-label:

Input and Output with Streans and Files
=======================================

Streams overview
----------------
- represents an ordered sequence of data
- provides a common I/O model
- provides abstraction details of underlying source or destination ( memory, disk-based storage, networking)
- stream types are unidirectional:
    - when you create an instance of a stream, you are either going to use it to read from or to write to
- there are 2 categories:
    - byte stream: provides a binary representation of the data
    - text streams : provides a character-based representation of the data


    .. image:: ../../../../images/java/development/streams/input-output-streams/all-input-output-hierarchy.png
        :align: center


- byte stream vs text stream:
    - byte stream can work with data with size eight bits (which is one bite)
        - all reading and writing oeprations works with eight bits
        - these types were created since Java creation
        - after that, were created types can be procesed as 2 bytes
        - are widely used to reading and writing resources, like images
    - character streams are used to work with text data
        - in case you need to read or write text data, it is recommanded to character streams to ensure that you can read all characters from files 
- you can process Unicode characters and describe them in 16 bits, that is 2 bytes

Buffered Streams
----------------
- buffer is a region of phisical memory storage used to temporarily store data while it is being moved from one place to another
- it is flushed automatically in bellow cases:
    - when maximum size of the buffer is reached
    - when stream is closed
    - when the flush() methods is called
- in case you wrote something to the buffer and not reached the max size and forget to close the stream, the data will not be moved to the file itself


    .. code-block:: python
       :linenos:

        private static void noWriteWithoutFlush(String path, String textToWrite) throws IOException {
            var bos = new BufferedOutputStream(new FileOutputStream(path));
            bos.write(textToWrite.getBytes());
            //bos.flush();
        }


- improve efficiency with files because:
    - they buffer the content in memory
    - performes reads/writes in large chunks
    - reduces underlying stream interaction
- There are available for all 4 stream types : BufferedReader, BufferedWriter, BufferedInputStream, BufferedOutputStream
- using would be the same as with an InputStream


    .. code-block:: python
       :linenos:

       try(BufferedReader br = new BufferedReader(new FileReader("file1.txt"))){
           int intVal;
           while((intVal = br.read()) >= 0){
               char charVal = (char) intVal;
               // do something with charVal
           }
       }

Byte Streams
------------
- there are 2 main interfaces with multiple implementations:
    - InputStream
        - FileInputStream
        - ByteArrayInputStream
        - ObjectInputStream
        - FilterInputStream
    - OutputStream:
        - FileOutputStream
        - ByteArrayOutputStream
        - OjectOutputStream
        - FilterOutputStream


Reading with byte streams
-------------------------
    - the base class for reading from a binary data is a class called InputStream
    - provides methods:
        - int read()
            - return the value read , or -1 if no one found
            - return type is an int, which is 32-bit value, while bytes is a 8-bits value

    .. code-block:: python
       :linenos:

       InputeStream input = // create input stream
       int intVal;
       while((intVal = input.read()) >= 0) {
           byte b = (byte) intVal;
           ...
       }

        - int read(byte[] buff)
            - returns the number of values read
            - will read up to the number of values that will fir in the array, but if stream doesn't have enought, it will
              populate with the remaining values

    .. code-block:: python
       :linenos:

       InputeStream input = // create input stream
       int lenght;
       byte[] byteBuff = new byte[10];
       while((lenght = input.read(byteBuff)) >= 0) {
           for ( int i = 0; i < length; i++){
             byte byteValue = byteBuff[i]; // do something with it
           }
       }

FileOutputStream
----------------
- provides methods:
        - void write(int b) -> write an individual byte
        - void write(byte[] buff) -> write an array of bytes

Writing to a bytes stream
-------------------------
    - the base class for reading from a byte data is a class called OutputStream
    - provides methods:
        - void write(int b) -> write an individual byte
        - void write(byte[] buff) -> write an array of bytes

    .. code-block:: python
       :linenos:

        String textToWrite = "Some text example " + System.lineSeparator() + "with Line separator and cyrylic "
                + "characters: Тут кириллические символы" + System.lineSeparator();

        public static void writeFileToPathFileOutputStream(String path, String textToWrite)
            throws FileNotFoundException, IOException {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path);
                byte[] bytes = textToWrite.getBytes();
                fos.write(bytes);
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }

        public static void writeFileToPathFileOutputStreamWithBuffer(String path, String textToWrite)
                throws FileNotFoundException, IOException {
            try (var fbos = new BufferedOutputStream(new FileOutputStream(path))) {
                fbos.write(textToWrite.getBytes());
            }
        }

    - outside of the try-with-resource, blocked buffer will be flused automatically

Reading with data streams
-------------------------
    - the base class for reading from a text data is a class called Reader
    - workes with Unicode characters
    - provides methods:
        - int read()
            - return the value read , or -1 if no one found
            - return type is an int, which is 32-bit value, while char is a 16-bits value

    .. code-block:: python
       :linenos:

       Reader reader = // create reader
       int intVal;
       while((intVal = reader.read()) >= 0) {
           char c = (char) intVal;
           ...
       }

        - int read(char[] buff)
            - returns the number of values read
            - will read up to the number of values that will fir in the array, but if stream doesn't have enought, it will
              populate with the remaining values
    .. code-block:: python
       :linenos:

       Reader reader = // create input stream
       int lenght;
       char[] charBuff = new char[10];
       while((lenght = input.read(charBuff)) >= 0) {
           for ( int i = 0; i < length; i++){
             char charValue = charBuff[i]; // do something with it
           }
       }

Writing to a byte stream
------------------------
    - the base class for reading from a text data is a class called Write
    - provides methods:
        - void write(int b) -> write an individual char
        - void write(char[] buff) -> write an array of char
        - void write(String str) -> write astring

    .. code-block:: python
       :linenos:

       Write output = // create output stream
       char charVal = 'a';
       output.write(charVal);

       char[] charbyff = {'a', 'b', 'c'};
       output.write(charbyff);

       output.write("Hello World");

Common Input/OutputStream Derived Classes
-----------------------------------------
    - InputStream
        - ByteArrayInputStream
        - PipedInputStrem
        - FileInputStream
    - OutputStream
        - ByteArrayOutputStream
        - PipedOutputStream
        - FileOutputStream

    - ByteArrayInputStream and ByteArrayOutputStream works with bytes[]
    - PipedInputStream and PipedOutputStream works as produce / consumer, where one part of the program can use PipedOutputStream
      and write content into it and a differnt part of program can use the PipedInputStream and read the content back out

    - FileInputStream and FileOutputStream allows to create stream over files


    .. image:: ../../../../images/java/development/streams/input-output-streams/input-output-strea-common-input-output-stream.png
        :align: center


Common Reader/Write Derived Classes
-----------------------------------------
    - Reader
        - CharArrayReader
        - StringReader
        - PipedReader
        - InputStreamReader
        - FileReader - inherited from InputStreamReader
    - Writer
        - CharArrayWriter
        - StringWriter
        - PipedWriter
        - OutputStreamWriter
        - FileWriter - inherited from OutputStreamWriter

    - CharArrayReader and CharArrayWriter works with char[]
    - StringReader and StringWriter allows us to work with StringBuffer and put stream over top of them
    - PipedReader and PipedWriter works as produce / consumer, where one part of the program can use PipedReader
      and write content into it and a differnt part of program can use the PipedWriter and read the content back out

    - InputStreamReader and OutputStreamWriter allows to create reader over an InputStream or a writer over an OutputStream.
      So we can have an InputStream, which is a binary stream, and by putting InputStreamReader over top of it,
      we can consume it as a text stream

    - FileReader and FileWriter allows as to have file-based content with a reader and writer over top of those

    .. image:: ../../../../images/java/development/streams/input-output-streams/input-output-strea-common-reader-writer.png
        :align: center

Stream Cleanup
--------------
    - it refers to concept of closing down a steam when we're done with it
    - Stream implements Closable interface, which with java 7 it extends AutoClosable
    - try with resources:
        - cleanup automation using AutoClosable inteface
        - a "resource" is any type that implements AutoClosable
        - can declare multiple resources
    - for multiple exceptions occure ( like exception in try, exception in closing stream), Java keeps track of those as
      suppressed exceptions

File and Buffered Streams
-------------------------
    - Accessing Files
        - common use of streams is for file-based I/O
        - there is FileReader, FileWriter, FileInputStream, FileOutputStream


Accessing files with Java 8
---------------------------
    - Path:
        - used to locate a file system item
        - can be a file or directory
    - Paths:
        - Static Path factory methods
        - from string-based hierarchical path
        - from URI
    - Files
        - static methods for interacting with files (CRUD)
        - open files streams
            - newBufferedReader
            - newBufferedWriter
            - newInputStream
            - newOutputStream

    .. code-block:: python
       :linenos:

       void readData() throws IOExceptions{
            try(BufferedReader br = Files.newBufferedReader(Paths.get("data.txt"))){
               String inVal;
               while((inVal = br.readLine()) != null){
                   // do soemthing with inVal
               }
            }
       }

File Object
------------
- File class is used to represent a file
- is from package java.io which is alder version
- creating a new instance of File will not create a real file in file system
- the String given in the constructor is converted into abstract path name
- constants:
    - separator
        - the file separator used
    - pathSeparator:
        - the separator used when defining multiple jar in the system env PATH

- methods:
    - mkdir()
        - will create a new folder
        - will return true in case is created or false in case is already existing

    - mkdirs():
        - will create directory and the corresponding subdirectories by specifiend the named join with the folder separator. It is better to us File.separator to be platforme independend


    .. code-block:: python
       :linenos:

        file = new File("testDirectory3" + File.separator + "innerTestDirectory");   
        if (file.mkdirs()) {
            System.out.println("Success");
        } else {
            System.out.println("Files are exist");
        }


    - createNewFile():
        - creates a new file
    - exists()
        - check if the files exists or not
    - isDirectory():
        - checks if the file is directory file type
    - isFile():
        - return if the file is a file type
    - listFiles()
        - returns array of Files (File[]) from the directory
    - listFiles(FileFilter)
        - return array of file which are passing the FileFilter


    .. code-block:: python
       :linenos:

        File[] listFiles = file.listFiles(pathname -> pathname.getName().endsWith(".java"));


    - getAbsolutePath():
        - return the absolut path of the file instance (file or directory)
    - getName():
        - returns the name of the file
    - canExecute():
        - checks if the file is executable
    - isHidden():
        - returns if the file is hidden or not

- in order to create a File contect with new lines, it is better to use System.lineSeparator()
- in case the name from file is not absolute, it is relative to the project file (if running from ideea) or jar path


Path object

File Systems
------------
    - Files are contained within a file system
        - there is System Operation default file system
        - you can have specialized file system. Example : Zip file system
    - Path instances ar tied to a file system
    - Paths class works only for default file system
    - FileSystem:
        - Represents an individual file system
        - Factory for Path instances
        - it is identified using URI, or Universal Resource Identifiers
        - Zip file systems used "jar:file" scheme
            - Example : "jar:file:/jimwilson/data/bar.zip" -> bar.zip is a file system contained in directory data, under jimwilson
        - each file system support custom properties (String encoding)
    - FileSystems:
        - Static FileSystem factory methods
        - Open or create a file System : newFileSystem()

Java 11 Improvements
--------------------

 - new methods were added to Files: readString() and writeString()

    .. code-block:: python
           :linenos:

            Path filePath = Files.writeString(Files.createTempFile(tempDir, "demo", ".txt"), "Sample text");
            String fileContent = Files.readString(filePath);
            assertThat(fileContent).isEqualTo("Sample text");

Java 12 improvments
-------------------
- new methods were added to Files: mismatch()
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


:ref:`Go Back <java-development-streams-input-output-label>`.