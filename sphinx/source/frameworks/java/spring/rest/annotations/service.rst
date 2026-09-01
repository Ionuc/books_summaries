.. _frameworks-java-spring-rest-annotations-service-label:

Service
=======
- is implementation of the Service Facade design pattern
- intermediate layer for custom business logic
- integrate data from multiple sources (DAO / repositories)
- best practiaces:
    - apply transactionla boundaries at the service layer
    - apply @Transactional on service methods
    - remove @Transactional on DAO methods if they already exist

    .. code-block:: python
        :linenos:

        @Service
        public class EmployeeServiceImpl{
            public List<Employee> findAll() {
                ...
                return null
            }

        }


:ref:`Go Back <frameworks-java-spring-rest-annotations-label>`.