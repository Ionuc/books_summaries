.. _python-general-label:

Python
======
    - is a strongly typed language, meaning any object in the language had a definit type and there's generally no way to circumvent that type
    - is dynamically-typed, meaning that there's no type checking of your code prior to running it
    - this is in contrast to statically-typed languages, like C++, or Java, where a compiler does a lot of type checking for you, rejecting programs which misuse objects
    - it uses duck typing where an object's suitability for a context is only determined at run time
    - is an interpreted language
    - python is normally compiled into a form of byte code before it is executed
    - this compilation happens invisibly
    - used white space (or identation level) to delimit code blocks
    - there are different implementation of python:
        - CPython:
            - written in C
            - runs on native
        - Jython
            - written in Java
            - runs on JVM
        - IronPython
            - written in C#
            - runs on Microsoft .NET
        - Pypy
            - written in a specialized subset of python named RPython
            - runs on native & others

    - python command-line environment is a Read-Eval-Print-Loop (or REPL):
        - will read whatever we type
        - will evaluated it
        - print the result
        - loop back to the beginning

    - statements (like while loop, for loop, if-statement ) are terminated by a colon : ":" ,indicating the body of the construct is to follow

Types and triks
---------------
    - division ("/") between integers will return a float value, if you want to return a int use "//"

Special attributes in Python
----------------------------
    - the Python runtime system defines some special variables and attributes, the names of which are delimited by double underscores
    - one such variable is __name__ which provides the means for our module to detect whether it has been run as a script or imported into another module or the REPL:
        - evaluates to "__main__" or the actual module name depending on how the enclosing module is being used

Python module, Python script and Python program
-----------------------------------------------
    - python code is placed in *.py files called "modules"
    - modules can be executed directly with

    .. code-block:: python
       :linenos:

       python module_name.py

    - modules can be brought into the REPL or other modules with

    .. code-block:: python
       :linenos:

       import module_name

    - any py file constitutes a Python module
    - modules can be written for:
        - convenient import
        - convenient execution
        - both : using the if __name__ equals __main__ idom
    - it is recomanded to make even simple scripts importable since it eases development and testing so much if you can access your code from REPL
    - whether you consider our module to be a Python script or a Python program is a matter of context and usage
    - module code is executed exactly once, on first import 

Command lines
-------------
    - command lines arguments can be accessed as a list of strings accessible through argv attribute of the sys modules : "sys.argv"
    - the 0th command line argument is the script filename, so the item at index 1 is the first true argument

Setting up a main()
-------------------
    - it doesn't have to be called the main() method
    - what is put after the "if __name__ == '__main__' :" can be moved to a new function : main() which now can be tested
    - system arguments are prefered to be passed as main parameters and retrieved when main() function is called

    .. code-block:: python
       :linenos:

       def main(url):
           words = fetch_words(url);
           print_items(words)

       if __name__ == '__main__' :
           main(sys.argv[1])
    - if __name__ == "__main__" the modules is being executed directly as a program, if not, then it means it was imported in another program and executed there (example REPL)

Documenting using Docstrings
----------------------------
    - Docstrings are literal strings, which occur as the first statement within a named block, such a function or module
    - add the documentation between the declaration and the first line of execution of any method
    - add the documentation as the first line for the entire module
    - are delimited by triple quotes
    - use """<documentation>"""
    - using help() method you can view the Docstrings for provided module or function

Comments
--------
    - starts with "#" until the end of line

The Shebang
-----------
    - is common on Unix-like systems to have the first line of a script include a special comment called a shebang
    - this begins with the usual "#!"
    - the shebang allos the program loader to identify which interpreter should be used to run the program
    - Shebangs have an additional purpose of conveniently documenting at the top of a file whether the Python code they're in is Python 2 or Python 3

    .. code-block:: python
       :linenos:

       #!/usr/bin/env python3

    - on Mac or Linux, we must mark our script as executable using the chmod command before the shebang will have any effect
    - the shebang will work also on Windows because of Windows Python distribution which uses a program called PyLauncher:
        - PyLauncher, the executable for which is simple py.exe, will pass the shebang and locate the appropriate version of Python on your system

Variables
---------
    - Python does not really have variables in the methaphorical sens of a box holding a value. It only has named references to objects and the references behave like labels
      which allows us to retrieve objects

Type System
-----------
    - Dynamic vs static typing
        - dynamic typing means that the type of an object reference isn't resolved until the programm is running and needn't specified up front when the program is written
        - static typing means the types are checked before run-time
    - Strong vs weakly type language
        - strong type system means the language will not in general implicitly convert objects between types 
        - in a weakly typed language, the type of a value depends on how it is used
    - Python can be characterized as having a dynamic and strong type system

Variable Scoping
----------------
    - scopes are cpntexts in which named references can be looked up
    - there are 4 main types of scope in Python arranged in a hierarchy:
        - local -> those named defined inside the current function
        - enclosing -> those named defined inside any and all enclosing functions
        - global -> top-level of module. Each module brings with it a new global scope
        - built-in -> those named built into the Python language through the special built-ins module

Conditionals
------------
    - Conditional statement

    .. code-block:: python
       :linenos:

       if condition:
           result = true_value
       else:
           result - false_value

    - Conditional expression

    .. code-block:: python
       :linenos:

       result = true_value if condition else false_value

:ref:`Go Back <python-label>`.
