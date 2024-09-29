package org.example.factory;

import org.example.dao.ClienteDAO;
import org.example.dao.FacturaDAO;
import org.example.dao.FacturaProductoDAO;
import org.example.dao.ProductoDAO;

public abstract class FactoryDAO {

    public static final int DRIVER_MYSQL = 1;
    //public static final int DRIVER_DERBY = 2;

    public static FactoryDAO getFactoryDAO(int DRIVER){
        switch(DRIVER){
            case DRIVER_MYSQL: return new FactoryDAOMysql();
            //case DRIVER_MYSQL: return new FactoryDAODerby();
            default: return null;
        }
    }

    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract FacturaProductoDAO getFacturaProductoDAO();

}
