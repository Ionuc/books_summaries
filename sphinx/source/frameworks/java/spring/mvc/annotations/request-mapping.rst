.. _frameworks-java-spring-mvc-annotation-request-mapping-label:

RequestMapping
==============
- annotation used to bind a controller method to a request
- the return value is a String and the value should be the name of the html file from resources/template/ directory
- in case is not specified a method name, it will handle all method requests: GET, POST, PUT, etc..

    .. code-block:: python
        :linenos:

        // need a controller method to read form data and
        // add data to the model

        @RequestMapping("/example1")
        public String example1(HttpServletRequest request, Model model) {

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


Set method type
---------------
- you can specify a controller method to map only on a predefined method type


    .. code-block:: python
        :linenos:

        @RequestMapping(
            path="/processForm",
            method=RequestMethod.GET
        )
        public String processForm(...) {
            ....
        }

:ref:`Go Back <frameworks-java-spring-mvc-annotation-label>`.
