package org.example.dao;

import java.sql.SQLException;

public interface FacturaDAO {
    void insertFactura(int idFactura, int idCliente)  throws SQLException;
}
