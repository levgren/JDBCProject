package com.jdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jekas_schema?useSSL=false";
    private static final String USERNAME = "jeka";
    private static final String PASSWORD = "1907";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBProcessor dbProcessor = new DBProcessor();
        Connection connection = dbProcessor.getConnection(URL, USERNAME, PASSWORD);
        String querry = "select * from products where product_id = 3";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(querry);
        while (resultSet.next()) {
            int id = resultSet.getInt("product_id");
            String name = resultSet.getString("product_name");
            double price = resultSet.getDouble("price");
            int shopId = resultSet.getInt("shop_id");
            Product product = new Product(id, name, price, shopId);
            logger.info(String.valueOf(product));
        }
        statement.close();
        connection.close();
    }

    private static void createConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("The connection was established successfully");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select product_name from products where product_id = 3");
            while (resultSet.next()) {
                String name = resultSet.getString("product_name");
                logger.info(name);
            }
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
