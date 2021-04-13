/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores_y_modelos;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Connection {
    
    java.sql.Connection con;
    Propiedades prop = new Propiedades();
    
    public Connection() throws SQLException {
      
      //prop.setURL("jdbc:mysql://localhost:3306/cristomessenger2");
      prop.setURL("jdbc:mysql://clasedam2.ddns.net:3306/cristomessenger");
      prop.setUser("clasedam2");
      //prop.setUser("root");
      prop.setPassword("root");
      
      con = DriverManager.getConnection(prop.getURL(), prop.getUser(), prop.getPassword());    
    }
    
    public void conectarBD() throws SQLException{
        this.con = DriverManager.getConnection(prop.getURL(), prop.getUser(), prop.getPassword());
    }
    
    public void desconectarBD() throws SQLException{
        this.con.close();
    }
   
    
            
}
