/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores_y_modelos;

import Clases.Friend;
import Clases.Message;
import Clases.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
class Model extends Connection{

    public Model() throws SQLException {
    }

    public String getNombre(String login, String passw) throws SQLException {
        
        this.conectarBD();
        
        String busqueda_username = "NULL";
        
        Statement stmt = (Statement) con.createStatement();;
        ResultSet rs;        
        
        try { 
            String query_buscar_username = ("SELECT id_user, password FROM user WHERE id_user = '" + login + "' AND password = '" + passw + "'");
 
            rs = stmt.executeQuery(query_buscar_username);
        
            if(rs.next()){
                busqueda_username = ("YES");
            }        
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
        
        return busqueda_username;        
    }

    ArrayList getAmigos(String login, String passw, ArrayList<User> amigos) throws SQLException {
        
        this.conectarBD();
        
        String query_buscar_amigos =  "SELECT id_user_orig, id_user_dest FROM friend WHERE id_user_orig = '" + login + "' OR id_user_dest = '"+ login + "';";
        
        Statement stmt = (Statement) con.createStatement(); 
        
        try {                      
            ResultSet rs;
            rs = stmt.executeQuery(query_buscar_amigos);
            //System.out.println("Se mete1");
            while(rs.next()) {
                
                //String user1 = rs.getString("id_user_orig");
                String user2 = rs.getString("id_user_dest");
                //System.out.println("Se mete2");
                if(!user2.equals(login)){
                   //System.out.println("Se mete3");
                   String query_buscar1; 
                   query_buscar1 = ("SELECT id_user, name, state FROM user WHERE id_user = '" + user2 + "'");
                   
                   Statement stmt1;   
                   stmt1 = (Statement) con.createStatement();                    
                   
                   ResultSet rs1;
                   rs1 = stmt1.executeQuery(query_buscar1); 
                   
                   
                   if(rs1.next()){
                       
                    User u = new User();

                    String login_friend = rs1.getString("id_user");
                    u.setId_user(login_friend);

                    String name = rs1.getString("name");
                    u.setName(name);

                    int status = rs1.getInt("state"); 
                    if(status == 1){
                        u.setState(true);
                    }else{
                        u.setState(false);
                    }
                    
                    amigos.add(u); 
                   }
                }
            }
        } finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
        
        return amigos;
    }

    void setConectedToDB(String login, String passw) throws SQLException{
        //String busqueda_username = "NULL";
        this.conectarBD();
        Statement stmt;       
        
        stmt = (Statement) con.createStatement();
        
        String query_buscar_username = ("UPDATE User SET state = 1 WHERE id_user = '" + login + "' AND password = '" + passw + "'");
 
        try {
            stmt.executeUpdate(query_buscar_username);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
    }

    boolean checkStatus(String login_amigo) throws SQLException {
        
        this.conectarBD();
        boolean status = false;
        String query_comprueba_status = "SELECT state FROM user WHERE id_user = '" + login_amigo + "'";
        
        Statement stmt = (Statement) con.createStatement();
        ResultSet rs;
        
        try {
            rs = stmt.executeQuery(query_comprueba_status);
            
            if(rs.next()){
                status = rs.getBoolean("state");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
        
        return status;
    }

    String getUserInfo(String loginAmigo) throws SQLException{
        
        this.conectarBD();
        String query_buscar_info = ("SELECT name, surname1, surname2 FROM user WHERE id_user = '" + loginAmigo + "'");
        String infoAmigo = "NULL";
        
        Statement stmt = (Statement) con.createStatement();;
        ResultSet rs;
        
        try {
            rs = stmt.executeQuery(query_buscar_info);
            if(rs.next()){
                String nombre = rs.getString("name");
                String apellido1 = rs.getString("surname1");
                String apellido2 = rs.getString("surname2");

                infoAmigo = nombre + "#" + apellido1 + "#" + apellido2;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
    
        return infoAmigo;
    } 
    
    public int getNumeroTotalDeMessages(String loginCliente, String loginAmigo) throws SQLException {
        
        this.conectarBD();
        String query_buscar_mensajes_totales = ("SELECT * FROM message WHERE id_user_orig = '" + loginCliente + "' AND id_user_dest = '" + loginAmigo + "' OR id_user_orig = '" + loginAmigo + "' AND id_user_dest = '" + loginCliente + "'");
        System.out.println("Totales" + query_buscar_mensajes_totales);
        int contador = 0;
        
        Statement stmt;
        ResultSet rs;
        
        stmt = (Statement) con.createStatement();
        try {
            rs = stmt.executeQuery(query_buscar_mensajes_totales);
            while(rs.next()){
                contador++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
        

        return contador;
    }
    

    public int getNumeroDeMessagesEnFecha(String loginCliente, String loginAmigo, String time) throws SQLException {
        
        this.conectarBD();
        System.out.println("YEYO EN MI IPHONE\n" + time);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //String query_buscar_mensajes_hoy = ("SELECT * FROM message WHERE id_user_orig = '" + loginCliente + "' AND id_user_dest ='" + loginAmigo + "' OR id_user_orig = '" + loginAmigo + "' AND id_user_dest = '" + loginCliente + "'");
        String query_buscar_mensajes_hoy = ("SELECT * FROM message WHERE (id_user_orig = '" + loginCliente + "' AND id_user_dest ='" + loginAmigo + "' AND datetime BETWEEN '" + time + "' AND '" + timestamp.toString() + "') OR (id_user_orig = '" + loginAmigo + "' AND id_user_dest = '" + loginCliente + "' AND datetime BETWEEN '" + time + "' AND '" + timestamp.toString() + "');"); 
        System.out.println(query_buscar_mensajes_hoy);
        int contador = 0;
            
        Statement stmt;
        ResultSet rs;
        
        stmt = (Statement) con.createStatement();
        try {
            rs = stmt.executeQuery(query_buscar_mensajes_hoy);
            while(rs.next()){
                contador++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }   
        
        return contador;
    }

    ArrayList getMessage(String loginCliente, String loginAmigo, String time, ArrayList<Message> messages) throws SQLException {
        
        this.conectarBD();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String query_buscar_mensajes_hoy = ("SELECT * FROM message WHERE (id_user_orig = '" + loginCliente + "' AND id_user_dest ='" + loginAmigo + "' AND datetime BETWEEN '" + time + "' AND '" + timestamp.toString() + "') OR (id_user_orig = '" + loginAmigo + "' AND id_user_dest = '" + loginCliente + "' AND datetime BETWEEN '" + time + "' AND '" + timestamp.toString() + "');"); 
        //String query_buscar_mensajes_hoy = ("SELECT * FROM message WHERE (id_user_orig = '" + loginCliente + "' AND id_user_dest ='" + loginAmigo + "') OR (id_user_orig = '" + loginAmigo + "' AND id_user_dest = '" + loginCliente + "') AND datetime BETWEEN '" + time + "' AND '" + timestamp.toString() + "';"); 
        
        Statement stmt = (Statement) con.createStatement();;
        ResultSet rs;
        
        try {
            rs = stmt.executeQuery(query_buscar_mensajes_hoy);
            while(rs.next()){
                Message m = new Message();

                String user_orig = rs.getString("id_user_orig");
                String user_dest = rs.getString("id_user_dest");
                Timestamp fecha = rs.getTimestamp("datetime");
                String text = rs.getString("text");

                System.out.println(user_orig);
                System.out.println(user_dest);

                m.setUser_orig(user_orig);
                m.setUser_dest(user_dest);
                m.setDate(fecha.toString());
                m.setText(text);

                messages.add(m);

                this.setReadMessage(user_orig, user_dest, fecha.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
        
        //System.out.println("Estoy en lo mas profundo y quiero saber el tamaño de los mensajes : \n" + messages.size());
        
        return messages;
    }

    private void setReadMessage(String user_orig, String user_dest, String date) throws SQLException  {
        
        this.conectarBD();
        Statement stmt = (Statement) con.createStatement();       
        
        String query_buscar_username = ("UPDATE message SET read_msg = 1 WHERE id_user_orig = '" + user_orig + "' AND id_user_dest = '" + user_dest + "' AND datetime= '" + date + "'");
        System.out.println(query_buscar_username);
        
        try {
            stmt.executeUpdate(query_buscar_username);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
        
    }

    void setDisConectedToDB(String login) throws SQLException {
        
        this.conectarBD();
        Statement stmt;       
        
        stmt = (Statement) con.createStatement();
        
        String query_buscar_username = ("UPDATE User SET state = 0 WHERE id_user = '" + login + "'");
 
        try {    
            stmt.executeUpdate(query_buscar_username);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
    }

    String getURLPhoto(String login) throws SQLException{
        
        this.conectarBD();
        Statement stmt; 
        ResultSet rs;
        String URL = "data\\ZDefault\\default.jpg";
        
        stmt = (Statement) con.createStatement();
        
        String query_buscar_username = ("SELECT photo FROM user WHERE id_user = '" + login + "';");
 
        try { 
            rs = stmt.executeQuery(query_buscar_username);
            if(rs.next()){
                if(rs.getString("photo").contains("data")){
                    URL = rs.getString("photo");
                }
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }

        return URL;
    }

    void setMSGToDB(String login_orig, String login_dest, String text) throws SQLException {
        
        this.conectarBD();
        Statement stmt;       
        stmt = (Statement) con.createStatement();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        try {
            String query_insertar_msgs = ("INSERT INTO message VALUES('" + login_orig + "', '" + login_dest + "', '" + timestamp.toString() + "', 0, 1, '" + text + "');");
            stmt.executeUpdate(query_insertar_msgs); 
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stmt != null) { 
                stmt.close(); 
                this.desconectarBD();
            }
        }
    }
}
    

