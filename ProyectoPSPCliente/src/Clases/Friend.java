package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Friend {    
    
    private String user_orig;
    private String user_dest;
    private boolean accept_request;

    public Friend() {        
        this.user_orig = "NULL";
        this.user_dest = "NULL";
        this.accept_request = false;
    
    }

    public Friend(String user_orig, String user_dest, boolean accept_request) {
        this.user_orig = user_orig;
        this.user_dest = user_dest;
        this.accept_request = accept_request;
    }

    public void setUser_orig(String user_orig) {
        this.user_orig = user_orig;
    }

    public void setUser_dest(String user_dest) {
        this.user_dest = user_dest;
    }

    public void setAccept_request(boolean accept_request) {
        this.accept_request = accept_request;
    }

    public String getUser_orig() {
        return user_orig;
    }

    public String getUser_dest() {
        return user_dest;
    }

    public boolean isAccept_request() {
        return accept_request;
    }      
    
    
}


