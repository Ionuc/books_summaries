.. _frameworks-java-spring-rest-annotations-request-param-label:

RequestParam
============
- used to process a url and to extract query params from it
- example of path variable:
    - /api/students?page=2&studentName=Ionut => should retrieve a single student based on the ID


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

            @GetMapping("/students")
            public List<Student> getStudent(@RequestParam("studentName") String studentName, @RequestParam("page") int page) {
                // return filtered students
            }
        }


:ref:`Go Back <frameworks-java-spring-rest-annotations-label>`.