.. _frameworks-java-spring-doc-general-label:

General
=======
- is used to generate API documentation
- it insepcts API endoints based on Spring configs, annotations, etc
- generates a Swagger web UI for accessing endpoints:
    - no need for other tools, like Postman
    - default url path: /swagger-ui/index.html
- dependency : springdoc-openapi-starter-webmvc-ui

- custom configurations:
    - custom path for html file:
        - springdoc.swagger-ui.path=/my-fun-ui.html

- you can retrieve API endpoints as JSON or YAML:
    - usefull for integration with other development tools
    - client SDK generation, APImocking, contract testing, etc
    - can be accessed from path:
        - JSON version: /v3/api-docs
        - YAML version: /v3/api-docs.yaml
    - you can override the custom path for documents:
        - springdoc.api-docs.path=/my-api-docs


Important
---------
- OpenAPI / Swagger does NOT currently work with Spring Data REST in Spring Boot 4
- OpenAPI / Swagger does work with regular @RestController based projects

    .. code-block:: python
        :linenos:

        public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
            // that's it ... no need to write any code!
        }
        // => the endpoint name will be "employees"



:ref:`Go Back <frameworks-java-spring-doc-label>`.