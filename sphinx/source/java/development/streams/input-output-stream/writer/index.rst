.. _java-development-streams-input-output-writer-label:

OutputStream
===========
- the base class for writing characters to a source 


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
