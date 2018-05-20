
package dominio.preferences;

import java.io.Serializable;
/** 
 * Clase preferencesBeanMeta. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class preferencesBeanMeta
    implements Serializable
{

    public preferencesBeanMeta()
    {
    }

    public void setDefaultSettings()
    {
        setMetLocAbsotuteId(true);
        setMetLocAbsotuteName("");
        setMetLocAbsotutePath("");
        setMetLocRelative("");
        setMetLocRelativeId(false);
        setMetMFEnd("1");
        setMetMFRingBuffer("0");
        setMetMFRingBufferId(false);
        setMetMFStar("1");
    }

    public boolean getMetLocRelativeId()
    {
        return metLocRelativeId;
    }

    public void setMetLocRelativeId(boolean aux)
    {
        metLocRelativeId = aux;
    }

    public String getMetLocRelative()
    {
        return metLocRelative;
    }

    public void setMetLocRelative(String aux)
    {
        metLocRelative = aux;
    }

    public boolean getMetLocAbsotuteId()
    {
        return metLocAbsotuteId;
    }

    public void setMetLocAbsotuteId(boolean aux)
    {
        metLocAbsotuteId = aux;
    }

    public String getMetLocAbsotutePath()
    {
        return metLocAbsotutePath;
    }

    public void setMetLocAbsotutePath(String aux)
    {
        metLocAbsotutePath = aux;
    }

    public String getMetLocAbsotuteName()
    {
        return metLocAbsotuteName;
    }

    public void setMetLocAbsotuteName(String aux)
    {
        metLocAbsotuteName = aux;
    }

    public boolean getMetMultipleFileId()
    {
        return metMultipleFileId;
    }

    public void setMetMultipleFileId(boolean aux)
    {
        metMultipleFileId = aux;
    }

    public boolean getMetMFRingBufferId()
    {
        return metMFRingBufferId;
    }

    public void setMetMFRingBufferId(boolean aux)
    {
        metMFRingBufferId = aux;
    }

    public String getMetMFRingBuffer()
    {
        return metMFRingBuffer;
    }

    public void setMetMFRingBuffer(String aux)
    {
        metMFRingBuffer = aux;
    }

    public String getMetMFStar()
    {
        return metMFStar;
    }

    public void setMetMFStar(String aux)
    {
        metMFStar = aux;
    }

    public String getMetMFEnd()
    {
        return metMFEnd;
    }

    public void setMetMFEnd(String aux)
    {
        metMFEnd = aux;
    }

    private boolean metLocRelativeId;
    private String metLocRelative;
    private boolean metLocAbsotuteId;
    private String metLocAbsotutePath;
    private String metLocAbsotuteName;
    private boolean metMultipleFileId;
    private boolean metMFRingBufferId;
    private String metMFRingBuffer;
    private String metMFStar;
    private String metMFEnd;
}
