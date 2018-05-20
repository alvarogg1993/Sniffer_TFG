
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMInicioDefinicion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMInicioDefinicion extends JMenuItem
    implements Comando
{

    public CJMInicioDefinicion(Mediador mediador)
    {
        super("Definici\u00f3n de Paquetes", 76);
        KeyStroke ctrlN = KeyStroke.getKeyStroke(76, 2);
        setAccelerator(ctrlN);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioDefinicion(true);
    }

    private Mediador mediador;
}
