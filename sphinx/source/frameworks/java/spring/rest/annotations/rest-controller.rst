.. _frameworks-java-spring-rest-annotations-rest-controller-label:

RestController
==============
- is used to specify Spring that the component is a controller


.. code-block:: python
        :linenos:

        @RestController
        @RequestMapping("/api")
        public class EmployeeRestController {

            private EmployeeService employeeService;

            @Autowired
            public EmployeeRestController(EmployeeService theEmployeeService) {
                employeeService = theEmployeeService;
            }
        }



HTTP GET method
---------------
- Simple GET Method
    - it should return data without any side effect
    - annotation GetMapping is used
    - should be idenpotent


    .. code-block:: python
        :linenos:

        // expose "/employees" and return a list of employees
        @GetMapping("/employees")
        public List<Employee> findAll() {
            return employeeService.findAll();
        }


- HTTP GET method with PathVariable
    - it should return data without any side effect
    - annotation GetMapping is used
    - should be idenpotent


    .. code-block:: python
        :linenos:

        @GetMapping("/employees/{employeeId}")
        public Employee getEmployee(@PathVariable int employeeId) {

            Employee theEmployee = employeeService.findById(employeeId);

            if (theEmployee == null) {
                throw new RuntimeException("Employee id not found - " + employeeId); // will be handled by the glovan exception handler
            }

            return theEmployee;
        }


HTTP POST method
----------------
- used to create a new resource
- annotation PostMapping is used
- data from request body is retrieved using method parameter having annotation @RequestBody
- is NOT idenpotent


    .. code-block:: python
        :linenos:

        @PostMapping("/employees")
        public Employee addEmployee(@RequestBody Employee theEmployee) {

            // also just in case they pass an id in JSON ... set id to 0
            // this is to force a save of new item ... instead of update

            theEmployee.setId(0);

            Employee dbEmployee = employeeService.save(theEmployee);

            return dbEmployee;
        }


HTTP PUT method
---------------
- should replace existing model with given fields (remove other fields which were not set)
- annotation PutMapping is used
- data from request body is retrieved using method parameter having annotation @RequestBody
- should be idenpotent

    .. code-block:: python
        :linenos:

        @PutMapping("/employees")
        public Employee updateEmployee(@RequestBody Employee theEmployee) {

            Employee dbEmployee = employeeService.save(theEmployee);

            return dbEmployee;
        }


HTTP PATCH method
-----------------
- should update existing model with given fields (other fields should not be alterated)
- annotation PatchMapping is used
- data from request body is retrieved using method parameter having annotation @RequestBody
- should be idenpotent
- JsonMapper from Jackson library is used to:
    - convert JAva objects to JSON and vice-versa
    - merge of JSON nodes
    - provides type safety for conversions: JAva <-> JSON
    - is already preconfigured by Spring Boot, meaning it can be injected in any component

    .. code-block:: python
        :linenos:

        @RestController
        @RequestMapping("/api")
        public class EmployeeRestController {

            private EmployeeService employeeService;
            private JsonMapper jsonMapper;

            @Autowired
            public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper) {
                employeeService = theEmployeeService;
                jsonMapper = theJsonMapper;
            }

            @PatchMapping("/employees/{employeeId}")
            public Employee patchEmployee(@PathVariable int employeeId,
                    @RequestBody Map<String, Object> patchPayload) {

                // Step 1: Retrieve the existing employee from database
                Employee tempEmployee = employeeService.findById(employeeId);

                if (tempEmployee == null) {
                    throw new RuntimeException("Employee id not found - " + employeeId);
                }

                // Step 2: Security check - prevent ID modifications
                // The ID should never change, so reject any attempts to modify it
                if (patchPayload.containsKey("id")) {
                    throw new RuntimeException(
                            "Employee id cannot be modified. Remove 'id' from request body.");
                }

                // Step 3: Apply the partial update
                // This creates a NEW employee object with the updates applied
                Employee patchedEmployee = jsonMapper.updateValue(tempEmployee, patchPayload);

                // Step 4: Save the updated employee to database and return it
                Employee dbEmployee = employeeService.save(patchedEmployee);

                return dbEmployee;
            }
        }


- if you need to patch nested objects or more complex fields, you can use  RFC editor:
    - RFC 6902 - JSON Patch
    - RFC 7386 - JSON Merge Patch


HTTP DELETE method
------------------
- should delete existing model
- annotation DeleteMapping is used
- should be idenpotent

    .. code-block:: python
        :linenos:

        @DeleteMapping("/employees/{employeeId}")
        public String deleteEmployee(@PathVariable int employeeId) {

            Employee tempEmployee = employeeService.findById(employeeId);

            // throw exception if null

            if (tempEmployee == null) {
                throw new RuntimeException("Employee id not found - " + employeeId);
            }

            employeeService.deleteById(employeeId);

            return "Deleted employee id - " + employeeId;
        }


:ref:`Go Back <frameworks-java-spring-rest-annotations-label>`.