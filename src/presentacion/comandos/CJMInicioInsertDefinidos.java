
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMInicioInsetDefinidos. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMInicioInsertDefinidos extends JMenuItem
    implements Comando
{

    public CJMInicioInsertDefinidos(Mediador mediador)
    {
        super("Inserci\363n Paquetes Definidos", 74);
        KeyStroke ctrlN = KeyStroke.getKeyStroke(74, 2);
        setAccelerator(ctrlN);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioInsertDef(true);
    }

    private Mediador mediador;
}
