/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matt Workstation
 */
public class ReadEncodeFile_DecodeWriteFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String line = "";
        int cont = 0;
        ArrayList<String> arrayLines = new ArrayList<String>();
        ArrayList<String> encodeLines = new ArrayList<String>();
        ArrayList<String> decodeLines = new ArrayList<String>();
        
        //Lectura de fichero a ArrayList de String len 512chars
        try(FileInputStream input=new FileInputStream("C:\\Users\\Usuario\\Documents\\data\\Raul_Ruiz\\Raul_Ruiz.jpg")){
            int valor = input.read();
            while(valor!=-1){
                if (cont > 511) {
                    arrayLines.add(line);
                    line = "";
                    cont = 0;
                    System.out.println("yepa");
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
            Logger.getLogger(ReadEncodeFile_DecodeWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadEncodeFile_DecodeWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Lectura de las lineas en bruto
        for (String s : arrayLines) {
            System.out.println("\n\nLinea[" + cont + "]: " + s);
            cont++;
        }
        //Encode de la cadena a Base64
        for (String s : arrayLines) {
            encodeLines.add(Base64.getEncoder().encodeToString(s.getBytes()));
        }
        //Lectura de las lineas en encode base64
        cont = 0;
        for (String s : encodeLines) {
            System.out.println("\n\nLinea Encode[" + cont + "]: " + s);
            cont++;
        }
        //Decode de la cadena en Base64
        for (String s : encodeLines) {
            decodeLines.add(new String(Base64.getDecoder().decode(s)));
        }
        cont = 0;
        //Lectura de las lineas en decode base64
        for (String s : decodeLines) {
            System.out.println("\n\nLinea Decode[" + cont + "]: " + s);
            cont++;
        }
        
        System.out.println(arrayLines.size());
        System.out.println(encodeLines.size());
        System.out.println(decodeLines.size());
        System.out.println("puiser");
        //System.out.println(decodeLines.get(159).length());
        //System.out.println(decodeLines.get(160).length());
        //System.out.println(decodeLines.get(161).length());
        System.out.println("si");
        //System.out.println(encodeLines.get(157).length());
        //System.out.println(encodeLines.get(158).length());
        //System.out.println(encodeLines.get(159).length());
        //System.out.println(encodeLines.get(160).length());
        //System.out.println(encodeLines.get(161).length());
        
        //Escritura de fichero de ArrayList a File
        try{
            File file = new File("C:\\Users\\Usuario\\Documents\\data\\Alejandro_Muñoz\\Suprimo");
            file.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                for (String s : decodeLines) {
                    for (char c : s.toCharArray()) {
                        fos.write(c);
                    }
                }
                fos.flush();
                fos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ReadEncodeFile_DecodeWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
