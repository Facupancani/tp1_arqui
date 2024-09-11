package org.example;

import org.example.dao.ClienteDAOMysql;
import org.example.dao.ProductoDAOMysql;
import org.example.helpers.DBHelper;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args)  {

        try{

            System.out.println("Dropeando tablas..");
            DBHelper.dropTables();
            System.out.println("Creando tablas..");
            DBHelper.crearTablas();
            System.out.println("Cargando tablas..");
            DBHelper.populateTables();
            System.out.println("Tablas cargadas exitosamente! \n");

            System.out.printf("Lista de clientes ordenada por mas facturados: \n");
            ClienteDAOMysql clienteDAO = new ClienteDAOMysql();
            clienteDAO.getPorFacturados();
            System.out.printf("  Fin de la lista. \n");


            ProductoDAOMysql productoDAO = new ProductoDAOMysql();
            productoDAO.getMasFacturado();

        }catch (Exception e){
            throw new RuntimeException(e);
        }




    }



}
