/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Message;
import Clases.User;
import CustomsModelLists.CustomListModelUser;
import Controladores_y_Protocolo.CMProtocol;
import CustomsModelLists.CustomListModelMessage;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

/**
 *
 * @author Usuario
 */
public class Cliente extends javax.swing.JFrame {

    Socket socket;
    String hostName;
    CMProtocol protocolo;
    int portNumber;
    String login;
    String passw;
    aTask a;
    CustomListModelUser users = new CustomListModelUser();
    CustomListModelMessage messages = new CustomListModelMessage();
    PrintWriter out; 
    BufferedReader in; 
    
    
    //this.socket, this.protocolo, this.login, this.out, this.in, fromServer
    
    public Cliente(Socket socket, CMProtocol protocolo, String login, PrintWriter out, BufferedReader in, String friends) throws IOException {
        initComponents();
        
        this.socket = socket;
        this.protocolo = protocolo;
        this.login = login;
        this.portNumber = portNumber;
        this.out = out;
        this.in = in;
        
        users = protocolo.getFriends(friends, users);
        this.listaFriends.setModel(users);
        
        this.getFotoUser(this.login);
        
        //aTask a;
        
    }

    private Cliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        tituloCristo = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        messageTxt = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaMessages = new javax.swing.JList<>();
        btnEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaFriends = new javax.swing.JList<>();
        datosFriendTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblPhoto = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jTabbedPane7 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tituloCristo.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tituloCristo.setText("Cristo Messenger");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setViewportView(messageTxt);

        listaMessages.setModel(messages);
        listaMessages.setEnabled(false);
        jScrollPane2.setViewportView(listaMessages);

