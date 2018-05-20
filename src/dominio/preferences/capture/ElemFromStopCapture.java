
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemFromStopCaputre. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemFromStopCapture extends Element
{

    public ElemFromStopCapture(preferencesBeanFromFile pBFromFile)
    {
        super("Stop_Capture");
        try
        {
            pOperation = new preferencesOperation();
            setPBFromFile(pBFromFile);
            setAfterPackets();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setAfterPackets()
    {
        Element eAux = new Element("After_Packets");
        eAux.setAttribute("Id", pOperation.validate(getPBFromFile().getffFilMultipleFileId()));
        eAux.setText(getPBFromFile().getffStpAfterPackets());
        addContent(eAux);
    }

    private void setAfterSpace()
    {
        Element eAux = new Element("After_Space");
        eAux.setAttribute("Id", "Yes");
        eAux.setAttribute("Type", "Bytes");
        eAux.setText("1000");
        addContent(eAux);
    }

    private void setAfterTime()
    {
        Element eAux = new Element("After_Time");
        eAux.setAttribute("Id", "No");
        eAux.setAttribute("No", "Seconds");
        eAux.setText("1000");
        addContent(eAux);
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
