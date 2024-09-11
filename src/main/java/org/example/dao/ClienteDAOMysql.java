package org.example.dao;

import org.example.entidades.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAOMysql implements ClienteDAO {

    private Connection conn;

    public ClienteDAOMysql() {
        try {
            this.conn = SingletonDB.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to Database failed", e);
        }
    }

    @Override
    public void insertCliente(int idCliente, String nombre, String email) throws SQLException {
        String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, idCliente);
        ps.setString(2, nombre);
        ps.setString(3, email);
        ps.executeUpdate();
        this.conn.commit();
        ps.close();
    }

    @Override
    public void getPorFacturados() throws SQLException {

        String query = "SELECT c.idCliente, c.nombre, c.email, SUM(p.valor * fp.cantidad as facturado"+
        "FROM cliente c"+
        "JOIN factura f ON c.idCliente = f.idCliente"+
        "JOIN factura_producto fp ON f.idFactura = fp.idFactura"+
        "JOIN producto p ON fp.idProducto = p.idProducto"+
        "GROUP BY c.idCliente"+
        "ORDER BY facturado DESC";

        ResultSet rs = this.conn.prepareStatement(query).executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()){
            System.out.println("id: "+rs.getInt("idCliente")+", nombre: "+rs.getString("nombre")+ ", email: "+rs.getString("email")+", recaudado: "+rs.getString("recaudado"));
        }

    }



}