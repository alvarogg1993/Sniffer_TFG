
package presentacion.comandos;

import java.io.PrintStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBFinInsert. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBFinInsert extends JButton
    implements Comando
{

    public CBFinInsert(Mediador mediador)
    {
        super(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("stop.png").toString()));
        setToolTipText("Parar Insercion");
        setMnemonic('P');
        setAlignmentY(0.5F);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        System.out.println("pararEjecucion");
        mediador.irFinInsert();
        System.out.println("CBFinInsert => Ejecutar");
        mediador.cerrarVentana(mediador.getVentanaPresentacion());
    }

    private Mediador mediador;
}
