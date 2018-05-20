
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMAbrirFichero. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMAbrirFichero extends JMenuItem
    implements Comando
{

    public CJMAbrirFichero(Mediador mediador, String titulo)
    {
        super(titulo);
        if(titulo.equals("Abrir fichero de Capturas..."))
        {
            setMnemonic('A');
            KeyStroke ctrlA = KeyStroke.getKeyStroke(65, 2);
            setAccelerator(ctrlA);
        }
        if(titulo.equals("Cargar fichero de preferecias..."))
        {
            setMnemonic('P');
            KeyStroke ctrlP = KeyStroke.getKeyStroke(80, 2);
            setAccelerator(ctrlP);
        }
        this.titulo = titulo;
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAAbrirElegirFichero(titulo);
    }

    private Mediador mediador;
    private String titulo;
}
