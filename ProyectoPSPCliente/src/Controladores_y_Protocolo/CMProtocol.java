/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores_y_Protocolo;

import Clases.Message;
import Clases.User;
import CustomsModelLists.CustomListModelMessage;
import CustomsModelLists.CustomListModelUser;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class CMProtocol {
    
    Date date = new Date();
    DateFormat hourdateFormat;
    String output;
    ArrayList<String> decodePhotoLines = new ArrayList<String>();
    String photoPacket;
    CustomListModelMessage messages;
    
    public CMProtocol(){
        date = new Date();
        hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        messages = new CustomListModelMessage();
        
    }
    
    public String writeGetDatosUser(String loginAmigo) {
        
        output = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#CLIENT#ALLDATA_USER#" + loginAmigo;
        
        return output;
    }
    
    public String writeLogin(String login, String passw){
        
        output = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#CLIENT#LOGIN#" + login + "#" + passw + "";
        
        return output;
    }
    
    public String writePideMessages(String loginCliente, String loginAmigo, int resta){
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 24 * resta * 60 * 60 * 1000L);
        
        output = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#CLIENT#MSGS#" + loginCliente + "#" + loginAmigo + "#" + timestamp;
        
        System.out.println("Lo que enviamos desde el protocolo del cliente es: \n" + output);
        
        return output;
    }
    
    public String writeFriendStatus(String loginCliente, String loginAmigo){
        
        output = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#CLIENT#STATUS#" + loginCliente + "#" + loginAmigo;
    
        return output;
    }
    
    public String writeSend() {
        String send = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#CLIENT#MSGS#OK_SEND!";
        
        return send;
    }
    
    public String writeAllReceived() {
        output = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#CLIENT#MSGS#ALL_RECEIVED";
        
        return output;
    }
    
    public String writeMessage(String login, String id_user, String text) {
        String message = "NULL";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        if(text.length() < 1000){
            message = "PROTOCOLCRISTOMESSENGER1.0#" + timestamp + "#CLIENT#CHAT#" + login + "#" + id_user + "#" + text;
        }
        
        System.out.println(message);
        
        return message;
    }    
    
    public String writeGetFotoUser(String loginClient) {
        String message = "";
        
        message = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#CLIENT#GET_PHOTO#" + loginClient;
     
        return message;
    }

    public CustomListModelUser getFriends(String fromServer, CustomListModelUser users) {
        
        String[] cadena_troceada = fromServer.split("#");
        int posicion_login = 7;
        int posicion_conect = 8;
        
        int iteraciones = Integer.parseInt(cadena_troceada[6]);
        //System.out.println(cadena_troceada.length);
        
        for(int i = 0; i < iteraciones ; i++){
            
            User u = new User();
            u.setId_user(cadena_troceada[posicion_login]);
            if(cadena_troceada[posicion_conect].equals("CONNECTED")){
                u.setState(true);
            }else{
                u.setState(false);
            }
            
            users.addUser(u);
            
            if(posicion_login >= cadena_troceada.length || posicion_login == cadena_troceada.length - 2){
                
            }else{
                posicion_login += 2;
                posicion_conect += 2;
            }
        }
        return users;        
    }
    
    public boolean checkMainMessage(String fromServer){
        String[] cadena_troceada = fromServer.split("#");
        boolean correcto = false;
        
        if(cadena_troceada[0].equals("PROTOCOLCRISTOMESSENGER1.0")){
            if(cadena_troceada[2].equals("SERVER")){
                correcto = true;
            }
        }
        
        return correcto;
    }

    public boolean checkLoginMessageFromServer(String fromServer) {
        boolean correcto = false;
        
        if(fromServer.equals("PROTOCOLCRISTOMESSENGER1.0#12:37:25 12/02/2020#SERVER#BAD_LOGIN")){
            correcto = false;
        }else {
            correcto = true;
        }
        
        return correcto;
    }
    
  

    public void getMessage(String loginCliente, String loginAmigo, String fromServer) {
        
        String[] cadena_troceada = fromServer.split("#");
        
        int posicion_loginClient = 4;
        int posicion_loginAmigo = 5;
        int posicion_date = 6;
        int posicion_text = 7;
        
            
        Message u = new Message();
        
        u.setUser_orig(cadena_troceada[posicion_loginClient]);
        u.setUser_dest(cadena_troceada[posicion_loginAmigo]);
        u.setDate(cadena_troceada[posicion_date]);
        u.setText(cadena_troceada[posicion_text]);
        u.setRead(true);
        u.setSent(true);
            
        this.messages.addMessage(u);
    }  
    
    public boolean getConectado(String fromServer){
        String[] cadena_troceada = fromServer.split("#");
        boolean conectado = false;
        
        if(this.checkMainMessage(fromServer) == true){
            if(cadena_troceada[5].equals("CONNECTED")){
                conectado = true;
            }else if(cadena_troceada[5].equals("NOT_CONNECTED")){
                conectado = false;
            }else if(cadena_troceada[4].equals("BAD_PKG")){
                System.out.println("HA HABIDO UN PROBLEMA CON EL RETORNO DE EL STATUS");
            }
        }
        return conectado;
    }

    public User getInfoUserAmigo(String fromServer, User user) {
        String[] cadena_troceada = fromServer.split("#");
        
        if(this.checkMainMessage(fromServer) == true){
            user.setName(cadena_troceada[5]);
            user.setSurname1(cadena_troceada[6]);
            user.setSurname2(cadena_troceada[7]);
        }else{
            System.out.println("HA HABIDO UN PROBLEMA CON EL RETORNO DE INFORMACION DEL USUARIO SOLICITADO");
        }
        
        return user;
    }

    public int checkMensajesRestantes(String fromServer) {
        String[] cadena_troceada = fromServer.split("#");
        int mensajesRestantes;
        
        mensajesRestantes = Integer.parseInt(cadena_troceada[6]);
        
        return mensajesRestantes;
    }
    
    public int checkMensajesRestantesFecha(String fromServer) {
        String[] cadena_troceada = fromServer.split("#");
        int mensajesRestantes;
        
        mensajesRestantes = Integer.parseInt(cadena_troceada[7]);
        
        return mensajesRestantes;
    }

    public CustomListModelMessage getAllMessages() {
        return messages;
    }

    public void resetMessages() {
        CustomListModelMessage messages1 = new CustomListModelMessage();
        this.messages = messages1;
    }

    public void getPhotoPacket(String input) {
        //PROTOCOLCRISTOMESSENGER1.0#FECHA/HORA#SERVER#RESPONSE_MULTIMEDIA#<LOGIN_CLIENTE>#<TOTAL_BYTES_MULTIMEDIA>#<SIZE_PACKET_MULTIMEDIA>#@512BYTES_FOTO
        String[] cadena_troceada = input.split("#");
        
        System.out.println(input);
        
        for(int i = 0; i < cadena_troceada.length; i++){
            System.out.println(cadena_troceada[i] + " " + i);
        }
        
        photoPacket = cadena_troceada[7];
        decodePhotoLines.add(new String(Base64.getDecoder().decode(photoPacket)));
    }

    public ArrayList returnCompletePhoto() {
        return decodePhotoLines;
    }









  
}
