
package presentacion.comandos;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase CBAbrirFichero. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBAbrirFichero extends JButton  implements Comando
{

    public CBAbrirFichero(Mediador mediador, String titulo)
    {
        if(titulo.equals("Abrir fichero de Capturas..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Abrir Captura");
            setMnemonic('A');
        }
        if(titulo.equals("Abrir fichero de Capturas desde fichero..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Abrir Captura desde fichero");
            setMnemonic('D');
        }
        if(titulo.equals("Cargar fichero de preferecias..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Abrir fichero preferencias");
            setMnemonic('P');
        }
        if(titulo.equals("BrowseInicioCaptura"))
        {
            setText("Abrir");
            setMnemonic('A');
        }
        if(titulo.equals("AbrirFicheroXMLFromFile"))
        {
            setText("Abrir");
            setMnemonic('A');
        }
        if(titulo.equals("GenerarBatPreferencias"))
        {
            setMnemonic('W');
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
        }
        if(titulo.equals("GenerarFromFileXML"))
        {
            setMnemonic('P');
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
        }
        if(titulo.equals("GenerarFromFileXMLOpenXML"))
        {
            setText("  Abrir  ");
            setFont(new Font("Arial", 1, 12));
            setAlignmentY(0.5F);
        }
        if(titulo.equals("AbrirDefinicionProtocolo"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Abrir Definicion Protocolo");
            setMnemonic('A');
        }
        if(titulo.equals("AbrirDefinicionProtocoloInsercion"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Abrir Definicion Protocolo para Insercion");
            setMnemonic('A');
        }
        if(titulo.equals("Abrir fichero de exportaciones..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Abrir Exportaciones");
            setMnemonic('A');
        }
        if(titulo.equals("Abrir fichero de Capturas Insercion..."))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open.png").toString()));
            setToolTipText("Abrir Captura");
            setMnemonic('A');
        }
        if(titulo.equals("Fichero de Sincronismo"))
        {
            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
            setToolTipText("Abrir fichero de sincronismo");
            setMnemonic('A');
        }
//        if(titulo.equals("CapturaIntrusos"))/*Proceso de intrusos*/
//        {
//            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
//            setToolTipText("Abrir Captura (*.xml)");
//            //setText("Abrir");
//            setMnemonic('A');
//            
//        }
//        if(titulo.equals("Intrusos"))
//        {
//            setIcon(new ImageIcon((new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES")))).append(System.getProperty("file.separator")).append("open_small.png").toString()));
//            setToolTipText("Abrir Lista Negra (*.txt)");
//         //   setText("Abrir");
//            setMnemonic('A');
//        }        
        setAlignmentY(0.5F);
        this.titulo = titulo;
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        mediador.irAAbrirElegirFichero(titulo);
        if(titulo.equals("Abrir fichero de Capturas..."))
            mediador.repaintVentana(mediador.getVentanaMenuSniffer());
    }

    private Mediador mediador;
    private String titulo;
}
