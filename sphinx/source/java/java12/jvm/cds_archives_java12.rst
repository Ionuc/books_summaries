.. _java12_cds_archives:

Default CDS Archives
====================

    - the Class Data Sharing (CDS) feature helps reduce the startup time and memory footprint between multiple Java Virtual Machines
    - tt uses a built-time generated default class list that contains the selected core library classes.
    - The change that came with Java 12 is that the CDS archive is enabled by default
    - To run programs with CDS turned off we need to set the Xshare flag to off:


    .. code-block:: python
           :linenos:

            java -Xshare:off HelloWorld.java



:ref:`Go Back <java12-jvm-label>`.