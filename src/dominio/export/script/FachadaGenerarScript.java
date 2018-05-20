
package dominio.export.script;

import java.io.FileOutputStream;
import java.io.PrintStream;
/** 
 * Clase FachadaGenerarScript. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public abstract class FachadaGenerarScript
{

    public FachadaGenerarScript()
    {
        setRuta(System.getProperty("user.dir"));
        setEjecutable("sniffer.jar");
        setRutaBat("");
        setMvm("");
        setParam("", "");
    }

    public void writeToDisk()
    {
        try
        {
            writer = new FileOutputStream(getRutaBat());
            buffer = new PrintStream(writer);
            writeToDiskCabecera();
            writeToDiskVariables();
            writeToDiskCabeceraVisible();
            writeToDiskEjecutable();
            buffer.close();
            writer.close();
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
    }

    public abstract void writeToDiskCabecera();

    public abstract void writeToDiskVariables();

    public abstract void writeToDiskCabeceraVisible();

    public abstract void writeToDiskEjecutable();

    private void setRuta(String aux)
    {
        ruta = aux;
    }

    public String getRuta()
    {
        return ruta;
    }

    private void setEjecutable(String aux)
    {
        ejecutable = aux;
    }

    public String getEjecutable()
    {
        return ejecutable;
    }

    public void setMvm(String aux)
    {
        mvm = aux;
    }

    public String getMvm()
    {
        String aux;
        if(mvm.equals("-1"))
            aux = "0";
        else
            aux = mvm;
        return aux;
    }

    public void setRutaBat(String aux)
    {
        rutaBat = aux;
    }

    public String getRutaBat()
    {
        return rutaBat;
    }

    public abstract void setParam(String s, String s1);

    public String getParam()
    {
        return param;
    }

    public String getTipoCommand(String tipo)
    {
        String aux = "-command ";
        if(tipo.equals("captura"))
            aux = (new StringBuilder(String.valueOf(aux))).append("-scan").toString();
        if(tipo.equals("exporta"))
            aux = (new StringBuilder(String.valueOf(aux))).append("-export").toString();
        if(tipo.equals("desde fichero"))
            aux = (new StringBuilder(String.valueOf(aux))).append("-fromfile").toString();
        return aux;
    }

    private String ruta;
    private String ejecutable;
    public String mvm;
    public String rutaBat;
    public String param;
    public FileOutputStream writer;
    public PrintStream buffer;
}
