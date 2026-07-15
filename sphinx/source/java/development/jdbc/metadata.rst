.. _java-development-jdbc-metadata-label:

JDBC Metadata
=============
- you can investigate the structure of the database on the fly and create dynamic queries, depending on the business logic
- these information can be retrieved using class DatabaseMetaData created from a connection
- methods:
    - getTables():
        - retrieve all tables from the schema:
            - last parameter is the type to retrieve, which is "TABLE"
    - getTableTypes():
        - retrieve all table types


    .. code-block:: python
           :linenos:

            public static void main(String[] args) throws SQLException {
                try (var conn = DBUtils.getConnection()) {
                    DatabaseMetaData metaData = conn.getMetaData();

                    System.out.println("===== TABLE NAMES =====");
                    ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"}); 
                    while(resultSet.next()) { 
                        String tableName = resultSet.getString("TABLE_NAME"); 
                        System.out.println("Table name:\t" + tableName);
                    }
                    
                    System.out.println("===== COLUMN VALUES =====");
                    ResultSet tableTypes = metaData.getTableTypes();
                    ResultSetMetaData metaDataTableTypes = tableTypes.getMetaData();
                    int columnCount = metaDataTableTypes.getColumnCount();
                    while (tableTypes.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.println(tableTypes.getString(i));
                        }
                    }
                    
                }
            }



:ref:`Go Back <java-development-jdbc-label>`.