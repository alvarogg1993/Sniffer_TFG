
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMInicioInsertCapturados. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMInicioInsertCapturados extends JMenuItem
    implements Comando
{

    public CJMInicioInsertCapturados(Mediador mediador)
    {
        super("Inserci\363n Paquetes Capturados", 75);
        KeyStroke ctrlN = KeyStroke.getKeyStroke(75, 2);
        setAccelerator(ctrlN);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioInsertCap(true);
    }

    private Mediador mediador;
}
