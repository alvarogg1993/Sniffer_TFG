
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMAyudaContenidos. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMAyudaContenidos extends JMenuItem
    implements Comando
{

    public CJMAyudaContenidos(Mediador mediador)
    {
        super("Contenidos");
        KeyStroke ctrlF1 = KeyStroke.getKeyStroke("F1");
        setAccelerator(ctrlF1);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAAyuda();
    }

    private Mediador mediador;
}
