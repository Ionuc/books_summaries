.. _java-development-input-output-stream-file-system-label:

File Systems
============
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

Java 13 improvements
--------------------
- FileSystems.newFileSystems()
    - allows cretion of a new file system based on the specified PAth and configuration properties
    - returns a FileSystem object representing the new file system
    - throws IOException in case I/O error occurs during the cretion of the file system


    .. code-block:: python
       :linenos:

       Path zipFilePAth = Paths.get("/path/to/archive.zip");
       Map<String, String env = new HashMap{}();
       env.put("create", "true");
       try (FileSystem zipFileSystem = FileSystems.newFileSysmte(zipFilePAth, env)) {
            // perform operation on the zip file 
       } catch(IOException e) {
            // handle IOException
       }


:ref:`Go Back <java-development-input-output-stream-label>`