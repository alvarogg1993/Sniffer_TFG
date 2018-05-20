
package presentacion.seleccionFicheros;

import java.io.File;
import javax.swing.filechooser.FileFilter;
/** 
 * Clase FilterPcap. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class FilterPcap extends FileFilter
{

    public FilterPcap()
    {
    }

    public boolean accept(File f)
    {
        if(f.isDirectory())
            return true;
        String extension = getExtension(f);
        return extension.equals("pcap");
    }

    public String getDescription()
    {
        return "Captures pcap";
    }

    private String getExtension(File f)
    {
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if(i > 0 && i < s.length() - 1)
            return s.substring(i + 1).toLowerCase();
        else
            return "";
    }
}
