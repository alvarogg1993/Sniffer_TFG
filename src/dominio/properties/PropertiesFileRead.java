
package dominio.properties;

import java.io.*;
import java.util.Properties;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase PropertiesFileRead. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.properties:
//            PropertiesBeanSniffer

public class PropertiesFileRead
{

    public PropertiesFileRead()
    {
        p = new Properties(System.getProperties());
    }

    public void setFileReadSniffer(String ruta)
    {
        setFileSniffer(ruta);
    }

    public void leerPorperties()
    {
        boolean aux = leerPropertiesSniffer();
        if(aux)
        {
            System.out.println("Fichero de propiedades leido correctamente");
        } else
        {
            System.out.println("Fichero de propiedades del programa no encontrado!!");
            System.out.println("---Se cargar\341 parametros por defecto");
        }
    }

    public boolean leerPropertiesSniffer()
    {
        boolean exists = true;
        try
        {
            exists = (new File(getFileSniffer())).exists();
            if(exists)
            {
                FileInputStream propSniffer = new FileInputStream(getFileSniffer());
                p.load(propSniffer);
                if(p.getProperty("WinX") != null)
                    getPBSniffer().setWinX(p.getProperty("WinX"));
                if(p.getProperty("WinY") != null)
                    getPBSniffer().setWinY(p.getProperty("WinY"));
                if(p.getProperty("WinHeight") != null)
                    getPBSniffer().setWinHeight(p.getProperty("WinHeight"));
                if(p.getProperty("WinWidth") != null)
                    getPBSniffer().setWinWidth(p.getProperty("WinWidth"));
                if(p.getProperty("TableView") != null)
                    getPBSniffer().setTableView(p.getProperty("TableView"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public void grabarPorperties()
    {
        boolean aux = grabarPropertiesSniffer();
        if(aux)
        {
            System.out.println("Fichero de propiedades grabado correctamente");
        } else
        {
            System.out.println("Fichero de propiedades del programa no encontrado!!");
            System.out.println("---Se grabar\341 parametros por defecto");
        }
    }

    private boolean grabarPropertiesSniffer()
    {
        boolean exists = true;
        try
        {
            p.put("WinX", getPBSniffer().getWinX());
            p.put("WinY", getPBSniffer().getWinY());
            p.put("WinHeight", getPBSniffer().getWinHeight());
            p.put("WinWidth", getPBSniffer().getWinWidth());
            p.put("TableView", getPBSniffer().getTableView());
            p.store(new FileOutputStream(getDefaultFileSniffer()), "Sniffer I");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public void setFileSniffer(String aux)
    {
        ficheroSniffer = aux;
    }

    public String getFileSniffer()
    {
        String aux;
        if(ficheroSniffer == null || ficheroSniffer == "")
            aux = getDefaultFileSniffer();
        else
            aux = ficheroSniffer;
        return aux;
    }

    public String getDefaultFileSniffer()
    {
        return (new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_PROPERTIES")))).append(System.getProperty("file.separator")).append("sniffer.property").toString();
    }

    public PropertiesBeanSniffer getPBSniffer()
    {
        if(pBSniffer == null)
            pBSniffer = new PropertiesBeanSniffer();
        return pBSniffer;
    }

    private String noNull(String aux)
    {
        if(aux == null)
            aux = "null";
        return aux;
    }

    private boolean noNull(boolean aux)
    {
        return aux;
    }

    private PropertiesBeanSniffer pBSniffer;
    private Properties p;
    private String ficheroSniffer;
}
