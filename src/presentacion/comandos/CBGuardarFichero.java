
package presentacion.comandos;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBGuardarFichero. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBGuardarFichero extends JButton
    implements Comando
{

    public CBGuardarFichero(Mediador mediador, String titulo)
    {
        if(titulo.equals("Guardar fichero capturado..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("save.png").toString()));
            setToolTipText("Guardar fichero capturado");
            setMnemonic('G');
        }
        if(titulo.equals("Guardar fichero de preferecias..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("xml.png").toString()));
            setToolTipText("Guardar fichero preferencias");
            setMnemonic('F');
        }
        if(titulo.equals("Exportar fichero a XML..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("xml.png").toString()));
            setToolTipText("Exportar fichero a XML...");
            setMnemonic('X');
        }
        if(titulo.equals("Browse..."))
        {
            setMnemonic('W');
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
        }
        if(titulo.equals("Browse...FromFile"))
        {
            setMnemonic('W');
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
        }
        if(titulo.equals("GuardarInicioCapturaXML"))
        {
            setMnemonic('G');
            setText("Guardar");
        }
        if(titulo.equals("GuardarInicioCapturaXMLFromFile"))
        {
            setMnemonic('G');
            setText("Guardar");
        }
        if(titulo.equals("GenerarBatBat"))
        {
            setMnemonic('W');
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
        }
        if(titulo.equals("GenerarFromFileXML"))
        {
            setMnemonic('W');
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
        }
        if(titulo.equals("GenerarFromFileXMLSaveXML"))
        {
            setText(" Guardar ");
            setFont(new Font("Arial", 1, 12));
            setAlignmentY(0.5F);
        }
        if(titulo.equals("GuardarProtocoloDefinido"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("save.png").toString()));
            setToolTipText("Guardar Protocolo definido");
            setMnemonic('Q');
        }
        
//        if(titulo.equals("Resultado"))
//        {
//            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
//            setToolTipText("Seleccionar Carpeta de resultados");
//            setMnemonic('R');
//        }
//        
//        if(titulo.equals("ResultadoIntrusos"))
//        {
//            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
//            setToolTipText("Seleccionar Carpeta de resultados");
//            setMnemonic('I');
//        }
        
        this.titulo = titulo;
        setAlignmentY(0.5F);
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAGuardarElegirFichero(titulo);
    }

    private Mediador mediador;
    private String titulo;
}
