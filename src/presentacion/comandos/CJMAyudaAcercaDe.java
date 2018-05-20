
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMAyudaAcercaDe. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMAyudaAcercaDe extends JMenuItem
    implements Comando
{

    public CJMAyudaAcercaDe(Mediador mediador)
    {
        super("Acerca de Sniffer III", 65);
        KeyStroke ctrlA = KeyStroke.getKeyStroke("F2");
        setAccelerator(ctrlA);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAAyudaAcercaDe();
    }

    private Mediador mediador;
}
