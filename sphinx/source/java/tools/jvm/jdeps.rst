.. _java-tools-jvm-jdeps-label:

Jdeps
=====
- it was introduced with Java 8
- is a java dependency analysis tool
- parameters:
    - dotoutput <value> :
        - directory where the output of command will stored  
    - recursive:
        - traverses all runtime dependencies
    - class-path:
        - specifies directory with classpath, including external dependencies
    - the last program argument is jar to analyze


    .. code-block:: python
           :linenos:

            $ jdeps -dotoutput . -recursive --class-path "C:\Users\Andrey Pyatakha\Downloads" jarexport.jar
            $ jdeps --module-path modules --add-modules=ALL-MODULE-PATH -dotputput .



:ref:`Go Back <java-tools-jvm-label>`.