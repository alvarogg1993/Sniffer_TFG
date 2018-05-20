
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanCapture;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemCapStopCaputure. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemCapStopCapture extends Element
{

    public ElemCapStopCapture(preferencesBeanCapture pBCapture)
    {
        super("Stop_Capture");
        try
        {
            pOperation = new preferencesOperation();
            setPBCapture(pBCapture);
            setAfterPackets();
            setAfterSpace();
            setAfterTime();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setAfterPackets()
    {
        Element eAux = new Element("After_Packets");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getstpAfterPacketsId()));
        eAux.setText(getPBCapture().getstpAfterPackets());
        addContent(eAux);
    }

    private void setAfterSpace()
    {
        Element eAux = new Element("After_Space");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getstpAfterSpaceId()));
        eAux.setAttribute("Type", getPBCapture().getstpAfterSpaceType());
        eAux.setText(getPBCapture().getstpAfterSpace());
        addContent(eAux);
    }

    private void setAfterTime()
    {
        Element eAux = new Element("After_Time");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getstpAfterTimeId()));
        eAux.setAttribute("Type", getPBCapture().getstpAfterTimeType());
        eAux.setText(getPBCapture().getstpAfterTime());
        addContent(eAux);
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
    private preferencesOperation pOperation;
}
