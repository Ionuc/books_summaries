.. _sql-injection-security-label:


SQL Injection
=============
- An SQL injection is when the attacker tries to modify and corrupt the SQL clauses used by the application
- SQL Injection may happen when the application builds SQL commands by concatenating the strings along with application input parameters

.. code-block:: python
   :linenos:

   algorithm VulnerableLoginFormHandler(username, password):
        // INPUT
        //    username, password = login data from an HTML form
        // OUTPUT
        //    authOk (boolean) = authentication status
        //    UserId = user ID 
        //    FullName = the name of the user

        passwd_hash <- md5 of password
        sql <- "select id, fullname from users where passwordHash = '" + passwd_hash
        sql <- sql + "' and userName ='" + username + "'"
        result <- query sql and fetch from the database

        if result is an error:
            return false, null, null
        else:
            return true, result[id], result[fullname]


- in the above example, here are same example of how it cane be used:
    - Username = "root" and password ="root"
        -> SELECT id,Fullname FROM users WHERE Password ='63a9f0ea7bb98050796b649e85481845' AND Username ='root'
    - Username = "root’ or 1=1 limit 1;–" and Password = "":
        -> SELECT id,Fullname FROM users WHERE Password ='d41d8cd98f00b204e9800998ecf8427e' AND Username ='root' or 1=1 limit 1;--'
- the example above gives the same result because of the condition inserted : "or 1=1"
- other usage can make even more damage:
    - Username=”root’ or 1=1 ; select * from customers;– “, to select on other tables
    - Username=”root’ or 1=1 ; insert into users (username, password) values (‘backdoor_user’,’some_hash’);– “, to insert data
    - Username=”root’ or 1=1 ; select * customers; — “, to steal application data; or even
    - Username=”root’ or 1=1 ; drop table customers; — “, to delete data
    - Username=”root’ or 1=1 union select password_hash as fullname from users; — “, to steal password hashes

- what makes many applications vulnerable to SQL Inject attacks is the fact that their user inputs are concatenated directly to build their SQL commands

Types of SQL injections
-----------------------
- In-band SQLI
    - is the most common and easy-to-exploit
    - occures when an attacker is able ot use the same communication channel to both launch the attack and gather results
    - types:
        - Error-based
            - the application returns the SQL errors back
            - the attacker can use the error message as feedback to improve their approach and to obtain information about the structure of the database
            - is a good practice to have proper application error handling, not giving details directly to the user and to log the error to a file

        - Union-based
            - leverage the UNION SQL operator to combine the result of two or more SELECT statements into a single result which is then returned as part of the HTTP response
- Inferential SQLI (BLind SQLI)
    - it may take longer for an attacker to exploit
    - no data is actually transferred via the web application and the attacker would not be able to see th result of an attach
    - instead, an attacker is able to reconstruct the database structure by sending payloads, observing the web application's response and the resulting behavior of that DB server
    - types:
        - Boolean-based Blind SQLI
            - relies on sending an SQL query to th DB which forces the application to return a different result depending on whether the query returns TRUE or FALSE result
            - depending on the result, the content within the HTTP response will change, or remain the same
            - This allows an attacker to infer if the payload used returned true or false, even though no data from the database is returned
        - Time-based Blind SQLI:
            - relies on sending an SQL query to the database which forces the database to wait for a specified amount of time (in seconds) before responding
            - The response time will indicate to the attacker whether the result of the query is TRUE or FALSE
            - Depending on the result, an HTTP response will be returned with a delay, or returned immediately
            - This allows an attacker to infer if the payload used returned true or false, even though no data from the database is returned
- Out-of-band SQLI:
    - it depends on features being enabled on the database server being used by the web application
    - Out-of-band SQL Injection occurs when an attacker is unable to use the same channel to launch the attack and gather results
    - would rely on the database server’s ability to make DNS or HTTP requests to deliver data to an attacker
    - example : the case with Microsoft SQL Server’s xp_dirtree command, which can be used to make DNS requests to a server an attacker controls


:ref:`Go Back <security-db-label>`.