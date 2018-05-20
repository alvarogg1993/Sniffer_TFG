

package dominio.pcap;

import dominio.FachadaDominio;
import dominio.pcapDumper.Filtro;
import dominio.pcapDumper.SavePacketHandler;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

import org.jnetpcap.PcapIf;

import presentacion.visualizarCaptura.VisualizarCaptura;
import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.client.CaptureHistory;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.util.TcpdumpWriter;

/** 
 * Clase Captura. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class Captura extends Thread
{

    public Captura()
    {
        pcap = new PacketCapture();
        filtro = new Filtro();
        PACKET_COUNT = -1;
        history = new CaptureHistory();
        rawPacket = null;
        setPromiscuo(true);
    }

    public String[] capDispositivos()
    {
        try
        {
            devs = PacketCapture.lookupDevices();
            String dispo = pcap.findDevice();
            if(devs.length == 0)
                devs[0] = dispo;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return devs;
    }

//    public void openCaptura(List<PcapIf> list)
//        throws Exception
//    {
//        try
//        {
//            System.out.println("");
//            String osName = System.getProperty("os.name");
//            System.out.println((new StringBuilder("=> ")).append(osName).toString());
//            if(osName.compareTo("Linux") == 0)
//            {
//                pcap.open(list, promiscuo);
//            } else
//            {
//                list = list.substring(0, list.indexOf("\n"));
//                pcap.open(list, 1514, promiscuo, 1000);
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    public void run()
    {
        try
        {
            FILTER = filtro.getfilter();
            pcap.setFilter(FILTER, true);
            System.out.println((new StringBuilder("=> Filter => ")).append(FILTER).toString());
            SaveRPH.runHilos(this);
            pcap.addRawPacketListener(SaveRPH);
            runCapture();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void runCapture()
    {
        try
        {
            pcap.capture(PACKET_COUNT);
        }
        catch(Exception e)
        {
            System.out.println("<<<<< ===== runCapture ===== >>>>> ");
        }
    }

    public void closeCap()
    {
        pcap.endCapture();
        pcap.close();
        pcap = null;
        System.err.println("Cerro Captura ");
    }

    public void endCapture()
    {
        if(pcap != null)
        {
            System.out.println("NOOta a null ====>>>> ");
            closeCap();
            FachadaDominio.saveMetaCaptura();
            if(SaveRPH != null)
            {
                System.out.println("endCApture => No SaveRPH");
                SaveRPH.stopHilos();
            } else
            {
                System.out.println("endCApture => Si SaveRPH");
            }
            if(isAlive())
            {
                System.out.println("endCApture => Destruyendo hilo Captura");
                stop();
            }
            System.out.println("null ====>>>> ");
        } else
        {
            System.out.println("esta a null ====>>>> ");
        }
    }

    public void setListener()
    {
        String aux = getFilePathCapture().trim();
        setSFName();
        if(aux.equals(""))
            SaveRPH = new SaveRawPacketHandler(getSFname());
        else
            SaveRPH = new SaveRawPacketHandler(getSFname(), aux);
    }

    public void setSFName()
    {
        if(sfn == null)
            sfn = new SaveFileName();
    }

    public SaveFileName getSFname()
    {
        return sfn;
    }

    public void setFilePathCapture(String aux)
    {
        filePathCapture = aux;
    }

    public String getFilePathCapture()
    {
        return filePathCapture;
    }

    public void setMFilSpace(String aux)
    {
        try
        {
            System.out.println((new StringBuilder("  -->M Fil Space ")).append(aux).toString());
            long lSpace = Long.parseLong(aux);
            SaveRPH.setSpace(lSpace);
        }
        catch(NumberFormatException nfe)
        {
            System.out.println((new StringBuilder("NumberFormatException: ")).append(nfe.getMessage()).toString());
        }
    }

    public void setMFilTime(String aux)
    {
        try
        {
            System.out.println((new StringBuilder("  -->M Fil Time ")).append(aux).toString());
            long lTime = Long.parseLong(aux);
            SaveRPH.setTime(lTime);
        }
        catch(NumberFormatException nfe)
        {
            System.out.println((new StringBuilder("NumberFormatException: ")).append(nfe.getMessage()).toString());
        }
    }

    public void setMFilPila(String aux)
    {
        try
        {
            System.out.println((new StringBuilder("  -->M Fil Pila ")).append(aux).toString());
            int iTime = Integer.parseInt(aux);
            SaveRPH.setPila(iTime);
        }
        catch(NumberFormatException nfe)
        {
            System.out.println((new StringBuilder("NumberFormatException: ")).append(nfe.getMessage()).toString());
        }
    }

    public void setMFilStop(String aux)
    {
        try
        {
            System.out.println((new StringBuilder("  -->M Fil Stop ")).append(aux).toString());
            int iMax = Integer.parseInt(aux);
            SaveRPH.setMaximo(iMax);
        }
        catch(NumberFormatException nfe)
        {
            System.out.println((new StringBuilder("NumberFormatException: ")).append(nfe.getMessage()).toString());
        }
    }
 
    
    public void setFiltro(Filtro filtro2)
    {
        filtro = filtro2;
    }

    public void setPromiscuo(boolean aux)
    {
        promiscuo = aux;
    }

    public void setNumPaquetes(String aux)
    {
        PACKET_COUNT = Integer.parseInt(aux);
    }

    public void addPacketListener(PacketListener aux)
    {
        pcap.addPacketListener(aux);
    }

    public void addRawPacketListener(RawPacketListener aux)
    {
        pcap.addRawPacketListener(aux);
    }

    public void addpackethistory(Packet paquete)
    {
        history.add(paquete);
    }

//    public void clearHistory()
//    {
//        history.clear();
//    }

//    public CaptureHistory getpackethistory()
//    {
//        return history;
//    }

    public int getLinkLayer()
    {
        int Linklayer = -1;
        try
        {
            Linklayer = pcap.getLinkLayerType();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return Linklayer;
    }

    public void openOffline(String ruta)
        throws CaptureFileOpenException
    {
        try
        {
            System.out.println((new StringBuilder("--> openOffline => ")).append(ruta).toString());
            pcap.openOffline(ruta);
        }
        catch(Exception exception) { }
    }

    public void setPcapV(Vector RawP)
    {
        rawPacket = RawP;
    }

    public Vector getPcapV()
    {
        return rawPacket;
    }

//    public int savePcapFile(String nomFile)
//    {
//        TcpdumpWriter file = new TcpdumpWriter();
//        try
//        {
//            TcpdumpWriter.writeHeader(nomFile, 1, 96L);
//            System.out.println(nomFile.toString());
//        }
//        catch(Exception exceptionfile)
//        {
//            exceptionfile.printStackTrace();
//        }
//        if(getPcapV() != null)
//        {
//            for(int i = 0; i < getPcapV().size(); i++)
//                try
//                {
//                    RawPacket rawPacket = (RawPacket)getPcapV().elementAt(i);
//                    TcpdumpWriter.appendPacket(nomFile, rawPacket, 1);
//                }
//                catch(Exception exception)
//                {
//                    exception.printStackTrace();
//                }
//
//            return 0;
//        } else
//        {
//            return 1;
//        }
//    }

//    public void pruebaCaptura(String dispositivo)
//    {
//        boolean visualframe = false;
//        pcap = new PacketCapture();
//        history = new CaptureHistory();
//        SaveRawPacketHandler SaveRPH = new SaveRawPacketHandler();
//        try
//        {
//            String osName = System.getProperty("os.name");
//            System.out.println((new StringBuilder("os.Name:")).append(osName).toString());
//            String tmp = System.getProperty("user.dir");
//            System.out.println((new StringBuilder("Working directory del usuario: ")).append(tmp).toString());
//            if(osName.compareTo("Linux") == 0)
//            {
//                pcap.open(dispositivo, promiscuo);
//            } else
//            {
//                dispositivo = dispositivo.substring(0, dispositivo.indexOf("\n"));
//                pcap.open(dispositivo, 1514, promiscuo, 1000);
//            }
//            FILTER = "";
//            pcap.setFilter(FILTER, true);
//            SaveRPH = new SaveRawPacketHandler();
//            SaveRPH.runHilos(this);
//            pcap.addRawPacketListener(SaveRPH);
//            pcap.capture(-1);
//            pcap.close();
//        }
//        catch(InvalidFilterException invalidfilterexception) { }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    private static final int INFINITE = -1;
    private int PACKET_COUNT;
    protected String devs[];
    protected String filePathCapture;
    private boolean promiscuo;
   
    protected PacketCapture pcap;
    private SaveRawPacketHandler SaveRPH;
    public SaveFileName sfn;
    private Filtro filtro;
    private String FILTER;
    public static CaptureHistory history;
	private static Packet pakete;
    private Vector rawPacket;
	
	
}
