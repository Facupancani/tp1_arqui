package org.example.dao;

import java.sql.*;

public class FacturaDAOMysql implements FacturaDAO{

    private Connection conn;

    public FacturaDAOMysql() {
        try {
            this.conn = SingletonDB.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to Database failed", e);
        }
    }

    @Override
    public void insertFactura(int idFactura, int idCliente) throws SQLException{
        String query = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, idFactura);
        ps.setInt(2, idCliente);
        ps.executeUpdate();
        this.conn.commit();
        ps.close();
    }
}
