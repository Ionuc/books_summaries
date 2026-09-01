.. _frameworks-java-jpa-entityc-class-label:

Entity class
============
- the class must have @Entity annotation
- the class must have a public or protected no-argument constrcutor:
    - the class can have other constructors

Steps to map to a table
-----------------------
1. Map the java class to the table name using @Table annotations:
    - in case @Table annotation is not provided, class name will be used 
    - is recommended to provide @Table name
2. Map each field class to the table column using @Column annotation:
    - in case @Column name is not provided, the field name will be used. 
    - is recommended to provide @Column name
3. The ID field should have also:
    - @Id annotation
    - @GeneratedValue to generate new value when inserting

Primary Key
-----------
- uniquely identifies each row in a table
- must e a unique value
- cannot contain NULL values

- at the DB level it can be set to be AUTO_INCREMENT in order to generate new values

  .. code-block:: python
        :linenos:

        CREATE TABLE student(
            id int NOT NULL AUTO_INCREMENT,
            first_name vrchar(45) DEFAULT NULL,
            lastname varchar(45) DEFAULT NULL
            email varchar(45) DEFAULT NULL
            PRIMARY KEY(id)
        )


- at JPA level, it needs to be specified using:
    - @Id annotation
    - @GeneratedValue annotation


ID Generation Strategies
------------------------
- GenerationType.AUTO: pick an appropriate strategy for the particular database
- GenerationType.IDENTITY: assign primary keys using database identity column
- GenerationType.SEQUENCE: assign primary keys using a database sequence
- GenerationType.TABLE: assign primary keys using underlyinh database tabl to ensure uniqueness
- GenerationType.UUID: assign primary keys using a globally unique identifier (UUID) to ensure uniqueness

Custom Generation Strategy
--------------------------
- you can create a custom implementation of org.hibernate.id.IdentifierGenerator
- override the method: public Serializable generate()

Example of Entity
-----------------

    .. code-block:: python
        :linenos:

        @Entity
        @Table(name="student")
        public class Student {

            // define fields
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="id")
            private int id;

            @Column(name="first_name")
            private String firstName;

            @Column(name="last_name")
            private String lastName;

            @Column(name="email")
            private String email;

            // define constructors
            public Student() {

            }

            public Student(String firstName, String lastName, String email) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
            }

            // define getters/setters

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }


            // define toString() method

            @Override
            public String toString() {
                return "Student{" +
                        "id=" + id +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", email='" + email + '\'' +
                        '}';
            }
        }



:ref:`Go Back <frameworks-java-jpa-label>`.