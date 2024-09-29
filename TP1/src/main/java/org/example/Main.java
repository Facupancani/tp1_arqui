package org.example;

import org.example.dao.ClienteDAOMysql;
import org.example.dao.ProductoDAOMysql;
import org.example.helpers.DBHelper;

public class Main {

    public static void main(String[] args)  {

        try{

            System.out.println("[+] Rolling back tables..");
            DBHelper.dropTables();
            DBHelper.crearTablas();
            System.out.println("[+] Populating data from CSV...");
            DBHelper.populateTables();
            System.out.println("[+] OK!");

            System.out.println("\n============================================================\n");
            ClienteDAOMysql clienteDAO = new ClienteDAOMysql();
            clienteDAO.getPorFacturados();
            System.out.println("\n============================================================\n");

            System.out.println("[+] Most Billed: \n");
            ProductoDAOMysql productoDAO = new ProductoDAOMysql();
            productoDAO.getMasFacturado();

        }catch (Exception e){
            throw new RuntimeException(e);
        }




    }



}