        btnEnviar.setText("-->");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEnviar)))
                .addContainerGap())
        );

        listaFriends.setModel(users);
        listaFriends.setToolTipText("");
        listaFriends.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaFriendsMouseClicked(evt);
            }
        });
        listaFriends.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaFriendsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaFriends);

        datosFriendTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datosFriendTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(datosFriendTxt)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tituloCristo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tituloCristo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 24, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datosFriendTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chats", jPanel1);
        jTabbedPane1.addTab("tab2", jTabbedPane5);
        jTabbedPane1.addTab("tab3", jTabbedPane6);
        jTabbedPane1.addTab("tab4", jTabbedPane7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaFriendsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaFriendsMouseClicked

            User us = new User();
            us = users.getUser(listaFriends.getSelectedIndex());
            
            String loginClient = us.getId_user();
            
        try {
            this.getDatosDeUsuario(us);
            protocolo.resetMessages();
            this.getMessages(this.login, loginClient);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listaFriendsMouseClicked

    private void listaFriendsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaFriendsValueChanged

    }//GEN-LAST:event_listaFriendsValueChanged

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        
        //PROTOCOLCRISTOMESSENGER1.0#FECHA/HORA#CLIENT#CHAT#<LOGIN_ORIG#<LOGIN_DEST>#<MESSAGE>
        //<MESSAGE> = *Cadena de Texto de 1000 caracteres m??ximo*

        User u = users.getUser(listaFriends.getSelectedIndex());
        
        String message = protocolo.writeMessage(this.login, u.getId_user(), messageTxt.getText());
        
        if(message != "NULL"){
            out.println(message);
        }else{
            System.out.println("EL MENSAJE ES MUY LARGO PARA SER ENVIADO");
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void datosFriendTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datosFriendTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datosFriendTxtActionPerformed

    private void getMessages(String loginUser, String loginAmigo) throws IOException{
        
        int mensajesRestantes = 0;
        int mensajesRestantesFecha = 0;
        int resta = 1;
        boolean sinMensajes = false;
        
        String input = protocolo.writePideMessages(loginUser, loginAmigo, resta);
        out.println(input);

        System.out.println("El imput que envio es: \n" + input);
        String fromServer = in.readLine();
        System.out.println("Lo que leo es: \n" + fromServer);
        
        mensajesRestantes = protocolo.checkMensajesRestantes(fromServer);
        mensajesRestantesFecha = protocolo.checkMensajesRestantesFecha(fromServer);
        
        if(mensajesRestantes != 0){
            while(mensajesRestantesFecha == 0){

                input = protocolo.writePideMessages(loginUser, loginAmigo, resta);
                out.println(input);

                System.out.println("El imput que envio es: \n" + input);
                fromServer = in.readLine();
                System.out.println("Lo que leo es: \n" + fromServer);
                System.out.println(fromServer);

                mensajesRestantes = protocolo.checkMensajesRestantes(fromServer);
                mensajesRestantesFecha = protocolo.checkMensajesRestantesFecha(fromServer);

                System.out.println("Mensajes Restantes1: " + mensajesRestantes);
                System.out.println("Mensajes en fecha1: " + mensajesRestantesFecha);

                resta++;
            }
            
            for(int i = 0; i < mensajesRestantesFecha; i++){
                
                String send = protocolo.writeSend();
                out.println(send);
                System.out.println("Envio esto(send) \n" + send);

                fromServer = in.readLine();
                System.out.println("Recibimos del server esto..\n" + fromServer);
                protocolo.getMessage(this.login, loginAmigo, fromServer);

            }
            
            messages = protocolo.getAllMessages();
            System.out.println("El tama??o del array de mensajes es: " + messages.getSize());
            this.listaMessages.setModel(messages);

            String allReceived = protocolo.writeAllReceived();

            out.println(allReceived);
            System.out.println("Lo que envio (allrec) \n" + allReceived);
        }else{
            System.out.println("NO HAY MENSAJES PARA ESTE BRO");
            CustomListModelMessage messages2 = new CustomListModelMessage();
            this.listaMessages.setModel(messages2);
        }
        
        /*
        while(sinMensajes == false){
            if(mensajesRestantes > 0 && mensajesRestantesFecha == 0){
                while(mensajesRestantesFecha == 0){
                    
                    input = protocolo.writePideMessages(loginUser, loginAmigo, resta);

                    out.println(input);
                    fromServer = in.readLine();
                    System.out.println("Lo que viene del server es: \n" + fromServer);

                    mensajesRestantes = protocolo.checkMensajesRestantes(fromServer);
                    mensajesRestantesFecha = protocolo.checkMensajesRestantesFecha(fromServer);

                    System.out.println("Mensajes Restantes2: \n" + mensajesRestantes);
                    System.out.println("Mensajes en fecha2: \n" + mensajesRestantesFecha);

                    resta++;
                    System.out.println("Resta vale: " + resta);
                }
            }else if(mensajesRestantes != 0 || mensajesRestantesFecha != 0){
                System.out.println("Por que no te metes?\n " + mensajesRestantes + " " + mensajesRestantesFecha);
                String send = protocolo.writeSend();
                out.println(send);
                
                System.out.println("Aqu?? se mete\n " + send);
                
                while(mensajesRestantesFecha > 0){
                    fromServer = in.readLine();
                    messages = protocolo.getMessages(this.login, loginAmigo, fromServer);
                    this.listaMessages.setModel(messages);
                    mensajesRestantesFecha--;
                    mensajesRestantes--;
                    out.println(send);
                    System.out.println("Aqu?? tambien est?? llegando brouston " + send);
                }
            }else{
                System.out.println("Por que te metes aqui si entavia nooo?");
                sinMensajes = true;
            }
        }
            String allReceived = protocolo.writeAllReceived();
            out.println(allReceived);
        */
    }
    
    private void getDatosDeUsuario(User user) throws IOException{
        
        String input = protocolo.writeGetDatosUser(user.getId_user());
        out.println(input);
        
        String fromServer = in.readLine();
        user = protocolo.getInfoUserAmigo(fromServer, user);
        
        datosFriendTxt.setText(user.getName() + " " + user.getSurname1() + " " + user.getSurname2() + " " + user.stringState());
    }
    
    public void getFotoUser(String loginClient){
        
        String output = protocolo.writeGetFotoUser(loginClient);
        boolean photo = true;
        
        out.println(output);
        
        while(photo == true){
            String input = "";
            try {
                input = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(input.contains("ENDING_MULTIMEDIA_TRANSMISSION")){
                photo = false;
            }else{
                protocolo.getPhotoPacket(input);
                System.out.println("YEPA QUE PASA XAVALE");
            }
        }
        ArrayList<String> decodePhotoLines = new ArrayList<String>();
        decodePhotoLines  = protocolo.returnCompletePhoto();
        
        try{
            File file = new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ProyectoPSPServidor\\data\\Zfotos\\foto.jpg");
            file.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                for (String s : decodePhotoLines) {
                    for (char c : s.toCharArray()) {
                        fos.write(c);
                    }
                }
                fos.flush();
                fos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedImage image;
        try {
            image = ImageIO.read(new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ProyectoPSPServidor\\data\\Zfotos\\foto.jpg"));
            Image img = image.getScaledInstance(140, 100, Image.SCALE_SMOOTH);
            lblPhoto.setIcon(new javax.swing.ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        System.out.println("Que guapo soy maemia");
        
    }
            
    private void comprobarStatusAmigos() throws IOException{
        String input;
        
        for(int i = 0; i < users.getSize(); i++){
            
            input = protocolo.writeFriendStatus(this.login, users.getUser(i).getId_user());
            out.println(input);
            String fromServer = in.readLine();
            
            if(protocolo.getConectado(fromServer) == true){
                users.getUser(i).setState(true);
            }else{
                users.getUser(i).setState(false);
            }
        }
    }
    
    private void actualizarStatusAmigosEnJList(){
        try {
            this.comprobarStatusAmigos();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.listaFriends.setModel(users);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JTextField datosFriendTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JList<String> listaFriends;
    private javax.swing.JList<String> listaMessages;
    private javax.swing.JTextPane messageTxt;
    private java.awt.Label tituloCristo;
    // End of variables declaration//GEN-END:variables

   public class aTask extends SwingWorker<String, Void>{

        String login;
        String passw;

        public aTask(String login, String passw) {
            this.login = login;
            this.passw = passw;
        }
        
        
        
        @Override
        protected String doInBackground() throws Exception {
            
        String retorno = new String();
        /*
        String input = "NULL";
        
        input = protocolo.writeLogin(this.login, this.passw);
        System.out.println("Aqui llega");
        
        if(input != "NULL"){
            try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            ) {
                System.out.println("Aqui llega2");
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String fromServer;
                String fromUser;
                
                while ((fromServer = in.readLine()) != null) {
                    System.out.println("Server: " + fromServer);
                
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
                        System.out.println("Client: " + fromUser);
                        out.println(input);
                    } 
                }

            }catch (UnknownHostException e) {
                System.err.println("Don't know about host " + hostName);
                System.exit(1);

            }catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
                System.exit(1);
            }  
        }else{
            System.out.println("TU MENSAJE ESTA VACIO");
        }  
*/
            return retorno;
        }
        
        @Override
        protected void done(){
            
        }
    }

}
