
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMPreferenciasCaptura. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMPreferenciasCaptura extends JMenuItem
    implements Comando
{

    public CJMPreferenciasCaptura(Mediador mediador)
    {
        super("Captura", 71);
        KeyStroke ctrlC = KeyStroke.getKeyStroke(71, 2);
        setAccelerator(ctrlC);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioCapture(false);
    }

    private Mediador mediador;
}
