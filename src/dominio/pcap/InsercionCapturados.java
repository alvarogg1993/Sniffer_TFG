

package dominio.pcap;

import dominio.FachadaDominio;
import java.io.*;

import net.sourceforge.jpcap.net.Packet;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

//import jpcap.*;
//import jpcap.packet.Packet;
import presentacion.preferencias.PreferenciasVisualizarInsercion;
import presentacion.visualizarCaptura.PacketHandlerPcapLib;
import presentacion.visualizarCaptura.VisualizarCaptura;

/** 
 * Clase InsercionCapturados. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
   

public class InsercionCapturados extends Thread
{
	StringBuilder MsgError = new StringBuilder();
	protected int i;
	
    public InsercionCapturados(String dispo, String file, int rep)
    {
        total = 0;
        continuar = true;
        PACKET_COUNT = rep;
        devs = dispo;
        filePathInsert = file;
    }
    /** Metodo donde se reciben los datos para configurar el envio de paquetes.
     * @param String dispositivo
     * @return Sin valor de retorno 
      */
    public void openInsercion(String dispositivo)
        throws Exception
    {
        try
        {
        	int caplen = 64 * 1024; // Capture all packets, no trucation
    		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
    		int timeout = 10 * 1000; // 10 seconds in millis
    		 StringBuilder MsgError = new StringBuilder();
    		
            System.out.println("openInsercion");
            String osName = System.getProperty("os.name");
            System.out.println((new StringBuilder("=> ")).append(osName).toString());
            PcapIf disp = FachadaDominio.isDispositivo(dispositivo);
            if(disp != null)              
            	sender  = Pcap.openLive(disp.getName(), caplen, flags, timeout, MsgError);  
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public void run()
    {
        try
        {
            while(continuar) 
                runInsert();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void runInsert()
    {
        try
        {
            if(paquete != null && devs != null)
                openInsercion(devs);
            else
            if(filePathInsert != "" && devs != null)
            {
                openInsercion(devs);
                openFileInsercion(filePathInsert);
                for(; total != PACKET_COUNT; total++)
                {
                    insertPacketCapturados();
                    captor.close();
                    openFileInsercion(filePathInsert);
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("<<<<< ===== runInsert ===== >>>>> ");
        }
    }
    /** Metodo donde se realiza la apertura del archivo para enviar los paquetes.
     * @param String file
     * @return Sin valor de retorno 
      */
    public void openFileInsercion(String file)
        throws IOException
    {
        File fichero = new File(file);
        if(file != "" && fichero.length() != 0)
                	captor = Pcap.openOffline(file, MsgError);
    }
    /** Metodo donde realiza el envio de los paquetes capturados.
     * @param Sin valor de entrada
     * @return Sin valor de retorno 
      */
    public void insertPacketCapturados()
        throws IOException
    {
        try
        {
            openInsercion(devs);
            openFileInsercion(filePathInsert);
        }catch(Exception e){
            e.printStackTrace();
        }
      
       /*Esta cogiendo paquetes hasta que llega a el final que no tiene mas paquetes 
        * y deja el interrruptor "i" a 0, no cambia el valor de i a 1 por lo que se sale del while */
        do {
			i = 0;			
			PcapPacketHandler<String> handler = new PcapPacketHandler<String>() {
	      /*En paqueteAux coge el paquete indivudual de todos el conjunto recibido, y los va recorriendo*/
				public void nextPacket(PcapPacket paqueteAux, String user) {
					
	                sender.sendPacket(paqueteAux);
	                total++;
	                PreferenciasVisualizarInsercion.setCont(total);
					i = 1;
				}
			};			
			captor.loop(1, handler, "jNetPcap rocks!");

		} while (i == 1 );
        
        
        
    }

    public void setNumPaquetes(String aux)
    {
        PACKET_COUNT = Integer.parseInt(aux);
    }

    private static final int INFINITE = -1;
    private int PACKET_COUNT;
    protected String devs;
    protected String filePathInsert;
    protected PcapPacket paquete;
    protected Pcap sender;
    protected static Pcap captor;
    private int total;
    public boolean continuar;
}
