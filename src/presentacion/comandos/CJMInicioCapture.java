
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMInicioCapture. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMInicioCapture extends JMenuItem
    implements Comando
{

    public CJMInicioCapture(Mediador mediador)
    {
        super("Iniciar Captura", 73);
        KeyStroke ctrlI = KeyStroke.getKeyStroke(73, 2);
        setAccelerator(ctrlI);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioCapture(true);
    }

    private Mediador mediador;
}
