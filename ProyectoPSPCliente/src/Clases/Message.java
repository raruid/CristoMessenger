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
public class Message {
    
    private String user_orig;
    private String user_dest;
    private String date;
    private String hour;
    private boolean sent;
    private boolean read;
    private String text;

    public Message() {
        this.user_orig = "NULL";
        this.user_dest = "NULL";
        this.date = "NULL";
        this.hour = "NULL";
        this.sent = false;
        this.read = false;
        this.text = "NULL";
    }

    public Message(String user_orig, String user_dest, String date, String hour, boolean sent, boolean read, String text) {
        this.user_orig = user_orig;
        this.user_dest = user_dest;
        this.date = date;
        this.hour = hour;
        this.sent = sent;
        this.read = read;
        this.text = text;
    }

    public void setUser_orig(String user_orig) {
        this.user_orig = user_orig;
    }

    public void setUser_dest(String user_dest) {
        this.user_dest = user_dest;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser_orig() {
        return user_orig;
    }

    public String getUser_dest() {
        return user_dest;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public boolean getSent() {
        return sent;
    }

    public boolean getRead() {
        return read;
    }

    public String getText() {
        return text;
    }   
/*    
    public void getMessage(){
    }
*/     
}
