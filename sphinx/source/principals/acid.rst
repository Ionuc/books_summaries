.. _acid-label:

ACID
====

    - is a set of properties of database transactions intended to guarantee validity even in the event
      of errors, power failure, et etc.
    - in the context of databases, a sequance of database operations that satisfies the ACID properties
      and thus can be perceive as a single logical operation on the data, is called a transaction

Atomicity
---------
    - transactions are often composed of multiple statements
    - it guarantees that each transaction is treated as a single unit, which either succeeds completely
      or fails completely

    - if one statement fails, then the entire transaction fails and the DB is left unchanged
    - an atomic system must guarantee atomicity in each and every situation, including power failure,
      errors and crashes

    - prevents partial updates to DB

Consistency
-----------
    - consistency ensure that a transacton can only bring the databases from one valid state to another,
      maintaining database invariants: any data written to the database must be valid according to all
      defines rules, including constraints, cascades, triggers
    - this prevents database corruption by an illegal transactions, but does not guarantee that a transaction
      is correct.

Isolation
---------
    - transactions are often executed concurrently (reading and writting to multiple tables at the same time)
    - isolation ensures that concurrent execution of transactions leaves the database in the same state that
      would have been optained if the transactions were executed sequentially

    - isolation is the main goal of concurrency control
    - depending on the method used, the effects if an incomplete transaction might not even be visible to other transactions
    - a lower isoltaion level increases the ability of many users to access the same data at the same time, but increases the number of concurrency effects( such as dirty reads
      or lost updates)

    - a higher level reduces the types of concurrency effects that users may encounter, but requires more system resources and increases the chances that one transaction will block
      another

    - Isolation levels:
        - Serializable
            - is the highest isolation level
            - with a lock-based concurrency control, serializability requires read and write loks (acquired on selected data) to be released at the end of the transaction
            - also range-locks must be acquired when a SELECT query uses a ranged WHERE clause, especially to avoid phantom reads phenomenon
        - Reapeatable reads
            - a lock-based concurrency control keeps read and write locks(acquired on selected data) until the end of transactions
            - however, range-locks are not managed, so phantom reads can occur
            - write skew is possible at this isolation level
        - Read committed
            - with a lock-based concurrency control, it keeps write locks (acquired on selected data) until the end of the transactions
            - read locks are released as soon as the SELECT operation is performed, so the non-repeatable reads phenomenon can occuer in this isolation leve
            -  as previous leve, range-locks are not managed
        - Read uncommitted 
            - this is the lowest isolation level
            - dirty reads are allowed, so one transaction may see not-yet-committed changes made by other transactions

    - Problems:
        - Dirty reads
            - occurs when a transaction is allowed to read data from a row that has been modified by another running transaction and not yet committed
            -  works similarly to non-repeatable reads, however, the second transaction would not need to be committed for the first query to return a different result
        - Non-repeatable reads
            - occurs, when during the course of a transaction, a row is retrieved twice and the values within the row differ between reads
            - non-repeatable reads phenomenon may occur in a lock-based concurrency control method when:
                - read locks are not acquire when performin a SELECT
                - or the acquired locks on affected row are released as soon as the SELECT operation is performed
        - Phantom reads
            - occurs when, i the course of a transaction, new rows are added by another transaction to the records being read
            - this may occur when range locks are not acquired on performing a SELECT ... WHERE operation

Durability
----------
    - guarantee that once a transaction has been committed, it will remaing committed even in the case of
      a system failure (eg powere outage or crash)

    - this usually means that completed transactions (or their effects) are recorded in non-volatile memory

	  
:ref:`Go Back <principals-label>`.

