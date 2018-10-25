.. _javascript-engine:

Javascript Engines
==================
    - a javascript engine is often termed a type of virtual machine
    - a virtual machine refers to the software-driven emulation of a given computer system
    - there are multiple virtual machines, and they are classified by how precisely they are able to emulate an actual physical machines:
        - system virtual machine:
            - provides a complete emulation of a platform on which an operating system can be executed
        - process virtual machine:
            - can run one program or process
    - the basic job of a JavaScript engine is to take the JavaScript code that a developer writes and convert it to fast,
      that can be interpreted by a browser or even embedder into an application

    - each JavaScript engine implements a version of ECMAScript, of which JavaSCript is a dialect
    - not all engines are simple ones, for example JavaSCriptCore has 6 building blocks that analyze, interpret, optimize and garbage collect JavaScript code

.. toctree::
    :maxdepth: 2
    :caption: Contents:

    javaScriptCore.rst
    v8.rst


:ref:`Go Back <javascript-label>`.