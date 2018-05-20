
package dominio.pcapDumper.rules;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import net.sourceforge.jpcap.net.Packet;
import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
/** 
 * Clase XMLlog. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.pcap.rules:
//            AlertType, Rule

public class XMLlog
{

    public XMLlog(int ncap)
    {
        nalert = 0;
        Date d = new Date();
        nombrefichero = (new StringBuilder("./log/")).append(String.valueOf(d.getDate())).append("_").append(String.valueOf(d.getMonth())).append("_").append(String.valueOf(d.getTime())).append("_log").append(String.valueOf(ncap)).append(".xml").toString();
        root = new Element("Incidencia");
    }

    public void CrearLog()
    {
        Document doc = new Document(root);
        try
        {
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(nombrefichero);
            out.output(doc, file);
            file.flush();
            file.close();
            logcreado = true;
        }
        catch(IllegalAddException e)
        {
            System.err.println("El fichero log ya existe");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addAlert(Rule objetoRule, int numeropaquete, Packet paquete, String IpOrigen, String IpDestino, String portsrc, String portdest)
    {
        nalert++;
        AlertType newalert = new AlertType(objetoRule, paquete, numeropaquete, IpOrigen, IpDestino, portsrc, portdest);
        newalert.setAttribute("numero", String.valueOf(nalert));
        root.addContent(newalert);
    }

    public boolean logCreado()
    {
        return logcreado;
    }

    private Element root;
    private Element packet;
    private String nombrefichero;
    private boolean logcreado;
    private int nalert;
}
