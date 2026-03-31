.. _java-development-streams-input-output-input-stream-label:

InputStream
===========

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


FileInputStream
---------------
- it read file by bites until the read() method will return -1, meaning it is the end of the file
- to print to console, it needs to convert the integer value to char
- issue:
    - you can read only first 256 characters from Unicode table
    - Cyrillic symbols won't be read
    - because it will read only characters with 1 byte and not with 2 bytes

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


BufferedInputStream
-------------------


    .. code-block:: python
       :linenos:

        private static void printFileWithFileInputStreamWithBuffer(String path) throws IOException, FileNotFoundException {
            try (var fis = new FileInputStream(path);
                var bis = new BufferedInputStream(fis);
                var dis = new DataInputStream(bis);
        //      DataInputStream dis2 = new DataInputStream(new BufferedInputStream(new FileInputStream(path)))  
                ) {
                    int i;    
                    while (dis.available() != 0) {
        //              int intValue = dis.readInt();
                        System.out.print(dis.readLine());
                        System.out.println();
                    }
                }
            }


ByteArrayInputStream
--------------------

ObjectInputStream
-----------------

FilterInputStream
-----------------


:ref:`Go Back <java-development-streams-input-output-stream-label>`.
