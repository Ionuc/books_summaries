.. _java-development-input-output-stream-open-options-label:

Open Options
============
- are used to write content to a file

StandardOpenOptions
-------------------
- available enum values implemeting OpenOptions
    - READ
        - open for read
    - WRITE
        - open for write
        - if file does not exists, it will be created
    - CREATE
        - create a file if it does not exist
    - CREATE_NEW
        - create a new file, failing if the file already exists
    - APPEND
        - append the date to the enf of the file
        - if the file does not exists, it will be created
    - TRUNCATE_EXISTING
        - if the file already existis and it is open for WRITE access, then its length is truncate to 0
        - will ignore if the file is open only for READ
        - the file is not create if it does not exists
    - SYNC
        - every update to the file's content or metadata be  written synchorunosuly to the underlying storage device immediately
        - it ensures that both the file content and metadata are synchronized with the underlying storage device
    - DSync
        - requires that every update to the file's content be written synchronously to the underlying storage device
         only data related updates will be written to the storage device, not necessarily the metadata, like:
            - timestamp
            - permision
            - etc



:ref:`Go Back <java-development-input-output-stream-label>`