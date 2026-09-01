.. _frameworks-java-jpa-join-fetch-label:

Join Fetch
==========
- you can join result from multiple table in order to retrieve all data into a single request and not in multiple individual requests
- even if the model is mapped to one-to-many with fetch type LAZY (@OneToMany(fetchType=LAZY)), the result will contain information from both table
- the JOIN FETCH si similar to EAGER loading
- example of fetching with multiple join clauses for Course and InstructorDetail

  .. code-block:: python
        :linenos:

        public AppDAOImpl {
            ...

            public Instructor findInstructorByIdJoinFetch(int id) {
            TypeQuery<Instructor> query = entityManager.createQuery("select i from Instructor i"
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail"
                + "where i,id= :data", Instructor.class);
            query.setPAramter("data", id);

            return query.getSingleResul();
            }
        }


  .. code-block:: python
        :linenos:

        public CrudodemoApplication() {
            private void findInstructorWithCourses(AppDAO appDDAO){
                int id = 1;
                Instructor instructor = appDAO.findInstructorById(id);
                System.out.println("instructor" + instructor);
                System.out.println("courses: " + instructor.courses()); => will not thrown SessionClose exception because the data will not be fetched again

            }
        }


:ref:`Go Back <frameworks-java-jpa-label>`.