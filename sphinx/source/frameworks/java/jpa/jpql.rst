.. _frameworks-java-jpa-jpql-label:

JPA Query Language (JPQL)
=========================
- query language for retrieving objects
- is based on entity name and entity fields, while SQL is based on table names and table columns
- similar in concept to SQL:
    - where
    - lile
    - order by
    - join
    - in
    - etc
- named parameter:
    - is like sql parameter biding:
    - starts with ":"
    - used to create the template and to bind the label to the actual value


    .. code-block:: python
        :linenos:

        TypedQuery<Student> theQuery = entityManager.createQuery(
                                            "select s FROM Student s WHERE s.lastName=:theData", Student.class);

        // set query parameters
        theQuery.setParameter("theData", theLastName);


    - the "select" clause is required for Strict JPQL, 
        - the Hibernate implementation is lenient and allows Hibernate Query Language (HQL) where "select is not required

    .. code-block:: python
        :linenos:

        TypedQuery<Student> theQuery = entityManager.createQuery(
                                            "select s FROM Student s WHERE s.email like '@ionut.mesaros@yahoo.com'", Student.class); // S is reference to the entity

        // set query parameters
        theQuery.setParameter("theData", theLastName);


- ordering:
    - you can return a list of entities order by a field or list of fields
    - in case not specify. default ordering is ascending (asc)
    - you can order also on descending (desc)


    .. code-block:: python
        :linenos:

        TypedQuery<Student> theQuery = entityManager.createQuery(
                                            "select s FROM Student s order by s.lastName asc", Student.class);

        // set query parameters
        theQuery.setParameter("theData", theLastName);


:ref:`Go Back <frameworks-java-jpa-label>`.