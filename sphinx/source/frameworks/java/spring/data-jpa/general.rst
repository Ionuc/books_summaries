.. _frameworks-java-spring-data-jpa-general-label:

General
=======
- used to provide methods used to CRUD operations based on 2 properties, like:
    - entity type: is the model class
    - primary key type: the class for the identifier
- minimize the boiler-plate DAO code

JPA Repository
--------------
- is the interface provided by Spring Data JPA
- expose methods:
    - findAll())
    - findByID
    - save()
    - deleteById()
    - etc
- is not needed the implementation, it is enough just to inject it in the needed component
- all methods are having @Transaction annotations


    .. code-block:: python
        :linenos:

        public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
            // that's it ... no need to write any code!
        }

JpaRepository vs EntityManager
--------------------------
- EntityManager is used in case you need:
    - low-level control over the database operations
    - want to write custom queries
    - complex queries that required advanced feature such as native SQL queries or stored procedures calls
- JPARepository is used when you need:
    - commonly CRUD operations out of the box, reducing the boiler plate code
    - pagination, sorting
    - generate queries based on methods name
    - you can create also custom queries using @Query annotation


Advanced features
-----------------
- extending and adding custom queries with JPQL
- Query Domain Specific Language
- define custom methods if you need some low level coding

:ref:`Go Back <frameworks-java-spring-data-jpa-label>`.