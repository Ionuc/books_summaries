.. _frameworks-java-spring-mvc-general-label:

General
=======
Overview
--------
- steps of the request from Web Broser:
    - FrontController
    - Controller
    - View Template
    - back to Web Broser


    .. image:: ../../../../images/frameworks/java/spring/mvc/architecture-overview.png
        :align: center


FrontController
---------------
- known as DispatcherServlet
- part of Spring framework
- will delegate the request to other components (Controller)

Controller
----------
- contains the business logic
    - handle the request
    - store / retrieve data (db, web services)
    - place data in model
- send to corresponding view template

Model
-----
- contains the actual data
- can be any Java object / collection
- used to share data between components: Controller, ViewTemplate, etc
    - controller can put data to model
    - view template can access data from model

    - controller example:


    .. code-block:: python
        :linenos:

        // need a controller method to read form data and
        // add data to the model

        @RequestMapping("/processFormVersionTwo")
        public String letsShoutDude(HttpServletRequest request, Model model) {

            // read the request parameter from the HTML form
            String theName = request.getParameter("studentName");

            // convert the data to all caps
            theName = theName.toUpperCase();

            // create the message
            String result = "Yo! " + theName;

            // add message to the model
            model.addAttribute("message", result);

            return "helloworld";
        }



    - form view:



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

              <input type="text" name="studentName"
                     placeholder="What's your name?" />

              <input type="submit" />

          </form>

        </body>
        </html>



    - page displaying the data from model for property "message"



    .. code-block:: python
        :linenos:

        <!DOCTYPE HTML>
        <html xmlns:th="http://www.thymeleaf.org">

        <head>
            <title>Thymeleaf Demo</title>
        </head>

        <body>

        Hello World of Spring!

        <br><br>

        Student name: <span th:text="${param.studentName}" />

        <br><br>

        The message: <span th:text="${message}" />

        </body>

        </html>


View Template
-------------
- display data
- developers can create different pages
- support many view templates: thymeleaf, Groovy, Velocity, Freemarker, etc


Hello World exaple
------------------
- example with Thymeleaf
    - Controller code:


    .. code-block:: python
        :linenos:

        @Controller
        public class DemoController {

            // create a mapping for "/hello"

            @GetMapping("/hello")
            public String sayHello(Model theModel) {

                theModel.addAttribute("theDate", java.time.LocalDateTime.now());

                theModel.addAttribute("student", new Student());

                return "helloworld"; // => the file name with extension created under resources/templates/
            }
        }


    - view template defined under resources/templates/helloworld.html:


    .. code-block:: python
        :linenos:

        <!DOCTYPE HTML>
        <html xmlns:th="http://www.thymeleaf.org">

        <head>
            <title>Thymeleaf Demo</title>
        </head>

        <body>

            <p th:text="'Time on the server is ' + ${theDate}" />
            <p th:text="'The student is confirmed ' + ${student.firstName} + ' ' + ${student.lastName}" />

        </body>

        </html>


Sending data with GET
---------------------
- form data sent using method GET is added to the end of URL as name/value pairs
    - example: theUrl?field1=value1&field2=value2...


    .. code-block:: python
        :linenos:

        <form th:action="@{/processForm}" method="GET" ..>
        ...
        </form>


Sending data with POST
----------------------
- form data sent using method POST is passed in the ody of HTTP request message


    .. code-block:: python
        :linenos:

        <form th:action="@{/processForm}" method="POST" ..>
        ...
        </form>


Data Binding
------------
- is the process of automatically setting / retrieving data from Java object / bean


Logout
------
- on logout, the Spring Security will:
    - invalidate uses's HTTP session and remove session cookies
    - send user back to your login page
    - append a logout parameter: ?logout

:ref:`Go Back <frameworks-java-spring-mvc-label>`.