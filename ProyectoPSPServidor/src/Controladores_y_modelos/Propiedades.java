/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores_y_modelos;

/**
 *
 * @author Usuario
 */
class Propiedades {
    
    private String URL;
    private String user;
    private String password;

    public Propiedades() {
        this.URL = "NULL";
        this.user = "NULL";
        this.password = "NULL";
    }

    public Propiedades(String URL, String user, String password) {
        this.URL = URL;
        this.user = user;
        this.password = password;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getURL() {
        return URL;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }      
}
