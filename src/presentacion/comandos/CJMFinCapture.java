
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMFinCapture. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMFinCapture extends JMenuItem
    implements Comando
{

    public CJMFinCapture(Mediador mediador)
    {
        super("Finalizar Captura", 70);
        KeyStroke ctrlF = KeyStroke.getKeyStroke(70, 2);
        setAccelerator(ctrlF);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.freePantallaCaptura();
    }

    private Mediador mediador;
}
