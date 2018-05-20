
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMInicioCaptureFromFile. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMInicioCaptureFromFile extends JMenuItem
    implements Comando
{

    public CJMInicioCaptureFromFile(Mediador mediador)
    {
        super("Captura desde fichero", 69);
        KeyStroke ctrlE = KeyStroke.getKeyStroke(69, 2);
        setAccelerator(ctrlE);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioCaptureFromFile(true);
    }

    private Mediador mediador;
}
