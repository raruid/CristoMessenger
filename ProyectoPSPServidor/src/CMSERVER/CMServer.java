/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMSERVER;

import Controladores_y_modelos.CMProtocol;
import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Usuario
 */
public class CMServer extends javax.swing.JFrame {

    int portNumber;
    boolean listening;
    aTask a;
    CMProtocol protocolo;
    ArrayList<ServerThread> threads;
    
    public CMServer(int portNumber) {
        initComponents();
        threads = new ArrayList();
    }
    
    public CMServer() {
        initComponents();
        threads = new ArrayList();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        debugArea = new javax.swing.JTextArea();
        btnPlay = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        textPort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        labelConexion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        debugArea.setEditable(false);
        debugArea.setColumns(20);
        debugArea.setRows(5);
        jScrollPane1.setViewportView(debugArea);

        btnPlay.setText("PLAY");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnStop.setText("STOP");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        textPort.setText("39999");
        textPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPortActionPerformed(evt);
            }
        });

        jLabel1.setText("Puerto");

        labelConexion.setText("No activo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPlay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                        .addComponent(labelConexion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelConexion)
                        .addGap(42, 42, 42)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPortActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
  
///*
        a = new aTask(Integer.parseInt(textPort.getText()), listening, this);
        
        a.execute();
        btnPlay.setEnabled(false);
        btnStop.setEnabled(true);
        textPort.setEditable(false);
//*/       
/*        
        this.portNumber = Integer.parseInt(textPort.getText());
        this.listening = true;
        
        //System.out.println("llego aqui");
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            //System.out.println("llego aqui2");
            labelConexion.setText("Activo");
            while (listening) {
                //System.out.println("llego aqui3");
	            new ServerThread(serverSocket.accept()).start();
                    
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
*/
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed

        if(a != null){
            a.cancel(listening);
            btnPlay.setEnabled(true);
            btnStop.setEnabled(false);
            textPort.setEditable(true);
        } 
    }//GEN-LAST:event_btnStopActionPerformed

    public PrintWriter returnOut(String login){
        PrintWriter print = null;
        for(int i = 0; i < threads.size(); i++){
            if(threads.get(i).returnLogin().equals(login)){
                print = threads.get(i).returnPrintWriter();
            }
        }
        return print;
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
            java.util.logging.Logger.getLogger(CMServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CMServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CMServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CMServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CMServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnStop;
    public static javax.swing.JTextArea debugArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelConexion;
    private javax.swing.JTextField textPort;
    // End of variables declaration//GEN-END:variables

    public class aTask extends SwingWorker<String, Void>{

        int portNumber;
        boolean listening;
        CMServer cmserver;

        public aTask(int portNumber, boolean listening, CMServer aThis) {
            this.portNumber = portNumber;
            this.listening = listening;
            this.cmserver = aThis;
        }
        
        
        
        @Override
        protected String doInBackground() throws Exception {
            
            String retorno = new String();

            this.portNumber = Integer.parseInt(textPort.getText());
            this.listening = true;

            //System.out.println("llego aqui");
            try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
                labelConexion.setText("Activo");
                while (listening) { 
                        //System.out.println("antoniooo");
                        threads.add(new ServerThread(serverSocket.accept(), cmserver));
                        //System.out.println("antoniooo2");
                        threads.get(threads.size() -1).start();
                        //System.out.println("antoniooo3");
                        CMServer.debugArea.setText(CMServer.debugArea.getText() + "\nConexion aceptada");
                    }
            }catch (IOException e) {
                System.err.println("Could not listen on port " + portNumber);
                System.exit(-1);
            }
                return retorno;
        }
        
        @Override
        protected void done(){
            
        }
    }

}
