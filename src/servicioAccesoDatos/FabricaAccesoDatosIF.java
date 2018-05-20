
package servicioAccesoDatos;

import java.io.Serializable;
/** 
 * Clase FabricaAccesoDatosIF. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package servicioAccesoDatos:
//            FachadaFichero

public abstract class FabricaAccesoDatosIF
    implements Serializable
{

    public FabricaAccesoDatosIF()
    {
    }

    public abstract FachadaFichero creaFachadaFichero(String s, String s1);
}
