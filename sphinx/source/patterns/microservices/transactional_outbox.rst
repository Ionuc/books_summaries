.. _transactional_outbox-microservices-pattern-label:

Transactional Outbox Pattern
============================

General
-------
- A service command typically needs to create/update/delete aggregates in the database and send messages/events to a message broker
- for example, a service that participates in a saga needs to update business entities and send messages/events.
- The command must atomically update the database and send messages in order to avoid data inconsistencies and bugs
- However, it is not viable to use a traditional distributed transaction (2PC) that spans the database and the message broker
- The database and/or the message broker might not support 2PC
- also, it is undesirable to couple the service to both the database and the message broker.

Problem:
--------
- without using 2PC, sending a message in the middle of a transaction is not reliable
- There’s no guarantee that the transaction will commit
- if a service sends a message after committing the transaction there’s no guarantee that it won’t crash before sending the message.
- messages must be sent to the message broker in the order they were sent by the service

- If the database transaction commits then the messages must be sent. Conversely, if the database rolls back, the messages must not be sent
- Messages must be sent to the message broker in the order they were sent by the service. This ordering must be preserved across multiple service instances that update the same aggregate.


Solution
--------


The simple explanation of the pattern is in four steps.
1. The order data is inserted/updated/deleted into/from the ORDER table (here for simplicity the customer order data is kept). If the operation succeeds, the process continues with insertion into the OUTBOX table with a description of the operation made. This must be done in a DB transaction.
2. Message Relay reads the OUTBOX table record, processes it, and prepares the message that should be published to the Message Broker.
3. Message Relay publishes the prepared message in point 2.

    .. image:: ../../images/patterns/microservices/saga/architectural_overview.png
        :align: center

Participants
-------------
- Sender - the service that sends the message
- Database - the database that stores the business entities and message outbox
- Message outbox - if it’s a relational database, this is a table that stores the messages to be sent. Otherwise, if it’s a NoSQL database, the outbox is a property of each database record (e.g. document or item)
- Message relay - sends the messages stored in the outbox to the message broker

Challenges
----------
- message duplication:
    - It might, for example, crash after publishing a message but before recording the fact that it has done so
    - When it restarts, it will then publish the message again
    - As a result, a message consumer must be idempotent, perhaps by tracking the IDs of the messages that it has already processed
    - mesasge consumers usually need to be idempotent (should not be a problem if the message is sent multiple times)
- message versioning:
    -  Let’s have an environment where a message could have more than one version, the message with version X is processing but the service has already processed a moment ago version X+1. In that case, the message could be rejected because might have outdated information
- Having multiple Active-Active Message Relay instances allows higher availability but increases the probability of publishing duplicates and unordering
- Integration of Retry Mechanism:
    - if a Message Relay doesn’t manage to publish a message, it will try some more times to do it
    - if all attempts are unsuccessful the message will be put in another place/queue for later processing
- Order of the messages:
    - for some systems is important to send messages or events in the same order that the customer orders come, this sequence should be tracked somehow

Advatages
---------
- Consistency:
    - ensuring that data is successfully processed between all parties in the system without any loss
- Reliability:
    - if a service fails or goes down, it will resume its wor from the point where it stopped once it is back online
- Decoupling:
    - different systems can operate independently without needing to know about each other

Cons
----
- Complexity:
    - splitting the workflow among multiple components or services
- Performance
    - adding more parties to the workflow increases processing complexity and data transfer betwen them
- Additional Storage:
    - the outbox table

:ref:`Go Back <microservices_patterns-label>`.