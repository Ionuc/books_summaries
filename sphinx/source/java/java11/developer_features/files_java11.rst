.. _java11_files:

Files
======

    - new methods were added to Files: readString() and writeString()

    .. code-block:: python
           :linenos:

            Path filePath = Files.writeString(Files.createTempFile(tempDir, "demo", ".txt"), "Sample text");
            String fileContent = Files.readString(filePath);
            assertThat(fileContent).isEqualTo("Sample text");


:ref:`Go Back <java11-label>`.