.. _data-base-per-service-microservices-pattern-label:

Database Per Service Pattern
============================

Traditional implementation
---------------------------
- with traditional implementation, multiple microservices would access the same database
- problems:
    - create tight couples microservices
    - performance bottleneck
    - is that microservices can not be scaled & deployed indenpendently
    - different services have different data storage requirements. For some services, a relational database is the best choice. Other services might need a NoSQL database such as MongoDB


    .. image:: ../../images/patterns/microservices/db_per_service/traditional_architectura.png
        :align: center



New solution
------------
- each microservices owns its own database
- gives full control of its data schema and how it sotres or retrieves information 
- each microservices can be developed deployed and scal independently
- it is eliminated the risk of changing one DB in one service affecting others
- each microservice will access the db using API and not database direct access


    .. image:: ../../images/patterns/microservices/db_per_service/db_per_service_architecture.png
        :align: center

- It is a good idea to create barriers that enforce this modularity. You could, for example, assign a different database user id to each service and use a database access control mechanism such as grants

Types
------
- private table per service pattern
    - each microservice gets exclusiveset of tables in a shared database
    - these tables aren't accesable to the other services


    .. image:: ../../images/patterns/microservices/db_per_service/table_per_service.png
        :align: center


- schema per service pattern 
    - each server has its own database schema
    - multiple schema can exist in the same database but they are isolated from one another 


    .. image:: ../../images/patterns/microservices/db_per_service/schema_per_service.png
        :align: center


- database per service pattern
    - each server has its own database entirely separte form others
    - 

    .. image:: ../../images/patterns/microservices/db_per_service/database_per_service.png
        :align: center


Challenges
----------
- Data consistency
    - it can be difficult cu maintain data consistency especially when transactions span across multiple services
    - Global ACID transactions don’t exist anymore
    - inconsistent updates
    - partial failures
    - This leads to eventual consistency instead of strong consistency.
    - Payment succeeds, but Order DB update fails → user is charged, but order is not placed
        - Solution hint: Use SAGA pattern for compensation (reverse the payment if order fails).
- data duplicates
    - The same entity (like User) might exist in multiple databases (User Service, Order Service, Payment Service).
    - Updating that entity everywhere becomes tricky.
    - Example: If a user updates their email, how do you reflect it in Orders and Payments?
- complex reporting & analystics
    - In a monolith, one SQL query could join Orders + Users + Payments.
    - With per-service DBs, this becomes impossible directly.
    - Data is scattered → making dashboards and reports painful.
        - Solution hint: Push aggregated events to a data warehouse / data lake for reporting.
- operational overhead
    - Instead of one DB, you now manage dozens (or hundreds).
    - Monitoring, scaling, patching, and backups all multiply.
    - Requires DevOps maturity and strong observability practices.
- distributed transactions 
    - Business workflows often span services (like “place order → charge payment → update inventory”).
    - Without a single DB transaction, you risk partial failures.
        - Solution hint: Use distributed patterns like SAGA or event sourcing.

Data Management Techniques
----------------------------
- eventual consistency:
    - Services don’t update each other directly.
    - Instead, they publish events like OrderPlaced, PaymentProcessed, UserUpdated.
    - Other services consume and update their DBs asynchronously.
    - Pros: Loose coupling, scalable
    - Cons: Temporary inconsistencies
- SAGA pattern:
    - Breaks a long business transaction into smaller local ones.
    - If something fails, run a compensating transaction.
    - Pros: Reliability, avoids inconsistent state
    - Cons: More logic in services, harder debugging
    - example:
        - Order Service creates an order (pending).
        - Payment Service charges the card.
        - If payment fails → send a rollback command → Order Service cancels the order.
- CQRS:
    - Separate write models and read models.
    - Writes go to the service DBs.
    - Reads (reports, dashboards) are served from materialized views or a query-optimized DB.
    - Pros: Great for scaling reads
    - Cons: More moving parts
- Event sourcing
    - Instead of storing only the final state, store all events that led to it.
    - Example: Instead of storing “Order = Delivered”, store the sequence:
    - OrderPlaced → PaymentReceived → Shipped → Delivered
    - You can replay events to rebuild state or debug issues.
    - Pros: Transparency, history tracking
    - Cons: Event storage can grow huge, adds complexity
- Data Lakes & Materialized Views
    - For reporting/analytics, push events into a centralized data lake (e.g., S3 + Redshift, or Kafka + Druid).
    - Create pre-computed views for faster queries.
    - Pros: Solves cross-service reporting

:ref:`Go Back <microservices_patterns-label>`.