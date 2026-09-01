.. _frameworks-java-spring-mvc-validation-form-validation-label:

Form Validation
===============
- you perform validation on controller, which will perform some validation and return:
    - the next page if success
    - or same page, but with the model having the exception
- After that, the page can render the exception:

- model validation on field "lastName":

    .. code-block:: python
        :linenos:

        public class Customer {
            private String firstName;
            
            @NotNull("message" = "is required")
            @Size(min=1, message = "is required")
            private String lastName;
        }


- controller validation:
    - @Valid is used to tell Sprin MVC to execute java validation rules defined on field properties
    - result of validation is return in the BindingResult instance


    .. code-block:: python
        :linenos:

        @GetMappint("/")
        public void showForm(Model model) {
            model.addAttribute("customer", new Customer());
            return "customer-form";
        }

        @PostMapping("/processForm")
        public String processForm(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindintResult
        ) {
            if (the bindingResult.hasErrors()) {
                return "customer-form"; // go back to the form
            }
            return "customer-confirmation"; // go to next page
        }


- template rendaring

    .. code-block:: python
        :linenos:

        <!DOCTYPE HTML>
        <html xmlns:th="http://www.thymeleaf.org">

        <head>
            <meta charset="UTF-8">
            <title>Hello World - Input Form</title>
        </head>
        <body>

            <form th:action="@{/processForm}" th:object="${customer}" method="POST">
                First name: <input type="text" th:field="*{firstName}"/>
                <br><br>
                Last name: <input type="text" th:field="*{lastName}"/>
                <br><br>

                <!-- Show error message if present -->
                <span th:if="${#fields.hasError('lastName')}" th.errors="*{lastName}"></span>
                <input type="submit" value="Submit"/>
            </form>

        </body>
        </html>


:ref:`Go Back <frameworks-java-spring-mvc-validation-label>`.