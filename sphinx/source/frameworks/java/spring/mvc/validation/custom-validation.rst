.. _frameworks-java-spring-mvc-validation-custom-validation-label:

Custom Validation
==================
- you can create a new annotation to perform a custom validation on the field
- steps:
    - 1) create the new annotation => @CourseCode
    - 2) create the corresponding ConstraintValidator => CourseCodeConstraintValidator


1. create annotation:
    - @Constrinat => helper class to performe the business logic
    - @Target => used to specify in which this annotation will be used
    - @Retention => specify when this will be used

    - groups() & payload() are used to return just the default values

    .. code-block:: python
        :linenos:

        @Constraint(validationBy = CourseCodeConstraintValidator.class)
        @Target({ElementType.METHOD, ElementType.FIELD})
        @Retention(RetentionPolicy.RUNTIME)
        public @interface CourseCode {
            public String value() default "Ionut";
            public String message() default "must start with Ionut";

            public Class<?>[] groups() default {};
            public class<? extends Payload>[] payload() default {};
        }


2. Create ConstraintValidator:
    - 


    .. code-block:: python
        :linenos:

        public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
            private String coursePrefix;

            @Overide
            public void initialize(CourseCode courseCode) {
                coursePrefix = courseCode.value();
            }

            @Override
            public voolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
                boolean result;
                if (code != null) {
                    code = code.startWith(coursPrefix);
                } else {
                    restul = true;
                }
                return result;
            }
        }


3. Use @CourseCode


    .. code-block:: python
        :linenos:

        public class Customer {
            private String firstName;
            
            @CourseCode(value="Ionut", message="message should start with Ionut")
            private String courseCode;
        }


:ref:`Go Back <frameworks-java-spring-mvc-validation-label>`.