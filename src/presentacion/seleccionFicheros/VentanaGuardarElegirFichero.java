
package presentacion.seleccionFicheros;

import java.io.*;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import presentacion.Mediador;
import presentacion.avisos.AvisoFicheroExiste;

import presentacion.ventanaMenuSniffer.MenuSniffer;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase VentanaGuardarElegirFichero. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package presentacion.seleccionFicheros:
//            FiltroFileChooser

public class VentanaGuardarElegirFichero extends JDialog
{

    public VentanaGuardarElegirFichero(String tip)
    {
        this.tip = tip;
        inicializacionComponentes();
    }

    public void inicializacionComponentes()
    {
        JDialog ventana = new JDialog();
        ventana.setSize(300, 80);
        mediador = new Mediador();
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
        FiltroFileChooser filterbat = new FiltroFileChooser();
        filterbat.addExtension("bat");
        filterbat.setDescription("Proceso por lotes ");
        FiltroFileChooser filtersh = new FiltroFileChooser();
        filtersh.addExtension("sh");
        filtersh.setDescription("Script Unix ");
        String barra = System.getProperty("file.separator");
        if(tip.equals("Guardar fichero capturado..."))
        {
            fc.setDialogTitle("Guardar fichero capturado...");
            fc.setApproveButtonText("Guardar");
            fc.addChoosableFileFilter(filterpcap);
            fc.setFileFilter(filterpcap);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Guardar fichero de preferecias..."))
        {
            fc.setDialogTitle("Guardar fichero de preferecias...");
            fc.setApproveButtonText("Guardar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Exportar fichero a XML...") || tip.equals("Captura a XML..."))
        {
            fc.setDialogTitle("Exportar fichero...");
            fc.setApproveButtonText("Exportar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_EXPORTACIONES"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Browse..."))
        {
            fc.setDialogTitle("Elegir ruta y fichero donde capturar...");
            fc.addChoosableFileFilter(filterpcap);
            fc.setFileFilter(filterpcap);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("Browse...FromFile"))
        {
            fc.setDialogTitle("Elegir ruta y fichero donde capturar...");
            fc.addChoosableFileFilter(filterpcap);
            fc.setFileFilter(filterpcap);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_CAPTURAS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GuardarInicioCapturaXML"))
        {
            fc.setDialogTitle("Guardar Preferencias de Captura...");
            fc.setApproveButtonText("Guardar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GuardarInicioCapturaXMLFromFile"))
        {
            fc.setDialogTitle("Guardar Preferencias de Captura desde fichero...");
            fc.setApproveButtonText("Guardar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GenerarBatBat"))
        {
            fc.setDialogTitle("Guardar fichero .bat...");
            fc.setApproveButtonText("Aceptar");
            fc.setFileFilter(filterbat);
            fc.setFileFilter(filtersh);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_SCRIPTS"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GenerarFromFileXML"))
        {
            fc.setDialogTitle("Guardar fichero .XML...");
            fc.setApproveButtonText("Aceptar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_EXPORTACIONES"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GenerarFromFileXMLSaveXML"))
        {
            fc.setDialogTitle("Guardar Preferencias de Exportaci\363n...");
            fc.setApproveButtonText("Guardar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_PARAMETRIZACION"));
            fc.setCurrentDirectory(directorio);
        }
        if(tip.equals("GuardarProtocoloDefinido"))
        {
            fc.setDialogTitle("Guardar definicion de protocolo...");
            fc.setApproveButtonText("Guardar");
            fc.addChoosableFileFilter(filterxml);
            fc.setFileFilter(filterxml);
            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_DEFINICIONES"));
            fc.setCurrentDirectory(directorio);
        }
//        if(tip.equals("ResultadoIntrusos"))
//        {
//            fc.setDialogTitle("Guardar Resultados Intrusos");
//            fc.setApproveButtonText("Guardar");
//            fc.setFileFilter(filtertxt);
//            File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_INTRUSO"));
//            fc.setCurrentDirectory(directorio);
//            PreferenciasIntruso.setDestino(fRuta);
//        }        
        
        int returnVal = fc.showOpenDialog(MenuSniffer.getFrames()[0]);
        if(returnVal == 0)
        {
            File file = fc.getSelectedFile();
            String nombre = file.getName();
            fRuta = file.getAbsolutePath();
            String sobreescribir = "Si";
            if((new File(fRuta)).exists())
            {
                String respuesta = (new AvisoFicheroExiste(new File(fRuta))).getrespuesta();
                if(respuesta.equals("Si"))
                    sobreescribir = "Si";
                else
                    sobreescribir = "No";
            }
            if(sobreescribir == "Si")
            {
                if(tip.equals("Guardar fichero capturado..."))
                {
                    mediador.savePcapFileCaptured(fRuta);
                    System.out.println((new StringBuilder("Guardar fichero capturado...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("Guardar fichero de preferecias..."))
                {
                    mediador.PrefCapGuardarXML(1, fRuta);
                    System.out.println((new StringBuilder("Guardar fichero de preferecias...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                System.out.println("=>");
                System.out.println("=>");
                System.out.println(tip.toString());
                System.out.println("=>");
                if(tip.equals("Exportar fichero a XML...") || tip.equals("Exportar Captura a XML...") || tip.equals("Captura a XML..."))
                {
                    mediador.guardarXML(file);
                    System.out.println((new StringBuilder("Exportar fichero......=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("Browse..."))
                {
                    mediador.setPrefCapFichero(fRuta);
                    System.out.println((new StringBuilder("Fichero donde se guardara la captura...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("Browse...FromFile"))
                {
                    mediador.setPrefCapFicheroFromFileFichero(fRuta);
                    System.out.println((new StringBuilder("Fichero donde se guardara la captura...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GuardarInicioCapturaXML"))
                {
                    mediador.PrefCapGuardarXML(1, fRuta);
                    System.out.println((new StringBuilder("Guardar fichero de preferecias...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GuardarInicioCapturaXMLFromFile"))
                {
                    mediador.PrefCapGuardarXMLFromFile(3, fRuta);
                    System.out.println((new StringBuilder("Guardar fichero de preferecias...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GenerarBatBat"))
                {
                    mediador.setPrefGenFicheroBat(fRuta);
                    System.out.println((new StringBuilder("Seleccion de fichero .bat .....=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GenerarFromFileXML"))
                {
                    mediador.setExportFromFileXML(fRuta);
                    System.out.println((new StringBuilder("Seleccion de fichero .XML .....=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GenerarFromFileXMLSaveXML"))
                {
                    mediador.PrefExpGuardarXML(2, fRuta);
                    System.out.println((new StringBuilder("Guardar fichero de preferecias...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
                }
                if(tip.equals("GuardarProtocoloDefinido"))
                {
                    mediador.PrefDefGuardarXML(7, fRuta);
                    System.out.println((new StringBuilder("Guardar definicion de protocolo...=> ")).append(file.getName()).append("   ").append(file.getAbsolutePath()).toString());
                }
//                if(tip.equals("ResultadoIntrusos"))
//                {
//                    fc.setDialogTitle("Guardar Resultados Intrusos");
//                    fc.setApproveButtonText("Guardar");
//                    fc.setFileFilter(filtertxt);
//                    File directorio = new File(FachadaFicheroDirectorios.getdirectorio("DIR_INTRUSO"));
//                    fc.setCurrentDirectory(directorio);
//                    System.out.println((new StringBuilder("Guardar de fichero txt...=> ")).append(file.getName()).append("   ").append(file.getPath()).toString());
//                    PreferenciasIntruso.setDestino(fRuta);
//                }
            }
        } else
        {
            System.out.println("Cancelado");
        }
    }

    public static String getrutaFichero()
    {
        return fRuta;
    }

    public static String fRuta;
    public String fName;
    private BufferedReader in;
    FileInputStream inStream;
    Mediador mediador;
    String tip;
}
