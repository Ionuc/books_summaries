.. _frameworks-java-jpa-directional-label:

Directional relationship
========================
- refers to how to access rows linked by foreign keys

Uni-Directional
---------------
- it refers that you need the object of the  first model to access the object from the the second model

- example: you have entities: Instructor -> InstructorDetail
    - you can have the reference of InstructorDetails only by loading first the Instructor

Bi-Directional
--------------
- you can have access to objects linked by FOREIGN_KEY in both directions

- example: you have entities: Instructor <-> InstructorDetail
    - you can have the reference of InstructorDetails by loading first the Instructor
    - you can also have the reference of Instructor by loading first the InstructorDetail

:ref:`Go Back <frameworks-java-jpa-label>`.