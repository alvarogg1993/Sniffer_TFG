
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CBVentanaSalir 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBVentanaSalir extends JMenuItem
    implements Comando
{

    public CBVentanaSalir(Mediador mediador)
    {
        super("Salir", 83);
        KeyStroke ctrlS = KeyStroke.getKeyStroke(83, 2);
        setAccelerator(ctrlS);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAVentanaSalir();
    }

    private Mediador mediador;
}
