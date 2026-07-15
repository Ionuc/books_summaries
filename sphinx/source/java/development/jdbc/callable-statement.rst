.. _java-development-jdbc-callable-statement-label:

Callable Statement
==================
- is the interface to execute SQL store procedures
- stres procedures:
    - are a group of statemetns that we compile in the database for some tasks
    - are used when we are dealing with multiple tables with complex scenarios
    - we can send only one request with required data to stored procedures instead of multiple request to each table
- same as prepared statement, an template is created with corresponding parameters
- in case you want to declare that one of the parameters is out parameter, you have to register it first, using registerOutParameter(<argument_index>, <argument_sql_type>)
- you can read the out parameter using the corresponding get() method

    .. code-block:: python
           :linenos:

            public static void main(String[] args) throws SQLException {
                try (var conn = DBUtils.getConnection();
                        CallableStatement callStatement = conn.prepareCall("CALL select_user_by_email(?)")) {
                    
                    callStatement.setString(1, "s.ivanov@email.com");
                    
                    // ONLY IN CASE out parameter register the OUT parameter before calling the stored procedure
        //          callStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
                    
                    try (ResultSet rs = callStatement.executeQuery()) {
                        
                        if (rs.next()) {
                            System.out.println("User last name: " + rs.getString("last_name"));
                        }
                        
                        // ONLY IN CASE out parameter read the OUT parameter now
        //              String result = callStatement.getString(1);
                        
                    }
                    
                }


:ref:`Go Back <java-development-jdbc-label>`.