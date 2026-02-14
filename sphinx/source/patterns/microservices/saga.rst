.. _saga-microservices-pattern-label:

Saga Pattern
============
- The Saga architecture pattern provides transaction management using a sequence of local transactions
- A local transaction is the unit of work performed by a Saga participant
- Every operation that is part of the Saga can be rolled back by a compensating transaction
- the Saga pattern guarantees that either all operations complete successfully or the corresponding compensation transactions are run to undo the work previously completed.
- In the Saga pattern, a compensating transaction must be idempotent and retryable
- The Saga Execution Coordinator (SEC) guarantees these principles:


    .. image:: ../../images/patterns/microservices/saga/architectural_overview.png
        :align: center



The Saga Execution Coordinator
------------------------------
- is the central component to implement a Saga flow
- It contains a Saga log that captures the sequence of events of a distributed transaction
- For any failure, the SEC component inspects the Saga log to identify the impacted components and the sequence in which the compensating transactions should run.
- For any failure in the SEC component, it can read the Saga log once it’s coming back up.
- It can then identify the transactions successfully rolled back, which ones are pending, and can take appropriate actions:


    .. image:: ../../images/patterns/microservices/saga/sec.png
        :align: center


- There are two approaches to implement the Saga pattern:
    1. choreography
    2. orchestration

1. Saga Choreography Pattern
----------------------------
- each microservice that is part of the transaction publishes an event that is processed by the next microservice
- To use this pattern, we need to decide if the microservice will be part of the Saga
- the Saga Execution Coordinator is either embedded within the microservice or can be a standalone component.
- In the Saga, choreography flow is successful if all the microservices complete their local transaction, and none of the microservices reported any failure.


    .. image:: ../../images/patterns/microservices/saga/saga_coregraphy_success.png
        :align: center


- in case of failure, the microservice reports the failure to SEC, and it is the SEC’s responsibility to invoke the relevant compensation transactions


    .. image:: ../../images/patterns/microservices/saga/saga_coregraphy_failure.png
        :align: center


- works for greenfield microservice application development
- this pattern is suitable when there are fewer participants in the transaction.

2. Saga Orchestration Pattern
-----------------------------
-  a single orchestrator is responsible for managing the overall transaction status.
- If any of the microservices encounter a failure, the orchestrator is responsible for invoking the necessary compensating transactions:


    .. image:: ../../images/patterns/microservices/saga/saga_orchestration.png
        :align: center

- useful for brownfield microservice application development architecture
- In other words, this pattern works when we already have a set of microservices and would like to implement the Saga pattern in the application.
- We need to define the appropriate compensating transactions to proceed with this pattern.

Challenges
----------
- Lack of automatic rollback : 
    - a developer must design compensating transactions that explicitly undo changes made earlier in a saga rather than relying on the automatic rollback feature of ACID transactions
- Lack of isolation:
    - there’s risk that the concurrent execution of multiple sagas and transactions can use data anomalies. consequently, a saga developer must typical use countermeasures, which are design techniques that implement isolation
- Reliability:
    - a service must atomically update its database and publish a message/event.
    - It cannot use the traditional mechanism of a distributed transaction that spans the database and the message broker.
- Synchornous calls:
    - A client that initiates the saga, which an asynchronous flow, using a synchronous request (e.g. HTTP POST /orders) needs to be able to determine its outcome
    - There are several options, each with different trade-offs:
        - The service sends back a response once the saga completes, e.g. once it receives an OrderApproved or OrderRejected event.
        - The service sends back a response (e.g. containing the orderID) after initiating the saga and the client periodically polls (e.g. GET /orders/{orderID}) to determine the outcome
        - The service sends back a response (e.g. containing the orderID) after initiating the saga, and then sends an event (e.g. websocket, web hook, etc) to the client once the saga completes.

:ref:`Go Back <microservices_patterns-label>`.