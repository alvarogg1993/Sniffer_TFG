
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemFromCapture. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemFromCapture extends Element
{

    public ElemFromCapture(preferencesBeanFromFile pBFromFile)
    {
        super("Capture");
        try
        {
            pOperation = new preferencesOperation();
            setPBFromFile(pBFromFile);
            setSource();
            setFiter();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setSource()
    {
        Element eAux = new Element("Source");
        eAux.setText(getPBFromFile().getffCapSource());
        addContent(eAux);
    }

    private void setFiter()
    {
        Element eFilter = new Element("Filter");
        eFilter.setAttribute("Id", pOperation.validate(getPBFromFile().getffCapFilterId()));
        Element eAux = new Element("Advanced");
        eAux.setAttribute("Id", pOperation.validate(getPBFromFile().getffCapAdvanceId()));
        eAux.setText(getPBFromFile().getffCapAdvance());
        eFilter.addContent(eAux);
        eAux = getNormal();
        eFilter.addContent(eAux);
        addContent(eFilter);
    }

    private Element getNormal()
    {
        Element ePadre = new Element("Normal");
        ePadre.setAttribute("Id", pOperation.validate(getPBFromFile().getffCapNormal()));
        Element eAux = new Element("Host");
        eAux.setText(getPBFromFile().getffCapHost());
        ePadre.addContent(eAux);
        eAux = new Element("Protocol");
        eAux.setText(getPBFromFile().getffCapProtocol());
        ePadre.addContent(eAux);
        eAux = new Element("Port");
        eAux.setText(getPBFromFile().getffCapPort());
        ePadre.addContent(eAux);
        return ePadre;
    }

    private void setPBFromFile(preferencesBeanFromFile aux)
    {
        pBFromFile = aux;
    }

    private preferencesBeanFromFile getPBFromFile()
    {
        return pBFromFile;
    }

    private preferencesBeanFromFile pBFromFile;
    private preferencesOperation pOperation;
}
