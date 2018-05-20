
package presentacion.capturandoDumper;

import dominio.pcapDumper.CountPacketHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
/** 
 * Clase TimePacket. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.capturandoDumper:
//            Fcaptura

public class TimePacket extends Thread
{

    private static boolean parar;
	public TimePacket(Fcaptura venpadre, long time, CountPacketHandler RCountPacketHandler)
    {
        this.RCountPacketHandler = RCountPacketHandler;
        this.venpadre = venpadre;
        ethernet = 0;
        arp = 0;
        ip = 0;
        icmp = 0;
        igmp = 0;
        tcp = 0;
        udp = 0;
        this.time = time;
        numpaquetesperiodo = 0;
    }

    public void run()
    {
    	setParar(true);
        do
            try
            {
                timecapture = GetTime();
                ethernet = RCountPacketHandler.ethernet;
                arp = RCountPacketHandler.arp;
                ip = RCountPacketHandler.ip;
                icmp = RCountPacketHandler.icmp;
                igmp = RCountPacketHandler.igmp;
                tcp = RCountPacketHandler.tcp;
                udp = RCountPacketHandler.udp;
                numpaquetesperiodo = RCountPacketHandler.i;
                npkVisualizados = RCountPacketHandler.contpk;
                if(npkVisualizados > pkVisibles)
                {
                    RCountPacketHandler.eraseContParcial();
                    venpadre.borrarTextArea();
                }
                venpadre.mostrarPackets(RCountPacketHandler.pktotal, RCountPacketHandler.getPackets());
                RCountPacketHandler.delPackets();
                venpadre.intervalo(RCountPacketHandler.pktotal, numpaquetesperiodo, ethernet, arp, ip, icmp, igmp, tcp, udp);
                //sleep(time);
            }
            catch(Exception interruptedexception) { }
        while(isParar());
    }

    public String GetTime()
    {
        Date hoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:ms");
        String horaActual = new String(sdf.format(hoy));
        return horaActual;
    }

    public void setFrameVisibles(int contpk)
    {
        pkVisibles = contpk;
    }

    public static void setParar(boolean parar) {
		TimePacket.parar = parar;
	}

	public boolean isParar() {
		return parar;
	}

	private int ethernet;
    private int ip;
    private int arp;
    private int icmp;
    private int igmp;
    private int tcp;
    private int udp;
    private int numpaquetesperiodo;
    private int npkVisualizados;
    public long time;
    private String timecapture;
    private int pkVisibles;
    public Fcaptura venpadre;
    private CountPacketHandler RCountPacketHandler;
}
