
package dominio.pcapDumper;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

import dominio.export.xml_PcapLib.CrearXMLOffline;
import dominio.export.xml_PcapLib.XmlPacketHandler;

/** 
 * Clase OfflineSavePacketHandler. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/


public class OfflineSavePacketHandler
  
{
	
	 private Pcap pcap;
	    Pcap jpcap;
	    Pcap jpcap_writer;
	    public long time;
	    public long space;
	    private SaveTime STime;
	    private SaveSpace SSpace;
	    public static SaveFileName SFName;
	    public String strFile;
	    public String auxStrFile;
	    public static long contPacket;
	    public static long numPacket;
	    public CountPacketHandler RCountPH;
	    public static Captura venpadre;
	    static long contSpaceLen;
	    private boolean otroFile;
	    private static boolean controlPacket;
	    private static  PcapDumper dumper;
		public static int aux;
		  public static  XmlPacketHandler ficheroxmlenconstruccion;
		  private static String nombreficheroxml;
	    StringBuilder MsgError = new StringBuilder();

    public OfflineSavePacketHandler(Captura cap, SaveFileName SFN, Pcap jpcap)
    {
        otroFile = false;
        this.jpcap = jpcap;
        venpadre = cap;
        String aux = "./files/Capturas";
        String strF = "capturaJpacpLib.pcap";
        System.out.println(aux);
        System.out.println(strF);
        SFN.setSaveFileName(aux, strF);
        SFName = SFN;
        setTime(0L);
        setSpace(0L);
        setPila(0);
        setFile(SFName.getFile());
        setNumPacket(0L);
        setContPacket(0L);
        
        DefinirXML(aux + SFN.getFile().toString() + ".XML");
        dumper = jpcap.dumpOpen(SFN.getFullPath());
    }

    public OfflineSavePacketHandler(Captura cap, SaveFileName SFN, Pcap jpcap2, String fullPath)
    {
        otroFile = false;
        this.jpcap = jpcap2;
        venpadre = cap;
        SFN.setSaveFileName(fullPath);
        SFName = SFN;
        setTime(0L);
        setSpace(0L);
        setPila(0);
        setFile(SFName.getFile());
        setNumPacket(0L);
        setContPacket(0L);
        DefinirXML(SFN.getPath()+ "\\" +SFN.getNameFile() + ".XML");
        dumper = jpcap.dumpOpen(SFN.getFullPath());
    }

    public OfflineSavePacketHandler()
    {
        otroFile = false;
        String aux = "./files/capturas";
        String strF = "captura_offline.pcap";
        System.out.println(aux);
        System.out.println(strF);
        SFName = new SaveFileName(aux, strF);
        setFile(SFName.getFile());
        setTcpDumpWriter();
        DefinirXML(SFName.getPath()+ "\\" +SFName.getNameFile() + ".XML");
        dumper = jpcap.dumpOpen(SFName.getFullPath());
    }

    public OfflineSavePacketHandler(String path, String file)
    {
        otroFile = false;
        SFName = new SaveFileName(path, file);
        setTime(0L);
        setSpace(0L);
        setPila(0);
        setFile(SFName.getFile());
    }

    public OfflineSavePacketHandler(String fullPath)
    {
        otroFile = false;
        SFName = new SaveFileName(fullPath);
        setTime(0L);
        setSpace(0L);
        setPila(0);
        setFile(SFName.getFile());
        DefinirXML(SFName.getPath()+ "\\" +SFName.getNameFile() + ".XML");
        dumper = jpcap.dumpOpen(SFName.getFullPath());
    }
    
    public void DefinirXML(String nombrefichero){
  	  
 	   this.nombreficheroxml = nombrefichero;
 	   /*En esta Clase CrearXMLOffline creamos los datos (Nombre) del archivo XML a crear a la vez que el pcap*/
 	   CrearXMLOffline crearxmloffline  = new CrearXMLOffline(nombreficheroxml);
 	    /*En esta clase podemos crear XmlPacketHandler*/
 	   
 	   ficheroxmlenconstruccion = new XmlPacketHandler(crearxmloffline);
 	   
 	   
 	   
    }
    /** Metodo setTcpDumpWriter().
     * @param Sin valor de entrada
     * @return Sin valor de retorno
      */
    public void setTcpDumpWriter()
    {
        try
        {
            pcap.close();
            pcap= Pcap.openOffline(getFullName(), MsgError);
                   resetSpaceLen();
            otroFile = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /** Metodo setTcpDumpWriter().
     * @param Sin valor de entrada
     * @return Sin valor de retorno
      */
    public void setTcpDumpWriter_first()
    {
        try
        {
        	 pcap= Pcap.openOffline(getFullName(), MsgError);
//            file = JpcapWriter.openDumpFile(jpcap_writer, getFullName());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /** Metodo setTcpDumpWriter().
     * @param String strAuxName
     * @return Sin valor de retorno
      */
    public void setTcpDumpWriter(String strAuxName)
    {
        try
        {
            setAuxFile(strAuxName);
            otroFile = true;
            resetSpaceLen();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        setFile(strAuxName);
    }
    /** Metodo setTcpDumpWriter_first.
     * @param String strAuxName
     * @return Sin valor de retorno
      */
    public void setTcpDumpWriter_first(String strAuxName)
    {
        try
        {
            setAuxFile(strAuxName);
            pcap= Pcap.openOffline(getAuxFullName(), MsgError);
            resetSpaceLen();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        setFile(strAuxName);
    }
    /** Metodo receivePacket, gestiona los paquetes capturados de un archivo pcap y 
     * los traslada a un archivo de formato XML.
     * @param Pcap jpcap2
     * @return Sin valor de retorno
      */
    public static void receivePacket(Pcap jpcap2)
    {
        try
        {
                     
        	PcapPacketHandler<PcapDumper> dumpHandler =  new PcapPacketHandler<PcapDumper>() {		  
                public void nextPacket(PcapPacket packet, PcapDumper dumper) {  
                
              	  PcapHeader header = packet.getCaptureHeader();
      			    dumper.dump(header, packet);
      			  if (controlPacket) {
                     
                      ficheroxmlenconstruccion.receivePacket(packet);
                
                      
                      }                 
                }
            };
            
            jpcap2.loop(1, dumpHandler,dumper);  
            nextContPacket();
            
          
            if(getContPacket() >= getNumPacket() && getNumPacket() != 0L){
            	 aux=0;
            	stopCaptura();
              
                saveficheroOffline();
          	 
                
            }
//            
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
    }

    private static void saveficheroOffline() {
    	 File file = new File(SFName.getFullPath());
   		System.out.printf("%s file has %d bytes in it!\n", SFName.getFullPath(), file.length());
   		/*Cerramos Objeto para que se guarde completamente las capturas en el fichero xml*/
         ficheroxmlenconstruccion.finEntrada();
     	   System.out.println("\n Se ha realizado el archivo en XML en " + nombreficheroxml );
   		dumper.close();
	}

	public void runHilos(Captura vp)
    {
        venpadre = vp;
        if(getTime() == 0L)
            if(getSpace() != 0L)
            {
                SFName.setNext();
                setTcpDumpWriter_first(SFName.getNameTime());
            } else
            {
                setTcpDumpWriter_first();
            }
    }

    public static void stopCaptura()
    {
        getVenPadre().stopCaptureThread();
//        jpcap.close();
    }

    public void stopHilos()
    {
        if(STime != null)
        {
            System.out.println("Destruyendo hiloSaveTiem");
            STime.stop();
        }
        if(SSpace != null)
        {
            System.out.println("Destruyendo hiloSaveSpace");
            SSpace.stop();
        }
        pcap.close();
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public long getTime()
    {
        return time;
    }

    public void setSpace(long space)
    {
        this.space = space;
    }

    public long getSpace()
    {
        return space;
    }

    public void setFile(String file)
    {
        strFile = file;
    }

    public String getFile()
    {
        return strFile;
    }

    public void setPila(int pila)
    {
        SFName.setPila(pila);
    }

    public int getPila()
    {
        return SFName.getPila();
    }

    public void setMaximo(int max)
    {
        SFName.setMaximo(max);
    }

    public int getMaximo()
    {
        return SFName.getMaximo();
    }

    public void setAuxFile(String file)
    {
        auxStrFile = file;
    }

    public String getAuxFile()
    {
        return auxStrFile;
    }

    public String getFullName()
    {
        return (new StringBuilder(String.valueOf(SFName.getPath()))).append(SFName.getSeparator()).append(getFile()).toString();
    }

    public String getAuxFullName()
    {
        return (new StringBuilder(String.valueOf(SFName.getPath()))).append(SFName.getSeparator()).append(getAuxFile()).toString();
    }

    public static Captura getVenPadre()
    {
        return venpadre;
    }

    public void setNumPacket(long aux)
    {
        numPacket = aux;
    }

    public static long getNumPacket()
    {
        return numPacket;
    }

    public void setContPacket(long aux)
    {
        contPacket = aux;
    }

    public static long getContPacket()
    {
        return contPacket;
    }

    public static void nextContPacket()
    {
        contPacket++;
    }

    public void resetSpaceLen()
    {
        contSpaceLen = 0L;
    }

    public static long getspaceLen()
    {
        return contSpaceLen;
    }

    public void setWriter(Pcap jWriter)
    {
        jpcap_writer = jWriter;
    }

   
}
