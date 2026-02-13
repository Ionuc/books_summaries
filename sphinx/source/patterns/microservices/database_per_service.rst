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
- ensure data consistency across services
    - it can be difficult cu maintain data consistency especially when transactions span across multiple services 
    - inconsistent updates
    - partial failures
- distributed transactions => eventual consistency models instead of traditional transactions
- data duplicates
- cross-service queries
    - you cannot easily join data acrross diffent databases forcing microservices to comunicate with each other to retrieve necessary information

Event driven architecture
-------------------------
- this architecture can help with the challenges above
- when a significant change occurs, an event will be published and all other services will process it

:ref:`Go Back <microservices_patterns-label>`.