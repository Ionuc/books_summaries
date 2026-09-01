.. _frameworks-java-spring-rest-annotations-path-variables-label:

PathVariables
==============
- used to process a url and to extract diferent identifiers from it
- example of path variable:
    - /api/students/{studentId} => should retrieve a single student based on the ID
    - usages : /api/students/0 => should retrieve student with ID = 0
- Spring will extract the path variable from URL and create the Java object to be used as parameter for the bind method

    .. code-block:: python
        :linenos:

        @RestController
        @RequestMappings("/api")
        public class StudentRestController{
            @GetMapping("/students")
            public List<Student> getStudents() {
                List<Student> students = new ArrayList<>();
                students.add(new Student("poornima", "Patel"));
                return students;
            }

            @GetMapping("/students/{studentID}")
            public Student getStudent(@PathVariable int studentId) {
                Map<Integer, Student> students = new HashMap<>();
                ...
                return students.get(studentId);
            }
        }


:ref:`Go Back <frameworks-java-spring-rest-annotations-label>`.