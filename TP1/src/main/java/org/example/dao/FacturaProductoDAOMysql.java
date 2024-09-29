package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaProductoDAOMysql implements FacturaProductoDAO{

    private Connection conn;

    public FacturaProductoDAOMysql() {
        try {
            this.conn = SingletonDB.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to Database failed", e);
        }
    }
    
    @Override
    public void insertFacturaProducto(int idFactura, int idProducto, int cantidad) throws SQLException {
        String query = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, idFactura);
        ps.setInt(2, idProducto);
        ps.setInt(3, cantidad);
        ps.executeUpdate();
        this.conn.commit();
        ps.close();
    }
}
