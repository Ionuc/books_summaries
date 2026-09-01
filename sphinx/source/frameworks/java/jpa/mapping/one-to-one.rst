.. _frameworks-java-jpa-mapping-one-to-one-label:

One-to-One Mapping
==================
- one object from a the first model is directly link to only one object from the second model
- data is stored in 2 different tables
- example: Instructor -> (one) InstructorDetail
- default fetch type:
    - OneToOne => FetchType.EAGER

Uni-directional
---------------
- DB table example


  .. code-block:: python
        :linenos:

        CREATE TABLE `instructor_detail`(
            id int(11) NOT NULL AUTO_INCREMENT,
            youtube_channel vrchar(128) DEFAULT NULL,
            hobby varchar(45) DEFAULT NULL,
            PRIMARY KEY (id)
        );

        CREATE TABLE `instructor_detil`(
            id int(11) NOT NULL AUTO_INCREMENT,
            first_name vrchar(45) DEFAULT NULL,
            lastname varchar(45) DEFAULT NULL
            email varchar(45) DEFAULT NULL
            instructor_detil_id int(11) DEFAULT NULL,

            PRIMARY KEY(id),

            CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail`(`id`)
        );


- Java entity example


  .. code-block:: python
        :linenos:

        @Entity
        @Table(name="instructor_detail")
        public class InstructorDetail{
            @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)
            @Column(name="id")
            private int id;

            @Column(name="youtube_channel")
            private String youtubeChannel

            @Column(name="hobby")
            private String hobby

            // constructor, getters & setters
        }

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
            
            // constructor, getters & setters
        }


Setting Cascade type
--------------------
- you can specify the cascade type directly on the annotation the list of cascade values
- by default, no operation will cascade


  .. code-block:: python
        :linenos:

        @Entity
        @Table(name="instructor")
        public class Instructor{
            ....
            @OneToOne(cascade=CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE)
            @JoinColumn(name="instructor_detail_id")
            private InstructorDetail instructorDetail;
            ...
        }

        @Repository
        public class AppDAOImpl implements AppDAO {
            private EntityManager entityManager
            @Autowire
            public AppDAOImpl(EntityManager entityManager) {
                this.entityManager = entityManager;
            }

            @Override
            @Transactional
            public void save(Instructor instructor) {
                entityManager.persiste(instructor) // will save also the InstructorDetails
            }

            @Override
            public void findInstructorById(int id) {
                entityManager.find(Instructor.class, id) // will retrieve also the InstructorDetails
            }
        }

Bi-Directional
--------------
- is the case when you want to access each models from the other model
- in our case, to load:
    - an Instructor and have the reference to InstructorDetail
    - an InstructorDetail and have the reference to Instructor
- to use Bi-Directional, you can keep the existing database schema:
    - no changes required to database, with the foreign keys set as uni-directional
- it is just needed to be added the Instructor field to the InstructorDetail with the @OneToOne annotation:
    - should be used the "mappedBy" property of OneToOne annotation with the value as the field name from Instructor entity
    - so Hibernate will use the value from mappedy() to look in  the first model at the "JoinColumn" to figure the associated model (Instructor)
    - you can set alse the CascadeType which will cascade action from InstructorDetail to the Instructor


    .. code-block:: python
        :linenos:

        @Entity
        @Table(name="instructor_detail")
        public class InstructorDetail{
            @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)
            @Column(name="id")
            private int id;

            @Column(name="youtube_channel")
            private String youtubeChannel

            @Column(name="hobby")
            private String hobby

            @OneToOne(mappedBy="instructorDetail", cascade=CascadeType.All)
            private Instructor istructor;

            // constructor, getters & setters
        }

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
            
            // constructor, getters & setters
        }


:ref:`Go Back <frameworks-java-jpa-mapping-label>`.
triggre 
blue green deploment