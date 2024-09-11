package org.example.factory;

import org.example.dao.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class FactoryDAOMysql extends FactoryDAO{

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URI = "jdbc:mysql://localhost:3306/integrador1";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection conn;

    public FactoryDAOMysql() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            this.conn = DriverManager.getConnection(URI, USER, PASSWORD);
            this.conn.setAutoCommit(false);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // deberia pasar this.conn como argumento en los DAO
    // pero como tenemos singleton, no nesecita los DAO, para no implementar constructores innecesarios

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAOMysql(); // this.conn
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAOMysql(); // this.conn
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoDAOMysql(); // this.conn
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {
        return new FacturaProductoDAOMysql(); // this.conn
    }
}
