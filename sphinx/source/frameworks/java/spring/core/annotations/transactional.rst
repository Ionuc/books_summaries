.. _frameworks-java-spring-core-annotation-transactional-label:

Transactional
=============
- it automatically starts a new transaction for your JPA code
- it can be added on method


    .. code-block:: python
        :linenos:

        @Override
        @Transactional
        public void save(Student theStudent) {
            entityManager.persist(theStudent);
        }


:ref:`Go Back <frameworks-java-spring-core-annotation-label>`.
