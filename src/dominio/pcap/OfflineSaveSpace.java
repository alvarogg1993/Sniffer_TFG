
package dominio.pcap;

import java.io.File;
import java.io.PrintStream;

/** 
 * Clase OfflineSaveSpace. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class OfflineSaveSpace extends Thread
{

    public OfflineSaveSpace(OfflineSaveRawPacketHandler venpadre, long space, SaveFileName SFN)
    {
        time = 500L;
        setVenPadre(venpadre);
        setSpace(space);
        setSFName(SFN);
    }

    public void run()
    {
        long fileSpace = -1L;
        do
            try
            {
                if(SFName.getNext() == -1)
                    getVenPadre().stopCaptura();
                if(fileSpace > getSpace() || fileSpace == -1L)
                {
                    SFName.setNext();
                    getVenPadre().setTcpDumpWriter(SFName.getNameTime());
                    System.out.println((new StringBuilder("getNameTime=> ")).append(getSFName().getNameTime()).toString());
                    System.out.println("SaveSpace    =>    ENTRA");
                }
                f = new File(SFName.getFullNameTime());
                boolean exists = f.exists();
                if(exists)
                {
                    fileSpace = f.length();
                    System.out.println((new StringBuilder("SaveSpace    =>    ")).append(fileSpace).append(" => ").append(getSpace()).toString());
                }
                sleep(time);
            }
            catch(InterruptedException interruptedexception) { }
        while(true);
    }

    private void setVenPadre(OfflineSaveRawPacketHandler padre)
    {
        venpadre = padre;
    }

    private OfflineSaveRawPacketHandler getVenPadre()
    {
        return venpadre;
    }

    private void setSpace(long t)
    {
        spaceBytes = t;
    }

    private long getSpace()
    {
        return spaceBytes;
    }

    private void setSFName(SaveFileName SFN)
    {
        SFName = SFN;
    }

    private SaveFileName getSFName()
    {
        return SFName;
    }

    public OfflineSaveRawPacketHandler venpadre;
    private SaveFileName SFName;
    public long spaceBytes;
    private long time;
    private File f;
}
