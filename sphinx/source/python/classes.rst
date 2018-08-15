.. _python-class-label:

Class
======
    - calling a class incokes the constructor
    - method:
        a function defined within a class
    - instance methods:
        - functions which can be called on objects
    - self:
        - the first argument to all instance methods
    - __init__():
        - instance method for initializing new objects

        .. code-block:: python
       :linenos:

       class Flight:
          def __init__(self, number):
             self._number = number

          def number(self):
             return self._number

       f = Flight('test')
       f.number() # will print test
       Flight.number(f) # will print test

    - there is no private, protected, public access modifiers, everything is public
    - class invariants should be implemented in the __init__() method
	- Python used late binding

Attributes
----------
    - instance attributes:
        - are defined methods level ( like __init__)
        - self.attr = something always creates an instance attribute, never a class attribute
        - are updated only per instance
    - class attributes (or static attributes):
        - are defined at class level
        - are global to all instances of the same class

Static methods
--------------
    - Python provides a decorator : @staticmethod
    - used when no access needed to either class or instance objects
    - can be overridden in subclasses
    - in case a base class method is using another static method by referencing the class, if you want to use the overridden method from inherited class, you should call the method
      from the self.method()

        .. code-block:: python
       :linenos:

       class ShippingContainer:
          next_serial = 1337
          
          @staticmethod
          def _get_next_serial():
             result = ShippingContainer.next_serial
             ShippingContainer.next_serial += 1
             return result

Class methods
-------------
    - it is done using decorator : @classmethod
    - used when it requires access to the class object to call other class methods or the constructor
    - are inherited by the inherited class
    - to get the reference to the base class, it is used method : super()

        .. code-block:: python
       :linenos:

       class ShippingContainer:
          next_serial = 1337
          
          @classmethod
          def _get_next_serial(cls):
             result = cls.next_serial
             cls.next_serial += 1
             return result

Encapsulation
-------------
    - you can use encapsulation:
        - by providing the getter & setter and prefix the attribute name with "_"
        - using @property & @<property>.setter decorator:
            - which allows getters and setter to be exposed to seemingly regular attributes performing a graceful upgrade in capabilities
            
:ref:`Go Back <python-label>`.
