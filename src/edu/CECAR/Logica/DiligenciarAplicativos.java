
package edu.CECAR.Logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author JUAN
 */
public final class DiligenciarAplicativos {

     private File archivo = new File("C:\\Users\\JUAN\\Desktop\\listado.txt");
     private static ArrayList<String>Programasinstalados = new ArrayList<String>();
     private static ArrayList<String> ProgramasAdmin = new ArrayList<String>();
      private int posicion;
      private boolean duplicado= false;
      private boolean eliminar=  false;
    public static DefaultListModel  GenerarAplicativos(){
           String s = null;
	   String a=null;
	   int size = 0;
           DefaultListModel modelo = new DefaultListModel();
		try{
                        
                    String comando = "cmd /c wmic product get name";
			Process p = Runtime.getRuntime().exec(comando);
                        BufferedReader stdInput = new BufferedReader(new InputStreamReader( p.getInputStream()));
				while ((s = stdInput.readLine()) != null) {
					if (s.length()>0) {
						Programasinstalados.add(s.trim());
						size=Programasinstalados.size();
					}
				}
                                
			Programasinstalados.remove(0);
				
				for(int i=0; i<Programasinstalados.size(); i++){
					if (Programasinstalados.get(i).trim().isEmpty()){
						Programasinstalados.remove(i);
					i--;
					}
				}
                        
                                Programasinstalados.forEach((programa) -> {
                                    modelo.addElement(programa);
               });
                     
                    } catch (IOException ex) {
                            Logger.getLogger(DiligenciarAplicativos.class.getName()).log(Level.SEVERE, null, ex);
        
                    }
     return modelo;
    }

   
    public  DefaultListModel ProgramasAdmin( String Programas){
         DefaultListModel modelo = new DefaultListModel();
         for(int i=0; i<ProgramasAdmin.size();i++){
         if(ProgramasAdmin.get(i).equalsIgnoreCase(Programas)){
            duplicado=true;
         }
        }
         if(duplicado==true){
             JOptionPane.showMessageDialog(null,"Aplicacion duplicada");
         }
          if(Programas !=null && duplicado==false ){
                ProgramasAdmin.add(Programas);
            }
          
          ProgramasAdmin.forEach((programa) -> {
              modelo.addElement(programa);
         });
         if(archivo.exists()){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
         for (int i = 0; i < ProgramasAdmin.size(); i++) {
        bw.write((String) ( ProgramasAdmin.get(i)));
        bw.newLine();
        }
        } catch (Exception e) {
          
        }}else{
              File archivo = new File("C:\\Users\\JUAN\\Desktop\\listado.txt");
               try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
         for (int i = 0; i < ProgramasAdmin.size(); i++) {
        bw.write((String) ( ProgramasAdmin.get(i)));
        bw.newLine();
        }
        } catch (Exception e) {
          
        }
         }
         
        return modelo;
    }
    public DefaultListModel leer(){
        DefaultListModel modelo2 = new DefaultListModel();
       if(archivo.exists()){
      
           try {
         String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
               while((cadena = b.readLine())!=null) {
                  ProgramasAdmin.add(cadena);
               }
                ProgramasAdmin.forEach((programa) -> {
                                    modelo2.addElement(programa);
                                        });
           } catch (IOException ex) {
               Logger.getLogger(DiligenciarAplicativos.class.getName()).log(Level.SEVERE, null, ex);
           }
       // b.close();
       
    }
   
           
        return modelo2;
    
    }
    public DefaultListModel Eliminar(String Programa){
       DefaultListModel modelo3 = new DefaultListModel();
        for(int i=0 ; i<ProgramasAdmin.size();i++){
            if(ProgramasAdmin.get(i).equals(Programa)){
                posicion=i;
                eliminar=true;
            }
        }
        if(eliminar==true){
        ProgramasAdmin.remove(posicion);}
        else{
            JOptionPane.showMessageDialog(null,"Programa no se puede eliminar.. Programa no existe");
        }
        
            File archivo = new File("C:\\Users\\JUAN\\Desktop\\listado.txt");
               try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
         for (int i = 0; i < ProgramasAdmin.size(); i++) {
        bw.write((String) ( ProgramasAdmin.get(i)));
        bw.newLine();
        }
        } catch (Exception e) {
          
        }
                ProgramasAdmin.forEach((programa) -> {
              modelo3.addElement(programa);
         });
        return modelo3;
    }
}
