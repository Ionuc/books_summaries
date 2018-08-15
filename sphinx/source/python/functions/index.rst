.. _python-functions-label:

Functions
=========
    - named functions are defined with the "def" keyword
    - def is a statement which binds executable code (function objects which contains a function definition) to a function name

    .. code-block:: python
       :linenos:

       def function_name(arg1, arn):

    - return from function using "return" keyword with optional parameter
    - omitted return parameter ir implicit return ar end returns None
    - functions are first class which is to say they are objects which can be passed around just like any other object
    - functions can be defined at module scope, at class scope ( called methods ), or at function scope (called local functions )

Callable instances
-----------------
    - one instance of the class can be called directly by adding __call__() method to the class
    - this method allows objects of our domain design to become callable just like functions

Lambda
------
    - is a expression which evaluates to a function
    - body of lambda can contain only a single expression
    - the return value is given by the body expression
    - no return statement is permitted
    - cannot have docstrings
    - example : lambda name : name.split()[1]


Local Functions
---------------
    - useful for specialized one-off functions
    - similar to lambdas, but more general:
        - may contain multiple expresions
        - may contain statements

        .. code-block:: python
         linenos:

         def funct():
           def local_func():
               a = 'hello, '
               b = 'world'
               return a + b
         return local_func()

    - you can return a local function
    - this ability to return functions is part of the broader notion of first-class functions where functions can be passed to and returned from other functions

Closures
--------
    - a closure essentially remembers the objects from the enclosing scope that the local function needs
    - it the keeps them alive so that when the local function is executed they can still be used
    - one way to think of this is that the local function closes over the objects it needs preventing them from being garbage collected
    - Python implements closures with a special attribute named __closure__

Function Factory
----------------
    - are functions that returns new specialized functions
    - it can creates local function which are having as arguments the parameters from the factory

        .. code-block:: python
         linenos:

         def raise_to(exp)
           def raise_to_exp(x):
               return pow(x, exp)
         return raise_to_exp()
         
         square = raise_to(2)
         square(5) => return 25

Global and nonlocal Keyword
--------------
    - when setting values to a variable, new bindings will be created.
    - these bindings will exists only in the current scope
    - if you want to override the module variables, you need to use the global keyword:
        - introduce names from the local namespace into global namespace
    - if you want to override the function variables, you need to use the nonlocal keyword:
        - introduce names from the enclosing namespace into the local namespace

        .. code-block:: python
         linenos:

         message = 'global'
         
         def enclosing()
           global message
           message = 'enclosing'
           
           def local():
               nonlocal message
               message = 'local'
           
           print('enclosing message: ', message)
           local()
           print('enclosing message: ', message)
         
         print('global message:', message)
         enclosing()
         print('global message:', message)

.. toctree::
    :maxdepth: 2
    :caption: Contents:

    arguments.rst
    decorators.rst

:ref:`Go Back <python-label>`.
    
