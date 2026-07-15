.. __principals-dry-label:

DRY Principal
==============
- stands for Keep it Don't Repeat Yearself
- aims to reduce code repetition
- involves abstracting common functionality into reusable functions, modules or classes

Benefits
--------
- improved code maintainability:
    - changes needs to be made in only one place
    - easier and less error prone code updates
- reduces risk of bugs:
    - avoid inconsistencies and bugs caused by redundant code
    - bugs fixed in one place are resolved everywhere
- easier refactoring
- enhanced readability and clarity
- better code reusability

How to apply
------------
- identify redundant code:
    - code review
    - static analysis tools
- Abstract Repeated logic
    - encapsulate repeated code in functions and methods
- Modularize Code
    - organiza code into modules or packages
- Use Design patterns
- leverave inheritance and polymorphism:
- utilize template methods:
    - define the skeleton of an algorithm in a base class and allw subclasses to override specific steps

Contrasting Concepts
--------------------
- WET:
    - stands for Write Everything Twice
    - represents the opposite of DRY
    suggest writing duplicate or similar code

- AHA:
    - stands for Avoid Hasty Abstractions
    - advises against prematurely abstracting code or creating overly complex abstractions
    - it advocate for simplicity

:ref:`Go Back <principals-label>`.