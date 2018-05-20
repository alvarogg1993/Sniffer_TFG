
package presentacion.visualizarCaptura;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
/** 
 * Clase Conexión. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class Conexion
{

    public Conexion(String IpOrigen, String IpDestino, int PuertoOrigen, int PuertoDestino)
    {
        VectorPaquetes = new Vector();
        IpSrc = IpOrigen;
        IpDst = IpDestino;
        PortSrc = PuertoOrigen;
        PortDest = PuertoDestino;
        numeropaquetes = 0;
        Establecimiento = GetTime();
    }

    public String GetTime()
    {
        Date hoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:ms");
        String horaActual = new String(sdf.format(hoy));
        return horaActual;
    }

    public Vector getvector()
    {
        return VectorPaquetes;
    }

    public String getTimeEstablecimiento()
    {
        return Establecimiento;
    }

    public String getIpSrc()
    {
        return IpSrc;
    }

    public String getIpDest()
    {
        return IpDst;
    }

    public int getPuertoSrc()
    {
        return PortSrc;
    }

    public int getPuertoDst()
    {
        return PortDest;
    }

    public int getnumeropaquetes()
    {
        return numeropaquetes;
    }

    public void addpaquete(int posicionpaquete)
    {
        numeropaquetes++;
        posicionpk = new Integer(posicionpaquete);
        VectorPaquetes.add(posicionpk);
    }

    private String Establecimiento;
    private String IpSrc;
    private String IpDst;
    private int PortSrc;
    private int PortDest;
    private int numeropaquetes;
    private Vector VectorPaquetes;
    private Integer posicionpk;
}
