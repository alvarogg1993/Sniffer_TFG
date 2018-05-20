
package dominio.pcapDumper;

import java.io.*;
import java.util.Properties;
/** 
 * Clase StateCaptura. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class StateCaptura
{

    public StateCaptura()
    {
        p = new Properties(System.getProperties());
    }

    public void setFileReadSniffer(String ruta)
    {
        setFile(ruta);
    }

    public boolean leerPorperties()
    {
        boolean aux = leerPropertiesState();
        if(!aux)
        {
            System.out.println("Fichero de propiedades del programa no encontrado!!");
            System.out.println("---Se cargar\341 parametros por defecto");
        }
        return aux;
    }

    private boolean leerPropertiesState()
    {
        boolean exists = true;
        try
        {
            exists = (new File(getFile())).exists();
            if(exists)
            {
                FileInputStream propSniffer = new FileInputStream(getFile());
                p.loadFromXML(propSniffer);
                if(p.getProperty("Capturando") != null)
                    setCapturando(p.getProperty("Capturando"));
                if(p.getProperty("Locate") != null)
                    setLocate(p.getProperty("Locate"));
                if(p.getProperty("Name") != null)
                    setName(p.getProperty("Name"));
                if(p.getProperty("Extension") != null)
                    setExtension(p.getProperty("Extension"));
                if(p.getProperty("MultipleFile") != null)
                    setMultipleFile(p.getProperty("MultipleFile"));
                if(p.getProperty("RingBuffer") != null)
                    setRingBuffer(p.getProperty("RingBuffer"));
                if(p.getProperty("StopAfter") != null)
                    setStopAfter(p.getProperty("StopAfter"));
                if(p.getProperty("LastCaptureFile") != null)
                    setLastCaptureFile(p.getProperty("LastCaptureFile"));
                if(p.getProperty("LastNumCaptureFile") != null)
                    setLastNumFileCapturado(p.getProperty("LastNumCaptureFile"));
            }
        }
        catch(Exception exception) { }
        return exists;
    }

    public void grabarPorperties(boolean state)
    {
        setCapturando(state);
        boolean aux = grabarProperties();
        if(!aux)
            System.out.println("No grabado fichero de estado de captura");
    }

    private boolean grabarProperties()
    {
        boolean exists = false;
        try
        {
            p.put("Capturando", getCapturando());
            p.put("Locate", getLocate());
            p.put("Name", getName());
            p.put("Extension", getExtension());
            p.put("MultipleFile", getMultipleFile());
            p.put("RingBuffer", getRingBuffer());
            p.put("StopAfter", getStopAfter());
            p.put("LastCaptureFile", getLastCaptureFile());
            p.put("LastNumCaptureFile", getLastNumFileCapturado());
            p.storeToXML(new FileOutputStream((new StringBuilder(String.valueOf(getFile()))).append("_state.xml").toString()), "Capture File");
            exists = true;
        }
        catch(FileNotFoundException eFNF)
        {
            System.out.println(eFNF.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public void setFile(String aux)
    {
        fichero = aux;
    }

    public String getFile()
    {
        return fichero;
    }

    public void setCapturando(String aux)
    {
        strCapturando = aux;
    }

    public void setCapturando(boolean aux)
    {
        if(aux)
            strCapturando = "Yes";
        else
            strCapturando = "No";
    }

    public String getCapturando()
    {
        return strCapturando;
    }

    public void setLocate(String aux)
    {
        strLocate = aux;
    }

    public String getLocate()
    {
        return strLocate;
    }

    public void setName(String aux)
    {
        strName = aux;
    }

    public String getName()
    {
        return strName;
    }

    public void setExtension(String aux)
    {
        strExtension = aux;
    }

    public String getExtension()
    {
        return strExtension;
    }

    public void setMultipleFile(String aux)
    {
        strMultipleFile = aux;
    }

    public String getMultipleFile()
    {
        return strMultipleFile;
    }

    public void setRingBuffer(String aux)
    {
        strRingBuffer = aux;
    }

    public String getRingBuffer()
    {
        return strRingBuffer;
    }

    public void setStopAfter(String aux)
    {
        strStopAfter = aux;
    }

    public String getStopAfter()
    {
        return strStopAfter;
    }

    public void setLastCaptureFile(String aux)
    {
        strLastCaptureFile = aux;
    }

    public String getLastCaptureFile()
    {
        return strLastCaptureFile;
    }

    public void setLastNumFileCapturado(String aux)
    {
        strLastNumFileCapturado = aux;
    }

    public String getLastNumFileCapturado()
    {
        return strLastNumFileCapturado;
    }

    private String noNull(String aux)
    {
        if(aux == null)
            aux = "null";
        return aux;
    }

    private boolean noNull(boolean aux)
    {
        return aux;
    }

    private Properties p;
    private String fichero;
    private String strCapturando;
    private String strLocate;
    private String strName;
    private String strExtension;
    private String strMultipleFile;
    private String strRingBuffer;
    private String strStopAfter;
    private String strLastCaptureFile;
    private String strLastNumFileCapturado;
}
