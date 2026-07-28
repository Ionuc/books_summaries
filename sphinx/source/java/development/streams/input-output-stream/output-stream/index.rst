.. _java-development-streams-input-output-output-stream-label:

OutputStream
===========
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


FileOutputStream
----------------
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


BufferedOutputStream
--------------------


    .. code-block:: python
       :linenos:

        public static void writeFileToPathFileOutputStreamWithBuffer(String path, String textToWrite)
                throws FileNotFoundException, IOException {
            try (var fbos = new BufferedOutputStream(new FileOutputStream(path))) {
                fbos.write(textToWrite.getBytes());
            }
        }


ByteArrayOutputStream
--------------------

ObjectOutputStream
------------------

FilterOutputStream
------------------


:ref:`Go Back <java-development-streams-input-output-stream-label>`.
