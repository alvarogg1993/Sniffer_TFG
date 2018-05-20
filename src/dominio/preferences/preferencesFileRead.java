
package dominio.preferences;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
/** 
 * Clase preferencesFileRead. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.preferences:
//            preferencesBeanCapture, preferencesBeanExport, preferencesBeanFromFile, preferencesBeanMeta, 
//            preferencesBeanDefinicion, preferencesBeanIdentificacion, preferencesBeanExportInsercion

public class preferencesFileRead
{

    public preferencesFileRead()
    {
    }

    public void setFileReadCaptura(String ruta)
    {
        setFileCapture(ruta);
    }

    public void leerXML()
    {
        boolean aux = leerXMLCapture(true);
        if(aux)
        {
            System.out.println("Fichero de preferencias de captura leido correctamente");
        } else
        {
            System.out.println("Fichero de preferencias de captura no encontrado!!");
            System.out.println("---Se cargar\341 parametros por defecto");
        }
        aux = leerXMLExport();
        if(aux)
        {
            System.out.println("Fichero de preferencias de exportacion leido correctamente");
        } else
        {
            System.out.println("Fichero de preferencias de exportaci\363n no encontrado!!");
            System.out.println("---Se cargar\341 parametros por defecto");
        }
        aux = leerXMLFromFile();
        if(aux)
        {
            System.out.println("Fichero de preferencias de lectura desde fichero leido correctamente");
        } else
        {
            System.out.println("Fichero de preferencias de lectura desde fichero no encontrado!!");
            System.out.println("---Se cargar\341 parametros por defecto");
        }
    }

    public boolean leerXMLCapture(boolean porDefecto)
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            exists = (new File(getFileCapture())).exists();
            if(exists)
            {
                Document doc = builder.build(new File(getFileCapture()));
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                exists = false;
                for(Iterator i = preferences.iterator(); i.hasNext();)
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("CapturePreferences"))
                    {
                        exists = true;
                        pBCapture = new preferencesBeanCapture();
                        prefCapture(pref);
                    }
                }

            }
            if(!exists && porDefecto)
                pBCapture = new preferencesBeanCapture();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean leerXMLExport()
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            exists = (new File(getFileExport())).exists();
            if(exists)
            {
                Document doc = builder.build(new File(getFileExport()));
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                Iterator i = preferences.iterator();
                exists = false;
                while(i.hasNext()) 
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("ExportPreferences"))
                    {
                        exists = true;
                        pBExport = new preferencesBeanExport();
                        prefExport(pref);
                    }
                }
            }
            if(!exists)
                pBExport = new preferencesBeanExport();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean leerXMLFromFile()
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            exists = (new File(getFileFromFile())).exists();
            if(exists)
            {
                Document doc = builder.build(new File(getFileFromFile()));
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                Iterator i = preferences.iterator();
                exists = false;
                while(i.hasNext()) 
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("FromFilePreferences"))
                    {
                        exists = true;
                        pBFromFile = new preferencesBeanFromFile();
                        prefFromFile(pref);
                    }
                }
            }
            if(!exists)
            {
                pBFromFile = new preferencesBeanFromFile();
                pBFromFile.setDefaultSettings();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean leerXMLMETA()
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            exists = (new File(getFileMETA())).exists();
            if(exists)
            {
                Document doc = builder.build(new File(getFileMETA()));
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                for(Iterator i = preferences.iterator(); i.hasNext();)
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("MetaCapture"))
                    {
                        pBMeta = new preferencesBeanMeta();
                        prefMeta(pref);
                    }
                }

            } else
            {
                pBMeta = new preferencesBeanMeta();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public preferencesBeanDefinicion leerXMLDefinicion()
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            exists = (new File(getFileDefinicion())).exists();
            if(exists)
            {
                Document doc = builder.build(new File(getFileDefinicion()));
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                for(Iterator i = preferences.iterator(); i.hasNext();)
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("DefinicionProtocolo"))
                    {
                        pBDefinicion = new preferencesBeanDefinicion();
                        prefDefinicion(pref);
                    }
                }

            } else
            {
                pBDefinicion = new preferencesBeanDefinicion();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pBDefinicion;
    }

    public preferencesBeanIdentificacion leerXMLIdentificacion(File aux)
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            if(exists)
            {
                Document doc = builder.build(aux);
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                for(Iterator i = preferences.iterator(); i.hasNext();)
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("DefinicionProtocolo"))
                    {
                        pBIdentificacion = new preferencesBeanIdentificacion();
                        pBIdentificacion.setRutaProtocolo(aux.getAbsolutePath());
                        prefIdentificacion(pref);
                    }
                }

            } else
            {
                pBIdentificacion = new preferencesBeanIdentificacion();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pBIdentificacion;
    }

    public preferencesBeanDefinicion leerXMLProtocoloIdentificado(File aux)
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            if(exists)
            {
                Document doc = builder.build(aux);
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                for(Iterator i = preferences.iterator(); i.hasNext();)
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("DefinicionProtocolo"))
                    {
                        pBDefinicion = new preferencesBeanDefinicion();
                        prefDefinicion(pref);
                    }
                }

            } else
            {
                pBDefinicion = new preferencesBeanDefinicion();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pBDefinicion;
    }

    public boolean leerXMLExportInsercion()
    {
        boolean exists = true;
        try
        {
            SAXBuilder builder = new SAXBuilder(false);
            exists = (new File(getFileExport())).exists();
            if(exists)
            {
                Document doc = builder.build(new File(getFileExport()));
                Element root = doc.getRootElement();
                List preferences = root.getChildren();
                Iterator i = preferences.iterator();
                exists = false;
                pBExportInsert = new preferencesBeanExportInsercion();
                while(i.hasNext()) 
                {
                    Element pref = (Element)i.next();
                    String auxName = pref.getName();
                    if(auxName.equals("Packet"))
                    {
                        exists = true;
                        prefExportInsercion(pref);
                    }
                }
            }
            if(!exists)
                pBExportInsert = new preferencesBeanExportInsercion();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }

    public void setFileCapture(String aux)
    {
        ficheroCapture = aux;
    }

    public String getFileCapture()
    {
        String aux;
        if(ficheroCapture == null || ficheroCapture == "")
            aux = getDefaultFileCapture();
        else
            aux = ficheroCapture;
        return aux;
    }

    public String getDefaultFileCapture()
    {
        return "./files/parametrizacion/DefaultPreferencesCapture.xml";
    }

    public void setFileExport(String aux)
    {
        ficheroExport = aux;
    }

    public void setFileDefinicion(String aux)
    {
        ficheroDefinicion = aux;
    }

    public String getFileExport()
    {
        String aux;
        if(ficheroExport == null || ficheroExport == "")
            aux = getDefaultFileExport();
        else
            aux = ficheroExport;
        return aux;
    }

    public String getDefaultFileExport()
    {
        return "./files/parametrizacion/DefaultPreferencesExport.xml";
    }

    public void setFileFromFile(String aux)
    {
        ficheroFromFile = aux;
    }

    public String getFileFromFile()
    {
        String aux;
        if(ficheroFromFile == null || ficheroFromFile == "")
            aux = getDefaultFileFromFile();
        else
            aux = ficheroFromFile;
        return aux;
    }

    public String getDefaultFileFromFile()
    {
        return "./files/parametrizacion/DefaultPreferencesFromFile.xml";
    }

    public void setFileMETA(String aux)
    {
        ficheroMETA = aux;
    }

    public String getFileMETA()
    {
        String aux;
        if(ficheroMETA == null || ficheroMETA == "")
            aux = getDefaultFileMETA();
        else
            aux = ficheroMETA;
        return aux;
    }

    public String getFileDefinicion()
    {
        return ficheroDefinicion;
    }

    public String getDefaultFileMETA()
    {
        return "./files/preferencias/DefaultPreferencesFromFile.xml";
    }

    public preferencesBeanCapture getPBCapture()
    {
        if(pBCapture == null)
            pBCapture = new preferencesBeanCapture();
        return pBCapture;
    }

    public preferencesBeanExport getPBExport()
    {
        if(pBExport == null)
            pBExport = new preferencesBeanExport();
        return pBExport;
    }

    public preferencesBeanFromFile getPBFromFile()
    {
        if(pBFromFile == null)
            pBFromFile = new preferencesBeanFromFile();
        return pBFromFile;
    }

    public preferencesBeanMeta getPBMeta()
    {
        if(pBMeta == null)
            pBMeta = new preferencesBeanMeta();
        return pBMeta;
    }

    public preferencesBeanDefinicion getPBDefinicion()
    {
        if(pBDefinicion == null)
            pBDefinicion = new preferencesBeanDefinicion();
        return pBDefinicion;
    }

    private void prefCapture(Element auxElement)
    {
        prefCaptureCap(auxElement.getChild("Capture"));
        prefCaptureFile(auxElement.getChild("File"));
        prefCaptureStop(auxElement.getChild("Stop_Capture"));
    }

    private void prefExport(Element auxElement)
    {
        prefExportFile(auxElement.getChild("File"));
        prefExportStatistics(auxElement.getChild("Statistics"));
    }

    private void prefFromFile(Element auxElement)
    {
        prefFromFileCap(auxElement.getChild("Capture"));
        prefFromFileFile(auxElement.getChild("File"));
        prefFromFileStop(auxElement.getChild("Stop_Capture"));
    }

    private void prefMeta(Element auxElement)
    {
        prefMetaLocate(auxElement.getChild("Locate"));
        prefMetaMultipleFiles(auxElement.getChild("Multiple_Files"));
    }

    private void prefCaptureCap(Element auxElement)
    {
        pBCapture.setCapDispositive(auxElement.getChild("Interface").getText());
        pBCapture.setCapPromiscuousMode(validate(auxElement.getChild("Promiscuous_Modo").getText()));
        pBCapture.setNFSsync(validate(auxElement.getChild("NFS_Sync").getText()));
        Element tempElement = auxElement.getChild("Filter");
        pBCapture.setCapFilter(validate(tempElement.getAttributeValue("Id")));
        pBCapture.setCapAdvanceId(validate(tempElement.getChild("Advanced").getAttributeValue("Id")));
        pBCapture.setCapAdvance(tempElement.getChild("Advanced").getText());
        tempElement = tempElement.getChild("Normal");
        pBCapture.setCapNormal(validate(tempElement.getAttributeValue("Id")));
        pBCapture.setCapHost(tempElement.getChild("Host").getText());
        pBCapture.setCapProtocol(tempElement.getChild("Protocol").getText());
        pBCapture.setCapPort(tempElement.getChild("Port").getText());
    }

    private void prefCaptureFile(Element auxElement)
    {
        pBCapture.setFilLocate(auxElement.getChild("Locate").getText());
        Element tempElement = auxElement.getChild("Multiple_Files");
        pBCapture.setFilMultipleFileId(validate(tempElement.getAttributeValue("Id")));
        pBCapture.setFilSpaceId(validate(tempElement.getChild("Space").getAttributeValue("Id")));
        pBCapture.setFilSpaceType(tempElement.getChild("Space").getAttributeValue("Type"));
        pBCapture.setFilSpace(tempElement.getChild("Space").getText());
        pBCapture.setFilTimeId(validate(tempElement.getChild("Time").getAttributeValue("Id")));
        pBCapture.setFilTimeType(tempElement.getChild("Time").getAttributeValue("Type"));
        pBCapture.setFilTime(tempElement.getChild("Time").getText());
        pBCapture.setFilRingBufferId(validate(tempElement.getChild("Ring_Buffer").getAttributeValue("Id")));
        pBCapture.setFilRingBufferType(tempElement.getChild("Ring_Buffer").getAttributeValue("Type"));
        pBCapture.setFilRingBuffer(tempElement.getChild("Ring_Buffer").getText());
        pBCapture.setFilRingBufferXML(validate(tempElement.getChild("Circular_XML").getAttributeValue("Id")));
        pBCapture.setFilStopAfterId(validate(tempElement.getChild("Stop_After").getAttributeValue("Id")));
        pBCapture.setFilStopAfterType(tempElement.getChild("Stop_After").getAttributeValue("Type"));
        pBCapture.setFilStopAfter(tempElement.getChild("Stop_After").getText());
    }

    private void prefCaptureStop(Element auxElement)
    {
        pBCapture.setstpAfterPacketsId(validate(auxElement.getChild("After_Packets").getAttributeValue("Id")));
        pBCapture.setstpAfterPackets(auxElement.getChild("After_Packets").getText());
        pBCapture.setstpAfterSpaceId(validate(auxElement.getChild("After_Space").getAttributeValue("Id")));
        pBCapture.setstpAfterSpaceType(auxElement.getChild("After_Space").getAttributeValue("Type"));
        pBCapture.setstpAfterSpace(auxElement.getChild("After_Space").getText());
        pBCapture.setstpAfterTimeId(validate(auxElement.getChild("After_Time").getAttributeValue("Id")));
        pBCapture.setstpAfterTimeType(auxElement.getChild("After_Time").getAttributeValue("Type"));
        pBCapture.setstpAfterTime(auxElement.getChild("After_Time").getText());
    }

    private void prefExportFile(Element auxElement)
    {
        pBExport.setExpType(auxElement.getChild("Type").getText());
        pBExport.setExpSource(auxElement.getChild("Source").getText());
        pBExport.setExpDestination(auxElement.getChild("Destination").getText());
        pBExport.setExpMultifile(validate(auxElement.getChild("Multiple_Files").getText()));
    }

    private void prefExportStatistics(Element auxElement)
    {
        pBExport.setExpStatistics(validate(auxElement.getAttributeValue("Id")));
    }

    private void prefFromFileCap(Element auxElement)
    {
        pBFromFile.setffCapSource(auxElement.getChild("Source").getText());
        Element tempElement = auxElement.getChild("Filter");
        pBFromFile.setffCapFilterId(validate(tempElement.getAttributeValue("Id")));
        pBFromFile.setffCapAdvanceId(validate(tempElement.getChild("Advanced").getAttributeValue("Id")));
        pBFromFile.setffCapAdvance(tempElement.getChild("Advanced").getText());
        tempElement = tempElement.getChild("Normal");
        pBFromFile.setffCapNormal(validate(tempElement.getAttributeValue("Id")));
        pBFromFile.setffCapHost(tempElement.getChild("Host").getText());
        pBFromFile.setffCapProtocol(tempElement.getChild("Protocol").getText());
        pBFromFile.setffCapPort(tempElement.getChild("Protocol").getText());
    }

    private void prefFromFileFile(Element auxElement)
    {
        pBFromFile.setffFilLocate(auxElement.getChild("Locate").getText());
        Element tempElement = auxElement.getChild("Multiple_Files");
        pBFromFile.setffFilMultipleFileId(validate(tempElement.getAttributeValue("Id")));
        pBFromFile.setffFilSpaceId(validate(tempElement.getChild("Space").getAttributeValue("Id")));
        pBFromFile.setffFilSpaceType(tempElement.getChild("Space").getAttributeValue("Type"));
        pBFromFile.setffFilSpace(tempElement.getChild("Space").getText());
        pBFromFile.setffFilRingBufferId(validate(tempElement.getChild("Ring_Buffer").getAttributeValue("Id")));
        pBFromFile.setffFilRingBufferType(tempElement.getChild("Ring_Buffer").getAttributeValue("Type"));
        pBFromFile.setffFilRingBuffer(tempElement.getChild("Ring_Buffer").getText());
       // pBCapture.setFilRingBufferXML(validate(tempElement.getChild("Circular_XML").getAttributeValue("Id")));
        pBFromFile.setffFilStopAfterId(validate(tempElement.getChild("Stop_After").getAttributeValue("Id")));
        pBFromFile.setffFilStopAfterType(tempElement.getChild("Stop_After").getAttributeValue("Type"));
        pBFromFile.setffFilStopAfter(tempElement.getChild("Stop_After").getText());
    }

    private void prefFromFileStop(Element auxElement)
    {
        pBFromFile.setffStpAfterPacketsId(validate(auxElement.getChild("After_Packets").getAttributeValue("Id")));
        pBFromFile.setffStpAfterPackets(auxElement.getChild("After_Packets").getText());
    }

    private void prefMetaLocate(Element auxElement)
    {
        pBMeta.setMetLocRelativeId(validate(auxElement.getChild("Relative").getAttributeValue("Id")));
        pBMeta.setMetLocRelative(auxElement.getChild("Relative").getText());
        Element tempElement = auxElement.getChild("Absolute");
        pBMeta.setMetLocAbsotuteId(validate(tempElement.getAttributeValue("Id")));
        pBMeta.setMetLocAbsotutePath(tempElement.getChild("Path").getText());
        pBMeta.setMetLocAbsotuteName(tempElement.getChild("Name").getText());
    }

    private void prefMetaMultipleFiles(Element auxElement)
    {
        pBMeta.setMetMultipleFileId(validate(auxElement.getAttributeValue("Id")));
        pBMeta.setMetMFRingBufferId(validate(auxElement.getChild("Ring_Buffer").getAttributeValue("Id")));
        pBMeta.setMetMFRingBuffer(auxElement.getChild("Ring_Buffer").getText());
        pBMeta.setMetMFStar(auxElement.getChild("Start").getText());
        pBMeta.setMetMFEnd(auxElement.getChild("End").getText());
    }

    private void prefExportInsercion(Element auxElement)
    {
        if(auxElement.getChild("IP_v4") != null)
            prefExportIp(auxElement.getChild("IP_v4"));
        if(auxElement.getChild("Ethernet_Frame") != null)
            prefExportMac(auxElement.getChild("Ethernet_Frame"));
    }

    private void prefExportIp(Element auxElement)
    {
        if(auxElement.getChild("Source_IP") != null && !pBExportInsert.ipOrigen.contains(auxElement.getChild("Source_IP").getText()))
            pBExportInsert.setIpOrigen(auxElement.getChild("Source_IP").getText());
        if(auxElement.getChild("Destination_IP") != null && !pBExportInsert.ipDestino.contains(auxElement.getChild("Destination_IP").getText()))
            pBExportInsert.setIpDestino(auxElement.getChild("Destination_IP").getText());
    }

    private void prefExportMac(Element auxElement)
    {
        if(auxElement.getChild("Source_MAC") != null && !pBExportInsert.macOrigen.contains(auxElement.getChild("Source_MAC").getText()))
            pBExportInsert.setMacOrigen(auxElement.getChild("Source_MAC").getText());
        if(auxElement.getChild("Destination_MAC") != null && !pBExportInsert.macDestino.contains(auxElement.getChild("Destination_MAC").getText()))
            pBExportInsert.setMacDestino(auxElement.getChild("Destination_MAC").getText());
    }

    private void prefDefinicionUnicas(Element auxElement)
    {
        pBDefinicion.setNomProtocolo(auxElement.getChild("Protocolo").getText());
        pBDefinicion.setNivel(Integer.parseInt(auxElement.getChild("Nivel").getText()));
        pBDefinicion.setNumCampos(Integer.parseInt(auxElement.getChild("NumeroCampos").getText()));
        pBDefinicion.setCamposClave(auxElement.getChild("CamposClave").getText());
        pBDefinicion.setRFCProtocolo(auxElement.getChild("RFC").getText());
    }

    private void prefDefinicionTabla(Element auxElement, int fila)
    {
        pBDefinicion.setObjetoTabla(fila, 0, auxElement.getChild("IDCampo").getText());
        pBDefinicion.setObjetoTabla(fila, 1, auxElement.getChild("NombreCampo").getText());
        pBDefinicion.setObjetoTabla(fila, 2, auxElement.getChild("Tama\361oCampo").getText());
        pBDefinicion.setObjetoTabla(fila, 3, auxElement.getChild("ValorDefectoCampo").getText());
        pBDefinicion.setObjetoTabla(fila, 4, auxElement.getChild("DescripcionCampo").getText());
        pBDefinicion.setObjetoTabla(fila, 5, auxElement.getChild("TipoDato").getText());
        pBDefinicion.setObjetoTabla(fila, 6, auxElement.getChild("Opcional").getText());
        pBDefinicion.setObjetoTabla(fila, 7, auxElement.getChild("CampoReferenciado").getText());
    }

    private void prefIdentificacion(Element auxElement)
    {
        int id = 0;
        String clave = "Clave";
        String clav = "Clave";
        clave = (new StringBuilder(String.valueOf(clave))).append(String.valueOf(id)).toString();
        pBIdentificacion.setNomProtocolo(auxElement.getChild("CamposProtocolo").getChild("Protocolo").getText());
        pBIdentificacion.setNivelProtocolo(auxElement.getChild("CamposProtocolo").getChild("Nivel").getText());
        for(; auxElement.getChild(clave) != null; clave = (new StringBuilder(String.valueOf(clave))).append(String.valueOf(id)).toString())
        {
            pBIdentificacion.setIdentificador(id, 0, auxElement.getChild(clave).getChild("Valor").getText());
            pBIdentificacion.setIdentificador(id, 1, String.valueOf(Integer.valueOf(auxElement.getChild(clave).getChild("PosicionInicio").getText())));
            pBIdentificacion.setIdentificador(id, 2, auxElement.getChild(clave).getChild("Tama\361o").getText());
            pBIdentificacion.setIdentificador(id, 3, auxElement.getChild(clave).getChild("Tipo").getText());
            id++;
            clave = clav;
        }

    }

    private void prefDefinicion(Element auxElement)
    {
        int campo = 1;
        String camp = "Campo";
        String cam = "Campo";
        prefDefinicionUnicas(auxElement.getChild("CamposProtocolo"));
        pBDefinicion.setTabla(pBDefinicion.getTabla());
        for(int i = 0; i < pBDefinicion.getNumCampos(); i++)
        {
            camp = (new StringBuilder(String.valueOf(camp))).append(String.valueOf(campo)).toString();
            prefDefinicionTabla(auxElement.getChild(camp), i);
            campo++;
            camp = cam;
        }

    }

    private boolean validate(String state)
    {
        boolean auxB;
        if(state.toUpperCase().equals("YES"))
            auxB = true;
        else
            auxB = false;
        return auxB;
    }

    public void printState()
    {
        System.out.println("\n<CaptureSniffer>");
        if(pBCapture != null)
        {
            System.out.println("\t<CapturePreferences>");
            System.out.println("\t\t<Capture>");
            System.out.println((new StringBuilder("\t\t\t<Interface>")).append(noNull(pBCapture.getCapDispositive())).append("</Interface>").toString());
            System.out.println((new StringBuilder("\t\t\t<Promiscuous_Modo>")).append(noNull(pBCapture.getCapPromiscuousMode())).append("</Promiscuous_Modo>").toString());
            System.out.println((new StringBuilder("\t\t\t<Filter Id=\"")).append(noNull(pBCapture.getCapFilter())).append("\">").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Advance Id=\"")).append(noNull(pBCapture.getCapAdvanceId())).append("\">").append(noNull(pBCapture.getCapAdvance())).append("</Advance>").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Normal Id=\"")).append(noNull(pBCapture.getCapNormal())).append("\">").toString());
            System.out.println((new StringBuilder("\t\t\t\t\t<Host>")).append(noNull(pBCapture.getCapHost())).append("</Host>").toString());
            System.out.println((new StringBuilder("\t\t\t\t\t<Protocol>")).append(noNull(pBCapture.getCapProtocol())).append("</Protocol>").toString());
            System.out.println((new StringBuilder("\t\t\t\t\t<Port>")).append(noNull(pBCapture.getCapPort())).append("</Port>").toString());
            System.out.println("\t\t\t\t</Normal>");
            System.out.println("\t\t\t</Filter>");
            System.out.println("\t\t</Capture>");
            System.out.println("\t\t<File>");
            System.out.println((new StringBuilder("\t\t\t<Locate>")).append(noNull(pBCapture.getFilLocate())).append("</Locate>").toString());
            System.out.println((new StringBuilder("\t\t\t<Multiple_Files Id=\"")).append(noNull(pBCapture.getFilMultipleFileId())).append("\">").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Space Id=\"")).append(noNull(pBCapture.getFilSpaceId())).append("\" Type=\"").append(noNull(pBCapture.getFilSpaceType())).append("\">").append(noNull(pBCapture.getFilSpace())).append("</Space>").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Time Id=\"")).append(noNull(pBCapture.getFilTimeId())).append("\" Type=\"").append(noNull(pBCapture.getFilTimeType())).append("\">").append(noNull(pBCapture.getFilTime())).append("</Time>").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Ring_Buffer Id=\"")).append(noNull(pBCapture.getFilRingBufferId())).append("\" Type=\"").append(noNull(pBCapture.getFilRingBufferType())).append("\">").append(noNull(pBCapture.getFilRingBuffer())).append("</Ring_Buffer>").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Circular_XML Id=\"")).append(noNull(pBCapture.getFilRingBufferXML())).append("\">").toString());
            
            System.out.println((new StringBuilder("\t\t\t\t<Stop_After Id=\"")).append(noNull(pBCapture.getFilStopAfterId())).append("\" Type=\"").append(noNull(pBCapture.getFilStopAfterType())).append("\">").append(noNull(pBCapture.getFilStopAfter())).append("</Stop_After>").toString());
            System.out.println("\t\t\t</Multiple_Files>");
            System.out.println("\t\t</File>");
            System.out.println("\t\t<Stop_Capture>");
            System.out.println((new StringBuilder("\t\t\t<After_Packets Id=\"")).append(noNull(pBCapture.getstpAfterPacketsId())).append("\"").append(">").append(noNull(pBCapture.getstpAfterPackets())).append("</After_Packets>").toString());
            System.out.println((new StringBuilder("\t\t\t<After_Space Id=\"")).append(noNull(pBCapture.getstpAfterSpaceId())).append("\" Type=\"").append(noNull(pBCapture.getstpAfterSpaceType())).append("\">").append(noNull(pBCapture.getstpAfterSpace())).append("</After_Space>").toString());
            System.out.println((new StringBuilder("\t\t\t<After_Time Id=\"")).append(noNull(pBCapture.getstpAfterTimeId())).append("\" Type=\"").append(noNull(pBCapture.getstpAfterTimeType())).append("\">").append(noNull(pBCapture.getstpAfterTime())).append("</After_Time>").toString());
            System.out.println("\t\t</Stop_Capture>");
            System.out.println("\t</CapturePreferences>");
        }
        if(pBExport != null)
        {
            System.out.println("\t<ExportPreferences>");
            System.out.println("\t\t<File>");
            System.out.println((new StringBuilder("\t\t\t<Type>")).append(noNull(pBExport.getExpType())).append("</Type>").toString());
            System.out.println((new StringBuilder("\t\t\t<Source>")).append(noNull(pBExport.getExpSource())).append("</Source>").toString());
            System.out.println((new StringBuilder("\t\t\t<Destination>")).append(noNull(pBExport.getExpDestination())).append("</Destination>").toString());
            System.out.println((new StringBuilder("\t\t\t<Multiple_Files>")).append(noNull(pBExport.getExpMultifile())).append("</Multiple_Files>").toString());
            System.out.println("\t\t</File>");
            System.out.println("\t\t<Statistics>");
            System.out.println("\t\t</Statistics>");
            System.out.println("\t</ExportPreferences>");
        }
        if(pBFromFile != null)
        {
            System.out.println("\t<FromFilePreferences>");
            System.out.println("\t\t<Capture>");
            System.out.println((new StringBuilder("\t\t\t<Source>")).append(noNull(pBFromFile.getffCapSource())).append("</Source>").toString());
            System.out.println((new StringBuilder("\t\t\t<Filter Id=\"")).append(noNull(pBFromFile.getffCapFilterId())).append("\">").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Advance Id=\"")).append(noNull(pBFromFile.getffCapAdvanceId())).append("\">").append(noNull(pBFromFile.getffCapAdvance())).append("</Advance>").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Normal Id=\"")).append(noNull(pBFromFile.getffCapNormal())).append("\">").toString());
            System.out.println((new StringBuilder("\t\t\t\t\t<Host>")).append(noNull(pBFromFile.getffCapHost())).append("</Host>").toString());
            System.out.println((new StringBuilder("\t\t\t\t\t<Protocol>")).append(noNull(pBFromFile.getffCapProtocol())).append("</Protocol>").toString());
            System.out.println((new StringBuilder("\t\t\t\t\t<Port>")).append(noNull(pBFromFile.getffCapPort())).append("</Port>").toString());
            System.out.println("\t\t\t\t</Normal>");
            System.out.println("\t\t\t</Filter>");
            System.out.println("\t\t</Capture>");
            System.out.println("\t\t<File>");
            System.out.println((new StringBuilder("\t\t\t<Locate>")).append(noNull(pBFromFile.getffFilLocate())).append("</Locate>").toString());
            System.out.println((new StringBuilder("\t\t\t<Multiple_Files Id=\"")).append(noNull(pBFromFile.getffFilMultipleFileId())).append("\">").toString());
            System.out.println("\t\t\t</Multiple_Files>");
            System.out.println("\t\t</File>");
            System.out.println("\t\t<Stop_Capture>");
            System.out.println((new StringBuilder("\t\t\t<After_Packets Id=\"")).append(noNull(pBFromFile.getffStpAfterPacketsId())).append("\"").append(">").append(noNull(pBFromFile.getffStpAfterPackets())).append("</After_Packets>").toString());
            System.out.println("\t\t</Stop_Capture>");
            System.out.println("\t</FromFilePreferences>");
        }
        if(pBMeta != null)
        {
            System.out.println("\t<MetaCapture>");
            System.out.println("\t\t<Locate>");
            System.out.println((new StringBuilder("\t\t\t<Relative Id=\"")).append(pBMeta.getMetLocRelativeId()).append("\">").append(pBMeta.getMetLocRelative()).append("</Relative>").toString());
            System.out.println((new StringBuilder("\t\t\t<Absolute Id=\"")).append(pBMeta.getMetLocAbsotuteId()).append("\">").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Path>")).append(pBMeta.getMetLocAbsotutePath()).append("</Path>").toString());
            System.out.println((new StringBuilder("\t\t\t\t<Name>")).append(pBMeta.getMetLocAbsotuteName()).append("</Name>").toString());
            System.out.println("\t\t\t</Absolute>");
            System.out.println("\t\t</Locate>");
            System.out.println((new StringBuilder("\t\t<Multiple_Files Id=\"")).append(pBMeta.getMetMultipleFileId()).append("\">").toString());
            System.out.println((new StringBuilder("\t\t\t<Ring_Buffer Id=\"")).append(pBMeta.getMetMFRingBufferId()).append("\">").append(pBMeta.getMetMFRingBuffer()).append("</Ring_Buffer>").toString());
            System.out.println((new StringBuilder("\t\t\t<Start>")).append(pBMeta.getMetMFStar()).append("</Start>").toString());
            System.out.println((new StringBuilder("\t\t\t<End>")).append(pBMeta.getMetMFEnd()).append("</End>").toString());
            System.out.println("\t\t</Multiple_Files>");
            System.out.println("\t</MetaCapture>");
        }
        System.out.println("</CaptureSniffer>");
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

    public preferencesBeanExportInsercion getPBExportInsercion()
    {
        if(pBExportInsert == null)
            pBExportInsert = new preferencesBeanExportInsercion();
        return pBExportInsert;
    }

    private preferencesBeanCapture pBCapture;
    private preferencesBeanExport pBExport;
    private preferencesBeanExportInsercion pBExportInsert;
    private preferencesBeanFromFile pBFromFile;
    private preferencesBeanMeta pBMeta;
    private preferencesBeanDefinicion pBDefinicion;
    private preferencesBeanIdentificacion pBIdentificacion;
    private String ficheroCapture;
    private String ficheroExport;
    private String ficheroFromFile;
    private String ficheroMETA;
    private String ficheroDefinicion;
}
