.. _controlling-app-execution:

Controlling App Execution
=========================
    - information can be passed into an application using:
        - command line

Command-line arguments
----------------------
    - is the easiest way to pass startup info
        - specify the targe of app processing ( Input/output files, URLs )
        - behavior options
    - are passed as String[]
    - are received by apps main function
    - each argument is a separate element in the array
        - elements are separate by OS's whitespace
        - Honor's OS's value quoting

    .. code-block:: python
       :linenos:

       package com.jwhh.cmdline;
       class Main{
           public static void main(String[] args){
             if (args.length <1 ) System.out.prinln("No arguments provided");
             else {
                  for(String word: args)
                     System.out.prin(word);
              }
           }
       }
       // java com.jwhh.cmdline.Main Hello There World => HelloThereWorld

Managing Persistable key/values pairs
-------------------------------------
    - the issue with the HashTable is that it only exists for the lifetime of our application
    - there are cases when data needs to be persisted after the application is closed like:
        - store app configuration inf
        - track simple aspects of app state
        - track user preferences

Properties class
----------------
    - a solution for anaging Persistable key/values pairs
    - inherits from HashTable
    - has a setProperty() & getProperty()
    - can be written to & read from a stream
    - support 2 formats:
        - Simple text
            - use store & load methods
            - supports OutputStrean/InputStream or Writer/Reader
            - normally name file with .properties suffix
            - it writes one key/value pair per line:
                - key/value normally separated by = or :
                - whitespace surrounding "=" or ":" is ignored
            - comments start with "#" or "!"

    .. code-block:: python
       :linenos:

       // read to a file
       Properties props = new Properties();
       props.setProperty("displayName", "Ionut Mesaros");
       try (Writer writer = Files.newBufferedWriter(Paths.get("xyz.properties"))){
           props.store(writer, "My comment");
       }
       // write from file
       Properties props2 = new Properties();
       try (Reader reader = Files.newBufferedReader(Paths.get("xyz.properties"))){
           props.load(writer);
       }

        - Xml
            - use storeToXml & loadFromXml
            - supports OutputStream/InputStream
            - normally name file with .xml suffix
            - one key/value pair per XML element:
                - key is stored as key attribute
                - value is stored as element value

    .. code-block:: python
       :linenos:

       // read to a file
       Properties props = new Properties();
       props.setProperty("displayName", "Ionut Mesaros");
       try (OutputStream out = Files.newOutputStream(Paths.get("props.xml"))){
           props.storeToXml(out, "My Comment);
       }
       // write from file
       Properties props2 = new Properties();
       try (InputStream in = Files.newInputStream(Paths.get("xyz.properties"))){
           props.loadToXml(in);
       }

:ref:`Go Back <java-label>`.