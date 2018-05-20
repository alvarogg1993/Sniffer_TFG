
package dominio.pcap.rules;

/** 
 * Clase ClassificationRules. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/

public class ClassificationRules
{

    public ClassificationRules()
    {
    }

    public void setDescripcion_corta(String Descripcion_corta)
    {
        this.Descripcion_corta = Descripcion_corta;
    }

    public void setDescripcion_larga(String Descripcion_larga)
    {
        this.Descripcion_larga = Descripcion_corta;
    }

    public void setDescripcion_Prioridad(String Prioridad)
    {
        this.Prioridad = Prioridad;
    }

    public String getDescripcion_corta()
    {
        return Descripcion_corta;
    }

    public String getDescripcion_larga()
    {
        return Descripcion_larga;
    }

    public String getPrioridad()
    {
        return Prioridad;
    }

    private String Descripcion_corta;
    private String Descripcion_larga;
    private String Prioridad;
}
