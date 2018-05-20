
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanCapture;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemCapFile. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemCapFile extends Element
{

    public ElemCapFile(preferencesBeanCapture pBCapture)
    {
        super("File");
        try
        {
            pOperation = new preferencesOperation();
            setPBCapture(pBCapture);
            setLocate();
            setMultipleFiles();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setLocate()
    {
        addContent((new Element("Locate")).setText(getPBCapture().getFilLocate()));
    }

    private void setMultipleFiles()
    {
        Element eMultipleFiles = new Element("Multiple_Files");
        eMultipleFiles.setAttribute("Id", pOperation.validate(getPBCapture().getFilMultipleFileId()));
        Element eAux = new Element("Space");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getFilSpaceId()));
        eAux.setAttribute("Type", getPBCapture().getFilSpaceType());
        eAux.setText(getPBCapture().getFilSpace());
        eMultipleFiles.addContent(eAux);
        eAux = new Element("Time");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getFilTimeId()));
        eAux.setAttribute("Type", getPBCapture().getFilTimeType());
        eAux.setText(getPBCapture().getFilTime());
        eMultipleFiles.addContent(eAux);
        eAux = new Element("Ring_Buffer");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getFilRingBufferId()));
        eAux.setAttribute("Type", getPBCapture().getFilRingBufferType());
        eAux.setText(getPBCapture().getFilRingBuffer());
        eMultipleFiles.addContent(eAux);
        
        eAux = new Element("Circular_XML");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getFilRingBufferXML()));
        eMultipleFiles.addContent(eAux);
        
        eAux = new Element("Stop_After");
        eAux.setAttribute("Id", pOperation.validate(getPBCapture().getFilStopAfterId()));
        eAux.setAttribute("Type", getPBCapture().getFilStopAfterType());
        eAux.setText(getPBCapture().getFilStopAfter());
        eMultipleFiles.addContent(eAux);
        addContent(eMultipleFiles);
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
