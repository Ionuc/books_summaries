.. _frameworks-java-spring-data-rest-general-label:

General
=======
- provided by dependency: "spring-boot-starter-data-rest"
- used to expose CRUD endpoints for a given JPA Repository
- minimuze boler-plate REST code
- will expose methods for endpoints:
    - POST:
        - create new resource
        - endpoint: "/<name>"
    - GET ALL:
        - read all resources
        - endpoint: "/<name>"
    - GET Single:
        - read single resource
        - endpoint: "/<name>/{id}"
    - PUT:
        - update resource
        - endpoint: "/<name>/{id}"
    - DELETE:
        - removing resource
        - endpoint: "/<name>/{id}"
        - will return empty body with statuc 204

- will use only the id passed as PathVariale. Id set on the body will be ignored

How is working
--------------
- Spring Data REST will scan your project for JpaRepository
- Expose REST APIs for each entity type for your JpaRepository
- for the api <name>, it will use simple pluralize form:
    - first character of Entity type is lowercase
    - then just adds an "s" to the name


    .. code-block:: python
        :linenos:

        public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
            // that's it ... no need to write any code!
        }
        // => the endpoint name will be "employees"


- Spring DATA Rest will make Rest Controller to depend directly on JpaRepositories, without the "Service" layer:
    - no need for Rest controller implementations
    - no need for Service interface & implementation
    - it is needd only the depedendency

- Spring Data Rest will need only 3 items:
    - your Entity
    - JpaRepository
    - POM dependency for: "spring-boot-started-data-rest"

Response data
-------------
- the response is in HATEOAS format
- the response will contain the corresponding entity or entities data, but will contains also some metadata information:
    - will include also "_links" -> "self" & "<name>"
    - for a response of type collection it will include: page size, total elements, pages, etc


Customize base endpoint
-----------------------
- spring config "spring.data.rest.base-path" will be used to update all apis:
    - old: "/enployees"
    - new: "/magic-api/employees"

Configurtions
-------------
- overriding default resource name
    - add new annotation @RepositoryRestResource(path="<value>")

    .. code-block:: python
        :linenos:

        @RepositoryRestResource(path="members")
        public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
            // that's it ... no need to write any code!
        }
        // => the endpoint name will be "/members"

- pagination:
    - default value of page size = 20;
    - default starting page is 0
    - to override the page values, set servers properties:
        - default size of pages:
            - spring.data.rest.default-page-size=<value>
        - maximum size of pages:
            - spring.data.rest.max-page-size=<value>

- sorting:
    - you can you predefine sorting algorith by passing sorting values to the endpoint:
        - sort by last name(ascending is default):
            - /employee?sort=lastName
        - sort by first name, descending:
            - /employee?sort=firstName,desc
        - sort by multiple fieds, like last name and then first name, ascending:
            - /mployee?sort=lastName,firstName,asc

Advance features
----------------
- pagination, sorting and searching
- extending and adding custom queries with JPQL
- Query Domain Specific Language (Query DSL)

:ref:`Go Back <frameworks-java-spring-data-rest-label>`.