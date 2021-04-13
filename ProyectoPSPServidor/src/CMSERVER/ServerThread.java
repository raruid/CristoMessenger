/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMSERVER;

import Clases.Message;
import Controladores_y_modelos.CMProtocol;
import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ServerThread extends Thread {
    
    private Socket socket = null;
    private int asignarLogin = 0;
    private String loginCliente = "";
    private CMProtocol cmprot;
    CMServer cmserver;
    PrintWriter out;
    BufferedReader in;
 
    public ServerThread(Socket socket, CMServer aThis) {
        super("KKMultiServerThread");
        this.socket = socket;
        cmserver = aThis;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            cmprot = new CMProtocol(this);
        } catch (SQLException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public String returnLogin(){
        return this.loginCliente;
    }
    
    public PrintWriter returnPrintWriter(){
        return this.out;
    }
    
    public void run() {
 
        try  {
            String inputLine, outputLine;
            
            while ((inputLine = in.readLine()) != null) {
                if(inputLine.contains("OK_SEND!")){
                    System.out.println("Lo que recibo del clientes es: " + inputLine);
                    int iteraciones = 0;
                    iteraciones = cmprot.getMessagesSize();
                    System.out.println(iteraciones);
                    for(int i = 0; i < iteraciones; i++){
                        try {
                            outputLine = cmprot.returnMessages();
                            System.out.println("Lo que sale del server " + outputLine);
                            out.println(outputLine);
                        } catch (ParseException ex) {
                            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else if(inputLine.contains("CHAT")){
                    if(inputLine.contains("RECEIVED_MESSAGE")){
                        String [] cadena_troceada = inputLine.split("#");

                        PrintWriter print = this.cmserver.returnOut(cadena_troceada[5]);
                        String message = cmprot.writeSuccesfully(this.loginCliente, cadena_troceada[5], cadena_troceada[6]);
                        print.println(message);
                    }else{
                        String [] cadena_troceada = inputLine.split("#");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                        PrintWriter print = this.cmserver.returnOut(cadena_troceada[5]);
                        cmprot.setMessagesDB(inputLine);
                        String message = inputLine + "#" + timestamp;
                        print.println(message);
                    }
                    
                }else{
                    outputLine = cmprot.processInput(inputLine);
                    //CMServer.debugArea.setText(CMServer.debugArea.getText() + "\n" + "Lo que entra al server: " + inputLine);
                    System.out.println("El mensaje del cliente es: " + inputLine + "\n");
                    if(outputLine.equals("returnPhoto")){
                        outputLine = cmprot.writeStartMultimedia();
                        //CMServer.debugArea.setText(CMServer.debugArea.getText() + "\n" + "Lo que sale del server: " + outputLine);
                        out.println(outputLine);
                        outputLine = "returnPhoto";
                        while(outputLine.equals("returnPhoto")){
                            outputLine = cmprot.returnPhoto();
                            System.out.println("Lo que sale del server es: " + outputLine + "\n");
                            //CMServer.debugArea.setText(CMServer.debugArea.getText() + "\n" + "Lo que sale del server: " + outputLine);
                            out.println(outputLine);
                            if(outputLine.contains("RESPONSE_MULTIMEDIA")){
                                System.out.println("Lo que sale del server es: " + outputLine + "\n");
                                //CMServer.debugArea.setText(CMServer.debugArea.getText() + "\n" + "Lo que sale del server: " + outputLine);
                                outputLine = "returnPhoto";
                            }else{
                                System.out.println("NO RESPONSE");
                            }
                        }
                    }else if(outputLine != "NO_RESPONSE"){
                        System.out.println("Lo que finalmente se envia al cliente es:\n " + socket.getInetAddress().getHostAddress() + " " + outputLine);
                        out.println(outputLine);
                    }
                }
                    if(asignarLogin == 0){
                        this.loginCliente = cmprot.getLoginCliente();
                        asignarLogin++;
                    }
                
            }
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                cmprot.setDisconnectToDB(this.loginCliente);
                System.out.println("[" + this.loginCliente + "}" + " Se ha desconectado");
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.println("WAAASAAAAAAAA23");
    }
}
