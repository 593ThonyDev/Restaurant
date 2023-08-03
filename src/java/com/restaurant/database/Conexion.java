package com.restaurant.database;

import java.sql.*;

public class Conexion {

    public static Connection getConexion() {
        Connection cn = null;
        String DATA_BASE = "restaurant";
        String USER = "root";
        String HOST = "localhost";
        String PASSWORD = "";
        String PORT = ":3306/";
        String URL = "jdbc:mysql://" + HOST + PORT + DATA_BASE + "?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR DE CONEXION: " + e.getMessage());
        }
        return cn;
    }

}
