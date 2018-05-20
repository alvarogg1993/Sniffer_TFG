
package presentacion.comandos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBConfiguracion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBConfiguracion extends JButton
    implements Comando
{

    public CBConfiguracion(Mediador mediador)
    {
        super(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("configuracion.gif").toString()));
        setToolTipText("Configuracion");
        setAlignmentY(0.5F);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAConfigDirectorios();
    }

    private Mediador mediador;
}
