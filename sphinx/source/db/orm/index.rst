.. _orm-db-label:

Object Relational Mapping (ORM)
===============================

- Object Relational Mapping (ORM) is a technique used in creating a "bridge" between object-oriented programs and, in most cases, a relational database
- When interacting with a database using OOP languages, you'll have to perform different operations like creating, reading, updating, and deleting (CRUD) data from a database
- By design, you use SQL for performing these operations in relational databases
- The ORM and ORM tools help simplify the interaction between relational databases and different OOP languages
- brings the gap between two fundamentally different paradigms:
    - Object-Oriented Paradigm:
        - works with objects, inheritance, polymorphism, encapsulation
    - Relational PAradigm:
        - works with tables, rows, columns and relationships
- without ORM, you will have to write code to translate between objects and database raws

ORM Tool
--------
- An ORM tool is software designed to help OOP developers interact with relational databases


    .. code-block::
           :linenos:

            // retrieve information about a user — name, email, country, and phone_number from a table called users for id = 20 
            "SELECT id, name, email, country, phone_number FROM users WHERE id = 20"

            // same query with ORM
            users.GetById(20)

How ORM works
-------------
- there are 3 steps:
    - Mapping definitions:
        - define how your classes map to database tables by using annotation or configuration files


        .. image:: ../../images/db/orm/mapping_definition.png
           :align: center



    - Query generation:
        - the ORM translates object operations like saving retrieving data into SQL queries that the DB can understand


        .. image:: ../../images/db/orm/query_generation.png
           :align: center


    - Result Transformation:
        - once the database returns the results, the ORM coverts those results back into objects that can use in your code


        .. image:: ../../images/db/orm/result_transformation.png
           :align: center


ORM Relationships
-----------------
- One-to-One
    - each record in table A relates to exatly one record in table B


    .. image:: ../../images/db/orm/result_transformation.png
       :align: center


- One-to-Many
    - each record in table A relates to multiple records in table B


    .. image:: ../../images/db/orm/result_transformation.png
       :align: center


- Many-to-Many
    - Records in table A relate to multiple records in table B and vice versa


    .. image:: ../../images/db/orm/result_transformation.png
       :align: center


Performance Considerations
--------------------------
- Performance:
    - N_+ 1 Query Problem:
        - loading a collection of objects can result in one wuery for the parent and N additional queries for each child:
            - use eager loading with join fetches
            - implement batch fetching strategies
    - lazy loading:
        - defers loading of related objects until they'are explicity accessed
            - help avoid loading unnecessary data
            - be caution of session bounderies
    - Caching:
        - ORM provides multiple levels of caching to improve performance:
            - First-level (session) cache
            - SEcond-level (application) cache
    - Query Optimization
        - ORMs may generte suboptimal SQL for complex operations
            - use native queries for complex operations
            - monitor and analyze generted SQL

ORM Best Practices
------------------
- Database DEsign First
    - design your database schema before implementing ORM entities
        - ensure proper data modeling
        - prevents performance issues later
- Monitor Generated SQL:
    - regularly check the SQL queries your ORM genertes
        - use logging frameworks to campture queries
        - optimize inefficient queries
- Use Transactions
    - wrap related operations in transactions to maintain data integrity
        - ensure atomicity of operations
        - prevents partial updates
- Batch Operations
    - use batch processing for bulk operations
        - reduces database round trips
        - significantly improves performance

Advantages
----------
- abstraction: ORM hides the complexity of SQL allowing developers to work with familiar object-oriented cod
- security:
    - ORM helps prevents SQL injection attacks through parameterized queries
    - do inputs sanitizations
- productivity:
    - reduce boilerplate code
    - allows faster developerment with build-in create, read ,update and delete oeprations
- database agnostic
    - you can switch between different database systems with minima code changes

Disadvantages
-------------
- Learning how to use ORM tools can be time consuming.
- They are likely not going to perform better when very complex queries are involved.
- Performance:

.. toctree::
    :maxdepth: 2
    :caption: Contents:

    hibernate.rst

:ref:`Go Back <db-label>`.