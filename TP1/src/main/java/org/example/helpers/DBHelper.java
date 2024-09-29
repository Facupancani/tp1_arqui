package org.example.helpers;

import org.example.dao.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;


public class DBHelper {

    public static void crearTablas() throws SQLException{

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

    public static void populateTables() {

        ClienteDAOMysql clienteDAO = new ClienteDAOMysql();
        FacturaDAOMysql facturaDAO = new FacturaDAOMysql();
        ProductoDAOMysql productoDAO = new ProductoDAOMysql();
        FacturaProductoDAOMysql facturaProductoDAO = new FacturaProductoDAOMysql();

        try{

            CSVParser parserCliente = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/clientes.csv"));
            for(CSVRecord row : parserCliente){
                clienteDAO.insertCliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
            }

            CSVParser parserFactura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/facturas.csv"));
            for(CSVRecord row : parserFactura){
                facturaDAO.insertFactura(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")));
            }

            CSVParser parserProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/productos.csv"));
            for(CSVRecord row : parserProducto){
                productoDAO.insertProducto(Integer.parseInt(row.get("idProducto")), row.get("nombre"), Double.parseDouble(row.get("valor")));
            }

            CSVParser parserFacturaProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/facturas_productos.csv"));
            for(CSVRecord row : parserFacturaProducto){
                facturaProductoDAO.insertFacturaProducto(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad")));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void dropTables() throws SQLException{

        Connection conn = SingletonDB.getConnection();
        conn.setAutoCommit(false);

        String dropTablaFacturaProducto = "DROP TABLE IF EXISTS factura_producto";
        String dropTablaProducto = "DROP TABLE IF EXISTS producto";
        String dropTablaFactura = "DROP TABLE IF EXISTS factura";
        String dropTablaCliente = "DROP TABLE IF EXISTS cliente";

        conn.prepareStatement(dropTablaFacturaProducto).execute();
        conn.prepareStatement(dropTablaProducto).execute();
        conn.prepareStatement(dropTablaFactura).execute();
        conn.prepareStatement(dropTablaCliente).execute();
        conn.commit();

    }


}
