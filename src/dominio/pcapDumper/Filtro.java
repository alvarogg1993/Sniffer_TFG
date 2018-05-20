
package dominio.pcapDumper;

/** 
 * Clase Filtro. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class Filtro
{

    public Filtro()
    {
        Host = null;
        Proto = null;
        Puerto = null;
        FILTER = "";
    }

    public void setFiltro(String host, String proto, String puerto)
    {
        Host = host;
        Proto = proto;
        Puerto = puerto;
        if(Host == null)
            Host = "";
        if(Proto == null)
            Proto = "";
        if(Puerto == null)
            Puerto = "";
        if(Host.trim().equals("") & Proto.trim().equals("") && Puerto.trim().equals(""))
        {
            FILTER = "";
        } else
        {
            if(Host.trim().equals("") && Proto.trim().equals("") && !Puerto.trim().equals(""))
                FILTER = (new StringBuilder("port ")).append(Puerto).toString();
            if(Host.trim().equals("") && !Proto.trim().equals("") && !Puerto.trim().equals(""))
                FILTER = (new StringBuilder("proto ")).append(Proto).append(" and port ").append(Puerto).toString();
            if(Host.trim().equals("") && !Proto.trim().equals("") && Puerto.trim().equals(""))
                FILTER = (new StringBuilder("proto ")).append(Proto).toString();
            if(!Host.trim().equals("") && Proto.trim().equals("") && Puerto.trim().equals(""))
                FILTER = (new StringBuilder("host ")).append(Host).toString();
            if(!Host.trim().equals("") && !Proto.trim().equals("") && Puerto.trim().equals(""))
                FILTER = (new StringBuilder("host ")).append(Host).append(" and proto ").append(Proto).toString();
            if(!Host.trim().equals("") && Proto.trim().equals("") && !Puerto.trim().equals(""))
                FILTER = (new StringBuilder("host ")).append(Host).append(" and port ").append(Puerto).toString();
            if(!Host.trim().equals("") && !Proto.trim().equals("") && !Puerto.trim().equals(""))
                FILTER = (new StringBuilder("host ")).append(Host).append(" and proto ").append(Proto).append(" and port ").append(Puerto).toString();
        }
    }

    public void setFiltro_AV(String filtro_AV)
    {
        FILTER = filtro_AV;
    }

    public String getfilter()
    {
        return FILTER;
    }

    private String Host;
    private String Proto;
    private String Puerto;
    private String FILTER;
}
