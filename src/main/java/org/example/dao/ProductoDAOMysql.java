package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        ps.executeUpdate();
        this.conn.commit();
        ps.close();
    }

    @Override
    public void getMasFacturado() throws SQLException {
        String query = "SELECT p.idProducto, p.nombre, p.valor, fp.total_vendido FROM Producto p " +
                "JOIN ( " +
                "    SELECT fp.idProducto, (SUM(fp.cantidad) * p.valor) AS total_vendido " +
                "    FROM Factura_Producto fp " +
                "    JOIN Producto p ON p.idProducto = fp.idProducto " +
                "    GROUP BY fp.idProducto " +
                "    ORDER BY total_vendido DESC " +
                ") fp ON fp.idProducto = p.idProducto " +
                "LIMIT 1;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        this.conn.commit();
        ResultSet rs = this.conn.prepareStatement(query).executeQuery();
        while (rs.next()){
            System.out.println("\nEl producto que mas ha recaudado es: " + "id: "+rs.getInt("idProducto")+", nombre: "+rs.getString("nombre")+ ", valor: $"+rs.getString("valor")+", total_vendido: "+rs.getString("total_vendido") + " unidades.");
        }
        ps.close();
    }

}
