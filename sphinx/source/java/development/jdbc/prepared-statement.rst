.. _java-development-jdbc-prepared-statement-label:

Prepared Statement
==================
- is a way of preventing SQL injection
- defined a SQL query template and lets client only define parameters values
- have better performance:
    - SQL queries passed to the Prepared statement methods go to database for pre-compilation if the JDBC driver supports it
    - if driver does not support it, pre-complation occurs when you execute prepared queries
    - preparedstatement queries are pre-compiled on the database and the access plan will be reused to execute further queries
- it allows to create parameterezied SQL queries and send different parameters by using the same SQL

- when setting values with PreparedStatements, index nr 1 is the index of the first position

Select
------

    .. code-block:: python
           :linenos:

            public static void main(String[] args) throws SQLException {
                String query = "SELECT * FROM user";
                
                try (var conn = DBUtils.getConnection();
                        PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    try (ResultSet rs = preparedStatement.executeQuery()) {
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
                String query = "UPDATE user SET money = 120.00 WHERE id = ?";
                
                try (var conn = DBUtils.getConnection();
                        PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setInt(1, 17);
                    int rows = preparedStatement.executeUpdate();
                    System.out.println(rows);
                    
                }
            }


Insert
------


    .. code-block:: python
           :linenos:

            public static void main(String[] args) throws SQLException {
                String query = "INSERT INTO user (first_name, last_name, email, fk_user_role, money) VALUES (?, ?, ?, ?, ?)";
                
                try (var conn = DBUtils.getConnection();
                        PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setString(1, "Dmytriy");
                    preparedStatement.setString(2, "Voloshov");
                    preparedStatement.setString(3, "d.voloshov@email.com");
                    preparedStatement.setInt(4, 4);
                    preparedStatement.setInt(5, 0);
                    int rows = preparedStatement.executeUpdate();
                    System.out.println(rows);
                    
                }
            }


Delete
------


    .. code-block:: python
           :linenos:


            public static void main(String[] args) throws SQLException {
                String query = "DELETE FROM user WHERE id = ?";
                try (var conn = DBUtils.getConnection();
                        PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setInt(1, 20);
                    int rows = preparedStatement.executeUpdate();
                    System.out.println(rows);
                    
                }
            }


:ref:`Go Back <java-development-jdbc-label>`.