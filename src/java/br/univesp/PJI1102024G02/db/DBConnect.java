package br.univesp.PJI1102024G02.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rubens
 */
public class DBConnect {

    static Connection connection = null;

    public static Connection getConnection() {

        if (connection == null) {
            
            // Driver MySQL
            String driver = "com.mysql.jdbc.Driver";
            try {
                // Carrega o driver
                Class.forName(driver);

                // Parametros de conexao
                String host = "localhost";
                String base = "agenda_da_beleza";
                String user = "agenda_user";
                String pass = "auser1234";

                String url = "jdbc:mysql://" + host + "/" + base;
                connection = DriverManager.getConnection(url, user, pass);
                
            } catch (ClassNotFoundException e) {
                System.out.println("O driver ["+driver+"] nao localizado.");
            } catch (SQLException e) {
                System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            }
        }

        return connection;
    }
}
