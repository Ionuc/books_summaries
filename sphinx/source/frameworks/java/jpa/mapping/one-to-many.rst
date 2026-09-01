.. _frameworks-java-jpa-mapping-one-to-many-label:

One-to-Many & Many-To-One Mapping
=================================
- one object from the first model is link to multiple objects from the second model
- data is stored in 2 different tables


Uni-Directional
---------------
- @OneToMany annotation is used for uni-directional
- is placed on the main model:
    - example: (one) Course -> (many) Reviews
- it is recommended while deleting the main model to cascade it to the nested model

  .. code-block:: python
        :linenos:

        CREATE TABLE review(
            id int(11) NOT NULL AUTO_INCREMENT,
            comment vrchar(256) DEFAULT NULL,
            course_id int(11) DEFAULT NULL,

            PRIMARY KEY(id),
            UNIQUE KET `TITLE_UNIQUE` (`title`)

            KEY `FK_COURSE_idx` (`course_id`),
            CONSTRACONT `FK_COURSE` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
        )

  .. code-block:: python
        :linenos:

        @Entity
        @Table(name="review")
        public class Review{
            @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)
            @Column(name="id")
            private int id;

            @Column(name="comment")
            private String comment;

            // constructor, getters & setters
        }


  .. code-block:: python
        :linenos:

        @Entity
        @Table(name="course")
        public class Course{
            ....    
            @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
            @JoinColumn(name="course_id")
            private List<Review> reviews

            // constructor, getters & setters

            public void add(Review review) {
                if (reviews == null) {
                    reviews = new ArrayList<>();
                }
                reviews.add(review);
            }
        }

- deleting
    - to delete the main model, you need just to:
        - find the model by id
        - remove it

Bi-Directional
--------------
- example:
    - (one) Instructor -> (many) Course
    - (many) Course -> (one) Instructor
- it is not recommended to apply CascadeType.REMOVE
- in order to have access to references in both ways, it will be use @OneToMany and @ManyToOne annotations
- Many-To-One is the revers of On-To-Many:
    - used for Bi-Directional
    - is placed on the nested object (in our example: Course)
    - example:
        - (many) Course -> (one) Instructor
- you can set the fetch type to LAZY , EAGER by overriding value "fetch":
    - @OneToMany(fetch=FetchType.LAZY)
- default fetch type:
    - OneToMany => FetchType.LAZY
    - ManyToOne => FetchType.EAGER

  .. code-block:: python
        :linenos:

        CREATE TABLE course(
            id int(11) NOT NULL AUTO_INCREMENT,
            title vrchar(128) DEFAULT NULL,
            instructor_id int(11) DEFAULT NULL,

            PRIMARY KEY(id),
            UNIQUE KET `TITLE_UNIQUE` (`title`)

            KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
            CONSTRACONT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
        )


  .. code-block:: python
        :linenos:

        // instructor table can remain the same:
        CREATE TABLE `instructor_detil`(
            id int(11) NOT NULL AUTO_INCREMENT,
            first_name vrchar(45) DEFAULT NULL,
            lastname varchar(45) DEFAULT NULL
            email varchar(45) DEFAULT NULL
            instructor_detil_id int(11) DEFAULT NULL,

            PRIMARY KEY(id),

            CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail`(`id`)
        );


  .. code-block:: python
        :linenos:

        @Entity
        @Table(name="course")
        public class Course{
            @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)
            @Column(name="id")
            private int id;

            @Column(name="title")
            private String title

            @ManyToOne(cascade={CAscadeType.PERSISTE, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
            @JoinColumn(name="instructor_id")
            private Instructor instructor;

            // constructor, getters & setters
        }


  .. code-block:: python
        :linenos:

        @Entity
        @Table(name="instructor")
        public class Instructor{
            @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)
            @Column(name="id")
            private int id;

            @Column(name="first_name")
            private String firstName

            @Column(name="last_name")
            private String lastName

            @Column(name="email")
            private String email

            @OnetoOne
            @JoinColumn(name="instructor_detail_id")
            private InstructorDetail instructorDetail;

            @OneToMany(mappedBy="instructor"), fetch=FetchType.LAZY, cascade={CAscadeType.PERSISTE, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
            private List<Course> courses
            
            // constructor, getters & setters

            publc void add(Course course) {
                if (courses = null) {
                    courses = new ArrayList<>();
                }
                courses.add(course);
                course.setInstructor(this);
            }
        }

- Updating
    - to update operation on the main model or the nested models is the same as any other update operation:
        - retrieve the model (main , or nested)
        - set new fields
        - update using entityManager.merge()

- Deleting
    - to delete the nested model, you need to do:
        - find model by id
        - delete it 
    - to delete the main model, you need to do:
        - find model by id
        - break the associaton with nested objects 
        - delete it
    - in case you want to delete a main model which is having assocations, a SQL exception will be thrown
    - in case the operation is not cascaded to the nested object, all nested object will have NULL values to the column referring the first model id:
        - in our case, all courses referring the deleted Instructor will have NULL value for "instructor_id"

  .. code-block:: python
        :linenos:

        @Override
        @Transactional
        public void deleteInstructorById(int id) {
            Instructor instructor = entityManager.find(Instructor.class, id);
            List<Course> courses = instructor.getCourses();
            for (Course course : courses) {
                course.setInstructor(null); // break the association
            }
            entityManager.remove(instructor); //
        }



:ref:`Go Back <frameworks-java-jpa-mapping-label>`.