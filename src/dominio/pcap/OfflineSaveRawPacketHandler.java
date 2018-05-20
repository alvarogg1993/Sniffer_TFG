// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OfflineSaveRawPacketHandler.java

package dominio.pcap;

import java.io.IOException;
import java.io.PrintStream;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.util.TcpdumpWriter;

/** 
 * Clase OfflineSaveRawPacketHandler. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class OfflineSaveRawPacketHandler
    implements RawPacketListener
{

    public OfflineSaveRawPacketHandler()
    {
        String aux = "./files/capturas";
        String strF = "captura_offline.pcap";
        System.out.println(aux);
        System.out.println(strF);
        SFName = new SaveFileName(aux, strF);
        setFile(SFName.getFile());
        setTcpDumpWriter();
    }

    public OfflineSaveRawPacketHandler(String path, String file)
    {
        SFName = new SaveFileName(path, file);
        setTime(0);
        setSpace(0);
        setPila(0);
        setFile(SFName.getFile());
    }

    public OfflineSaveRawPacketHandler(String fullPath)
    {
        SFName = new SaveFileName(fullPath);
        setTime(0L);
        setSpace(0L);
        setPila(0);
        setFile(SFName.getFile());
    }

    public void setTcpDumpWriter()
    {
        try
        {
            System.out.println((new StringBuilder("OFFLINE =>")).append(getAuxFullName()).toString());
            file = new TcpdumpWriter();
            TcpdumpWriter.writeHeader(getFullName(), 1, 96L);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setTcpDumpWriter(String strAuxName)
    {
        try
        {
            setAuxFile(strAuxName);
            System.out.println((new StringBuilder("OFFLINE =>")).append(getAuxFullName()).toString());
            file = new TcpdumpWriter();
            TcpdumpWriter.writeHeader(getAuxFullName(), 1, 96L);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        setFile(strAuxName);
    }

    public void rawPacketArrived(RawPacket rawPacket)
    {
        try
        {
            TcpdumpWriter.appendPacket(getFullName(), rawPacket, 1);
        }
        catch(Exception exceptionfile)
        {
            exceptionfile.printStackTrace();
        }
    }

    public void initFilePackets()
    {
        try
        {
            System.out.println("@@@@@@@@ initFilePackets");
            TcpdumpWriter.writeHeader(getFullName(), 1, 96L);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void runHilos(Captura vp)
    {
        venpadre = vp;
        if(getTime() != 0L)
            STime.start();
        else
        if(getSpace() != 0L)
        {
            SSpace = new OfflineSaveSpace(this, getSpace(), SFName);
            SSpace.start();
        } else
        {
            setTcpDumpWriter();
        }
    }

    public void stopCaptura()
    {
        stopHilos();
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

    public Captura getVenPadre()
    {
        return venpadre;
    }

    private static TcpdumpWriter file;
    public long time;
    public long space;
    private SaveTime STime;
    private OfflineSaveSpace SSpace;
    public SaveFileName SFName;
    public String strFile;
    public String auxStrFile;
    public Captura venpadre;
}
