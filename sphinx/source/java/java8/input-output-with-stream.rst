.. _streams:

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

Writing to a bytes stream
-------------------------
    - the base class for reading from a byte data is a class called OutputStream
    - provides methods:
        - void write(int b) -> write an individual byte
        - void write(byte[] buff) -> write an array of bytes

    .. code-block:: python
       :linenos:

       OuputStream output = // create output stream
       byte byteVal = 100;
       output.write(byteVal);

       byte[] bytebyff = {0, 63, 127};
       output.write(bytebyff);

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

    .. image:: ../../images/java/java8/input-output-strea-common-input-output-stream.png
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

    .. image:: ../../images/java/java8/input-output-strea-common-reader-writer.png
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
        - there is FilwReader, FileWriter, FileInputStream, FileOutputStream
    - Buffered streams:
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

		- deals with line breaks:
            - Unix : \n
            - Windows \r\n 

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

:ref:`Go Back <java8-label>`.