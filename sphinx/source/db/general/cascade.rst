.. _db-general-cascade-label:

Cascade
=======
- you can cascade operations, meaning you can apply the same operation to related entities
- example:
    - save new rows: 
        - in case you have 2 tables: instructor -> instructor_details, if you save a row in instructor table it will also cascade that operation and apply the same operation to the instructor_details table
    - delete new rows:
        - in case you delete a row from instructor, it will cascade the operation and delete the row also from instructor_details.
        - this is known as "CASCADE DELETE"
        - in case for Many-to-Many relation, you should be carefull if you want to cascade the delete operation 

:ref:`Go Back <db-general-label>`.