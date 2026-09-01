.. _frameworks-java-spring-rest-annotations-repository-label:

Repository
==========
- is used to connect to a database
- instance of EntityManager will be created and injected automatically by Spring if Autowire is used
- methods from EntityManager can be used to retrieve data from DB
- is recommended to not use @Transaction on repository layers, but on Service layer
- Wpring will also provides translation of any JDBC related exception to unchecked exceptions


    .. code-block:: python
        :linenos:


        @Repository
        public class EmployeeDAOJpaImpl implements EmployeeDAO {

            // define field for entitymanager
            private EntityManager entityManager;

            // set up constructor injection
            @Autowired
            public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
                entityManager = theEntityManager;
            }


            @Override
            public List<Employee> findAll() {

                // create a query
                TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

                // execute query and get result list
                List<Employee> employees = theQuery.getResultList();

                // return the results
                return employees;
            }
        }

        public interface EmployeeDAO {
            List<Employee> findAll();
        }


:ref:`Go Back <frameworks-java-spring-rest-annotations-label>`.