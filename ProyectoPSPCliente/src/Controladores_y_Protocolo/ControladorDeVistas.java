/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores_y_Protocolo;

import Vistas.Cliente;
import Vistas.ClienteLogin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Usuario
 */
public class ControladorDeVistas {
    
    Socket socket;
    String hostName;
    CMProtocol protocolo;
    int portNumber;
    String login;
    String passw;
    ClienteLogin lo;
    PrintWriter out; 
    BufferedReader in; 

    public ControladorDeVistas(String hostName, int portNumber, String login, String passw, ClienteLogin lo) throws IOException {
        this.protocolo = new CMProtocol();
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.login = login;
        this.passw = passw;
        
        socket = new Socket(hostName, portNumber);
        
        out = new PrintWriter(this.socket.getOutputStream(), true);
        in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        
        this.lo = lo;
    }

    

    public void loginUsuario() throws IOException, IOException{
        
        
        
        String input = "NULL";
        
        
        input = protocolo.writeLogin(this.login, this.passw);
        
        
            try  {
                String fromServer;
                
                out.println(input);
                fromServer = in.readLine();
                if(protocolo.checkLoginMessageFromServer(fromServer) == true){
                    System.out.println("El server dice:" + fromServer);
                    Cliente client = new Cliente(this.socket, this.protocolo, this.login, this.out, this.in, fromServer);
                    lo.setVisible(false);
                    client.setVisible(true);
                }else{
                    lo.mensajeError();
                }
                
                /*
                users = protocolo.getFriends(fromServer);
                System.out.println("Insertando los amigos en el JList..");
                this.listaFriends.setModel(users);
                */

                //}else{
                    //System.out.println("Este no es un mensaje valido");
                //}
                //System.out.println("Server: " + fromServer);
                
                
            }catch (UnknownHostException e) {
                System.err.println("Don't know about host " + hostName);
                System.exit(1);

            }catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
                System.exit(1);
            }
    }
}
