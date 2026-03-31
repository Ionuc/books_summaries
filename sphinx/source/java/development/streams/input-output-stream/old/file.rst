.. _java-development-streams-input-output--old-file-label:

File class
==========

- it is inside package java.io (input output), which is older version
- java.io is build on File abstraction

- creating a new instance of File will not create a real file in file system
- the String given in the constructor is converted into abstract path name
- constants:
    - separator
        - the file separator used
    - pathSeparator:
        - the separator used when defining multiple jar in the system env PATH

- methods:
    - mkdir()
        - will create a new folder
        - will return true in case is created or false in case is already existing

    - mkdirs():
        - will create directory and the corresponding subdirectories by specifiend the named join with the folder separator. It is better to us File.separator to be platforme independend


    .. code-block:: python
       :linenos:

        file = new File("testDirectory3" + File.separator + "innerTestDirectory");   
        if (file.mkdirs()) {
            System.out.println("Success");
        } else {
            System.out.println("Files are exist");
        }


    - createNewFile():
        - creates a new file
    - exists()
        - check if the files exists or not
    - isDirectory():
        - checks if the file is directory file type
    - isFile():
        - return if the file is a file type
    - listFiles()
        - returns array of Files (File[]) from the directory
    - listFiles(FileFilter)
        - return array of file which are passing the FileFilter


    .. code-block:: python
       :linenos:

        File[] listFiles = file.listFiles(pathname -> pathname.getName().endsWith(".java"));


    - getAbsolutePath():
        - return the absolut path of the file instance (file or directory)
    - getName():
        - returns the name of the file
    - canExecute():
        - checks if the file is executable
    - isHidden():
        - returns if the file is hidden or not

- in order to create a File contect with new lines, it is better to use System.lineSeparator()
- in case the name from file is not absolute, it is relative to the project file (if running from ideea) or jar path


:ref:`Go Back <java-development-streams-input-output-old-label>`.