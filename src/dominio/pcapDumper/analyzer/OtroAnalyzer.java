
package dominio.pcapDumper.analyzer;

import org.jnetpcap.packet.PcapPacket;

import dominio.preferences.identificacion.PrefIdentificacion;
/** 
 * Clase OtroAnalyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
public class OtroAnalyzer
{

    public OtroAnalyzer()
    {
        layer = DATALINK_LAYER;
        layer = APPLICATION_LAYER;
    }

    public String getProtocolName()
    {
        if(nombre == null)
            return "DESCONOCIDO";
        else
            return nombre;
    }
    /** Metodo  donde se analiza el paquete recibido si no es ninguno de los esperados se mete aqui.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ningún error (Excepción) definida
     */
    public boolean isAnalyzable(PcapPacket paquete)
    {
        return paquete.getCaptureHeader().caplen()>0;
    }

    public String[][] getValores()
    {
        return values;
    }
    /** Metodo  donde se analiza el paquete recibido si no es ninguno de los esperados se mete aqui.
     * @param byte arr[], String path, PrefIdentificacion pref
     * @return sin valor de retorno
     * @exception exceptions Ningún error (Excepción) definida
     */  
    public static void analizar(byte arr[], String path, PrefIdentificacion pref)
    {
        String cad = "";
        String prot = "";
        nombre = pref.getIdentificacionProtocolo(arr);
        values = pref.getCamposProtocoloIdentificado(nombre, arr);
        pref.getTamaF1o();
    }

    private static String dataCaracter(byte arr[])
    {
        String cad = "";
        for(int i = 0; i < arr.length; i++)
            cad = (new StringBuilder(String.valueOf(cad))).append((char)arr[i]).toString();

        return cad;
    }

    private static String dataBinario(byte arr[])
    {
        String cad = "";
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == 48)
                cad = (new StringBuilder(String.valueOf(cad))).append("0").toString();
            if(arr[i] == 49)
                cad = (new StringBuilder(String.valueOf(cad))).append("1").toString();
        }

        return cad;
    }

    static int tamaF1o = 0;
    static String nombre;
    static String values[][];
    public int layer;
    public static int DATALINK_LAYER = 0;
    public static int NETWORK_LAYER = 1;
    public static int TRANSPORT_LAYER = 2;
    public static int APPLICATION_LAYER = 3;

}
