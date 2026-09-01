.. _frameworks-java-jpa-mapping-many-to-many-label:

Many-to-Many Mapping
====================
- one object from The first model is link to multiple objects from second model, but also one object from second model can be linked to multiple objects from first model
- data is stored in 2 different tables
- example:
    - Course -> (many) Student
    - Student -> (many) Courses

- default fetch type:
    - ManyToMany => FetchType.LAZY

Join Table
----------
- Join tables are used to keep the relantions between linked tables
- is a table that provides a mapping between 2 tables
- it has foreign keys for each table to define the mapping relationship

- JoinTable is used to do the links:
    - tells Hiernate to look direct column name and inverse column name
    - will use the foreign keys defined in the join table
- for Bi-Directional
    - either side may be designated as the owning side
    - the non-owning side must use the "mappedBy" element of the @ManyToMany annotation
        - tells Hibernate to look on the field name from owning model and to use the @JoinTable information associated with that field

  .. code-block:: python
        :linenos:

        CREATE TABLE course_student(
            course_id int(11) NOT NULL,
            student_id int (11) not null,
            PRIMARY KEY(course_id, student_id)

            CONSTRAINT 'FK_COURSE' FOREIGN KEY ('course_id') REFERENCES 'course' ('id'),
            CONSTRAINT 'FK_STUDENT' FOREIGN KEY ('student_id') REFERENCES 'student' ('id')
        )


  .. code-block:: python
        :linenos:

        @Entity
        @Tale(name="course")
        pulic class Course {
            ....
            @ManytoMany(
                fetch=FetchType.LAZY,
                cascade={CascadeType.PERSISTE, CascadeType.MERGE}
            )
            @JoinTable(
                name="source_student",
                joinColumns=@JoinColumn(name="course_id"),
                inverseJoinColumns=@JoinColumn(name="student_id")
            )
            private List<Student> students;

            public void addStudent(Student student) {
                if (students == null) {
                    students = new ArrayList<>();
                }
                students.add(student);
            }
        }


  .. code-block:: python
        :linenos:

        @Entity
        @Tale(name="course")
        pulic class Student {
            ....
            @ManytoMany(
                mappedBy="students", // refers to the "student" property in the Course class
                fetch=FetchType.LAZY,
                cascade={CascadeType.PERSISTE, CascadeType.MERGE}
            )
            private List<Course> courses;

            public void addCourse(Course course) {
                if (courses == null) {
                    courses = new ArrayList<>();
                }
                courses.add(course);
                course.addStudent(this); // we add also on the other side
            }
        }


- deleting
    - deleting the model of owning side is simply:
        - find the model by id
        - remove it
    - deleting the modul of the other-side:
        - find the model by id
        - remove associations
        - remove it


  .. code-block:: python
        :linenos:

        public class AppDAOImpl {
            ...
            @Override
            @Transactional
            public void deleteStudentById(int id) {
                Student student = entityManager.find(Student.class, id);
                if (Student student != null) {
                    List<Course> courses = student.getCourses();
                    for (Course course : courses) {
                        course.getStudents.remove(student);
                    }
                    entityManager.remove(student);
                }
            }
        }


:ref:`Go Back <frameworks-java-jpa-mapping-label>`.