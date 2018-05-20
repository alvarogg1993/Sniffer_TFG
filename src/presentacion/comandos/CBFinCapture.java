
package presentacion.comandos;

import java.io.PrintStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBFinCapture. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBFinCapture extends JButton
    implements Comando
{

    public CBFinCapture(Mediador mediador)
    {
        super(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("fin.png").toString()));
        setToolTipText("Parar Captura");
        setMnemonic('P');
        setAlignmentY(0.5F);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irFinCapture();
        System.out.println("CBFinCapture => Ejecutar");
    }

    private Mediador mediador;
}
