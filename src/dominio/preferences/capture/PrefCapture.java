
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanCapture;
import org.jdom.Element;
/** 
 * Clase PrefCapture. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.preferences.capture:
//            ElemCapCapture, ElemCapFile, ElemCapStopCapture

public class PrefCapture extends Element
{

    public PrefCapture(preferencesBeanCapture pBCapture)
    {
        super("CapturePreferences");
        try
        {
            setPBCapture(pBCapture);
            setElemCapture();
            setElemFile();
            setElemStopCapture();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setElemCapture()
    {
        addContent(new ElemCapCapture(getPBCapture()));
    }

    private void setElemFile()
    {
        addContent(new ElemCapFile(getPBCapture()));
    }

    private void setElemStopCapture()
    {
        addContent(new ElemCapStopCapture(getPBCapture()));
    }

    private void setPBCapture(preferencesBeanCapture aux)
    {
        pBCapture = aux;
    }

    private preferencesBeanCapture getPBCapture()
    {
        return pBCapture;
    }

    private preferencesBeanCapture pBCapture;
}
