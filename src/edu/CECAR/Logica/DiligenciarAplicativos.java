
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
String Ruta;
    private String ruta;
    public DiligenciarAplicativos( String ruta) {
    try {
        this.f = new FileReader(ruta);
    } catch (FileNotFoundException ex) {
        Logger.getLogger(DiligenciarAplicativos.class.getName()).log(Level.SEVERE, null, ex);
    }
        this.Ruta=ruta;
    }
FileReader f;
    
    public static DefaultListModel  GenerarAplicativos(){
           String s = null;
	   String a=null;
	   int size = 0;
           DefaultListModel modelo = new DefaultListModel();
		try{
                        ArrayList<String> Programasinstalados = new ArrayList<String>();
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
                        
				for (String programa : Programasinstalados) {
					modelo.addElement(programa);
                                }
                     
                    } catch (IOException ex) {
                            Logger.getLogger(DiligenciarAplicativos.class.getName()).log(Level.SEVERE, null, ex);
        
                    }
     return modelo;
    }
 
    public  void guardar( ArrayList ProgramasAdmin, File archivo){
         
       try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
         for (int i = 0; i < ProgramasAdmin.size(); i++) {
        bw.write((String) ProgramasAdmin.get(i));
        bw.newLine();
        }
        } catch (Exception e) {
          
        
         }
     
    }
    
     
    public  void muestraContenido(String archivo, ArrayList ProgramasAdmin) throws FileNotFoundException, IOException {
        String cadena;
        BufferedReader b = new BufferedReader(f);
        
        while((cadena = b.readLine())!=null) {
         ProgramasAdmin.add(cadena);
        }
        b.close();
    }

}
