.. _frameworks-java-spring-rest-exception-handler-label:

Exception Handler
=================

Handling custom exception
-------------------------
- is used to handle different exception thrown by the server while processing requests
- ExceptionHandler annotation is used
- will return an ResponseEntity, which can provide:
    - HTTP status code
    - HTTP headers
    - ResponseBody

Steps
-----
- 1. create custom error response class

    .. code-block:: python
        :linenos:

        public record StudentErrorResponse(int status, String message, long timeStamp) {
        }


- 2. create custom exception


.. code-block:: python
        :linenos:

        public class StudentNotFoundException extends RuntimeException{
            public StudentNotFoundException(String message) {
                super(message);
            }
        }

- 3. Update REST service to throw exception

    .. code-block:: python
        :linenos:

        @RestController
        @RequestMappings("/api")
        public class StudentRestController{

            @GetMapping("/students/{studentID}")
            public Student getStudent(@PathVariable int studentId) {
                List<Student> students = new ArrayList<>();
                ...
                if (studentId >= students.size() || studentId < 0) {
                    throw new StudentNotFoundException("Student id not found " + studentId);
                }
                return students.get(studentId);
            }
        }

- 4 Add exception handler method

    .. code-block:: python
        :linenos:

        @RestController
        @RequestMappings("/api")
        public class StudentRestController{

            @ExceptionHandler
            public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {
                StudentErrorResponse error = new StudentErrorRespnse(
                    HttpStatus.NOT_FOUND.value(), ex.getMessage, System.currentTimeMillis());

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            @GetMapping("/students/{studentID}")
            public Student getStudent(@PathVariable int studentId) {
                List<Student> students = new ArrayList<>();
                ...
                if (studentId >= students.size() || studentId < 0) {
                    throw new StudentNotFoundException("Student id not found " + studentId);
                }
                return students.get(studentId);
            }
        }


Handling all Exceptions
-----------------------
- you can create an ExceptionHandler for generic Exception for a specific controller

    .. code-block:: python
        :linenos:

        @RestController
        @RequestMappings("/api")
        public class StudentRestController{

            @ExceptionHandler
            public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
                StudentErrorResponse error = new StudentErrorRespnse(
                    HttpStatus.BAD_REQUEST.value(), ex.getMessage, System.currentTimeMillis());

                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            @GetMapping("/students/{studentID}")
            public Student getStudent(@PathVariable int studentId) {
                List<Student> students = new ArrayList<>();
                ...
                if (studentId >= students.size() || studentId < 0) {
                    throw new StudentNotFoundException("Student id not found " + studentId);
                }
                return students.get(studentId);
            }
        }


Handling Global Exceptions
--------------------------
- this handler will be used in all controllers without duplicating logic on each controller
- @ControllerAdvice is used, similar to an interceptor / filter
- it can be used:
    - to pre-process requests to controllers
    - post-process resonses to handle exceptions
- all global exception handlers can be moved from independent controller to the new ControllerAdvice class

    .. code-block:: python
        :linenos:

        @ControllerAdvice
        public class StudentRestExceptionHandler{

            @ExceptionHandler
            public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {
                StudentErrorResponse error = new StudentErrorRespnse(
                    HttpStatus.BAD_REQUEST.value(), ex.getMessage, System.currentTimeMillis());

                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            @ExceptionHandler
            public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
                StudentErrorResponse error = new StudentErrorRespnse(
                    HttpStatus.BAD_REQUEST.value(), ex.getMessage, System.currentTimeMillis());

                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

        }



:ref:`Go Back <frameworks-java-spring-rest-label>`.