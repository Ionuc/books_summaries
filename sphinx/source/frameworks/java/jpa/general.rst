.. _frameworks-java-jpa-general-label:

JPA
===
- is previously known as Java Persistence API
- is a standard for Object-to-Relational-Map
- is only a specification
    - defines a set of interfaces
    - requires an implementtion to be usable

Implementations
---------------
- there are multiple implementatoion of JPA Spec:
    - Hibernate
    - EclipseLink

Benefits
--------
- By having a standard API, you are not locked to vendor's implementatins:
    - you can switch easily to other implementation by changing the configuration


Generate DB table
-----------------
- JPA provides an option to automatically create dtabase tables based on Java annotations
- useful for development and testing
- it will create the corresponding sql statements and will execute them on DB
- to enable this feature, you neet to set a config file:
    - spring.jpa.hibernate.ddl-auto=create
- it will drop existing tables and create them based on Java annotations

- existing values for key "spring.jpa.hibernate.ddl-auto":
    - none:
        - no action will be performed
    - create:
        - tables are dropped and created
        - all previous data is lost, the DB will contain only new data
    - create-drop:
        - tables are dropped followed by tables creation
        - on application shutdown, tables are dropped
        - used for unit tests
    - validate:
        - validate database table schema
    - update:
        - update the database table schema
        - if you provide new fields on the entity, it will update the corresponding table
        - old tables will keep the values

    .. code-block:: python
        :linenos:

        # Add logging configs to display SQL statements
        logging.level.org.hibernate.SQL=debug
        logging.level.org.hibernate.orm.jdbc.bind=trace

        # Configure JPA / Hibernate to auto create the table
        spring.jpa.hibernate.ddl-auto=create


:ref:`Go Back <frameworks-java-jpa-label>`.