.. _java-development-streams-input-output--new-path-label:

Path class
==========

- it is inside package java.nio (new input output), which is newest version
- java.io is build on Path abstraction

Paths class
-----------
- is used to create instances of Path interface


    .. code-block:: python
       :linenos:

        Path path = Paths.get("testDirectoryNio", "innerTestDirectoryNio");



Files class
-----------
- provides corresponding methods from old File class
- methods:
    - isDirectory(path)
        - returns if the path is a directory
    - isRegularFile(path)
        - returns if the Path is a normal file
    - createDirectories
        - creates the directories and subdirectories from the path name
    - createFile
        - creates the corresponding file
    - exists(path):
        - return if the file exists on the file system
    - delete(path)
        - it removes the files from the file system

:ref:`Go Back <java-development-streams-input-output-old-label>`.