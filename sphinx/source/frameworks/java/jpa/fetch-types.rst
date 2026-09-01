.. _frameworks-java-jpa-fetch-types-label:

Fetch Types
===========
- there are 2 types of loading:
    - eager loading
    - lazy loading

Eager Loading
-------------
- will load all dependent entities:
    - in our case, it will load the Instructor together with all courses at once
    - this can impact the performance of application in care there are a lot of models to be loaded once

Lazy loading
------------
- will load only the main entity
- dependent entities will be loaded on demand on dedicated requests
- this required on open Hibernate session
    - need an connection to database to retrieve data
    - Hibernate will thrown exception in case session is closed and you try to retrieve lazy data

- solution for Session closed:
    - 1). Change fetch type to EAGER
    - 2). Keep the fetch type LAZY:
        - it cannot be used the method directly from the model

  .. code-block:: python
        :linenos:

        public CrudodemoApplication() {
            private void findInstructorWithCourses(AppDAO appDDAO){
                int id = 1;
                Instructor instructor = appDAO.findInstructorById(id);
                System.out.println("instructor" + instructor);
                System.out.println("courses: " + instructor.courses()); => will thrown SessionClose exception
            }
        }


        - a new method should be provided on the Repository component to do a new request to DB

  .. code-block:: python
        :linenos:

        public AppDAOImpl {
            ...

            public List<Course> findCoursesByInstructorId(int id) {
            TypeQuery<Course> query = entityManager.createQuery("from COURSE where instructir.id=:data", Course.class);
            query.setPAramter("data", id);

            return query.getResultList();
            }
        }

        public CrudodemoApplication() {
            private void findInstructorWithCourses(AppDAO appDDAO){
                int id = 1;
                Instructor instructor = appDAO.findInstructorById(id);
                System.out.println("instructor" + instructor);
                // System.out.println("courses: " + instructor.courses()); => will thrown SessionClose exception

                List<Course> courses = appDAO.findCoursesByInstructorId(id);
                instructor.setCourses(courses);
                System.out.println("The courses: " + instructor.getCourses()); // will not throw exception because of the call of setCourses() from line above
            }
        }



Default values
--------------
- default fetch type:
    - OneToOne => FetchType.EAGER
    - OneToMany => FetchType.LAZY
    - ManyToOne => FetchType.EAGER
    - ManyToMany => FetchType.LAZY


Best Practice
-------------
- only load data when absolut needed
- prefer lazy loading instead of eager loading


:ref:`Go Back <frameworks-java-jpa-label>`.