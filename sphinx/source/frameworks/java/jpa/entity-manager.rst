.. _frameworks-java-jpa-entity-manager-label:

Entity Manager
==============
- is the main component used to create queries
- Spring will create automatically beans for EntityManager and DataSource
- used in case you need:
    - low-level control over the database operations
    - want to write custom queries
    - complex queries that required advanced feature such as native SQL queries or stored procedures calls
- provides low-level access to JPA and works directly with JPA entities
- alternative is the JpaRepository


Methods
-------
- persiste(<model>)
    - used to save an entity to DB


    .. code-block:: python
        :linenos:

        @Override
        @Transactional
        public void save(Student theStudent) {
            entityManager.persist(theStudent);
        }


- find(<entityClass>, <id>)
    - retrieve the entity based on the id value or NULL value


    .. code-block:: python
        :linenos:

        @Override
        public Student findById(Integer id) {
            return entityManager.find(Student.class, id);
        }


- createQuery()
    - used to create custom queries, like:
        - getting all items
        - filtered by some fields
        - delete all items or filtered items
        - update all items or filtered items
        - create tables
        - etc


    .. code-block:: python
        :linenos:

        @Override
        public List<Student> findAll() {
            // create query
            TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

            // return query results
            return theQuery.getResultList();
        }

        @Override
        public List<Student> findByLastName(String theLastName) {
            // create query
            TypedQuery<Student> theQuery = entityManager.createQuery(
                                            "FROM Student WHERE lastName=:theData", Student.class);

            // set query parameters
            theQuery.setParameter("theData", theLastName);

            // return query results
            return theQuery.getResultList();
        }

        @Override
        @Transactional
        public int deleteAll() {

            int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

            return numRowsDeleted;
        }

        @Override
        @Transactional
        public int updateAll() {

            int numRowsDeleted = entityManager.createQuery("UPDATE Student set lastName='Tester'").executeUpdate();

            return numRowsDeleted;
        }


- merge(<model>)
    - will update the model in case id is not the default value, or create new row in case id has the default value
    - in case id is of type int, then in case:
        - id == 0 => will create new value
        - id != 0 => will update existing one
    - in case id is of type Integer, then in case:
        - id == NULL => will create new value
        - id != NULL => will update existing one


    .. code-block:: python
        :linenos:

        @Override
        @Transactional
        public void update(Student theStudent) {
            entityManager.merge(theStudent);
        }

- remove(<model>)
    - removes a model from DB

    .. code-block:: python
        :linenos:

        @Override
        @Transactional
        public void delete(Integer id) {

            // retrieve the student
            Student theStudent = entityManager.find(Student.class, id);

            // delete the student
            entityManager.remove(theStudent);
        }

Example of StudentDAO
---------------------

    .. code-block:: python
        :linenos:

        public interface StudentDAO {

            void save(Student theStudent);

            Student findById(Integer id);

            List<Student> findAll();

            List<Student> findByLastName(String theLastName);

            void update(Student theStudent);

            void delete(Integer id);

            int deleteAll();
        }




    .. code-block:: python
        :linenos:


        @Repository
        public class StudentDAOImpl implements StudentDAO {

            // define field for entity manager
            private EntityManager entityManager;

            // inject entity manager using constructor injection
            @Autowired
            public StudentDAOImpl(EntityManager entityManager) {
                this.entityManager = entityManager;
            }

            // implement save method
            @Override
            @Transactional
            public void save(Student theStudent) {
                entityManager.persist(theStudent);
            }

            @Override
            public Student findById(Integer id) {
                return entityManager.find(Student.class, id);
            }

            @Override
            public List<Student> findAll() {
                // create query
                TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

                // return query results
                return theQuery.getResultList();
            }

            @Override
            public List<Student> findByLastName(String theLastName) {
                // create query
                TypedQuery<Student> theQuery = entityManager.createQuery(
                                                "FROM Student WHERE lastName=:theData", Student.class);

                // set query parameters
                theQuery.setParameter("theData", theLastName);

                // return query results
                return theQuery.getResultList();
            }

            @Override
            @Transactional
            public void update(Student theStudent) {
                entityManager.merge(theStudent);
            }

            @Override
            @Transactional
            public void delete(Integer id) {

                // retrieve the student
                Student theStudent = entityManager.find(Student.class, id);

                // delete the student
                entityManager.remove(theStudent);
            }

            @Override
            @Transactional
            public int deleteAll() {

                int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

                return numRowsDeleted;
            }
        }

:ref:`Go Back <frameworks-java-jpa-label>`.