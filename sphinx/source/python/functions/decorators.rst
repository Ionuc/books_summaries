.. _python-functions-decorator-label:

Decorators
=========
    - are a way to modify or enhange existing functions in a nonintrusive and maintainable way
    - decorators allow you to replace, enhance or modify existing function without changing those function definition
    - calling code does not need to change
    - decorator mechanims uses the modified function's original name
    - in Python, a decorator is a callable object that takes in a callable and returns a callable
    - to apply decorators to function use the "@"
    - what Python does when it sees a decorator:
        - first compiles the base function => this produce a new function object
        - Python then passes this function object to the function my_decorator
        - Python takes the return value from the decorator and bind it to the name of the original function

        .. code-block:: python
         linenos:

         def escape_unicode(f):
           def wrap(*args, **kwargs):
               x = f(*args, **kwargs
               return ascii(x)
           
           return wrap
         
         @escape_unicode
         def vegetable():
           return "any string with no ascii code"

    - in the example above it is a function as a decorator. But other objects can be decorators as well:
        - classes:
            
            .. code-block:: python
             linenos:

             class MyDec:
               def __init__(self, f):
                   ...
               
               def __call__(self):
                   ...
             
             @MyDec
             def funct():
               ...
        - class instance:
            - are useful for creating collections of decorated functions which you cna dynamically control in some way
    
            .. code-block:: python
             linenos:

             class AnotherDec:
               
               def __call__(self, f):
                   def wrap():
                       ...
                   return wrap()
             
             @AnotherDec()
             def funct():
               ...

    - you can have multiple decorators:
        - they are processed in reversed order
        - in the example below:
            - some_function is passed to decorator3, the result is passed to decorator2, and the result is passed to decorator1

        .. code-block:: python
         linenos:

         @decorator1
         @decorator2
         @decorator3
         def some_function(f):
           ....

functools.wrap()
----------------
    - naive decorators can lose important metadata like __name__, __doc__
    - naive decorators can return wrong information for other methods help()
    - in order to keeps the previous metadata, you should use functools.wraps()

:ref:`Go Back <python-functions-label>`.
