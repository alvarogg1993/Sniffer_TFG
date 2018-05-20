
package dominio.preferences.capture;

import dominio.preferences.*;
import java.io.FileOutputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
/** 
 * Clase PreferencesSniffer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.preferences.capture:
//            PrefCapture, PrefExport, PrefFromFile, PrefMeta

public class PreferencesSniffer
{

    public PreferencesSniffer()
    {
        doc = new Document();
        root = new Element("CaptureSniffer");
    }

    public void GenerateCapture(preferencesBeanCapture pBCapture)
    {
        setPrefCapture(pBCapture);
    }

    public void GenerateExportacion(preferencesBeanExport pBExport)
    {
        setPrefExport(pBExport);
    }

    public void GenerateFromto(preferencesBeanFromFile pBFromFile)
    {
        setPrefCaptureFromFile(pBFromFile);
    }

    public void GenerateMeta(preferencesBeanMeta pBMeta)
    {
        setPrefCaptureMeta(pBMeta);
    }

    private void setPrefCapture(preferencesBeanCapture pBCapture)
    {
        elem = new PrefCapture(pBCapture);
        root.addContent(elem);
    }

    private void setPrefExport(preferencesBeanExport pBExport)
    {
        elem = new PrefExport(pBExport);
        root.addContent(elem);
    }

    private void setPrefCaptureFromFile(preferencesBeanFromFile pBFromFile)
    {
        elem = new PrefFromFile(pBFromFile);
        root.addContent(elem);
    }

    private void setPrefCaptureMeta(preferencesBeanMeta pBMeta)
    {
        elem = new PrefMeta(pBMeta);
        root.addContent(elem);
    }

    public void savePreferences()
    {
        String nombrefichero = getFile();
        try
        {
            doc = new Document(root);
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(nombrefichero);
            out.output(doc, file);
            file.flush();
            file.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setFile(String aux)
    {
        fichero = aux;
    }

    public String getFile()
    {
        String aux;
        if(fichero == null || fichero == "")
            aux = getDefaultFile();
        else
            aux = fichero;
        return aux;
    }

    public String getDefaultFile()
    {
        String aux = "./files/preferencias";
        String strF = "prefencesSniffer";
        String nombrefichero = (new StringBuilder(String.valueOf(aux))).append("/").append(strF).append(".xml").toString();
        return nombrefichero;
    }

    protected static Document doc;
    protected static Element root;
    protected static Element elem;
    private String fichero;
    private preferencesBeanCapture pBCapture;
    private preferencesBeanExport pBExport;
    private preferencesBeanFromFile pBFromFile;
    private preferencesBeanFromFile pBMeta;
}
