
package presentacion.comandos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBAbrirCarpeta. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBAbrirCarpeta extends JButton
    implements Comando
{

    public CBAbrirCarpeta(Mediador mediador, String titulo)
    {
        if(titulo.equals("Capturas"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
            setToolTipText("Seleccionar capeta para Capturas");
            setMnemonic('C');
        }
        if(titulo.equals("Exportaciones"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
            setToolTipText("Seleccionar carpeta para Exportaciones");
            setMnemonic('E');
        }
        if(titulo.equals("Paremetrizacion"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
            setToolTipText("Seleccionar carpeta de Parametrizaciones");
            setMnemonic('P');
        }
        if(titulo.equals("Scripts"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
            setToolTipText("Seleccionar carpeta de Scripts");
            setMnemonic('S');
        }
              
        if(titulo.equals("Resultado"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
            setToolTipText("Seleccionar Carpeta de resultados");
            setMnemonic('W');
        }  
        

        
        setAlignmentY(0.5F);
        this.titulo = titulo;
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAVentanaElegirDirectorios(titulo);
    }

    private Mediador mediador;
    private String titulo;
}
