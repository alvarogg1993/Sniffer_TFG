package presentacion.comandos;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import presentacion.Mediador;

import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CB_Prueba2. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.2 
*/
public class CBProcesarFichero extends JButton  implements Comando
{
	 private String Origen;
	 private String Origen2;
	 private String Destino;
	 private String titulo;
	 public CBProcesarFichero(String Origen, String Origen2,String Destino,String titulo){
		this.Origen = Origen;
	 	this.Origen2 = Origen2;
	 	this.Destino = Destino;
        if(titulo.equals("Proceso de Intrusion"))
        {
             
        }		 
	 }
	
	
	public void ejecutar() {
		
		
	}

	
	
	
}
