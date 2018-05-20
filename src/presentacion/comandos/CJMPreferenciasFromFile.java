
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMPreferenciasFromFile. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMPreferenciasFromFile extends JMenuItem
    implements Comando
{

    public CJMPreferenciasFromFile(Mediador mediador)
    {
        super("Captura desde fichero", 68);
        KeyStroke ctrlD = KeyStroke.getKeyStroke(68, 2);
        setAccelerator(ctrlD);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irInicioCaptureFromFile(false);
    }

    private Mediador mediador;
}
