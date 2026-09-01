.. _db-general-fetch-types-label:

Fetch Types
===========

- there are 2 types of loading data:
    - eager: will retrieve everything
    - lazy: will retrieve on request

Eager loading
-------------
- will retrieve everything related to the row
- example: tables (one) instructor -> (many) course
    - in case you retrieve one instructor, it will retrieve also all the related courses 

Lazy Loading
------------
- will retrieve only needed rows without the rows linked with FOREIGN_KEYs
- example: tables (one) instructor -> (many) course
    - in case you retrieve one instructor, it will NOT retrieve also all the related courses
    - the related course rows will be retrieved on dedicated requests

:ref:`Go Back <db-general-label>`.