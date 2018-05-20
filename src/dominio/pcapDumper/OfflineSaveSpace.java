
package dominio.pcapDumper;

import java.io.File;
import java.io.PrintStream;
/** 
 * Clase OfflineSaveSpace. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/


public class OfflineSaveSpace extends Thread
{

    public OfflineSaveSpace(OfflineSavePacketHandler venpadre, long space, SaveFileName SFN)
    {
        time = 1L;
        setVenPadre(venpadre);
        setSpace(space);
        setSFName(SFN);
    }

    public void run()
    {
        boolean primeraVez = true;
        do
            try
            {
                while(getVenPadre().getspaceLen() < getSpace()) ;
                SFName.setNext();
                if(SFName.getNext() == -1)
                {
                    getVenPadre().stopCaptura();
                } else
                {
                    if(primeraVez)
                    {
                        primeraVez = false;
                        getVenPadre().setTcpDumpWriter_first(SFName.getNameTime());
                    } else
                    {
                        getVenPadre().setTcpDumpWriter(SFName.getNameTime());
                    }
                    System.out.println((new StringBuilder("\n----> ")).append(getSFName().getNameTime()).toString());
                }
            }
            catch(Exception exception) { }
        while(true);
    }

    private void setVenPadre(OfflineSavePacketHandler padre)
    {
        venpadre = padre;
    }

    private OfflineSavePacketHandler getVenPadre()
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

    public OfflineSavePacketHandler venpadre;
    private SaveFileName SFName;
    public long spaceBytes;
    private long time;
    private File f;
}
