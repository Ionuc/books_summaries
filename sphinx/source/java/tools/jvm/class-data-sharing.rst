.. _java-tools-jvm-class-data-sharing-label:

Class Data Sharing (CDS)
========================
- allows a set of classes to be pre-processed into a shared archive file that can then be memory-mapped at runtime to reduce startup time which can also reduce dynamic memory footprint when multiple JVMs share the same archive file.

- CDS only allows the bootstrap classloader, limiting this feature to system classes only.


Java 10 improvements
--------------------
- Application CDS extends CDS and allows the build-in system class loader and custom class loaders to load archive classes. This makes it possible to use the feature for application classes
- AppCDS was a commercial feature in Oracle JDK for JDK 8 and JDK 9. Now it is open sourced and made publicly available.
- How to use
    1. Get the list of classes to archive:
        - The following command will dump the classes loaded by the HelloWorld application into hello.lst:


    .. code-block:: python
           :linenos:

            $ java -Xshare:off -XX:+UseAppCDS -XX:DumpLoadedClassList=hello.lst -cp hello.jar HelloWorld


    2. Create the AppCDS archive
        - Following command creates hello.js a using the hello.lst as input:


    .. code-block:: python
           :linenos:

            $ java -Xshare:dump -XX:+UseAppCDS -XX:SharedClassListFile=hello.lst -XX:SharedArchiveFile=hello.jsa -cp hello.jar


    3. Use the AppCDS archive
        - Following command starts the HelloWorld application with hello.jsa as input:


    .. code-block:: python
           :linenos:

            $ java -Xshare:on -XX:+UseAppCDS -XX:SharedArchiveFile=hello.jsa -cp hello.jar HelloWorld


Java 12 improvement
-------------------
- The change that came with Java 12 is that the CDS archive is enabled by default. To run programs with CDS turned off we need to set the Xshare flag to off:


    .. code-block:: python
           :linenos:

            java -Xshare:off HelloWorld.java


Java 13 improvements
--------------------
- the process of archiving application classes was tedious. To generate archive files. developers had to
        - to do trial runs of their applications to create a class list first
        - to dump it into an archive
        - to archive could be used to share metadata between JVMs

    - With dynamic archiving, JDK 13 has simplified this process. Now we can generate a shared archive at the time the application is exiting
    - to enable applications to create a dynamic shared archive on top of the default system archive, we need to add an option -XX:ArchiveClassesAtExit and specify the archive name as argument: 


    .. code-block:: python
           :linenos:

            java -XX:ArchiveClassesAtExit=<archive filename> -cp <app jar> AppName


    - We can then use the newly created archive to run the same app with -XX:SharedArchiveFile option:



    .. code-block:: python
           :linenos:

            java -XX:SharedArchiveFile=<archive filename> -cp <app jar> AppName


:ref:`Go Back <java-tools-jvm-label>`.