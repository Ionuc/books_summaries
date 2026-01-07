.. _java11_dynamic_class_file_constant:

Dynamic Class-File Constant
===========================

    - java class-file format is extended to support a new constant-pool form named CONSTANT_Dynamic.
    - Loading the new constant-pool will delegate creation to a bootstrap method, just as linking an invokedynamic call site delegates linkage to a bootstrap method.
    - this feature enhances performance and targets language designers and compiler implementors.



:ref:`Go Back <java11-performance-features-label>`.