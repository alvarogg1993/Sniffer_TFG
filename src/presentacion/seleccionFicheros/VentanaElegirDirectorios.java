
package presentacion.seleccionFicheros;

import java.io.*;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import presentacion.Mediador;
import presentacion.ventanaMenuSniffer.MenuSniffer;
/** 
 * Clase VentanaElegirDirectorios. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
public class VentanaElegirDirectorios extends JDialog
{

    public VentanaElegirDirectorios(String ventana)
    {
        vent = ventana;
        inicializacionComponentes();
    }

    public void inicializacionComponentes()
    {
        mediador = new Mediador();
        JDialog ventana = new JDialog();
        ventana.setSize(300, 80);
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Directorios");
        fc.setFileSelectionMode(1);
        int returnVal = fc.showOpenDialog(MenuSniffer.getFrames()[0]);
        if(returnVal == 0)
        {
            File file = fc.getSelectedFile();
            fRuta = file.getAbsolutePath();
            fName = file.getName();
            mediador.actualizaTfRuta(fRuta, vent);
        }
    }

    public String fRuta;
    public String fName;
    private BufferedReader in;
    FileInputStream inStream;
    String vent;
    Mediador mediador;
}
