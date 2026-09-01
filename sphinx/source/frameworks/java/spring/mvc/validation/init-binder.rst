.. _frameworks-java-spring-mvc-validation-init-binder-label:

InitBinder
==========
- works as pre-processor
- it will pre-process each web request to our controller, like removing leading and trailing white spaces
- method annotated with @InitBinder is executed

- example of removing leading and trailing white spaces to all String values:
    - new StringTrimmerEditor(true) => true value will trim to null value if value has only white spaces


    .. code-block:: python
        :linenos:

        @Controller
        public class CustomerController {
            ....

            @InitBinder
            public void initBinder(WebDataBinder dataBinder) {
                StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
                dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
            }
        }


:ref:`Go Back <frameworks-java-spring-mvc-validation-label>`.