package org.example;

import org.example.dao.ClienteDAOMysql;
import org.example.helpers.DBHelper;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args)  {

        try{

            // DBHelper.dropTables();
            DBHelper.crearTablas();
            DBHelper.populateTables();

            ClienteDAOMysql clienteDAO = new ClienteDAOMysql();
            clienteDAO.getPorFacturados();


        }catch (Exception e){
            throw new RuntimeException(e);
        }




    }



}
