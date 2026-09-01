.. _frameworks-java-hibernate-entity-lifecycle-label:

Entity Lifecycle
================

- operations:
    - Detach:
        - if entity is detached, it is not associated with a Hibernate session
    - Merge:
        - if instance is detached from session, then merge will reattach to session
    - Persiste:
        - transition new instances to managed state
        - next flush / commit will save in DB
    - Remove:
        - transition managed entity to be removed
        - next flush / commit will delete from db
    - Refresh:
        - reload / sync objects with data from db
        - prevents stale data


Session Method calls
--------------------
- save / persiste:
    - used to persist the object
    - will move the object from New / Trasient -> Persistent / Managed
- rollback / new:
    - will move the oject from Persistent / Managed -> New / Transient
- refresh:
    - sync object with informtion from DB
    - will keep the same state of Persistent / Managed
- commit/rollback/close
    - the object is not associated with Hibernate session
    - will move the object from Persistent / Managed -> Detached
- merge
    - will reattac the object to the Hibernate session
    - will move the object from Detached -> Persistent / Managed
- delete / remove
    - will move the object from Persistent / Managed -> Removed

States
-------
- New / Transient
    - is the state of the object first created with new keyword
    - or a Persistent / Manages object which was rollback
- Persistent / Managed:
    - after it was saved or persist
- First, 

:ref:`Go Back <frameworks-java-hibernate-label>`.