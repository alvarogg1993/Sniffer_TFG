
package presentacion.avisos;

import javax.swing.*;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase Aviso. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class Aviso extends JDialog
{

    public Aviso(String mensaje, String tipo)
    {
        this.mensaje = mensaje;
        this.tipo = tipo;
        aviso();
    }

    public void aviso()
    {
        JFrame frame = new JFrame();
        if(tipo.equals("Error"))
            JOptionPane.showMessageDialog(frame, mensaje, "Error", 0);
        else
        if(tipo.equals("Advertencia"))
            JOptionPane.showMessageDialog(frame, mensaje, "Atenci\363n", 2);
        else
        if(tipo.equals("Informacion"))
            JOptionPane.showMessageDialog(frame, mensaje, "Informaci\363n", 1);
        else
        if(tipo.equals("Terminado"))
        {
            ImageIcon ico = new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("tic.jpg").toString());
            JOptionPane.showMessageDialog(frame, mensaje, "Mensaje", -1, ico);
        } else
        {
            JOptionPane.showMessageDialog(frame, mensaje, "Mensaje", -1);
        }
    }

    String mensaje;
    String tipo;
}
