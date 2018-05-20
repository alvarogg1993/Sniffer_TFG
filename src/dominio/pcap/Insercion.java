
package dominio.pcap;

import dominio.FachadaDominio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import net.sourceforge.jpcap.net.Packet;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

import presentacion.preferencias.PreferenciasVisualizarInsercion;
/** 
 * Clase Insercion. 
 * 
 * @author Jose Manuel Saiz, Rodrigo S�nchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
   

public class Insercion extends Thread
{
 protected int i;
private String dispoDescripcion;
private String dispoName;
private ArrayList<PcapIf> alldevs;
 
    public Insercion()
    {
        opcion = 0;
        PACKET_COUNT = -1;
        filePathInsert = "";
    }

    public Insercion(String dispo, String file, int rep, int op)
    {
        opcion = 0;
        PACKET_COUNT = rep;
        devs = dispo;
        filePathInsert = file;
        opcion = op;
    }

    public Insercion(String dispositivo, PcapPacket p, int op)
    {
        opcion = 0;
        PACKET_COUNT = -1;
        devs = dispositivo;
        paquete = p;
        opcion = op;
    }
    /** Metodo donde se reciben los datos para configurar el envio de paquetes.
     * @param String dispositivo
     * @return Sin valor de retorno 
      */
    public void openInsercion(String dispositivo)
        throws Exception
    {
    	
    	 alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
         StringBuilder errbuf = new StringBuilder(); // For any error msgs
         int r = Pcap.findAllDevs(alldevs, errbuf);
         if (r == Pcap.NOT_OK || alldevs.isEmpty()) {               
                 System.err.printf("Can't read list of devices, error is %s\n", errbuf.toString());
        
         }        
         try
        {
        	int caplen = 64 * 1024; // Capture all packets, no trucation
    		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
    		int timeout = 10 * 1000; // 10 seconds in millis
    		 
    		 
            System.out.println("openInsercion");
            String osName = System.getProperty("os.name");
            System.out.println((new StringBuilder("=> ")).append(osName).toString());
            String[] parts = dispositivo.split("\n");
            dispoDescripcion = parts[0]; // 004
//            for ( int i= 0; i<alldevs.size();i++){
//            	if (alldevs.get(i).getDescription().contains(dispoDescripcion)){
//            		dispoName = alldevs.get(i).getName();
//            	}
//            }
            for ( int i= 0; i<alldevs.size();i++){
            	if (alldevs.get(i).getName().contains(dispoDescripcion)){
            		dispoName = alldevs.get(i).getName();
            	}
            }

                   	sender  = Pcap.openLive(dispoName, caplen, flags, timeout, MsgError);  
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
            total = 0;
            if(opcion == 2)
            {
                if(paquete != null && devs != null)
                    openInsercion(devs);
                else
                if(filePathInsert != "" && devs != null)
                {
                    openInsercion(devs);
                    openFileInsercion(filePathInsert);
                    while(total != PACKET_COUNT) 
                    {
                        insertPacketCapturados();
                        sender = null;
                        captor.close();
                        openInsercion(devs);
                        openFileInsercion(filePathInsert);
                        total++;
                        PreferenciasVisualizarInsercion.setCont(total);
                    }
                    PreferenciasVisualizarInsercion.cambiarParada();
                    stop();
                }
            } else
            if(opcion == 1 && paquete != null && devs != null)
            {
                openInsercion(devs);
                for(; total != PACKET_COUNT && sender != null; total++)
                    insertPacketDefinidos();

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
            
    	/*Esta cogiendo paquetes hasta que llega a el final que no tiene mas paquetes 
         * y deja el interrruptor "i" a 0, no cambia el valor de i a 1 por lo que se sale del while */
    	while (continuar==false );  {
 			
 			System.out.println("->Insertando");
 			PcapPacketHandler<String> handler = new PcapPacketHandler<String>() {
 	      /*En paqueteAux coge el paquete indivudual de todos el conjunto recibido, y los va recorriendo*/
 				public void nextPacket(PcapPacket paqueteAux, String user) {
 	                sender.sendPacket(paqueteAux);
 					
 				}
 			};			
 			captor.loop(1, handler, "jNetPcap rocks!");
 		}          	
        System.out.println("->Fin Insercion");
    }

    public void insertPacketDefinidos()
        throws IOException
    {
        int cont = 0;
        while(paquete != null) 
        {
            try
            {
                sender.sendPacket(paquete);
                Thread.sleep(500);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            PreferenciasVisualizarInsercion.setCont(++cont);
        }
    }

    public void setNumPaquetes(String aux)
    {
        PACKET_COUNT = Integer.parseInt(aux);
    }

    public void setPrefVisualizar(PreferenciasVisualizarInsercion prefIns)
    {
        pref = prefIns;
    }

    public static void setContinuar()
    {
        continuar = false;
        total = PACKET_COUNT;
    }

    private static final int INFINITE = -1;
    private static int PACKET_COUNT;
    protected String devs;
    protected String filePathInsert;
    protected PcapPacket paquete; 
    protected Pcap sender;
    protected static Pcap captor;
    private static int total = 0;
    private int opcion;
    public static boolean continuar = true;
    PreferenciasVisualizarInsercion pref;
    StringBuilder MsgError = new StringBuilder();

}
