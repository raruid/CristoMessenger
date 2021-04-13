/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores_y_modelos;

import Clases.Message;
import Clases.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Controller {
    
    Model model;
    boolean correcto = false;
    ArrayList<String> arrayLinesPhoto = new ArrayList<String>();
    ArrayList<String> encodeLinesPhoto = new ArrayList<String>();

    public Controller() throws SQLException {
        model = new Model();
    }
    
    public boolean getLoginPassw(String login, String passw){
        String comprobado = "";
        
        try {
            comprobado = model.getNombre(login, passw);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(comprobado.equals("YES")){
            correcto = true;
        }else{
            correcto = false;
        }
        
        return correcto;
    }

    public String getFriends(String login, String passw, Date date){

        String amigos_frase_protocolo;
        ArrayList<User> users = new ArrayList();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        
        try {
            users = model.getAmigos(login, passw, users);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        amigos_frase_protocolo = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#LOGIN_CORRECT#" + login + "#FRIENDS#" + users.size();
        
        for(int i = 0; i < users.size(); i++){
            String conectado;
            if(users.get(i).getState() == true){
                conectado = "CONNECTED";
            }else{
                conectado = "NOT_CONNECTED";
            }
            amigos_frase_protocolo += "#" + users.get(i).getId_user() + "#" + conectado;
        }
        
        return amigos_frase_protocolo;
        
    }

    void setConnectedToDB(String login, String passw) {
        try {
            model.setConectedToDB(login, passw);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean getStatus(String login_amigo){
        boolean status = false;
        
        try {
            if(model.checkStatus(login_amigo) == true){
                status = true;
            }else{
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }

    String getInformacionUser(String loginAmigo){
        
        String infoAmigo = "";
        try {
            infoAmigo = model.getUserInfo(loginAmigo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return infoAmigo;
    }

    String getNumeroDeMessages(String loginCliente, String loginAmigo, String time) throws ParseException {
        
        String mensajesTotales;
        String mensajesFecha;
        String cadena = "";
        
        try {
            mensajesTotales = String.valueOf(model.getNumeroTotalDeMessages(loginCliente, loginAmigo));
            mensajesFecha = String.valueOf(model.getNumeroDeMessagesEnFecha(loginCliente, loginAmigo, time));
            cadena = mensajesTotales + "#" + mensajesFecha;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cadena;
    }

    ArrayList<Message> getMessages(String loginCliente, String loginAmigo, String time) throws SQLException {
        
        ArrayList<Message> messages = new ArrayList();
        
        messages = model.getMessage(loginCliente, loginAmigo, time, messages);
        
        return messages;
    }

    void setDisConnectedToDB(String login) {
        try {
            model.setDisConectedToDB(login);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getPhoto(String nombre, String apellidos) {

        //this.getPhotoUser(nombre, apellidos);
        
    }

    public void savePhotoUser(String login) throws IOException {
        
        String line = "";
        int cont = 0;
        ArrayList<String> arrayLines = new ArrayList<String>();
        ArrayList<String> encodeLines = new ArrayList<String>();
        //ArrayList<String> decodeLines = new ArrayList<String>();
        
        String URL = "data\\ZDefault\\default.jpg";
        
        try {
            URL = model.getURLPhoto(login);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("POR FABOR HAZME CASO " + URL);
        
        //Lectura de fichero a ArrayList de String len 512chars
        try(FileInputStream input = new FileInputStream(URL)){
            int valor = input.read();
            while(valor != -1){
                if (cont > 511) {
                    arrayLines.add(line);
                    line = "";
                    cont = 0;
                }
                line += (char)valor;
                valor=input.read();
                cont++;
            }
            if (cont > 0) {
                arrayLines.add(line);
                cont = 0;
            }
        } catch (FileNotFoundException ex) {
            URL = "data\\ZDefault\\default.jpg";
            

            FileInputStream input = null;
            try {
                input = new FileInputStream(URL);
                int valor = input.read();
                while(valor != -1){
                    if (cont > 511) {
                        arrayLines.add(line);
                        line = "";
                        cont = 0;
                    }
                line += (char)valor;
                valor=input.read();
                cont++;
                }
                if (cont > 0) {
                    arrayLines.add(line);
                    cont = 0;
                }
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Encode de la cadena a Base64
        for (String s : arrayLines) {
            encodeLines.add(Base64.getEncoder().encodeToString(s.getBytes()));
        }    
        
        this.arrayLinesPhoto = arrayLines;
    }

    boolean checkPhotoSize(int contador_vueltas_photo) {
        boolean correcto = false;
        
        if(contador_vueltas_photo < arrayLinesPhoto.size()){
            correcto = true;
        }
        
        return correcto;
    }

    long totalSizePacket() {
        long size = 0;
        
        for(int i = 0; i < arrayLinesPhoto.size(); i++){
            size += arrayLinesPhoto.get(i).length();
        }
        
        return size;
    }

    int getSizeMensaje(int size_mensaje, int contador_vueltas_photo) {
        
        size_mensaje = arrayLinesPhoto.get(contador_vueltas_photo).length();
        return size_mensaje;
    }

    String returnPhotoBytes(String mensajePhoto, int contador_vueltas_photo) {
        
       mensajePhoto = Base64.getEncoder().encodeToString(arrayLinesPhoto.get(contador_vueltas_photo).getBytes());
        //encodeLines.add(
        //Base64.getEncoder().encodeToString(s.getBytes()));
        
        return mensajePhoto;
    }

    void setMessageToDB(String login_orig, String login_dest, String text) {
        try {
            model.setMSGToDB(login_orig, login_dest, text);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
