
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMPreferenciasExportar. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMPreferenciasExportar extends JMenuItem
    implements Comando
{

    public CJMPreferenciasExportar(Mediador mediador)
    {
        super("Exportación", 79);
        KeyStroke ctrlO = KeyStroke.getKeyStroke(79, 2);
        setAccelerator(ctrlO);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAExportFromFile(false);
    }

    private Mediador mediador;
}
