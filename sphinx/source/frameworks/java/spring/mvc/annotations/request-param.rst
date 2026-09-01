.. _frameworks-java-spring-mvc-annotation-request-param-label:

RequestParam
============
- annotation used on controller method parameter to bind a form value to a model attribute
- allows to read form data

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

        @RequestMapping("/example2")
        public String example2(@RequestParam("studentName") String theName, Model model) {
            // convert the data to all caps
            theName = theName.toUpperCase();

            // create the message
            String result = "Yo! " + theName;

            // add message to the model
            model.addAttribute("message", result);

            return "helloworld";
        }


:ref:`Go Back <frameworks-java-spring-mvc-annotation-label>`.
