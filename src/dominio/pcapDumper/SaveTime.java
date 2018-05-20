
package dominio.pcapDumper;

import java.io.PrintStream;

import presentacion.preferencias.PreferenciasCaptura;
/** 
 * Clase SaveTime. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.pcapDumper:
//            SaveFileName, SavePacketHandler

public class SaveTime extends Thread
{


	private static boolean ParaSaveTime=true;
	private static boolean cambiaArchivo;
	public static boolean isCambiaArchivo() {
		return cambiaArchivo;
	}

	public static void setCambiaArchivo(boolean cambiaArchivo) {
		SaveTime.cambiaArchivo = cambiaArchivo;
	}

	public boolean isParaSaveTime() {
		return ParaSaveTime;
	}

	public static void setParaSaveTime(boolean paraSaveTime) {
		ParaSaveTime = paraSaveTime;
	}

	public SaveTime(SavePacketHandler venpadre, long time, SaveFileName SFN)
    {
        setVenPadre(venpadre);
        setTime(time);
        setSFName(SFN);
    }

    public void run()
    {
    	
    	
        boolean primeraVez = true;
        do
            try
            {
            	
                SFName.setNext();
                if(SFName.getNext() == -1)
                {
                    getVenPadre().stopCaptura();
                } else
                {
                    if(primeraVez)
                    {
                        primeraVez = false;
                        setCambiaArchivo(true);
                        getVenPadre().setTcpDumpWriter_first(SFName.getNameTime());
                    } else
                    { 
                    	
                        getVenPadre().setTcpDumpWriter(SFName.getNameTime());
                        setCambiaArchivo(true);
                 
                    }
                    SFName.saveStateMulti(true);
                    System.out.println((new StringBuilder("\n----> ")).append(getSFName().getNameTime()).toString());
                }
               
                sleep(getTime());
                
            }
            catch(InterruptedException interruptedexception) { }
        while(isParaSaveTime());
    
    	}

    private void setVenPadre(SavePacketHandler padre)
    {
        venpadre = padre;
    }

    private SavePacketHandler getVenPadre()
    {
        return venpadre;
    }

    private void setTime(long t)
    {
        timeSleep = t;
    }

    private long getTime()
    {
        return timeSleep;
    }

    private void setSFName(SaveFileName SFN)
    {
        SFName = SFN;
    }

    private SaveFileName getSFName()
    {
        return SFName;
    }

    public SavePacketHandler venpadre;
    private SaveFileName SFName;
    public long timeSleep;
}
