
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMConfiguracion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMConfiguracion extends JMenuItem
    implements Comando
{

    public CJMConfiguracion(Mediador mediador)
    {
        super("Configuración", 71);
        KeyStroke ctrlA = KeyStroke.getKeyStroke("F5");
        setAccelerator(ctrlA);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAConfigDirectorios();
    }

    private Mediador mediador;
}
