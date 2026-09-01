.. _java-development-streams-input-output-writer-label:

OutputStream
===========
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


FileWriter
----------


    .. code-block:: python
       :linenos:

        private static void writeFileToPathFileWriter(String path, String textToWrite) throws IOException {
            try (var fw = new FileWriter(path)) {
                fw.write(textToWrite);
            }
        }


BufferedWriter
--------------


    .. code-block:: python
       :linenos:

        private static void writeFileToPathFileWriterBuffered(String path, String textToWrite) throws IOException {
            try (var bfw = new BufferedWriter(new FileWriter(path))) {
                bfw.write(textToWrite);
            }
        }


OutputStreamWriter
------------------

ObjectOutputWriter
------------------

CharArrayWriter
---------------


:ref:`Go Back <java-development-streams-input-output-stream-label>`.
