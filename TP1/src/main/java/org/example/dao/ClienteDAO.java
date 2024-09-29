package org.example.dao;

import org.example.entidades.Cliente;
import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    void insertCliente(int idCliente, String nombre, String email) throws SQLException;
    void getPorFacturados() throws SQLException;

}
