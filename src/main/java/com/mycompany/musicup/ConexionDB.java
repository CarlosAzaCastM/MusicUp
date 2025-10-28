
package com.mycompany.musicup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // 1. Configura tus datos de conexión
    
    // El nombre de tu base de datos que creaste
    private static final String DB_NAME = "musicupdb"; 
    
    // Tu usuario de MySQL (normalmente "root")
    private static final String USER = "root"; 
    
    // Tu contraseña de MySQL
    private static final String PASSWORD = "Ambar72$"; // <-- ¡CAMBIA ESTO!
    
    // La URL de conexión.
    // 'localhost' es tu propia máquina. '3306' es el puerto por defecto de MySQL.
    // Los parámetros extra (?useSSL=false&serverTimezone=UTC) evitan errores comunes.
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME 
                                    + "?useSSL=false&serverTimezone=UTC";

    
    /**
     * Método estático para obtener una nueva conexión a la base de datos.
     * @return un objeto Connection o null si falla.
     */
    public static Connection getConnection() {
        Connection con = null;

        try {
            // No necesitas "Class.forName()" con los drivers modernos.
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión exitosa a la base de datos " + DB_NAME + "!");
            
        } catch (SQLException e) {
            System.out.println("ERROR: No se pudo conectar a la base de datos.");
            e.printStackTrace(); // Imprime el detalle del error en la consola
        }
        
        return con;
    }
}
