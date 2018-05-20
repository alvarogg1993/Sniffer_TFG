
package presentacion.seleccionFicheros;

import java.io.*;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import presentacion.Mediador;
import presentacion.avisos.Aviso;
//import presentacion.preferencias.PreferenciasIntruso;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase VentantaAbrirElegirFichero. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3
*/
// Referenced classes of package presentacion.seleccionFicheros:
//            FiltroFileChooser

public class VentanaAbrirElegirFichero extends JDialog
{

    public VentanaAbrirElegirFichero(String tipo, Mediador med)
    {
        tip = tipo;
        mediador = med;
        inicializacionComponentes();
    }

    public void inicializacionComponentes()
    {
        JDialog ventana = new JDialog();
        ventana.setSize(300, 80);
        JFileChooser fc = new JFileChooser();
        FiltroFileChooser filtertxt = new FiltroFileChooser();
        filtertxt.addExtension("txt");
        filtertxt.setDescription("Ficheros de texto ");
        FiltroFileChooser filterxml = new FiltroFileChooser();
        filterxml.addExtension("xml");
        filterxml.setDescription("Ficheros XML ");
        FiltroFileChooser filterpcap = new FiltroFileChooser();
        filterpcap.addExtension("pcap");
        filterpcap.setDescription("Ficheros de Captuta ");
        String barra = System.getProperty("file.separator");
        if(tip.equals("Abrir fichero de Capturas..."))
        {
            fc.setDialogTitle("Abrir fichero de Capturas...");
            fc.addChoosableFileFilter(filterpcap);
            fc.setFileFilter(filterpcap);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Abrir fichero de Capturas desde fichero..."))
        {
            fc.setDialogTitle("Abrir fichero de Capturas desde fichero...");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterpcap);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Cargar fichero de preferecias..."))
        {
            fc.setDialogTitle("Cargar fichero de preferecias...");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_FICHENTRADA"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("BrowseInicioCaptura"))
        {
            fc.setDialogTitle("Buscar Fichero de Preferencias...");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("AbrirFicheroXMLFromFile"))
        {
            fc.setDialogTitle("Buscar Fichero de Preferencias...");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GenerarBatPreferencias"))
        {
            fc.setDialogTitle("Elegir fichero de preferecias...");
            fc.setApproveButtonText("Aceptar");
            fc.addChoosableFileFilter(filterpcap);
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GenerarFromFileXML"))
        {
            fc.setDialogTitle("Elegir fichero de captura o metadatos...");
            fc.setApproveButtonText("Aceptar");
            fc.addChoosableFileFilter(filterpcap);
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterpcap);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GenerarFromFileXMLOpenXML"))
        {
            fc.setDialogTitle("Elegir fichero de preferecias...");
            fc.setApproveButtonText("Aceptar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("AbrirDefinicionProtocolo"))
        {
            fc.setDialogTitle("Elegir definicion de protocolo...");
            fc.setApproveButtonText("Aceptar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_DEFINICIONES"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("AbrirDefinicionProtocoloInsercion"))
        {
            fc.setDialogTitle("Elegir definicion de protocolo...");
            fc.setApproveButtonText("Aceptar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_DEFINICIONES"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Abrir fichero de exportaciones..."))
        {
            fc.setDialogTitle("Elegir fichero de exportaciones...");
            fc.setApproveButtonText("Abrir");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_EXPORTACIONES"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Abrir fichero de Capturas Insercion..."))
        {
            fc.setDialogTitle("Abrir fichero de Capturas Insercion...");
            fc.addChoosableFileFilter(filterpcap);
            fc.setFileFilter(filterpcap);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Fichero de Sincronismo"))
        {
            fc.setDialogTitle("Abrir fichero de Sincronización...");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("FILE_FICHEROSINCRONIZADO"));
            fc.setCurrentDirectory(directorio);
        }
        
        
        
        		/*Detención de Captura de intrusos*/
//        if(tip.equals("Seleccionar Archivo de Captura"))
//        {
//            fc.setDialogTitle("Seleccionar Archivo de Captura");
//            fc.addChoosableFileFilter(filterpcap);
//            fc.setFileFilter(filterpcap);
//            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
//            fc.setCurrentDirectory(directorio);
//        } 
//           
//        
//   		/*Detención de intrusos*/
//        if(tip.equals("CapturaIntrusos"))
//        {
//            fc.setDialogTitle("Seleccionar Archivo de Captura");
//            fc.addChoosableFileFilter(filterxml);
//            fc.setFileFilter(filterxml);
//            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_EXPORTACIONES"));
//            fc.setCurrentDirectory(directorio);
//        } 
//        if(tip.equals("Intrusos"))
//        {
//            fc.setDialogTitle("Seleccionar Archivo Lista Negra");
//            fc.addChoosableFileFilter(filtertxt);
//            fc.setFileFilter(filtertxt);
//            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_DEFINICIONES"));
//            fc.setCurrentDirectory(directorio);
//        }  
   	
                   
        
        
        int returnVal = fc.showOpenDialog(MenuSniffer.getFrames()[0]);
        if(returnVal == 0)
        {
            File file = fc.getSelectedFile();
            fRuta = file.getAbsolutePath();
            if(file.exists())
            {
                if(tip.equals("Abrir fichero de Capturas..."))
                {
                    mediador.AbrirFicheroCaptura(fRuta);
                    mediador.habiliatarBHelemento(3);
                    mediador.habiliatarBHelemento(4);
                    mediador.habilitarComponenteBarraMenus(1, 1);
                    mediador.habilitarComponenteBarraMenus(0, 1);
                    System.out.println((new StringBuilder("Abrir fichero de Capturas...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                    mediador.EnabledComponenteBarraMenus(0, 3, 0, true);
                }
                if(tip.equals("Abrir fichero de Capturas desde fichero..."))
                {
                    mediador.setPrefCapFicheroFromFileSource(fRuta);
                    System.out.println((new StringBuilder("Cargar fichero de preferecias...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("Cargar fichero de preferecias..."))
                    System.out.println((new StringBuilder("Cargar fichero de preferecias...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                if(tip.equals("BrowseInicioCaptura"))
                {
                    mediador.PrefCapLeerXML(fRuta);
                    System.out.println((new StringBuilder("Cargar fichero de preferencia de captura...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("AbrirFicheroXMLFromFile"))
                {
                    mediador.PrefFFLeerXML(fRuta);
                    System.out.println((new StringBuilder("Cargar fichero de preferencia desde fichero...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GenerarBatPreferencias"))
                {
                    mediador.setPrefGenFicheroPref(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero de preferencias...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GenerarFromFileXML"))
                {
                    mediador.setExportFromFilePref(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero pcap o Meta...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GenerarFromFileXMLOpenXML"))
                {
                    mediador.PrefExpLeerXML(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero pcap o Meta...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("AbrirDefinicionProtocolo"))
                {
                    mediador.PrefDefLeerXML(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero XML...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("AbrirDefinicionProtocoloInsercion"))
                {
                    mediador.PrefDefLeerXMLInsercion(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero XML...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("Abrir fichero de exportaciones..."))
                {
                    mediador.PrefDefLeerXMLExportaciones(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero XML...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("Abrir fichero de Capturas Insercion..."))
                {
                    mediador.PrefInserCapRuta(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero pcap...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("Fichero de Sincronismo"))
                {
                    mediador.PrefInserSyncRuta(fRuta);
                    System.out.println((new StringBuilder("Selecci\363n de fichero xml...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
//                if(tip.equals("CapturaIntrusos"))
//                {
//                	PreferenciasIntruso.setOrigen(fRuta);
//                	System.out.println((new StringBuilder("Selección de fichero xml...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
//                    
//                }
//                if(tip.equals("Intrusos"))
//                {
//                	PreferenciasIntruso.setOrigen2(fRuta);
//                    System.out.println((new StringBuilder("Selección de fichero txt...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
//                    
//                }
            } else
            {
                new Aviso("El fichero seleccionado no existe.", "Error");
            }
        }
    }

    public String fRuta;
    public String fName;
    private BufferedReader in;
    FileInputStream inStream;
    String tip;
    Mediador mediador;
}
