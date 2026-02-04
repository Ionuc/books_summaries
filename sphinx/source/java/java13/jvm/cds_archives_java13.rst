.. _java13_cds_archives:

Dynamic CDS Archives
====================

    - it allows class metadata to be shared across different JVMs to reduce startup time and memory footprint
    - JDK 10 extended this ability by adding application CDS (AppCDS) – to give developers the power to include application classes in the shared archive
    - JDK 12 further enhanced this feature to include CDS archives by default
    - However, the process of archiving application classes was tedious. To generate archive files. developers had to
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



:ref:`Go Back <java13-jvm-label>`.