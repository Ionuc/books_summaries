.. _cqrs-architectural-pattern-label:

CQRS - Command Query Responsibility Segregation
===============================================

- is an architectural pattern used to build scalable and high-performance systems by separating read and write operations
- it is especially effective for applications with complex business logic and demanding scalability needs
- Divides responsibilities of reading and writing data into separate models
- Enables more flexible, scalable, and efficient system architectures

    .. image:: ../../images/patterns/architectural/cqrs/architectural_view1.png
        :align: center


- Separating the methods for reading and publishing data is the primary goal of the CQRS architectural pattern
- It separates the read and update operations on a datastore into two separate models: Queries and Commands
- Implementing CQRS in our application can maximize its performance, scalability, and security.


    .. image:: ../../images/patterns/architectural/cqrs/architectural_view2.png
        :align: center


Limittion of Traditional Architecture
-------------------------------------
- Traditional architectures often face challenges in handling high loads and managing complex data requirements
- the same model is used for both reading (fetching data) and writing (updating data), which can lead to performance issues
- As the application grows, handling large read and write requests together becomes harder, creating bottlenecks and slowing down responses

- CQRS solves this be seprating read and write operations into distinc models:
    - This means write requests (commands) and read requests (queries) are processed independently, optimizing each for its specific task.
    - CQRS allows systems to handle higher traffic efficiently, improves performance, and simplifies scaling by allowing independent optimization of read and write parts


Basic Architecture of CQRS
--------------------------

- Commands:
    - Commands represent requests that change the state of the application.
    - Perform write operations such as Insert, Update, and Delete.
    - Do not return data; they only modify application state.
    - Encapsulate the operation name and required data.

- Command Handlers:
    - Command Handlers process commands and apply business logic.
    - Interpret commands and execute state-changing operations.
    - Produce events indicating success or failure of the command.

- Queries:
    - Queries are used to retrieve data without modifying it.
    - Perform read-only operations.
    - Return data to the client for display or processing.

- Query Handlers:
    - Query Handlers handle data retrieval requests.
    - Interpret query objects.
    - Fetch and return the requested data from the database.

Relationship between CQS and CQRS
---------------------------------
- Command Query Separation (CQS) and CQRS are related in that CQRS extends upon the fundamental concept of CQS
- CQS:
    - It is a programming principle that says you should separate operations that change data (commands) from those that read data (queries)
    - If you have a method, for instance, it should either return something or update something, but not both
- CQRS:
    - Promotes dividing the design of the entire system into two sections—one for managing commands (writing or modifying data) and another for managing queries (reading data),
    - CQRS expand the idea of CQS

When to use CQRS ?
------------------
- You should use the CQRS design pattern when your application has different types of operations—like when reading data is very frequent and writing data is complex or infrequent:
    - Handling complex queries: If your application needs to perform complicated read operations (queries), separating the read and write sides can optimize performance
    - Scalability: When you need to scale reading and writing operations independently, CQRS allows you to optimize each side separately for better scalability
    - Event-driven systems: In systems where changes trigger events, CQRS works well with event sourcing to handle complex workflows.

Database Synchronization in CQRS
---------------------------------
- Synchronizing databases in a system that follows the CQRS pattern can be challenging due to the separation of the write and read sides of the application


    .. image:: ../../images/patterns/architectural/cqrs/architectural_view3.png
        :align: center


- steps to implement:
    1. Write to the Command Database: When you make changes (create, update, delete), they are first saved in the command database. This database is optimized for handling write operations.
    2. Generate Events: After the write operation is successful, the system generates events that describe what changed (like "Order Created" or "User Updated"). These events serve as notifications about the updates.
    3. Update the Query Database: The read database, optimized for fast queries, listens for these events and applies the changes to its own copy of the data. This way, the query database gets updated with the latest information.
    4. Eventual Consistency: The key idea is that the query database doesn’t have to update immediately. There can be a slight delay, but eventually, both databases will sync, ensuring consistency over time.


Benefits of using CQRS
-----------------------
- Improved scalability:
    - CQRS allows the system to scale read and write workloads independently.
    - Query model can be optimized for high read performance.
    - Command model can be optimized for complex write operations.
- Improved Performance
    - Separating read and write models improves overall system efficiency.
    - Read model provides faster data access.
    - Write model ensures data consistency and integrity.
- Maintainability:
    - CQRS improves maintainability by clearly separating read and write responsibilities within the system.

Challenges of using CQRS
------------------------
-   Complexity:
    - Your system may become more comple
    - it can be difficult to coordinate data synchronization, manage distinct read and write models, and guarantee consistency between the two
- Consistency:
    - Maintaining consistency between the read and write models can be challenging, especially in distributed systems where data updates may not propagate immediately
- Data Synchronization:
    - It might be difficult to keep the read and write models in sync, particularly when handling complicated data transformations or big data sets
    - It can be beneficial to use strategies like message queues or event sources.
- Performance Overhead:
    - Implementing CQRS can introduce performance overhead, especially if not done carefully
    - For example, using event sourcing for the write model can impact write performance, while keeping the read model updated in real-time can impact read performance
- Operational Complexity
    - Operational complexity may rise while managing two databases or data storage (one for read and one for write)

Best practices for CQRS
-----------------------
- Separate Read and Write Models Carefully
- Use Asynchronous Communication When Needed:
    - Since commands and queries are separated, consider using asynchronous messaging for commands
- Keep Commands and Queries as Simple as Possible:
    - Avoid mixing read and write logic in either part to keep things clean and maintainable
- Embrace Event Sourcing for Data Consistency:
    - Event sourcing can be paired with CQRS to keep a record of all changes
    - Each change is saved as an event, and the current state is rebuilt from these events
    - This can make it easier to track history, recover data, or audit changes
- Consider the Complexity of Your System: 
    - CQRS adds some complexity, so it’s best suited for systems with high read and write demands or complex business rules
    - For simpler systems, CQRS might be overkill and add unnecessary development overhead.

:ref:`Go Back <design-patterns-behavioral-label>`.