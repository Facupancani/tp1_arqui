package org.example.helpers;

import org.example.dao.SingletonDB;
import java.sql.*;

public class DBHelper {

    public void crearTablas() throws SQLException{

        Connection conn = SingletonDB.getConnection();

        String crearTablaCliente = "CREATE TABLE cliente (" +
                "idCliente INT PRIMARY KEY," +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)" +
                ")";

        String crearTablaFactura = "CREATE TABLE factura (" +
                "idFactura INT PRIMARY KEY," +
                "idCliente INT," +
                "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)" +
                ")";

        String crearTablaProducto = "CREATE TABLE Producto (" +
                "idProducto INT PRIMARY KEY," +
                "nombre VARCHAR(100)," +
                "valor FLOAT" +
                ")";

        String crearTablaFacturaProducto = "CREATE TABLE factura_producto (" +
                "idFactura INT," +
                "idProducto INT," +
                "cantidad INT," +
                "PRIMARY KEY (idFactura, idProducto)," +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)," +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)" +
                ")";

        conn.prepareStatement(crearTablaCliente).execute();
        conn.prepareStatement(crearTablaFactura).execute();
        conn.prepareStatement(crearTablaProducto).execute();
        conn.prepareStatement(crearTablaFacturaProducto).execute();
        conn.commit();

    }

}
