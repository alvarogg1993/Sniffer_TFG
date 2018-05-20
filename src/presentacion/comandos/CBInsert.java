
package presentacion.comandos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import presentacion.preferencias.PreferenciasInsercion;
import presentacion.preferencias.PreferenciasInsercionCapturados;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBInsert. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBInsert extends JButton
    implements Comando
{

    public CBInsert(Mediador mediador, String titulo)
    {
        if(titulo.equals("Insertar Paquetes Capturados"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Insertar Paquetes Capturados");
            setMnemonic('A');
        }
        if(titulo.equals("Insertar Paquetes Definidos"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Insertar Paquetes Definidos");
            setMnemonic('D');
        }
        setAlignmentY(0.5F);
        this.titulo = titulo;
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        if(titulo.equals("Insertar Paquetes Capturados"))
            mediador.irAInsertarPaquetes(titulo, PreferenciasInsercionCapturados.getRepeticiones());
        if(titulo.equals("Insertar Paquetes Definidos"))
            mediador.irAInsertarPaquetes(titulo, PreferenciasInsercion.getEnvios());
    }

    private Mediador mediador;
    private String titulo;
}
