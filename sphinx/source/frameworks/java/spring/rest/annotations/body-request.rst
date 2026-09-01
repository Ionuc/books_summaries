.. _frameworks-java-spring-rest-annotations-body-request-label:

BodyRequest
===========
- used to process the data send as body
- Spring will extract the path variable from URL and create the Java object to be used as parameter for the bind method

    .. code-block:: python
        :linenos:

        @RestController
        @RequestMappings("/api")
        public class EmployeeRestController{
            
            @PostMapping("/employees")
            public Employee addEmployee(@RequestBody Employee theEmployee) {

                // also just in case they pass an id in JSON ... set id to 0
                // this is to force a save of new item ... instead of update

                theEmployee.setId(0);

                Employee dbEmployee = employeeService.save(theEmployee);

                return dbEmployee;
            }
        }


:ref:`Go Back <frameworks-java-spring-rest-annotations-label>`.