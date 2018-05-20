
package servicioAccesoDatos;

import java.io.IOException;
import java.io.PrintStream;
/** 
 * Clase FabricaAccesoDatos. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package servicioAccesoDatos:
//            FabricaAccesoDatosIF, FachadaFicheroDirectorios, FachadaFicheroExportacion, FachadaFichero

public class FabricaAccesoDatos extends FabricaAccesoDatosIF
{

    public FabricaAccesoDatos()
    {
    }

    public FachadaFichero creaFachadaFichero(String tipof, String nombreRutaF)
    {
        if(tipof.equals("directorios"))
            try
            {
                return new FachadaFicheroDirectorios(nombreRutaF);
            }
            catch(IOException e)
            {
                System.err.println(e.getMessage());
            }
        if(tipof.equals("exportacion"))
            try
            {
                return new FachadaFicheroExportacion(nombreRutaF);
            }
            catch(IOException e)
            {
                System.err.println(e.getMessage());
            }
        return null;
    }

    public static FabricaAccesoDatos getInstancia()
    {
        if(instancia == null)
            instancia = new FabricaAccesoDatos();
        return instancia;
    }

    private static FabricaAccesoDatos instancia;
}
