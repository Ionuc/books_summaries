.. _python-file-resources-label:

File and Resources Management
=============================
    - to open a file in Python, it can be used the build-in open() function:
        - file : path to file (required)
        - mode : read/write/append, binary/text
        - encoding: text encoding (default specified by Python)
    - at the file system level, fiels contain only a series of bytes
    - Python distinguishes between files opened in binary and text modes even when teh underlying operating system does not
    - files opened in binary mode return and manipulate their contents as bytes objects without any decoding
    - files opened in text mode will need the write() to encode, read() to decode

Writing
-------
    - you can use write() function
    - you should close the file
    - when writting to files it is our reponsibility to provide the newline characters for line breaks

    .. code-block:: python
       :linenos:

       f = open('wasteland.txt', mode='wt', encoding='utf-8')
       f.write('Some text\n')
       f.write('Another text')
       f.close()

Reading
-------
    - use read() method to read all content, or the specified number of bytes
    - use readline() to read an entire line
    - use readlines() to read all lines returned in a list of strings

    .. code-block:: python
       :linenos:

       f = open('wasteland.txt', mode='rt', encoding='utf-8')
       f.read(32)
       f.readline()
       f.readlines()

Appending to text file
----------------------
    - you can use writelines() to append a list of strings to the file. The new line character must be added to

    .. code-block:: python
       :linenos:

       f = open('wasteland.txt', mode='at', encoding='utf-8')
       f.read(32)
       f.readLine()
       f.readLines()

Files as Iterators
------------------
    - the result of open() method can be used with for each loop

    .. code-block:: python
       :linenos:

       f = open('wasteland.txt', mode='at', encoding='utf-8')
       for line in f:
           sys.stdout.write(line)
       f.close()

Try with resources
------------------
    - the close is important because it informs the underlying operating system that you're done working with a file
    - if you don't close a file when you're done with it, it is possible to lose data:
        - there may be pending rights buffered up, which might not get written completely
    - if you are open a lots of file, your system may run out of resources
    - the equivalent in Python is with block statement
    - with block can be used with any object which supports the context-manager protocol, that includes the file objects returned by open()

    .. code-block:: python
       :linenos:

       try:
           f = open('wasteland.txt', mode='at', encoding='utf-8')
               for line in f:
               sys.stdout.write(line)
       finally:
           f.close()

    - is equiavalent with:

    .. code-block:: python
       :linenos:

       with open('wasteland.txt', mode='at', encoding='utf-8') as f
           for line in f:
               sys.stdout.write(line)

    - if you want to have the with block behavior with classes which are not necessary files, you can use closing() method from contextlib

:ref:`Go Back <python-label>`.
