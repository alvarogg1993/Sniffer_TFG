
package dominio.preferences.insercion;

import dominio.preferences.preferencesFileRead;
import java.io.File;
/** 
 * Clase PrefExpInsercion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PrefExpInsercion
{

    public PrefExpInsercion()
    {
        leer = new preferencesFileRead();
    }

    private File fich;
    private preferencesFileRead leer;
}
