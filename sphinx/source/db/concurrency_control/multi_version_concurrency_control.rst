.. _mvcc-concurrency_control-db-label:

Multi Version Concurrency Control
=================================

Overview
--------

- when using 2PL, every read requires a shared lock acquisition, while a write operation requires taking an exclusive lock:
    - a shared lock blocks Writers, but it allows other Readers to acquire the same shared lock
    - an exclusive lock blocks both Readers and Writers concurring for the same lock
- but the 2PL approach, the contention affects calability, so we need another approach which:
    - Readers don’t block Writers
    - Writers don’t block Readers
    - The only use case that can still generate contention is when two concurrent transactions try to modify the same record since, once modified, a row is always locked until the transaction that modified this record either commits or rolls back.
    - the Concurrency Control mechanism must operate on multiple versions of the same record

PostgreSQL
----------
- Oracle and MySQL use the undo log to capture uncommitted changes so that rows can be reconstructed to their previously committed version
- PostgreSQL stores all row versions in the table data structure.
- every row has two additional columns:
    - Xmin  – which defines the transaction id that inserted the record
    - Xmax  – which defines the transaction id that deleted the row

- In PostgreSQL, the Transaction Id is a 32-bit integer, and the VACUUM process is responsible (among other things like reclaiming old row versions that are no longer in use) for making sure that the id does not overflow.

- If the transaction id is higher than the Xmin value of a committed row, the transaction is allowed to read this record version.
- If the transaction id is lower than the Xmin value, then it’s up to the isolation level to decide if a record should be visible or not:
    - For READ COMMITTED, the currently executing statement timestamp becomes the lower boundary for row visibility.
    - For REPEATABLE READ or SERIALIZABLE, all reads are relative to the start timestamp of the currently running transaction

- If the transaction id is greater than the Xmax value of a committed row, the transaction is not allowed to read this record version anymore.
- If the transaction id is lower than the Xmax value, then it’s up to the isolation level to decide if a record should be visible or not:
    - For READ COMMITTED, the currently executing statement timestamp becomes the lower boundary for row visibility. 
    - For REPEATABLE READ or SERIALIZABLE, all reads are relative to the start timestamp of the currently running transaction.

Inserting a record
------------------


.. image:: ../../images/db/concurrency_control/mvcc/mvcc_inserting_record.png
   :align: center


- What is happening:
    - Both Alice and Bob start a new transaction, and we can see their transaction ids by calling the txid_current() PostgreSQL function
    - When Alice inserts a new post row, the x_{\text{min}}  column value is set to Alice’s transaction id
    - Under default Read Committed isolation level, Bob cannot see Alice’s newly inserted record until Alice committs her transaction
    - After Alice has committed, Bob can now see Alice’s newly inserted row

- While in 2PL, Bob’s modification would block Alice read statement, in MVCC Alice is still allowed to see the previous version until Bob manages to commit his transaction.
- The DELETE operation does not physically remove a record, it just marks it as ready for deletion, and the VACUUM process will collect it when this row is no longer in use by any current running transaction.

Updating a record
-----------------


.. image:: ../../images/db/concurrency_control/mvcc/mvcc_inserting_record.png
   :align: center


- What is happening:
    - Both Alice and Bob start a new transaction, and we can see their transaction ids by calling the txid_current() PostgreSQL function
    - When Bob updates a post record, we can see two operations happening: a DELETE and an INSERT.
    - The previous row version is marked as deleted by setting the Xmax  column value to Bob’s transaction id, and a new row version is created which has the Xmin column value set to Bob’s transaction id
    - Under default Read Committed isolation level, until Bob manages to commit his transaction, Alice can still see the previous record version
    - After Bob has committed, Alice can now see the new row version that was updated by Bob

:ref:`Go Back <db-label>`.