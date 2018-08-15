.. _command-query-separation-label:

Command Query Separation
=========================

Overview
--------
    - your operations should be either commands or queries but not both
    - the abbreviation is CQS and is not related to CQES ( which is architecture design pattern )
    - it is possible to write operation that both has side effects and returns data

Commands
--------
    - a command is any operation that has an observable side effect in the system (storing something in memory, deleting a row from DB, ..)
    - they mutate state, they change the observable state of the system

    .. code-block:: python
       :linenos:

       void Save(Order order);
       void Send(T message);
       void Associate(IFoo foo, Bar bar);

    - all methods are returning void
    - if a method returns void, then it must be a command
    - this means a method that returns void must have a side effect
    - can invoke queries

Query
-----
    - is an operation that returns data
    - is not necessary something that id send to DB and translated into a SQL query
    - it can be an operation that return some data that's already in memory
    - do not mutate observable state

    .. code-block:: python
       :linenos:

       Order[] GetORders(int userId);
       IFoo Map(Bar bar);
       T Create();

    - all these methods return something
    - if a method returns something, then it must be a query
    - queries are idempotent (if you invoke an operation once or if you invoke an operation n times, it shouldn't change the state of hte system compared to the first time you
      invoke the operation
    - it is safe to invoke this query once on many times

:ref:`Go Back <principals-label>`.