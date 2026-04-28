.. _java-development-streams-input-output-reader-label:

Reader
======

- the base class for reading from a character data is a class called Reader
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


FileReader
---------------
-

    .. code-block:: python
       :linenos:

        String textToWrite = "Some text example " + System.lineSeparator() + "with Line separator and cyrylic "
                + "characters: Тут кириллические символы" + System.lineSeparator();

        private static void printFileWithFileInputStream(String path) throws IOException, FileNotFoundException {
            try (var fis = new FileInputStream(path)) {
                int i;
                while ((i = fis.read()) != -1) {
                    System.out.print((char) i);
                }
            }

        }

        // output:
        // Some text example
        // with Line separator and cyrylic characters: ??? ????????? ?????
        //


BufferedReader
-------------------
- you can read data not char by char, but line by line

    .. code-block:: python
       :linenos:

        private static void printFileWithBuffer(String path) throws FileNotFoundException, IOException {
            try (var br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }


InputStreamReader
-----------------

CharArrayReader
---------------



:ref:`Go Back <java-development-streams-input-output-stream-label>`.
