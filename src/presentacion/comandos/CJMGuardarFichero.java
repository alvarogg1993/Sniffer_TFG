
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;
/** 
 * Clase CJMGuardarFichero. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMGuardarFichero extends JMenuItem
    implements Comando
{

    public CJMGuardarFichero(Mediador mediador, String titulo)
    {
        super(titulo);
        if(titulo.equals("Guardar fichero capturado..."))
        {
            setMnemonic('G');
            KeyStroke ctrlG = KeyStroke.getKeyStroke(71, 2);
            setAccelerator(ctrlG);
        }
        if(titulo.equals("Guardar fichero de preferecias..."))
        {
            setMnemonic('F');
            KeyStroke ctrlF = KeyStroke.getKeyStroke(70, 2);
            setAccelerator(ctrlF);
        }
        if(titulo.equals("Captura a XML..."))
        {
            setMnemonic('X');
            KeyStroke ctrlX = KeyStroke.getKeyStroke(88, 2);
            setAccelerator(ctrlX);
        }
        if(titulo.equals("... desde fichero a XML..."))
        {
            setMnemonic('D');
            KeyStroke ctrlX = KeyStroke.getKeyStroke(68, 2);
            setAccelerator(ctrlX);
        }
        this.titulo = titulo;
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        if(titulo.equals("... desde fichero a XML..."))
            mediador.irAExportFromFile(true);
        else
            mediador.irAGuardarElegirFichero(titulo);
    }

    private Mediador mediador;
    private String titulo;
}
