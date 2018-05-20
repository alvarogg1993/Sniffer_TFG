

package dominio;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.text.Element;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;



import dominio.browser.UtilNavegador;
import dominio.export.script.FachadaGenerarScript;
import dominio.export.script.GenerarBat;
import dominio.export.script.GenerarSh;
import dominio.export.xml_PcapLib.CrearXMLOffline;
import dominio.export.xml_PcapLib.XmlPacketHandler;
import dominio.pcapDumper.Captura;
import dominio.pcapDumper.CountPacketHandler;
import dominio.pcapDumper.Filtro;
import dominio.pcapDumper.SaveFileName;
import dominio.pcapDumper.StateCaptura;
import dominio.preferences.preferencesBeanCapture;
import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesBeanExport;
import dominio.preferences.preferencesBeanExportInsercion;
import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesBeanMeta;
import dominio.preferences.preferencesFileRead;
import dominio.preferences.capture.PreferencesSniffer;
import dominio.preferences.definicion.PreferencesCheckDefinicion;
import dominio.preferences.definicion.PreferencesSnifferDefinicion;
import dominio.properties.PropertiesBeanSniffer;
import dominio.properties.PropertiesFileRead;

/** 
 * Clase FachadaDominio 
 *  
 * @author Jose Manuel Saiz, Rodrigo S�nchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/

public class FachadaDominio
{

	 static String names[];
	private static int i;
	private static ArrayList<PcapIf> alldevs;
	public static  String dispoName;
	private static String dispoDescripcion;
	public FachadaDominio()
    {
    }

    public static void mostrarAyudaSniffer(String ruta)
    {
        try
        {
            UtilNavegador.abrirURL(ruta);
        }
        catch(IOException e)
        {
            System.out.println("El fichero xml no se ha podido abrir");
        }
    }

    public static Captura crearPcapLib()
    {
        Captura aux = new Captura();
        jpcap = aux;
        return jpcap;
    }

    public static void setPcapLib()
    {
        jpcap = crearPcapLib();
    }

    public static Captura getPcapLib()
    {
        return jpcap;
    }
    /** Metodo donde se carga y devuelve los dispostivios de red de que dispone 
     *  el equipo, Tarjeta de red, wifi,....
     * @param Sin valor de entrada 
     * @return String[] Lista string donde se cargan los dispositivos del equipo 
     */
    public static String[] getCapDispositivosPcapLib()
    {
        if(jpcap != null)
        {
            if(jpcap.getPcap() != null)
                jpcap.getPcap().close();
            jpcap.getPcap_writer();
        }
        Captura jpcap = crearPcapLib();
        String[] aux = jpcap.capDispositivos();
        return aux;
    }
    
    /** Metodo donde se carga y devuelve los dispostivios de red de que dispone 
     *  el equipo, Tarjeta de red, wifi,....
     * @param Sin valor de entrada 
     * @return String[] Lista string donde se cargan los dispositivos del equipo 
     */
    public static String[] getCapDispositivosPcapLibDes()
    {
    	
    	alldevs = new ArrayList<PcapIf>(); 
        StringBuilder errbuf = new StringBuilder();     
        int r = Pcap.findAllDevs(alldevs, errbuf);
        
        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {               
                System.err.printf("No se puede leer la lista de dispositivos, error is %s\n", errbuf.toString());
                return names;
        } 
     //   System.out.println("Estos son todos los dispotivos");
      
            	 names = new String[alldevs.size()];
        for (i = 0; i < names.length; i++) {
        	names[i] = alldevs.get(i).getName();
//        	names[i] = alldevs.get(i).getName() + " # " + alldevs.get(i).getDescription();
       	 //names[i] = alldevs.get(i).getDescription() != null ? alldevs.get(i).getDescription() : alldevs.get(i).getName();
          // System.out.println(names[i] + names.length);
         }
    	     
		return names;
    }
    
    public static void openCapturaPcapLib()
        throws Exception
    {
        try
        {
            Captura jpcap = crearPcapLib();
            System.out.println((new StringBuilder("----> Dispositivo => ")).append(getPrefBeanCaptura().getCapDispositive()).toString());
             System.out.println((isDispositivo(getPrefBeanCaptura().getCapDispositive().toString())));
             jpcap.openCaptura(isDispositivo(getPrefBeanCaptura().getCapDispositive().toString()));
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public static void startCapturaPcapLib()
    {
        Capturando = true;
        getPcapLib().start(); //hilo en captura
    }

    
    /** codigo muerto */
    @SuppressWarnings("deprecation")
	public static void startCapturaPcapLibMeta()
    {
        Capturando = true;
        getPcapLib().stop();
        getPcapLib().interrupt();
        getPcapLib().start();
    }

    public static void setListenerPcapLib()
    {
        String aux = "0";
        getPcapLib().setListener();
        getPcapLib().setTypeOffline(false);
        if(getPrefBeanCaptura().getFilMultipleFileId())
        {
            if(getPrefBeanCaptura().getFilSpaceId())
            {
                aux = getMFilSpaceBytes(getPrefBeanCaptura().getFilSpace(), getPrefBeanCaptura().getFilSpaceType());
                getPcapLib().setMFilSpace(aux);
            }
            if(getPrefBeanCaptura().getFilTimeId())
            {
                aux = getMFilTimeSegundos(getPrefBeanCaptura().getFilTime(), getPrefBeanCaptura().getFilTimeType());
                getPcapLib().setMFilTime(aux);
            }
            if(getPrefBeanCaptura().getFilRingBufferId())
                getPcapLib().setMFilPila(getPrefBeanCaptura().getFilRingBuffer());
            if(getPrefBeanCaptura().getFilStopAfterId())
                getPcapLib().setMFilStop(getPrefBeanCaptura().getFilStopAfter());

            if(getPrefBeanCaptura().getFilRingBufferXML())
            	getPcapLib().setXMLCircular(true);
            else
            	getPcapLib().setXMLCircular(false);

        } else
        if(getPrefBeanCaptura().getstpAfterSpaceId())
        {
            aux = getMFilSpaceBytes(getPrefBeanCaptura().getstpAfterSpace(), getPrefBeanCaptura().getstpAfterSpaceType());
            getPcapLib().setMFilSpace(aux);
            getPcapLib().setMFilStop("1");
        } else
        if(getPrefBeanCaptura().getstpAfterTimeId())
        {
            aux = getMFilTimeSegundos(getPrefBeanCaptura().getstpAfterTime(), getPrefBeanCaptura().getstpAfterTimeType());
            getPcapLib().setMFilTime(aux);
            getPcapLib().setMFilStop("1");
        }
        if(getPrefBeanCaptura().getstpAfterPacketsId())
            getPcapLib().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
    }

    public static void setControlPacket(boolean aux)
    {
        getPcapLib().setControlPacket(aux);
    }

    public static void setFilePcapLibOpenOffline()
    {
        setPcapLib();
        getPcapLib().openOffline(getPrefBeanFromFile().getffCapSource());
    }

    public static void setListenerPcapLibOffline()
    {
        String aux = "0";
        getPcapLib().setListenerOffline();
        getPcapLib().setTypeOffline(true);
        if(getPrefBeanFromFile().getffFilMultipleFileId())
        {
            if(getPrefBeanFromFile().getffFilSpaceId())
            {
                aux = getMFilSpaceBytes(getPrefBeanFromFile().getffFilSpace(), getPrefBeanFromFile().getffFilSpaceType());
                getPcapLib().setMFilSpaceOffline(aux);
            }
            if(getPrefBeanFromFile().getffFilRingBufferId())
                getPcapLib().setMFilPilaOffline(getPrefBeanFromFile().getffFilRingBuffer());
            if(getPrefBeanFromFile().getffFilStopAfterId())
                getPcapLib().setMFilStopOffline(getPrefBeanFromFile().getffFilStopAfter());
        }
        if(getPrefBeanFromFile().getffStpAfterPacketsId())
            getPcapLib().setNumPaquetes(getPrefBeanFromFile().getffStpAfterPackets());
    }

    public static CountPacketHandler getCountPacketHandler()
    {
        return getPcapLib().getCountPacketHandler();
    }

  
    /** codigo muerto*/
    public static void stopCapturaDumper()
    {
        getPcapLib().endCapture(false);
        if(getPcapLib() != null)
            jpcap = null;
        Capturando = false;
    }

    public static void stopCaptura()
    {
        getPcapLib().endCapture(false);
        if(getPcap() != null)
            pcap = null;
        Capturando = false;
    }

    public static void stopCapturaPcapLib()
    {
        getPcapLib().stopCaptureThread();
        Capturando = false;
    }

    public static boolean getEndCapture()
    {
        return getPcapLib().getEndCapture();
    }

    public static boolean getEndAll(){
		boolean state;
		state = getPcapLib().finSaveMeta;
		if  (state == true){
		if (getPcapLib() != null){
			pcap = null;
		}
		}
		return state;
	}
    public static boolean getEstadoCaptura()
    {
        return Capturando;
    }

    public static boolean getRunningExport()
    {
        return stateCommandActivo;
    }

    public static boolean getEndFromFile()
    {
        return getPcapLib().getEndCapture();
    }

    public static void stopCapturaDumperCommand()
    {
        getPcapLib().endCaptureCommand();
    }

    public static void stopCapturaDumperCommandOnly()
    {
        getPcapLib().endCaptureCommand();
    }

    public static void setFileReaded(String ffile)
    {
        lastFileReaded = ffile;
    }

    public static String getFileReaded()
    {
        return lastFileReaded;
    }

    public static void saveMetaCaptura()
    {
        SaveFileName auxSFN = getPcapLib().getSFname();
        preferencesBeanMeta pBM = getPrefBeanMeta();
        preferencesBeanCapture pBC = getPrefBeanCaptura();
        pBM.setMetLocRelativeId(false);
        pBM.setMetLocRelative("");
        pBM.setMetLocAbsotuteId(true);
        String sNameFile;
     
        if(pBC.getFilMultipleFileId() || pBC.getstpAfterSpaceId() || pBC.getstpAfterTimeId())
            sNameFile = auxSFN.getNTwithout();
        else
            sNameFile = auxSFN.getNameFile();
        
        String sNamePathFile = (new StringBuilder(String.valueOf(auxSFN.getPath()))).append(auxSFN.getBarra()).append(sNameFile).toString();
        pBM.setMetLocAbsotutePath(auxSFN.getPath());
        pBM.setMetLocAbsotuteName(sNameFile);
        pBM.setMetMultipleFileId(pBC.getFilMultipleFileId());
        pBM.setMetMFRingBufferId(pBC.getFilRingBufferId());
        pBM.setMetMFRingBuffer(pBC.getFilRingBuffer());
        if(auxSFN.getPrimero() > auxSFN.getPila() && pBC.getFilRingBufferId())
            auxSFN.setNext();
        pBM.setMetMFStar(Integer.toString(auxSFN.getPrimero()));
        pBM.setMetMFEnd(Integer.toString(auxSFN.getUltimo()));
        PreferencesSniffer PS = new PreferencesSniffer();
        PS.GenerateCapture(pBC);
        PS.GenerateMeta(pBM);
        PS.setFile((new StringBuilder(String.valueOf(sNamePathFile))).append("_META.XML").toString());
        PS.savePreferences();
    }

    public static void saveMetaCapturaOffline()
    {
        SaveFileName auxSFN = getPcapLib().getSFname();
        preferencesBeanMeta pBM = getPrefBeanMeta();
        preferencesBeanFromFile PBFF = getPrefBeanFromFile();
        pBM.setMetLocRelativeId(false);
        pBM.setMetLocRelative("");
        pBM.setMetLocAbsotuteId(true);
        String sNameFile;
        if(PBFF.getffFilMultipleFileId())
            sNameFile = auxSFN.getNTwithout();
        else
            sNameFile = auxSFN.getNameFile();
        String sNamePathFile = (new StringBuilder(String.valueOf(auxSFN.getPath()))).append(auxSFN.getBarra()).append(sNameFile).toString();
        pBM.setMetLocAbsotutePath(auxSFN.getPath());
        pBM.setMetLocAbsotuteName(sNameFile);
        pBM.setMetMultipleFileId(PBFF.getffFilMultipleFileId());
        pBM.setMetMFRingBufferId(PBFF.getffFilRingBufferId());
        pBM.setMetMFRingBuffer(PBFF.getffFilRingBuffer());
        if(auxSFN.getPrimero() > auxSFN.getPila() && PBFF.getffFilRingBufferId())
            auxSFN.setNext();
        pBM.setMetMFStar(Integer.toString(auxSFN.getPrimero()));
        pBM.setMetMFEnd(Integer.toString(auxSFN.getUltimo()));
        PreferencesSniffer PS = new PreferencesSniffer();
        PS.GenerateFromto(PBFF);
        PS.GenerateMeta(pBM);
        PS.setFile((new StringBuilder(String.valueOf(sNamePathFile))).append("_META.XML").toString());
        PS.savePreferences();
    }

    public static void setPrefCapturaNFS(boolean b)
    {
    	getPcapLib().setNFSSync(b);
    }
    public static void setPrefCapturaXMLCircular(boolean b)
    {
    	getPcapLib().setXMLCircular(b);
    }
    
    public static void setPrefCapturaDumper()
    {
        getPcapLib().setFilePathCapture(getPrefBeanCaptura().getFilLocate());
        if(getPrefBeanCaptura().getstpAfterPacketsId())
            getPcapLib().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
        if(getPrefBeanCaptura().getCapPromiscuousMode())
            getPcapLib().setPromiscuo(true);
        else
            getPcapLib().setPromiscuo(true);
        if(getPrefBeanCaptura().getNFSsync())
            getPcapLib().setNFSSync(true);
        else
            getPcapLib().setNFSSync(false);
        if(getPrefBeanCaptura().getstpAfterPacketsId())
            getPcapLib().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
    }

    public static void setPrefFromFileDumper()
    {
        getPcapLib().setFilePathCapture(getPrefBeanFromFile().getffFilLocate());
        if(getPrefBeanFromFile().getffStpAfterPacketsId())
            getPcapLib().setNumPaquetes(getPrefBeanFromFile().getffStpAfterPackets());
        if(getPrefBeanFromFile().getffStpAfterPacketsId())
            getPcapLib().setNumPaquetes(getPrefBeanFromFile().getffStpAfterPackets());
    }

    public static Filtro setFilterDumperFromFile()
    {
        Filtro fil = new Filtro();
        if(getPrefBeanFromFile().getffCapFilterId())
            if(getPrefBeanFromFile().getffCapAdvanceId())
                fil.setFiltro_AV(getPrefBeanFromFile().getffCapAdvance());
            else
                fil.setFiltro(getPrefBeanFromFile().getffCapHost(), getPrefBeanFromFile().getffCapProtocol(), getPrefBeanFromFile().getffCapPort());
        return fil;
    }

    public static int saveMetaOrPcapToFile()
    {
        int valorReturn = 0;
        String nomFileOrigen = getPrefBeanFromFile().getffCapSource();
        String nomFileDestino = getPrefBeanFromFile().getffFilLocate();
        System.out.println((new StringBuilder("----> Fichero Origen => ")).append(nomFileOrigen).toString());
        System.out.println((new StringBuilder("----> Fichero Destino => ")).append(nomFileDestino).toString());
        System.out.println("--> Start FromFile");
        File fOrigen = new File(nomFileOrigen);
        boolean exists = fOrigen.exists();
        if(exists)
        {
            int dotPlace = nomFileOrigen.lastIndexOf(".");
            String ext;
            if(dotPlace >= 0)
                ext = nomFileOrigen.substring(dotPlace);
            else
                ext = "";
            if(ext.toLowerCase().equals(".pcap"))
                savePcapToFile();
            else
            if(ext.toLowerCase().equals(".xml"))
                saveMetaToFile();
        }
        return valorReturn;
    }

    public static int savePcapToFile(String auxFile)
    {
        getPreferencesFromFile();
        getPrefBeanFromFile().setDefaultSettings();
        getPrefBeanFromFile().setffCapSource(getFileReaded());
        getPrefBeanFromFile().setffFilLocate(auxFile);
        return savePcapToFile();
    }
    
    /** Metodo donde llegan los datos para la busqueda de intrusos en modo diferido
     *  Direcci�n del fichero captura, direcci�n de fichero lista negra y donde se quiere dejar el re.
     * @param Origen, Origen2, Destino 
     * @return Sin valor de retorno 
     * @exception exceptions Ning�n error (Excepci�n) definida
     */

	
    private static char[] getTagValue(String string, Element e) {
			return null;
	}

	private static int savePcapToFile()
    {
        int valorReturn = 0;
        try
        {
            setFilePcapLibOpenOffline();
            setPrefFromFileDumper();
            setListenerPcapLibOffline();
            startCapturaPcapLib();
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
        return valorReturn;
    }

    private static int saveMetaToFile()
    {
        int valorReturn = 0;
        boolean unaVez = false;
        String nomFileOrigen = getPrefBeanFromFile().getffCapSource();
        String nomFileDetino = getPrefBeanFromFile().getffFilLocate();
        setPreferencesMETA(nomFileOrigen);
        getPreferences().leerXMLMETA();
        try
        {
            dominio.pcap.SaveFileName SFName;
            if(getPrefBeanMeta().getMetLocRelativeId())
            {
                SFName = new dominio.pcap.SaveFileName(getPreferences().getFileMETA());
                SFName.setFile(getPrefBeanMeta().getMetLocRelative());
                SFName.getPartFile();
            } else
            {
                SFName = new dominio.pcap.SaveFileName(getPrefBeanMeta().getMetLocAbsotutePath(), getPrefBeanMeta().getMetLocAbsotuteName());
            }
            if(getPrefBeanMeta().getMetMultipleFileId())
            {
                int iRingBuffer;
                if(getPrefBeanMeta().getMetMFRingBufferId())
                    iRingBuffer = Integer.parseInt(getPrefBeanMeta().getMetMFRingBuffer());
                else
                    iRingBuffer = 0;
                int iStart = Integer.parseInt(getPrefBeanMeta().getMetMFStar());
                int iEnd = Integer.parseInt(getPrefBeanMeta().getMetMFEnd());
                int valorErrorSFName = SFName.SFNOffline(iRingBuffer, iStart - 1, iEnd);
                if(valorErrorSFName != 0)
                {
                    valorReturn = 2;
                } else
                {
                    SFName.setNext();
                    int aux = SFName.getNext();
                    setPcapLib();
                    if(aux > 0)
                        getPcapLib().openOffline_writer(SFName.getFullPathOffline());
                    while(aux > 0) 
                    {
                        getPcapLib().openOffline_read(SFName.getFullPathOffline());
                        setPrefFromFileDumper();
                        if(!unaVez)
                        {
                            setListenerPcapLibOffline();
                            getPcapLib().runHilosCapture();
                            unaVez = true;
                        }
                        getPcapLib().offfffff();
                        SFName.setNext();
                        aux = SFName.getNext();
                        getPcapLib().getPcap().close();
                    }
                    getPcapLib().eCOF();
                }
            } else
            {
                setPcapLib();
                getPcapLib().openOffline(SFName.getFullPathOffline());
                setPrefFromFileDumper();
                setListenerPcapLibOffline();
                getPcapLib().runHilosCapture();
                getPcapLib().offfffff();
                getPcapLib().eCOF();
            }
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
        return valorReturn;
    }

    public static int saveMetaOrPcapToXML(String fileOrigen, String fileXml, boolean multifile)
    {
        int valorReturn = 0;
        File fOrigen = new File(fileOrigen);
        boolean exists = fOrigen.exists();
        if(exists)
        {
            int dotPlace = fileOrigen.lastIndexOf(".");
            String ext;
            if(dotPlace >= 0)
                ext = fileOrigen.substring(dotPlace);
            else
                ext = "";
            if(ext.toLowerCase().equals(".pcap"))
                saveXMLOfflinePcapLib(fileOrigen, fileXml);
            else
            if(ext.toLowerCase().equals(".xml"))
                saveMetaXMLOfflinePcapLib(fileOrigen, fileXml, multifile);
        }
        return valorReturn;
    }

    public static int saveXML(File file)
    {
        int returnVal;
        if(file != null)
        {
            getPreferencesExport();
            getPrefBeanExport().setExpSource(getFileReaded());
            getPrefBeanExport().setExpDestination((new StringBuilder()).append(file.getAbsoluteFile()).append(".xml").toString());
            saveXMLOffline(getPrefBeanExport());
            returnVal = 1;
        } else
        {
            returnVal = 0;
        }
        return returnVal;
    }

    public static int saveXMLOffline(preferencesBeanExport pBE)
    {
        try
        {
            String nomFileOrigen = pBE.getExpSource();
            String nomFileDestino = pBE.getExpDestination();
            boolean multiFile = pBE.getExpMultifile();
            System.out.println((new StringBuilder("----> Fichero Origen => ")).append(nomFileOrigen).toString());
            System.out.println((new StringBuilder("----> Fichero Destino => ")).append(nomFileDestino).toString());
            System.out.println("--> Start Export");
            
            saveMetaOrPcapToXML(nomFileOrigen, nomFileDestino, multiFile);
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
        return 0;
    }

    public static int saveXMLOfflinePcapLib(String file, String fileXml)
    {
        String fichero = file;
        try
        {
            CrearXMLOffline CXMLOffline = new CrearXMLOffline(fileXml);
            XmlPacketHandler XMLPHandler = new XmlPacketHandler(CXMLOffline);
            setPcapLib();
            getPcapLib().openOffline(file);
            getPcapLib().offline_xml(XMLPHandler);
            CXMLOffline.endFile();
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
        stateCommandActivo = false;
        return 0;
    }
    /** Metodo donde se reciben los datos para crear un archivo XML y meter aqui
     *  los paquetes capturados.
     * @param   String fileOrigen, String fileXml, boolean multifile
     * @return int
     * @exception exceptions Ning�n error (Excepci�n) definida
     */
    public static int saveMetaXMLOfflinePcapLib(String fileOrigen, String fileXml, boolean multifile)
    {
        int valorReturn = 0;
        String nomFileOrigen = fileOrigen;
        String nomFileDetino = fileXml;
        setPreferencesMETA(nomFileOrigen);
        getPreferences().leerXMLMETA();
        try
        {
            dominio.pcap.SaveFileName SFName;
            if(getPrefBeanMeta().getMetLocRelativeId())
            {
                SFName = new dominio.pcap.SaveFileName(getPreferences().getFileMETA());
                SFName.setFile(getPrefBeanMeta().getMetLocRelative());
                SFName.getPartFile();
            } else
            {
                SFName = new dominio.pcap.SaveFileName(getPrefBeanMeta().getMetLocAbsotutePath(), getPrefBeanMeta().getMetLocAbsotuteName());
            }
            if(getPrefBeanMeta().getMetMultipleFileId())
            {
                int iRingBuffer;
                if(getPrefBeanMeta().getMetMFRingBufferId())
                    iRingBuffer = Integer.parseInt(getPrefBeanMeta().getMetMFRingBuffer());
                else
                    iRingBuffer = 0;
                int iStart = Integer.parseInt(getPrefBeanMeta().getMetMFStar());
                int iEnd = Integer.parseInt(getPrefBeanMeta().getMetMFEnd());
                int valorErrorSFName = SFName.SFNOffline(iRingBuffer, iStart - 1, iEnd);
                if(valorErrorSFName != 0)
                    valorReturn = 2;
                else
                if(!multifile)
                {
                    SFName.setNext();
                    int aux = SFName.getNext();
                    setPcapLib();
                    CrearXMLOffline CXMLOffline = new CrearXMLOffline(fileXml);
                    XmlPacketHandler XMLPHandler = new XmlPacketHandler(CXMLOffline);
                    System.out.println((new StringBuilder("--.--> ")).append(fileXml).toString());
                    while(aux > 0) 
                    {
                        getPcapLib().openOffline(SFName.getFullPathOffline());
                        getPcapLib().offline_xml(XMLPHandler);
                        SFName.setNext();
                        aux = SFName.getNext();
                        getPcapLib().both_ECOF();
                    }
                    CXMLOffline.endFile();
                } else
                {
                    SFName.setNext();
                    int aux = SFName.getNext();
                    setPcapLib();
                    while(aux > 0) 
                    {
                        System.out.println((new StringBuilder("--:--> ")).append(fileXml).append("_").append(aux).append(".xml").toString());
                        CrearXMLOffline CXMLOffline = new CrearXMLOffline((new StringBuilder(String.valueOf(fileXml))).append("_").append(aux).append(".xml").toString());
                        XmlPacketHandler XMLPHandler = new XmlPacketHandler(CXMLOffline);
                        getPcapLib().openOffline(SFName.getFullPathOffline());
                        getPcapLib().offline_xml(XMLPHandler);
                        SFName.setNext();
                        aux = SFName.getNext();
                        CXMLOffline.endFile();
                        getPcapLib().both_ECOF();
                    }
                }
            } else
            {
                setPcapLib();
                CrearXMLOffline CXMLOffline = new CrearXMLOffline(fileXml);
                XmlPacketHandler XMLPHandler = new XmlPacketHandler(CXMLOffline);
                System.out.println((new StringBuilder("--.--> ")).append(fileXml).toString());
                getPcapLib().openOffline(SFName.getFullPathOffline());
                getPcapLib().offline_xml(XMLPHandler);
                CXMLOffline.endFile();
                getPcapLib().both_ECOF();
            }
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
        stateCommandActivo = false;
        return valorReturn;
    }

    private static boolean validate(String state)
    {
        boolean auxB;
        if(state.toUpperCase().equals("YES"))
            auxB = true;
        else
            auxB = false;
        return auxB;
    }

    public static int saveXMLfromEstate(String file)
    {
        StateCaptura ficheroEstate = new StateCaptura();
        int returnVal;
        if(file != null)
        {
            getPrefBeanMeta().setDefaultSettings();
            ficheroEstate.setFile(file);
            ficheroEstate.leerPorperties();
            getPrefBeanMeta().setMetLocAbsotutePath(ficheroEstate.getLocate());
            getPrefBeanMeta().setMetLocAbsotuteName(ficheroEstate.getName());
            getPrefBeanMeta().setMetMultipleFileId(validate(ficheroEstate.getMultipleFile()));
            getPrefBeanMeta().setMetMFRingBuffer(ficheroEstate.getRingBuffer());
            saveFromEstate(file, ficheroEstate);
            returnVal = 1;
        } else
        {
            returnVal = 0;
        }
        return returnVal;
    }

    /** Metodo donde se rigen los criterios para salvar la paquetes capturados 
     *  en un archivo cuya direcci�n se recibe.
     * @param String file, StateCaptura ficheroEstate 
     * @return int  
     * @exception exceptions Ning�n error (Excepci�n) definida
     */    
    private static int saveFromEstate(String file, StateCaptura ficheroEstate)
    {
        int valorReturn = 0;
        try
        {
            dominio.pcap.SaveFileName SFName = new dominio.pcap.SaveFileName(getPrefBeanMeta().getMetLocAbsotutePath(), getPrefBeanMeta().getMetLocAbsotuteName());
            if(getPrefBeanMeta().getMetMultipleFileId())
            {
                int iRingBuffer;
                if(getPrefBeanMeta().getMetMFRingBufferId())
                    iRingBuffer = Integer.parseInt(getPrefBeanMeta().getMetMFRingBuffer());
                else
                    iRingBuffer = 0;
                int iStart = Integer.parseInt(getPrefBeanMeta().getMetMFStar());
                int iEnd = Integer.parseInt(getPrefBeanMeta().getMetMFEnd());
                int valorErrorSFName = 0;
                if(valorErrorSFName != 0)
                {
                    valorReturn = 2;
                } else
                {
                    setPcapLib();
                    SFName.setNext();
                    for(int aux = SFName.getNext(); aux > 0;)
                    {
                        boolean auxBoolean;
                        if(ficheroEstate.getLastNumFileCapturado().equals(String.valueOf(aux)))
                            auxBoolean = false;
                        else
                            auxBoolean = true;
                        if(ficheroEstate.getCapturando().equals("Yes") && auxBoolean || ficheroEstate.getCapturando().equals("No"))
                        {
                            String nomFileDetino = (new StringBuilder(String.valueOf(SFName.getPath()))).append(SFName.getBarra()).append(SFName.getNameFile()).append("_").append(aux).append(".xml").toString();
                            System.out.println((new StringBuilder("--:--> ")).append(nomFileDetino).toString());
                            CrearXMLOffline CXMLOffline = new CrearXMLOffline(nomFileDetino);
                            XmlPacketHandler XMLPHandler = new XmlPacketHandler(CXMLOffline);
                            String nomFileOrigen = (new StringBuilder(String.valueOf(SFName.getPath()))).append(SFName.getBarra()).append(SFName.getNameFile()).append("_").append(aux).append(ficheroEstate.getExtension()).toString();
                            getPcapLib().openOffline(nomFileOrigen);
                            getPcapLib().offline_xml(XMLPHandler);
                            SFName.setNext();
                            aux = SFName.getNext();
                            CXMLOffline.endFile();
                            getPcapLib().both_ECOF();
                            if(!auxBoolean)
                                aux = -1;
                        }
                        if(aux != -1)
                        {
                            for(int cont = 0; cont < 10000; cont++);
                            ficheroEstate.leerPorperties();
                        }
                    }

                }
            } else
            {
                for(int aux = 1; aux > 0;)
                {
                    if(ficheroEstate.getCapturando().equals("No"))
                    {
                        setPcapLib();
                        String nomFileDetino = (new StringBuilder(String.valueOf(SFName.getPath()))).append(SFName.getBarra()).append(SFName.getNameFile()).append(".xml").toString();
                        CrearXMLOffline CXMLOffline = new CrearXMLOffline(nomFileDetino);
                        XmlPacketHandler XMLPHandler = new XmlPacketHandler(CXMLOffline);
                        System.out.println((new StringBuilder("--.--> ")).append(nomFileDetino).toString());
                        String nomFileOrigen = (new StringBuilder(String.valueOf(SFName.getPath()))).append(SFName.getBarra()).append(SFName.getNameFile()).append(ficheroEstate.getExtension()).toString();
                        getPcapLib().openOffline(SFName.getFullPathOffline());
                        getPcapLib().offline_xml(XMLPHandler);
                        CXMLOffline.endFile();
                        getPcapLib().both_ECOF();
                        aux = -1;
                    }
                    if(aux != -1)
                    {
                        for(int cont = 0; cont < 10000; cont++);
                        ficheroEstate.leerPorperties();
                    }
                }

            }
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
        stateCommandActivo = false;
        return valorReturn;
    }

    private static int savefromEstate(File file)
    {
        StateCaptura ficheroEstate = new StateCaptura();
        SaveFileName controSFN = new SaveFileName();
        int returnVal;
        if(file != null)
        {
            getPrefBeanMeta().setDefaultSettings();
            ficheroEstate.setFile(file.getAbsolutePath());
            ficheroEstate.leerPorperties();
            getPrefBeanMeta().setMetLocAbsotutePath(ficheroEstate.getLocate());
            getPrefBeanMeta().setMetLocAbsotuteName(ficheroEstate.getName());
            getPrefBeanMeta().setMetMultipleFileId(validate(ficheroEstate.getMultipleFile()));
            returnVal = 1;
        } else
        {
            returnVal = 0;
        }
        return returnVal;
    }

    private static preferencesFileRead getPreferences()
    {
        if(preferences == null)
        {
            preferencesFileRead aux = new preferencesFileRead();
            preferences = aux;
        }
        return preferences;
    }

    public static void setPreferencesCapture(String ruta)
    {
        getPreferences().setFileCapture(ruta);
    }

    public static void setPreferencesExport(String ruta)
    {
        getPreferences().setFileExport(ruta);
    }

    public static void setPreferencesFromFile(String ruta)
    {
        getPreferences().setFileFromFile(ruta);
    }

    public static void setPreferencesMETA(String ruta)
    {
        getPreferences().setFileMETA(ruta);
    }

    public static void setPreferencesDefinicion(String ruta)
    {
        getPreferences().setFileDefinicion(ruta);
    }

    public static boolean getPreferencesCapture(boolean porDefecto)
    {
        return getPreferences().leerXMLCapture(porDefecto);
    }

    public static void getPreferencesExport()
    {
        getPreferences().leerXMLExport();
    }

    public static void getPreferencesFromFile()
    {
        getPreferences().leerXMLFromFile();
    }


    public static void getPreferencesDefinicion()
    {
        getPreferences().leerXMLDefinicion();
    }

    public static preferencesBeanCapture getPrefBeanCaptura()
    {
        preferencesFileRead aux = getPreferences();
        return aux.getPBCapture();
    }

    public static preferencesBeanExport getPrefBeanExport()
    {
        preferencesFileRead aux = getPreferences();
        return aux.getPBExport();
    }

    public static preferencesBeanFromFile getPrefBeanFromFile()
    {
        preferencesFileRead aux = getPreferences();
        return aux.getPBFromFile();
    }

    public static preferencesBeanMeta getPrefBeanMeta()
    {
        preferencesFileRead aux = getPreferences();
        return aux.getPBMeta();
    }

    public static preferencesBeanDefinicion getPrefBeanDefinicion()
    {
        preferencesFileRead aux = getPreferences();
        return aux.getPBDefinicion();
    }

    public static preferencesBeanExportInsercion getPrefBeanExportInsercion()
    {
        preferencesFileRead aux = getPreferences();
        return aux.getPBExportInsercion();
    }

    private static PropertiesFileRead getProperties()
    {
        if(properties == null)
        {
            PropertiesFileRead aux = new PropertiesFileRead();
            properties = aux;
        }
        return properties;
    }

    public static void setPropertiesColumns(String aux)
    {
        getProperties().getPBSniffer().setTableView(aux);
    }

    public static void grabarProperties()
    {
        getProperties().grabarPorperties();
    }

    public static PropertiesBeanSniffer getPropBeanSniffer()
    {
        PropertiesFileRead aux = getProperties();
        aux.leerPropertiesSniffer();
        return aux.getPBSniffer();
    }

    public static void savePreferences(int estado, String ruta)
    {
        PreferencesSniffer prue = new PreferencesSniffer();
        prue.setFile(ruta);
        preferencesFileRead aux = getPreferences();
        if(estado == 1 || estado == 4)
            prue.GenerateCapture(aux.getPBCapture());
        if(estado == 2 || estado == 5)
            prue.GenerateExportacion(aux.getPBExport());
        if(estado == 3 || estado == 6)
            prue.GenerateFromto(aux.getPBFromFile());
        if(estado == 4 || estado == 5 || estado == 6)
            prue.GenerateMeta(aux.getPBMeta());
        prue.savePreferences();
        System.out.println("Guarda XML");
    }
/**
 * Metodo savePreferencesDefinicion encargado de guarda el protocolo definido con la extension XML
 * Si la ruta no viene dada la extension, se le a�ade XML a la ruta.
 * @param estado
 * @param ruta
 * @param aux
 */
    public static void savePreferencesDefinicion(int estado, String ruta, preferencesBeanDefinicion aux)
    {
    
        PreferencesSnifferDefinicion prue = new PreferencesSnifferDefinicion();
       
        if (ruta.lastIndexOf(".")>0){
         if (ruta.substring(ruta.lastIndexOf(".")).toLowerCase().equalsIgnoreCase(".xml")){
        	 
         } else {
        	 prue.setFile(ruta+".xml");
         }}else{
        	 prue.setFile(ruta+".xml");
         }
        	 
        
        if(estado == 7)
            prue.GenerateDefinicion(aux);
        prue.savePreferences();
        System.out.println("Guarda XML Protocolo");
    }

    private static String getMFilSpaceBytes(String bytes, String tipo)
    {
        long lSpace = 0;
        try
        {
            lSpace = Long.parseLong(bytes);
        }
        catch(NumberFormatException e)
        {
            System.out.println((new StringBuilder(">>__ ")).append(e.toString()).toString());
            lSpace = 0L;
        }
        if(tipo.toLowerCase().contains("kilo"))
            lSpace *= 1024L;
        else
        if(tipo.toLowerCase().contains("mega"))
        {
            lSpace *= 1024L;
            lSpace *= 1024L;
        } else
        if(tipo.toLowerCase().contains("giga"))
        {
            lSpace *= 1024L;
            lSpace *= 1024L;
            lSpace *= 1024L;
        } else
        {
            lSpace = 0L;
            System.out.println("Tipo de espacio incorrecto. Debe ser kilobyte, megabyte, gigabyte");
        }
        String aux = String.valueOf(lSpace);
        return aux;
    }

    private static String getMFilTimeSegundos(String tiempo, String tipo)
    {
        long lTime = 0L;
        try
        {
            lTime = Long.parseLong(tiempo);
        }
        catch(NumberFormatException e)
        {
            System.out.println((new StringBuilder(">>__ ")).append(e.toString()).toString());
            lTime = 0L;
        }
        if(tipo.toLowerCase().contains("segundo"))
            lTime *= 1000L;
        else
        if(tipo.toLowerCase().contains("minuto"))
        {
            lTime *= 1000L;
            lTime *= 60L;
        } else
        if(tipo.toLowerCase().contains("hora"))
        {
            lTime *= 1000L;
            lTime *= 60L;
            lTime *= 60L;
        } else
        if(tipo.toLowerCase().contains("dia"))
        {
            lTime *= 1000L;
            lTime *= 60L;
            lTime *= 60L;
            lTime *= 24L;
        } else
        {
            lTime = 0L;
            System.out.println("Tipo de tiempo incorrecto. Debe ser segundo, minuto, hora, dia");
        }
        String aux = String.valueOf(lTime);
        return aux;
    }

    public static void setFileBat(String sRutaPref, String sRutaBat, String Tipo, String sMvm, String sSO)
    {
        FachadaGenerarScript genBat;
        if(sSO.equals("Windows"))
            genBat = new GenerarBat();
        else
            genBat = new GenerarSh();
        genBat.setRutaBat(sRutaBat);
        genBat.setMvm(sMvm);
        genBat.setParam(Tipo, sRutaPref);
        genBat.writeToDisk();
    }

    
    /** Metodo donde se realiza la apertura del dispositivo y se configura la escucha 
     * @param String Le llega un string con el nombre de dispositivo seleccionado.
     * @return PcapIf con todos los datos de la configuraci�n de la escucha.
     */
    public static PcapIf isDispositivo(String dispoSeleccionado)
    {        
    
    	 StringBuilder errbuf = new StringBuilder(); // For any error msgs
    	int snaplen = 64 * 1024;           // Capture all packets, no trucation  
        int flags = Pcap.MODE_PROMISCUOUS; // capture all packets  
        int timeout = 10 * 1000;           // 10 seconds in millis        
        
        
        String[] parts = dispoSeleccionado.split("\n");
        dispoDescripcion = parts[0]; // 004
//        for ( int i= 0; i<alldevs.size();i++){
//        	if (alldevs.get(i).getDescription().contains(dispoDescripcion)){
//        		dispoName = alldevs.get(i).getName();
//        	}
//        }
//        for ( int i= 0; i<alldevs.size();i++){
//        	if (alldevs.get(i).getName().contains(dispoDescripcion)){
//        		dispoName = alldevs.get(i).getName();
//        	}
//        }
        
        for ( int i= 0; i<alldevs.size();i++){
        	if (alldevs.get(i).getName().contains(dispoDescripcion)){
        		dispoName = alldevs.get(i).getName();
        	}
        }

        Pcap pcap = Pcap.openLive(dispoName, snaplen, flags, timeout, errbuf);  

        
        if (pcap == null) {  
            System.err.println("Error mientras se abria el dispositivo de escucha "  
                + errbuf.toString());  
            return null;  
        }
      return null;
  }
    /** Metodo donde se cambia el n�mero del dispositivo por su Descripti�n 
     * @param Int dispoSeleccionado Recibe el numero del dispositivo de escucha seleccionado.
     * @return String [] Devuelve un String String PcapIf con todos los datos de la configuraci�n de la escucha.
     */
    public static String isDispositivo(int dispoSeleccionado)
    {
        String[] dispo = getCapDispositivosPcapLib();
       if(dispoSeleccionado - 1 >= 0 && dispoSeleccionado <= dispo.length )
        	
            return dispo[dispoSeleccionado - 1];
        else
            return null;
    }
    /** Metodo donde se crea define y crea un objeto de tipo pcap. 
     * @param Sin valor de entrada 
     * @return dominio.pcap.Captura devuelve un objeto tipo Pcap.
     */
    public static dominio.pcap.Captura crearPcap()
    {
        if(pcap != null)
        {
            pcap.stop();
            pcap = null;
        }
        dominio.pcap.Captura aux = new dominio.pcap.Captura();
        pcap = aux;
        return pcap;
    }
    /** Metodo devuelve un objejto pcap ya creado. 
     * @param Sin valor de entrada 
     * @return dominio.pcap.Captura devuelve un objeto tipo Pcap ya creado.
     */
    public static dominio.pcap.Captura getPcap()
    {
        return pcap;
    }
    /** Metodo devuelve una lista de dispositivos de escucha. 
     * @return String [] devuelve una lista de dispositivos de escucha.
     */
    public static String[] getCapDispositivos()
    {
     dominio.pcap.Captura pcap = crearPcap();
        String aux[] = pcap.capDispositivos();
        return aux;
    }
    /** Metodo donde se realiza la apertura de la captura de paquetes
     * @param Sin valor de entrada 
     * @return Sin valor de retorno 
     * @exception exceptions 
     */
    public static void openCaptura()
        throws Exception
    {
        try
        {
            System.out.println("--> Open Captura START");
            dominio.pcap.Captura pcap = crearPcap();
            System.out.println(getPrefBeanCaptura().getCapDispositive());
            System.out.println("--> Open Captura END");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public static void setListener()
    {
        String aux = "0";
        getPcap().setListener();
        if(getPrefBeanCaptura().getFilMultipleFileId())
        {
            if(getPrefBeanCaptura().getFilSpaceId())
            {
                aux = getMFilSpaceBytes(getPrefBeanCaptura().getFilSpace(), getPrefBeanCaptura().getFilSpaceType());
                getPcap().setMFilSpace(aux);
            }
            if(getPrefBeanCaptura().getFilTimeId())
            {
                aux = getMFilTimeSegundos(getPrefBeanCaptura().getFilTime(), getPrefBeanCaptura().getFilTimeType());
                getPcap().setMFilTime(aux);
            }
            if(getPrefBeanCaptura().getFilRingBufferId())
                getPcap().setMFilPila(getPrefBeanCaptura().getFilRingBuffer());
            if(getPrefBeanCaptura().getFilStopAfterId())
                getPcap().setMFilStop(getPrefBeanCaptura().getFilStopAfter());
        } else
        if(getPrefBeanCaptura().getstpAfterSpaceId())
        {
            aux = getMFilSpaceBytes(getPrefBeanCaptura().getstpAfterSpace(), getPrefBeanCaptura().getstpAfterSpaceType());
            getPcap().setMFilSpace(aux);
            getPcap().setMFilStop("1");
        } else
        if(getPrefBeanCaptura().getstpAfterTimeId())
        {
            aux = getMFilTimeSegundos(getPrefBeanCaptura().getstpAfterTime(), getPrefBeanCaptura().getstpAfterTimeType());
            getPcap().setMFilTime(aux);
            getPcap().setMFilStop("1");
        }
    }

    public static void startCaptura()
    {
        Capturando = true;
        getPcap().start();
        System.out.println("");
        System.out.println("--> Start Captura");
    }

    public static void setPrefCaptura()
    {
        getPcap().setFilePathCapture(getPrefBeanCaptura().getFilLocate());
        getPcap().setFiltro(setFilter());
        if(getPrefBeanCaptura().getstpAfterPacketsId())
            getPcap().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
        if(getPrefBeanCaptura().getCapPromiscuousMode())
            getPcap().setPromiscuo(true);
        else
            getPcap().setPromiscuo(false);
        if(getPrefBeanCaptura().getstpAfterPacketsId())
            getPcap().setNumPaquetes(getPrefBeanCaptura().getstpAfterPackets());
    }

    public static Filtro setFilter()
    {
        Filtro fil = new Filtro();
        if(getPrefBeanCaptura().getCapFilter())
            if(getPrefBeanCaptura().getCapAdvanceId())
                fil.setFiltro_AV(getPrefBeanCaptura().getCapAdvance());
            else
                fil.setFiltro(getPrefBeanCaptura().getCapHost(), getPrefBeanCaptura().getCapProtocol(), getPrefBeanCaptura().getCapPort());
        return fil;
    }

    public static void setPreferencesExportacion(String fRuta)
    {
        getPreferences().setFileExport(fRuta);
    }

    public static void getPreferencesExportacion()
    {
        getPreferences().leerXMLExportInsercion();
    }
    
    public static void chequearDefinicionProtocolo(){
		PreferencesCheckDefinicion check = new PreferencesCheckDefinicion();
	}


    private static preferencesFileRead preferences;
    private static PropertiesFileRead properties;
    private static dominio.pcap.Captura pcap;
    private static boolean Capturando;
    private static Captura jpcap;
    private static String lastFileReaded;
    private static boolean stateCommandActivo = true;

}
