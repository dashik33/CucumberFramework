package utils;

import java.sql.*;
import java.util.*;

public class DBUtils {
    public static List<Map<String, String>> getDataFromDB(String query) {
        String dbUrl = ConfigReader.getPropertyValue("dbUrl");
        String dbUserName = ConfigReader.getPropertyValue("dbUserName");
        String dbPassword = ConfigReader.getPropertyValue("dbPassword");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData resultSetMetaData = null;
        List<Map<String, String>> tableData = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String columnName = resultSetMetaData.getColumnLabel(i);
                    String columnValue = resultSet.getString(columnName);
                    row.put(columnName, columnValue);
                }
                tableData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnectionStatementResultSet(connection, statement, resultSet);
        }
        return tableData;
    }

    public static void closeConnectionStatementResultSet(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


