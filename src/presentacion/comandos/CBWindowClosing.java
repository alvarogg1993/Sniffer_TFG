
package presentacion.comandos;

import javax.swing.JDialog;
import presentacion.Mediador;
/** 
 * Clase CBWindowClosing. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBWindowClosing extends JDialog
    implements Comando
{

    public CBWindowClosing(Mediador mediador, JDialog ventana)
    {
        this.mediador = mediador;
        addWindowListener(mediador);
    }

    public void ejecutar()
    {
        dispose();
        mediador.setPanelEstado("Se ha cerrado la ventana");
    }

    private Mediador mediador;
}
