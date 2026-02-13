.. _postgresql-indexes-label:


PostgreSQL Indexes
==================
- index is used to search faster after different columns
- indexes are stored into separate table, with 2 columns: 
    - the column you index
    - with pointer to where the record in question is stored
- the index table lentght will be the same as the original table

- without index, the PostgreSQL will search the entire table for the result


Types of Postgresql Indexes
---------------------------
- B-tree index
- Hash index
- GIN index
- GiST index
- SP-GiST index
- BRIN index

B-tree index
------------
- is the default index type in PostgreSQL
- maintain the sorted values
- are efficient for exact matches and range queries

Hash index
----------
- maintain 32-bit hash code created from the values of the indexed columns
- can only handle simple equality comparisons

GIN index
---------
- are inverted indexes
- are suiteble for composite values, such as arrays, JSONB data and full-text search
- stores a separate entry for each component
- it can handle queries that cehck for the existence of a specific component

GiST index
----------
- are versatile and support wide range of data types, including geometric and full-text data
- allows various search strategies such as nearest-neighbor and partial match search

SP-SiST index
-------------
- are useful for indexing data with hierarchical structures or complex data types
- partition the index space into non-overlaping regions, oferring efficient search capabilities for specialized data strucutres

BRIN (Block Range Index) index
------------------------------
- are dsigned for very large tables where indexing every roq is impractical
- divides the table into ranges of pages and stores summarized information about each range
- are efficient for range queries on large datasets while using minimal space

:ref:`Go Back <postgresql-db-label>`.