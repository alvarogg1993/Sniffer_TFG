
package presentacion.comandos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CB_Prueba2. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CB_Prueba2 extends JButton
    implements Comando
{

    public CB_Prueba2(Mediador mediador)
    {
        super(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("tic.gif").toString()));
        setToolTipText("Prueba con otra pcap lib");
        setAlignmentY(0.5F);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAPrueba2();
    }

    private Mediador mediador;
}
