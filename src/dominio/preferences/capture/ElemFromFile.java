
package dominio.preferences.capture;

import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesOperation;
import org.jdom.Element;
/** 
 * Clase ElemFromFile. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class ElemFromFile extends Element
{

    public ElemFromFile(preferencesBeanFromFile pBFromFile)
    {
        super("File");
        try
        {
            pOperation = new preferencesOperation();
            setPBFromFile(pBFromFile);
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
        addContent((new Element("Locate")).setText(getPBFromFile().getffFilLocate()));
    }

    private void setMultipleFiles()
    {
        Element eMultipleFiles = new Element("Multiple_Files");
        eMultipleFiles.setAttribute("Id", pOperation.validate(getPBFromFile().getffFilMultipleFileId()));
        Element eAux = new Element("Space");
        eAux.setAttribute("Id", pOperation.validate(getPBFromFile().getffFilSpaceId()));
        eAux.setAttribute("Type", getPBFromFile().getffFilSpaceType());
        eAux.setText(getPBFromFile().getffFilSpace());
        eMultipleFiles.addContent(eAux);
        eAux = new Element("Ring_Buffer");
        eAux.setAttribute("Id", pOperation.validate(getPBFromFile().getffFilRingBufferId()));
        eAux.setAttribute("Type", getPBFromFile().getffFilRingBufferType());
        eAux.setText(getPBFromFile().getffFilRingBuffer());
        eMultipleFiles.addContent(eAux);
        eAux = new Element("Stop_After");
        eAux.setAttribute("Id", pOperation.validate(getPBFromFile().getffFilStopAfterId()));
        eAux.setAttribute("Type", getPBFromFile().getffFilStopAfterType());
        eAux.setText(getPBFromFile().getffFilStopAfter());
        eMultipleFiles.addContent(eAux);
        addContent(eMultipleFiles);
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
