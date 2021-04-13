/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores_y_modelos;

import CMSERVER.CMServer;
import CMSERVER.ServerThread;
import Clases.Message;
import Controladores_y_modelos.Controller;
import Controladores_y_modelos.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.State.WAITING;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CMProtocol {
    
    Controller controlador;
    ServerThread server;
    String theOutput;
    Date date;
    DateFormat hourdateFormat; 
    ArrayList<Message> messages;
    int numeroDeMensajes;
    int numeroDeMensajesOrdenado;
    int contador_vueltas_photo = 0;
    long totalSizePacket = 0;
    String loginCliente;
    String loginClientePhoto;
    //PrintWriter out;
    //BufferedReader in;

    public CMProtocol(ServerThread aThis) throws SQLException {
        controlador = new Controller();
        server = aThis;
        date = new Date();
        hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        messages = new ArrayList();
        numeroDeMensajes = 0;
        numeroDeMensajesOrdenado = 0;
        //this.out = out;
        //this.in = in;
    }
    
    

    public String processInput(String theInput) throws SQLException, IOException {
        
        
        theOutput = "";
        CMServer.debugArea.setText(CMServer.debugArea.getText() + "\nLa cadena que hemos recibido es: \n" + theInput);

        
        if(theOutput != null) {
            if(theInput.contains("#")){
                //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El mensaje ha pasado la primera prueba..\n");
                if(checkMessage(theInput) == 1){
                    theOutput = getLoginPasswYMandar(theInput);
                }else if(checkMessage(theInput) == 2){
                    theOutput = getConectadoUsuario(theInput);
                }else if(checkMessage(theInput) == 3){
                    theOutput = getDatosUser(theInput);
                }else if(checkMessage(theInput) == 4){
                    try {
                        theOutput = getNumeroDeMessages(theInput);
                    } catch (ParseException ex) {
                        Logger.getLogger(CMProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(checkMessage(theInput) == 5){
                    try {
                        theOutput = this.returnMessages();
                    } catch (ParseException ex) {
                        Logger.getLogger(CMProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(checkMessage(theInput) == 7){
                    theOutput = setMessagesDB(theInput);
                }else if(checkMessage(theInput) == 6){
                    catchAllReceived(theInput);
                }else if(checkMessage(theInput) == 8){
                    this.getPhoto(theInput);
                }else if(checkMessage(theInput) == 9){
                    this.catchPhotoReceived(theInput);
                }else{
                    CMServer.debugArea.setText(CMServer.debugArea.getText() + "Mensaje incorrecto\n");
                    theOutput = null;
                }
            }else{
                CMServer.debugArea.setText(CMServer.debugArea.getText() + "Mensaje incorrecto\n");
                theOutput = null;
            }
        }else{
            CMServer.debugArea.setText(CMServer.debugArea.getText() + "Mensaje incorrecto\n");
            theOutput = null;
        }
            
        return theOutput;
    }    

    private int checkMessage(String theInput) {
        int correcto = 0;
        
        String[] cadena_troceada = theInput.split("#");
        
        if(cadena_troceada[0].equals("PROTOCOLCRISTOMESSENGER1.0")){
            //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El mensaje es compatible con el protocolo..\n");
            //CMServer.debugArea.setText(CMServer.debugArea.getText() + "Hora del envio: " + cadena_troceada[1] + "\n");
            if(cadena_troceada[2].equals("CLIENT")){
                //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El mensaje proviene del cliente..\n");
                if(cadena_troceada[3].equals("LOGIN")){
                    //System.out.println("La comprobacion de login se hara en breves..\n");
                    //System.out.println("El mensaje del cliente es: " + theInput + "\n");
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "La comprobacion de login se hara en breves..\n");
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El mensaje del cliente es: " + theInput + "\n");
                    correcto = 1;
                }else if(cadena_troceada[3].equals("STATUS")){
                    //System.out.println("El cliente quiere consultar el status de otro cliente\n");
                    //System.out.println("El mensaje del cliente es: " + theInput + "\n");
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El cliente quiere consultar el status de otro cliente\n");
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El mensaje del cliente es: " + theInput + "\n");
                    correcto = 2;
                }else if(cadena_troceada[3].equals("ALLDATA_USER")){
                    //System.out.println("El cliente quiere consultar los datos de otro cliente\n");
                    //System.out.println("El mensaje del cliente es: " + theInput + "\n");
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El cliente quiere consultar los datos de otro cliente\n");
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El mensaje del cliente es: " + theInput + "\n");
                    correcto = 3;
                }else if(cadena_troceada[3].equals("MSGS")){
                    if(cadena_troceada[4].equals("OK_SEND!")){
                        correcto = 5;
                    }else if(cadena_troceada[4].equals("ALL_RECEIVED")){
                        correcto = 6;
                    }else{
                        
                        //System.out.println("El mensaje del cliente es: " + theInput + "\n");
                        //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El cliente quiere acceder al numero de mensajes\n");
                        //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El mensaje del cliente es: " + theInput + "\n");
                        correcto = 4;
                    }
                }else if(cadena_troceada[3].equals("CHAT")){
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El cliente quiere escribir mensajes\n");
                    correcto = 7;
                }else if(cadena_troceada[3].equals("GET_PHOTO")){
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El cliente quiere acceder a las fotos\n");
                    correcto = 8;
                }else if(cadena_troceada[3].equals("PHOTO_RECEIVED")){
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "El cliente ha finalizado su transmision de la foto\n");
                    correcto = 9;
                }
                
                //PROTOCOLCRISTOMESSENGER1.0#FECHA/HORA#CLIENT#PHOTO_RECEIVED#<LOGIN_CLIENTE>
            }else{
                CMServer.debugArea.setText(CMServer.debugArea.getText() + "Su mensaje es incorrecto #CM02\n");
                correcto = 0;
            }
        }else{
            CMServer.debugArea.setText(CMServer.debugArea.getText() + "Su mensaje es incorrecto #CM01\n");
            correcto = 0;
        }
        
        return correcto;
    }
    
    private String getLoginPasswYMandar(String theInput) throws SQLException{
        String theOutput;
        
        String login = this.getLogin(theInput);
        this.loginCliente = login;
        String passw = this.getPassw(theInput);
        
        if(controlador.getLoginPassw(login, passw) == true){
            this.setConnectedToDB(login, passw);
            theOutput = this.devolverAmigos(login, passw);
            System.out.println(theOutput);
        }else{
            //Caso 3: obtenerhora y fecha y salida por pantalla con formato:
                                         
            theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#BAD_LOGIN";
        } 
        return theOutput;
    }

    private String getLogin(String theInput) {

        String login;

        String[] cadena_troceada = theInput.split("#");
        
        login = cadena_troceada[4];
        
        return login;
    }

    private String getPassw(String theInput) {
        String passw;

        passw = theInput.substring(theInput.lastIndexOf("#")+1, theInput.length());
        //System.out.println(passw);
        
        return passw;
    }

    private String devolverAmigos(String login, String passw) throws SQLException {
        String out;
        
        out = controlador.getFriends(login, passw, date);
        
        return out;
    }

    private String getConectadoUsuario(String theInput) {
        
        String[] cadena_troceada = theInput.split("#");
        boolean conectado = false;
        
        conectado = controlador.getStatus(cadena_troceada[5]);
        //PROTOCOLCRISTOMESSENGER1.0#FECHA/HORA#SERVER#STATUS#<ID_AMIGO>#[CONNECTED | NOT CONNECTED]
        if(conectado == true){
            theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#STATUS#" + cadena_troceada[5] + "#CONNECTED";
        }else if(conectado == false){
            theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#STATUS#" + cadena_troceada[5] + "#NOT_CONNECTED";
        }else{
            theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#BAD_PKG";
        }
        
        return theOutput;
    }

    private String getDatosUser(String theInput) {
        
        String[] cadena_troceada = theInput.split("#");
        String loginAmigo = cadena_troceada[4];
        String infoAmigo = "NULL";
        
        infoAmigo = controlador.getInformacionUser(loginAmigo);
        
        theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#ALLDATA_USER#" + loginAmigo + "#" + infoAmigo;

        return theOutput;
    }

    private String getNumeroDeMessages(String theInput) throws ParseException {
        
        String[] cadena_troceada = theInput.split("#");
        this.numeroDeMensajes = 0;
        this.numeroDeMensajesOrdenado = 0;
        
        System.out.println("Me preparo para leer mis cosillas");
        String send = theInput;
        System.out.println("Send dice:\n " + send);
        
        try {
            String cadena_mensajes = controlador.getNumeroDeMessages(cadena_troceada[4], cadena_troceada[5], cadena_troceada[6]);
            messages = controlador.getMessages(cadena_troceada[4], cadena_troceada[5], cadena_troceada[6]);
            theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#MSGS#" + cadena_troceada[4] + "#" + cadena_troceada[5] + "#" + cadena_mensajes;
            System.out.println("ABER K DECIMOS POR AQUI.. \n" + theOutput);
        } catch (SQLException ex) {
            Logger.getLogger(CMProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return theOutput;
    }

    
    public String returnMessages() throws ParseException {
        
        String messageFinal = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#MSGS";
        
        if(numeroDeMensajes == 0){
            this.numeroDeMensajes = messages.size();
        }
        
        if(numeroDeMensajesOrdenado < numeroDeMensajes){
            String user_orig = messages.get(numeroDeMensajesOrdenado).getUser_orig();
            String user_dest = messages.get(numeroDeMensajesOrdenado).getUser_dest();
            String date = messages.get(numeroDeMensajesOrdenado).getDate();
            String text = messages.get(numeroDeMensajesOrdenado).getText();
            
            messageFinal += "#" + user_orig + "#" + user_dest + "#" + date + "#" + text;
            
            theOutput = messageFinal;
            
            numeroDeMensajesOrdenado++;
        }
        
        System.out.println("Y ABER POR AKI K DECIMOS POR AQUI.. \n" + messageFinal);
        
        return theOutput;

    }
    
    
    private void setConnectedToDB(String login, String passw) throws SQLException {
        controlador.setConnectedToDB(login, passw);
    }    

    public String setMessagesDB(String theInput) {
        
        //PROTOCOLCRISTOMESSENGER1.0#FECHA/HORA#SERVER#CHAT#<LOGIN_ORIG#<LOGIN_DEST>#MESSAGE_SUCCESFULLY_PROCESSED#TIMESTAMP
        //PROTOCOLCRISTOMESSENGER1.0#FECHA/HORA#CLIENT#CHAT#<LOGIN_ORIG#<LOGIN_DEST>#<MESSAGE>
        
        String[] cadena_troceada = theInput.split("#");
        
        String login_orig = cadena_troceada[4];
        String login_dest = cadena_troceada[5];
        String text = cadena_troceada[6];
        
        controlador.setMessageToDB(login_orig, login_dest, text);
        
        theOutput = "NO_RESPONSE";
        
        return theOutput;
    }

    private void catchAllReceived(String theInput) {
        theOutput = "NO_RESPONSE";
    }
    
    private void catchPhotoReceived(String theInput) {
        theOutput = "NO_RESPONSE";    
    }
    

    public String getLoginCliente() {
        return this.loginCliente;
    }

    public void setDisconnectToDB(String login) {
        controlador.setDisConnectedToDB(login);    
    }

    private String getPhoto(String theInput) {
        
        String[] cadena_troceada = theInput.split("#");
        String loginAmigo = cadena_troceada[4];
        String infoAmigo = "NULL";
        loginClientePhoto = loginAmigo;
        
        contador_vueltas_photo = 0;
        totalSizePacket = 0;
        
        infoAmigo = controlador.getInformacionUser(loginAmigo);
        
        String[] info = infoAmigo.split("#");
        
        try {
            controlador.savePhotoUser(loginAmigo);
        } catch (IOException ex) {
            Logger.getLogger(CMProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.totalSizePacket = controlador.totalSizePacket();
        
        theOutput = "returnPhoto";
        
        return theOutput;
    }
    
    public String returnPhoto(){
        
        String mensajePhoto = "";
        int size_mensaje = 0;
        
        if(controlador.checkPhotoSize(contador_vueltas_photo)){
            size_mensaje = controlador.getSizeMensaje(size_mensaje, contador_vueltas_photo);
            mensajePhoto = controlador.returnPhotoBytes(mensajePhoto, contador_vueltas_photo);
            theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#RESPONSE_MULTIMEDIA#" + loginClientePhoto + "#" + totalSizePacket + "#" + size_mensaje + "#" + mensajePhoto;
            contador_vueltas_photo++;
        }else{
            theOutput = "PROTOCOLCRISTOMESSENGER1.0#" + hourdateFormat.format(date) + "#SERVER#ENDING_MULTIMEDIA_TRANSMISSION#" + loginClientePhoto;
        }
        
        return theOutput;
    }

    public String writeStartMultimedia() {
        theOutput = "PROTOCOLCRISTOMESSENGER1.0#FECHA/HORA#SERVER#STARTING_MULTIMEDIA_TRANSMISSION_TO#" + loginClientePhoto + "";
        
        return theOutput;    
    }

    public int getMessagesSize() {
        System.out.println("Size del vector de mensajes " + messages.size());
        return messages.size();
    }

    public String writeSuccesfully(String loginOrig, String loginDest, String time) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String message = "PROTOCOLCRISTOMESSENGER1.0#" + timestamp + "#SERVER#CHAT#" + loginOrig + "#" + loginDest + "#MESSAGE_SUCCESFULLY_PROCESSED#" + time + "";
        return message;
    }


    
}   
