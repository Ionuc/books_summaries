.. _java-development-streams-input-output-label:

Input Output Streams
====================

Overview
--------
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



.. toctree::
    :maxdepth: 2
    :caption: Contents:

    old/index.rst
    new/index.rst
    input-stream/index.rst
    output-stream/index.rst
    reader/index.rst
    writer/index.rst
    file-system.rst
    open-options.rst
    serialization.rst

:ref:`Go Back <java-development-streams-label>`.
