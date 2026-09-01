.. _frameworks-java-spring-mvc-form-label:

Form
====
- is used:
    - to send data to controller
    - bind view components to Java fields

Showing Form
------------
- in Spring controller:
    - before you show the form, you must add a model attribute
    - this is a bean that will hold form data for data binding


    .. code-block:: python
        :linenos:

        // need a controller method to read form data and
        // add data to the model

        @GetMapping("/shoStudentForm")
        public String showForm(Model model) {
            model.addAttribute("student", new Student());

            return "student-form";
        }


Setting up form
---------------
- action:
    - the method name from controller
- object:
    - the name of model attribute set from controller
- method:
    - the request method
-input.field:
    - the model attribute:
    - "*{firstName}" is the shorted syntax for "${student.firstName}"


    .. code-block:: python
        :linenos:

        <form th:Action="@{/processStudentForm}" th:object="${student}" method="POST">
            First name: <input type="text" th:field="*{firstName}"/>
            <br><br>
            Last name: <input type="text" th:field="*{lastName}"/>
            <br><br>
            <input type="submit" value="Submit"/>
        </form>


- when form is loaded, fields are pre-populated with values from the model:
    -"*{firstName}" will be populated with student.getFirstName()

Submitting form
---------------
- when form is submitted, it will actually call the setter methods on a new instance of model:
    - in our case, it will create a new instance of Student and call the setter methods with values from input and send it to the controller


Form
----
- <form> can be used to rendere a form
- th:action="@{/processStudentForm}" is set to specify the url name to be called
- th:object="${student}" is used bind to the model

    .. code-block:: python
        :linenos:

        <!DOCTYPE HTML>
        <html xmlns:th="http://www.thymeleaf.org">

        <head>
            <meta charset="UTF-8">
            <title>Hello World - Input Form</title>
        </head>
        <body>

            <form th:action="@{/processStudentForm}" th:object="${student}" method="POST">
                First name: <input type="text" th:field="*{firstName}"/>
                <br><br>
                Last name: <input type="text" th:field="*{lastName}"/>
                <br><br>
                <input type="submit" value="Submit"/>
            </form>

        </body>
        </html>


Text field
----------
- <input type="text" ...> can be used to send a text data field
- "th:field" is used to bind to a property on model:
    - "*{firstName}" is the shorted syntax for "${student.firstName}"
    - is bind to the Student Java field property


.. code-block:: python
        :linenos:

        <form th:Action="@{/processStudentForm}" th:object="${student}" method="POST">
            First name: <input type="text" th:field="*{firstName}"/>
            <br><br>
            Last name: <input type="text" th:field="*{lastName}"/>
            <br><br>
            <input type="submit" value="Submit"/>
        </form>


Drop-down
---------
- <select> is used to display a Drop-Down list
- "th:field" is used to bind to a property on model:
    - in our case, it will be mapped Student.country property: "${student.country}"
- each <option> tag is mapped to a value using "th:value":
    - <option th:value="Brazil">Brazil</option>    


    .. code-block:: python
        :linenos:

        <!DOCTYPE HTML>
        <html xmlns:th="http://www.thymeleaf.org">

        <head>
            <meta charset="UTF-8">
            <title>Hello World - Input Form</title>
        </head>
        <body>

          <form th:action="@{/processFormVersionTwo}" method="GET">

              <select th:field="*{country}">
                <option th:value="Brazil">Brazil</option>
                ...
                <option th:value="India">India</option>
              </select>

              <input type="submit" />

          </form>

        </body>
        </html>


- populating automaticaly options from "countries" field set on the Model:
    - set on controller:


    .. code-block:: python
        :linenos:

        public class StudentController{

            @Value("${coutries}")
            private List<String> countries;
            ....

            @GetMapping("/showStudentForm")
            public String showForm(Model model) {
                ...

                model.addAttribute("countries", countries);

                return "student-form";
            }
        }


    - display on browser:


    .. code-block:: python
        :linenos:

        <select th:field="*{country}">
            <option th:each="tempCountry : ${countries}" th:value="${tempCountry}" th:text="${tempCountry}" />
        </select>


Radio Button
------------
- <input type-"radio" ..> is used to display a radio button
- th:field="*{favoriteLanguage}" => will bind the value the the model:
    - in our case, to Student.favoriteLanguage attribute
- th:value="GO" => will send the value on submit

    .. code-block:: python
        :linenos:

        <input type="radio" th:field="*{favoriteLanguage}" th:value="GO">Go</input>
        <input type="radio" th:field="*{favoriteLanguage}" th:value="Java">Java</input>
        <input type="radio" th:field="*{favoriteLanguage}" th:value="Python">Python</input>


- populating automatically radio butons from "languages" field set on Model:
    - update controller:


    .. code-block:: python
        :linenos:

        public class StudentController{

            @Value("${languages}")
            private List<String> languages;
            ....

            @GetMapping("/showStudentForm")
            public String showForm(Model model) {
                ...

                model.addAttribute("languages", languages);

                return "student-form";
            }
        }


    - display on broser:


    .. code-block:: python
        :linenos:

        <input type="radio" 
            th:field="${favouriteLanguage}"
            th:each="tempLanguage : ${languages}" 
            th:value="${tempLanguage}"
            th:text="${tempLanguage}"
        />


Chec Boxes
----------
- <input type-"checkvox" ..> is used to display a check box
- th:field="*{favoriteSystems}" => will bind the value the the model:
    - in our case, to Student.favoriteSystems attribute
- th:value="Linux" => will send the value on submit:
    - in case value contain space " ", then the entire value should be included in a single quote

- as you can select multiple checkbox, the value on student should be a list of Strings

    .. code-block:: python
        :linenos:

        <input type="checkbox" th:field="*{favoriteSystems}" th:value="Linux">Linux</input>
        <input type="checkbox" th:field="*{favoriteSystems}" th:value="macOS">macOS</input>
        <input type="checkbox" th:field="*{favoriteSystems}" th:value="'Microsoft Windows'">Microsoft Windows</input>



- populating automatically checkboxes from "systems" field set on Model:
    - update controller:


    .. code-block:: python
        :linenos:

        public class StudentController{

            @Value("${systems}")
            private List<String> systems;
            ....

            @GetMapping("/showStudentForm")
            public String showForm(Model model) {
                ...

                model.addAttribute("systems", systems);

                return "student-form";
            }
        }


    - display on broser:


    .. code-block:: python
        :linenos:

        <input type="checkbox" 
            th:field="${favoriteSystems}"
            th:each="tempSystem : ${systems}" 
            th:value="${tempSystem}"
            th:text="${tempSystem}"
        />


Validation
----------
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


:ref:`Go Back <frameworks-java-spring-mvc-label>`.