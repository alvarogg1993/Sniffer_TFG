
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanCapture;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemCapCapture. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemCapCapture extends Element
{

    public ElemCapCapture(preferencesBeanCapture pBCapture)
    {
        super("Capture");
        try
        {
            pOperation = new preferencesOperation();
            setPBCapture(pBCapture);
            setInterface();
            setPromiscuousMode();
            setNFSSync();
            setFiter();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setInterface()
    {
        addContent((new Element("Interface")).setText(getPBCapture().getCapDispositive()));
    }

    private void setPromiscuousMode()
    {
        addContent((new Element("Promiscuous_Modo")).setText(pOperation.validate(getPBCapture().getCapPromiscuousMode())));
    }
    
    private void setNFSSync()
    {
        addContent((new Element("NFS_Sync")).setText(pOperation.validate(getPBCapture().getNFSsync())));
    }

    private void setFiter()
    {
        Element eFilter = new Element("Filter");
        eFilter.setAttribute("Id", pOperation.validate(getPBCapture().getCapFilter()));
        Element eAux = new Element("Advanced");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getCapAdvanceId()));
        eAux.setText(getPBCapture().getCapAdvance());
        eFilter.addContent(eAux);
        eAux = getNormal();
        eFilter.addContent(eAux);
        addContent(eFilter);
    }

    private Element getNormal()
    {
        Element ePadre = new Element("Normal");
        ePadre.setAttribute("Id", pOperation.validate(getPBCapture().getCapNormal()));
        Element eAux = new Element("Host");
        eAux.setText(getPBCapture().getCapHost());
        ePadre.addContent(eAux);
        eAux = new Element("Protocol");
        eAux.setText(getPBCapture().getCapProtocol());
        ePadre.addContent(eAux);
        eAux = new Element("Port");
        eAux.setText(getPBCapture().getCapPort());
        ePadre.addContent(eAux);
        return ePadre;
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
