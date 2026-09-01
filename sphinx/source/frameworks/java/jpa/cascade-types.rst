.. _frameworks-java-jpa-cascade-types-label:

Cascade Types
=============
- PERSIST:
    - if entity is persited / saved, related entitiy will also be persisted
- REMOVE:
    - if entity is removed / deleted, related entity will also be deleted
- REFRESH:
    - if entity is refreshed, related entity will also be refreshed
- DETACH:
    - if entity is detched (not associated with session), then related entity will also be detached
- MERGE:
    - if entity is merged, then related entity will also be merged
- ALL:
    - all actions will be applied to the related entity



:ref:`Go Back <frameworks-java-jpa-label>`.