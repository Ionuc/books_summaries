.. _frameworks-java-spring-rest-data-binding-jackson-label:

Jackson Data Binding
====================
- Data binding is the process of converting JSON data to a JAVA POJO and viceversa
- also known as:
    - serialization / deserialization
    - marshalling / unmarshelling
- is used by default by Spring
- by default, Jackson will call appropriate getter / setter methods in order to convert from JSON to Java or vice-versa

JSON to Java Pojo
-----------------
- Jackson will call only the setter methods
- will NOT access directly the private fields
- will create the setter method based on the key name:
    "key": 14 => will call setKey(int)

Java POJO to JSON
-----------------
- Jackson will call teh appropriate getter methods

Integration with Spring
-----------------------
- is used automatically by Spring framework by Spring REST component
- JSON data passed to REST controller is converted to POJO
- Java objects returned from REST controller is converted to JSON


    .. code-block:: python
        :linenos:

        @RestController
        @RequestMappings("/api")
        public class StudentRestController{
            @GetMapping("/students")
            public List<Student getStudents() {
                List<Student> students = new ArrayList<>();
                students.add(new Student("poornima", "Patel"));
                return students;
            }
        }

        // /api/students => will retrn {"students":[{"firstName": "poornima", "lastName":"Patel"}]}


Annotations
-----------
- common annotations:
    - @JsonProperty:
        - rename a field in JSON
    - @JsonIgnore:
        - ignore a field during serialization / deserialization
    - @JsonInclude:
        - include / exclude fields based on conditions
    - @JsonFormat:
        - define format for data/time fields
    - @JsonIgnoreProperties:
        - ignore multiple fields




    .. code-block:: python
        :linenos:


        @JsonInclude(JsonInclude.Include.NON_NULL) // skips null values
        public class User {

            private Integer id;

            @JsonProperty("full_name") // rename JSON field key
            private String name;

            @JsonIgnore // exclude field
            private String password;

            @JsonFormat(pattern = "yyyy-MM-dd") // format field
            private LocalDate dob;

            private String email;

            public User() {}

            public User(Integer id, String name, String password, LocalDate dob, String email) {
                this.id = id;
                this.name = name;
                this.password = password;
                this.dob = dob;
                this.email = email;
            }
            // getters & setters
        }

        // result of a simple GET:
        [{"id": 1,"dob":"1998-05-20","email":"ionut.mesaros@gmail.com","full_name":"Ionut Mesaros"}]


:ref:`Go Back <frameworks-java-spring-rest-data-binding-label>`.