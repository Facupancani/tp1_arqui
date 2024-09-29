package org.example.dao;

import java.sql.SQLException;

public interface FacturaProductoDAO {
    void insertFacturaProducto(int idFactura, int idProducto, int cantidad) throws SQLException;
}
