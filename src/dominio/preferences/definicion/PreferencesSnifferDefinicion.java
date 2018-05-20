
package dominio.preferences.definicion;

import dominio.preferences.preferencesBeanDefinicion;
import java.io.FileOutputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
/** 
 * Clase PreferencesSnifferDefinicion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.preferences.definicion:
//            PrefDef

public class PreferencesSnifferDefinicion
{

    public PreferencesSnifferDefinicion()
    {
        doc = new Document();
        root = new Element("DefinicionProtocoloSniffer");
    }

    public void GenerateDefinicion(preferencesBeanDefinicion pBDefinicion)
    {
        setPrefDefinicion(pBDefinicion);
    }

    private void setPrefDefinicion(preferencesBeanDefinicion pBDefinicion)
    {
        elem = new PrefDef(pBDefinicion);
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
        String aux = "./files/preferenciasDefinicion";
        String strF = "prefencesSnifferDefinicion";
        String nombrefichero = (new StringBuilder(String.valueOf(aux))).append("/").append(strF).append(".xml").toString();
        return nombrefichero;
    }

    protected static Document doc;
    protected static Element root;
    protected static Element elem;
    private String fichero;
    private preferencesBeanDefinicion pBDefinicion;
}
