.. _java-development-jdbc-statement-label:

Statement
=========
- is used for executing a static SQL statement and returns the results it produces
- is creating from a Connection object and should be closed after is used
- methods used to executeSQL queries:
    - execute( String sqlQuery)
        - returns boolean value:
            - true if the first result is a ResultSet
            - false if it is an update count or there are no results
        - it can be used when you execute dynamically unkonwn SQL statement, because of the return type
    - executeQuery(String sqlQuery)
        - returns ResultSet, which allows you to read all rows returned by SQL query
    - executeUpdate(String sqlQuery)
        - returns int value representing the row count that was impacted by SQL statement
        - is not suitable for select statement, but for insert, update or delete statements

- all execution methods in the statement interface implicitly close a current result set object of the statement if an open one exists, but it is good practice to close the result set


Select
------

    .. code-block:: python
           :linenos:

            public static void main(String[] args) throws SQLException {
                String query = "SELECT * FROM user";
                
                try (var conn = DBUtils.getConnection();
                        Statement statement = conn.createStatement()) {
                    try (ResultSet rs = statement.executeQuery(query)) {
                        while (rs.next()) {
                            System.out.println("=================");
                            System.out.println("ID:\t\t" + rs.getInt("ID"));
                            System.out.println("First Name:\t" + rs.getString("first_name"));
                            System.out.println("Last Name:\t" + rs.getString("last_name"));
                            System.out.println("Email:\t\t" + rs.getString("email"));
                        }
                    };
                    
                }
            }

Update
------


    .. code-block:: python
           :linenos:

            public static void main(String[] args) throws SQLException {
                String query = "UPDATE user SET money = 120.00 WHERE id = 17";
                try (var conn = DBUtils.getConnection();
                        Statement statement = conn.createStatement()) {
                    int rows = statement.executeUpdate(query);
                    System.out.println(rows);
                    
                }
            }


Insert
------


    .. code-block:: python
           :linenos:

            public static void main(String[] args) throws SQLException {
                String query = "INSERT INTO user (first_name, last_name, email, fk_user_role, money) VALUES ('Dmytriy', 'Voloshov', 'd.voloshov@email.com', 4, 0)";
                try (var conn = DBUtils.getConnection();
                        Statement statement = conn.createStatement()) {
                    int rows = statement.executeUpdate(query);
                    System.out.println(rows);
                    
                }
            }


Delete
------


    .. code-block:: python
           :linenos:


            public static void main(String[] args) throws SQLException {
                String query = "DELETE FROM user WHERE id = 20";
                
                try (var conn = DBUtils.getConnection();
                        Statement statement = conn.createStatement()) {
                    
                    int rows = statement.executeUpdate(query);
                    System.out.println(rows);
                    
                }
            }


ResultSet
---------
- is a table of data representing a database result set
- maintains a cursor pointing to its current row od data
- initially, the cursor is positioned before the first row
- only one result set object per statement object can be open at the same time
- methods:
    - next():
        - moves the cursor to the next row
        - returns false when there are no more rows in the result set object
    - getter methods return java data types



    .. image:: ../../../images/java/development/jdbc/result-set-to-java-data-types.png
        :align: center


SQL Injection
-------------
- is a wbe security vulnerabilities that allows an attacker to interfere with the queries that an application makes to its database
- it allows the attacker to view data data they are not normally able to retrieve
- common SQL injection examples:
    - retrieving hiddne data:
        - where you can modify an SQL query to return additional results
    - subverting application logic:
        - where you can change a query to interfere with the application's logic
    - UNION attacks:
        - you can retrieve data from different database tables
    - examining the database:
        - you can extract information about the version and structure of the database
    - blind SQL injection:
        - the results of a query you control are not returned in the application's responses

- Example of online shop:
    - the link htps://online-shop.com/products?category=Computers might produce the SQL:
        - SELECT * FROM product WHERE category = 'Computers' and status = 'active'
        - will return products from active status
    - the link htps://online-shop.com/products?category=Computers'--
        - will produce the SQL: SELECT * FROM product WHERE category = 'Computers'-- and status = 'active'
        - double hyphens in URL will be added in the SQL which means COMMENTS and everything comming after will be treated as a comments
        - the result will contain products from active AND inactive status
    - the link htps://online-shop.com/products?category=Computers' UNION select username, password from user --
        - will procut the SQL:  SELECT * FROM product WHERE category = 'Computers' UNION SELECT username, password FROM user-- and status = 'active'
        - with UNION you can pass another query to be executed

Prepared Statement
------------------
- is a way of preventing SQL injection
- defined a SQL query template and lets client only define parameters values
- have better performance:
    - SQL queries passed to the Prepared statement methods go to database for pre-compilation if the JDBC driver supports it
    - if driver does not support it, pre-complation occurs when you execute prepared queries
    - preparedstatement queries are pre-compiled on the database and the access plan will be reused to execute further queries
- it allows to create parameterezied SQL queries and send different parameters by using the same SQL




:ref:`Go Back <java-development-jdbc-label>`.