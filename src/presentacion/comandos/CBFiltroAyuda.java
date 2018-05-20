
package presentacion.comandos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBFiltroAyuda. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBFiltroAyuda extends JButton
    implements Comando
{

    public CBFiltroAyuda(Mediador mediador)
    {
        super(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("help.png").toString()));
        setToolTipText("Ayuda filtro");
        setAlignmentY(0.5F);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void setEnab(boolean aux)
    {
        setEnabled(aux);
    }

    public void ejecutar()
    {
        mediador.irAAyudaFilter();
    }

    private Mediador mediador;
}
