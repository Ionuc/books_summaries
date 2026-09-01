.. _db-general-keys-label:

DB Keys
========

Primary Key
-----------
- identify a unique row in a table

Foreign Key
-----------
- link tables together
- is a field in one tables that refers to primary key in another table
- main purpose is to preserver relationship between tables
    - referential integrity
- prevents operations that would destroy relationship
- ensures only valid data is inserted into the foreign key column:
    - con only contain valid reference to primary key in other table, else error will be thrown
- example:
    - table instructor_detail:
        - id -> int
        - youtube_channel -> varchar
        - hobby -> varchar
    - table instructor:
        - id -> in
        - first_name -> varchar
        - last_name -> varchar
        - instructor_detail_id -> int (THIS IS THE FOREIGN KEY)

:ref:`Go Back <db-general-label>`.