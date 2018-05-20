
package presentacion.avisos;

import java.io.File;
import javax.swing.*;
/** 
 * Clase AvisoFicheroExiste. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class AvisoFicheroExiste extends JDialog
{

    public AvisoFicheroExiste(File fichero)
    {
        respuesta = null;
        this.fichero = fichero;
        aviso();
    }

    public void aviso()
    {
        JFrame frame = new JFrame();
        int res = JOptionPane.showConfirmDialog(frame, (new StringBuilder("<html>Existe el fichero introducido:<br> ")).append(fichero.toString()).append(".<br>Si continua se borran los datos del fichero.<br>\277Desea continuar?</html>").toString(), "Fichero existente", 0);
        if(res == 0)
            respuesta = "Si";
        else
            respuesta = "No";
    }

    public String getrespuesta()
    {
        return respuesta;
    }

    File fichero;
    String respuesta;
}
