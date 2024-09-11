package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDAOMysql implements ProductoDAO{

    private Connection conn;

    public ProductoDAOMysql() {
        try {
            this.conn = SingletonDB.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to Database failed", e);
        }
    }

    @Override
    public void insertProducto(int idProducto, String nombre, double valor) throws SQLException {
        String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, idProducto);
        ps.setString(2, nombre);
        ps.setDouble(3, valor);
        this.conn.commit();
        ps.close();
    }
}
