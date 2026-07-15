.. _java-development-jdbc-transaction-label:

JDBC Transaction
================
- in JDBC, to take controll of the transaction, we need to set autoCommit() to False:
    - if a conection is in Autocommit mode, then all its SQL sttements will be executed and committed as individual transactions
    - othervise, its SQL statements are group into transactions that are terminated y a call to either commit() or rollback()
    - by default, new connections are in Autocommit mode
- if you want, to create create anytime a savePoint in order to rollback to that SavePoint and maybe to try the following steps one more time
- after executing all statements, connection.commit() method needs to be executed
- in case of any exception, connection.rollback() methods should be executed:
    - you can rollback fo any SavePoint created, or to rollback the entire transaction

SavePoint
---------
- the class SavePoint is used to store the state of the transaction
- this point within the current transaction can be referenced from the rollback method of the connection object
- when transaction is rollback to a SavePoint, all changes made after that save point are undone
- can be named or unnamed
- can be created from connection.setSavepoint() method

    .. code-block:: python
           :linenos:

            public static void main(String[] args) {
                String updateQuery = "UPDATE user SET money = ? WHERE id = ?";
                String selectQuery = "SELECT * FROM user WHERE id = ?";

                double moneyToTransfer = 80;
                int userFromId = 12;
                int userToId = 18;

                try (var conn = DBUtils.getConnection();
                        var psSelect = conn.prepareStatement(selectQuery);
                        var psUpdate = conn.prepareStatement(updateQuery);) {

                    Savepoint savepoint = null;
                    try {
                        conn.setAutoCommit(false);

                        psSelect.setInt(1, userFromId);
                        try (var rs = psSelect.executeQuery()) {
                            if (rs.next() == true) {
                                double moneyAmount = rs.getDouble("money");
                                if (moneyToTransfer > moneyAmount) {
                                    System.out.println("Not enough money for transfer");
                                    return;
                                } else {
                                    moneyAmount -= moneyToTransfer;
                                    psUpdate.setDouble(1, moneyAmount);
                                    psUpdate.setInt(2, userFromId);
                                    psUpdate.executeUpdate();
                                }
                            }
                        }

        //              savepoint = conn.setSavepoint();
                        
                        psSelect.setInt(1, userToId);
                        try (var rs = psSelect.executeQuery()) {
                            if (rs.next() == true) {
                                double moneyAmount = rs.getDouble("money");
                                moneyAmount += moneyToTransfer;
                                psUpdate.setDouble(1, moneyAmount);
                                psUpdate.setInt(2, userToId);
                                psUpdate.executeUpdate();
                            }
                        }

                        conn.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        conn.rollback();
        //              conn.rollback(savepoint);
                    }
                    System.out.println("Money transferred");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


JDBC batch update
-----------------
- is a batch of updates grouped together and send to the database in one batch, rather then separate requests
- is faster:
    - sending one by one will send each request after the previous one is done
- it doesn't make sens to send batch select requests
- it is recommended to execute all batch reuqest in one transaction instead of separate one, as partial statements can be executed with success and other with failures
- to add a statement to the batch, use preparedStatement.addBatch() method
- after all statements are created, use preparedStatement.executeBatch() method


    .. code-block:: python
           :linenos:

                    public static void main(String[] args) throws SQLException {
                        
                        try(var conn = DBUtils.getConnection();
                                PreparedStatement ps = conn.prepareStatement("INSERT INTO user (first_name, last_name, email, fk_user_role, money) VALUES (?, ?, ?, ?, ?)")) {
                            conn.setAutoCommit(false);
                            
                            try {
                                ps.setString(1, "Dmytriy");
                                ps.setString(2, "Voloshov");
                                ps.setString(3, "d.voloshov@email.com");
                                ps.setInt(4, 4);
                                ps.setInt(5, 0);
                                ps.addBatch();
                                
                                ps.setString(1, "Semen");
                                ps.setString(2, "Zhukov");
                                ps.setString(3, "s.zhukov@email.com");
                                ps.setInt(4, 4);
                                ps.setInt(5, 0);
                                ps.addBatch();
                                
                                ps.setString(1, "Andrey");
                                ps.setString(2, "Makarevych");
                                ps.setString(3, "a.makarevych@email.com");
                                ps.setInt(4, 4);
                                ps.setInt(5, 0);
                                ps.addBatch();
                                
                                ps.executeBatch();
                                
                                conn.commit();
                                System.out.println("All records successfully inserted!");
                            } catch (SQLException e) {
                                e.printStackTrace();
                                conn.rollback();
                            }
                        }
                    }


:ref:`Go Back <java-development-jdbc-label>`.