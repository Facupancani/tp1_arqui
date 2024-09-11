package org.example.dao;

import java.sql.SQLException;

public interface ProductoDAO {
    void insertProducto(int idProducto, String nombre, double valor) throws SQLException;
}
