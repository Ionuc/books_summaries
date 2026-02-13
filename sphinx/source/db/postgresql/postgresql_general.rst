.. _postgresql-general-label:


PostgreSQL
==========

Isolation level
----------------------
- you can set the isolation level as:

.. code-block:: python
   :linenos:

   BEGIN TRANSACTION ISOLATION LEVEL READ COMMITTED;
    -- Your queries
    COMMIT;

Pessimistic Locking
-------------------

- there are different locking modes, when selecting raws in Postgresql:
    - FOR UPDATE:
        - Blocks concurrent transactions on the same rows from performing SELECT FOR UPDATE, SELECT FOR NO KEY UPDATE, SELECT FOR SHARE, SELECT FOR KEY SHARE, UPDATE, and DELETE
        - This is the mode implicitly used for DELETE commands, and UPDATE commands on columns with unique indexes
        - Use when you want to prevent other transactions from modifying or acquiring any type of lock on the selected rows
    - FOR NO KEY UPDATE:
        - Blocks concurrent transactions on the same rows from performing SELECT FOR UPDATE, SELECT FOR NO KEY UPDATE, SELECT FOR SHARE, DELETE, and any UPDATE that changes key values (such as primary keys).
        - This is the mode implicitly used for UPDATE commands that don't acquire a FOR UPDATE lock
        - Use when you want to lock selected rows against concurrent updates that change key values and from exclusive locks, while allowing other transactions to acquire SELECT FOR KEY SHARE locks
    - FOR SHARE:
        - Blocks concurrent transactions on the same rows from performing SELECT FOR UPDATE, SELECT FOR NO KEY UPDATE, UPDATE, and DELETE.
        - Use when you want to lock selected rows against all concurrent updates and from exclusive locks, while allowing other transactions to acquire SELECT FOR SHARE or SELECT FOR KEY SHARE locks
    - FOR KEY SHARE:
        - Blocks concurrent transactions on the same rows from performing SELECT FOR UPDATE, DELETE, and any UPDATE that changes key values (such as primary keys).
        - Use when you want to lock selected rows against concurrent updates that change key values and from exclusive locks, while allowing other transactions to acquire any type of shared lock

.. code-block:: python
   :linenos:

    BEGIN;
    SELECT * FROM products WHERE id = 1 FOR UPDATE;
    -- Other transactions trying to read or update the same row will be blocked until this transaction completes
    UPDATE products SET price = 10 WHERE id = 1;
    COMMIT;

- Please note that normal SELECT statements under an isolation level like READ COMMITTED will not be blocked by a SELECT FOR UPDATE or any other locking mode, as normal SELECT statements don't acquire any locks on the rows they read, so they don't cause any conflicts with other transactions.

Optimistic Locking
------------------
- Optimistic locking doesn't assume that conflicts will happen, so it allows all transactions to run concurrently
- If a conflict is detected at commit time (two concurrent transactions have modified the same data), one transaction will proceed and the other will be rolled back and must retry.
- In PostgreSQL, you can implement optimistic locking using a versioning field in your tables

.. code-block:: python
   :linenos:

    CREATE TABLE products (
        id SERIAL PRIMARY KEY,
        name TEXT,
        price NUMERIC,
        version INT DEFAULT 1
    );

    UPDATE 
        products 
    SET 
        price = 10.0,
        version = version + 1
    WHERE
        id = 1 AND version = version
    RETURNING count(id);



Deadlocks
---------
- A deadlock happens when two or more transactions are waiting for each other to release locks
- PostgreSQL periodically checks for deadlocks and handles them.
- If a lock isn't released within the configurable parameter deadlock_timeout (default is 1 second), PostgreSQL will trigger the deadlock detection algorithm and if a deadlock was found, one of the involved transactions will be rolled back.

:ref:`Go Back <postgresql-db-label>`.