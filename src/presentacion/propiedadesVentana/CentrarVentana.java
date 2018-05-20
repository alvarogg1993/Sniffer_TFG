
package presentacion.propiedadesVentana;

import java.awt.*;
/** 
 * Clase CentrarVentana. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class CentrarVentana
{

    public CentrarVentana(Window o)
    {
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        if(o.getHeight() > tamanoPantalla.height)
            o.setSize(o.getWidth(), tamanoPantalla.height);
        if(o.getWidth() > tamanoPantalla.width)
            o.setSize(tamanoPantalla.width, o.getHeight());
        o.setLocation((tamanoPantalla.width - o.getWidth()) / 2, (tamanoPantalla.height - o.getHeight()) / 2);
    }
}
