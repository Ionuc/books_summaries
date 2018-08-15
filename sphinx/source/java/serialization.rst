.. _serialization:

Serialization
=============
    - java has build-in ability to persist objects
        - store from runtime into a byte stream
        - restore from bytestream into runtime
    - by default it stores only instance variable, but it can be customized to store static
    - objects can be saved to :
        - file system
        - database:
            - Real Database Management System (RDBMS) can store as blob
            - Object Oriented Database Management System (OODBMS) can store directly
    - refers to thinks:
        - serializing -> storing an object-graph to a byte stream
        - deserializing -> restoring an object-graph from a byte stream

    - if a type is serializable, then all its members needs to be serializable
    - primitives are by default serializable

Serialization types
-------------------
    - Serializable:
        - implemented by serializable types
        - indicates that type supports serialization
        - has no methods ( is a marker interface )
    - ObjectOutputStream & ObjectInputStream
        - used to serialize / deserialize types

Class Version Incompatibility
-----------------------------
    - it is computed a serial version unique identifier which represents the secure hash value that identifies the structure of the class
    - that value:
        - is stored in the byte stream with the corresponding object
        - indicates version compatibility (compatible version have same value)
    - on restoring the oobject graph, it is computed again the hash value for that class. If the values are different, then InvalidClassException is thrown
    - we can specify the serial version unique identifier: serialVersionUID field which must be long, static, final. It should be private

Customizing Serialization
-------------------------
    - needs to add methods:
        - writeObject(ObjectOutputStream out)
        - readObject(ObjectInputStream in)

Transient Fields
----------------
    - are fields which should not be serializable
    - have the modifier : transient

:ref:`Go Back <java-label>`.