
package servicioAccesoDatos;

import java.io.*;
/** 
 * Clase FachadaFicheroExportacion. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.2 
*/
// Referenced classes of package servicioAccesoDatos:
//            FachadaFichero

public class FachadaFicheroExportacion extends FachadaFichero
{

    public FachadaFicheroExportacion(String nombreRutaF)
        throws IOException
    {
        FachadaFichero.nombreRutaF = nombreRutaF;
        this.nombreRutaF = nombreRutaF;
    }

    public PrintWriter getOutput()
    {
        return output;
    }

    public static String getRutaFichero()
    {
        return nombreRutaF;
    }

    public PrintWriter getwriter()
    {
        try
        {
            if(output == null)
                output = new PrintWriter(new BufferedWriter(new FileWriter(nombreRutaF)));
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return output;
    }

    public void escribir(String contenido)
    {
        getwriter().println(contenido);
    }

    public void cerrar()
    {
        getwriter().flush();
        if(output != null)
            output.close();
    }

    public Object[] leer()
    {
        try
        {
            BufferedReader inlong = new BufferedReader(new FileReader(nombreRutaF));
            String linea2;
            int nLineas;
            for(nLineas = 0; (linea2 = inlong.readLine()) != null; nLineas++);
            in = new BufferedReader(new FileReader(nombreRutaF));
            Object lineaXML[] = new Object[nLineas];
            String linea;
            for(int i = 0; (linea = in.readLine()) != null; i++)
                lineaXML[i] = linea;

            in.close();
            return lineaXML;
        }
        catch(IOException e)
        {
            System.out.println("Se ha producido un error al leer");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String leerString()
    {
        try
        {
            FileInputStream inStream = new FileInputStream(nombreRutaF);
            int inBytes = inStream.available();
            byte inBuf[] = new byte[inBytes];
            int bytesRead = inStream.read(inBuf, 0, inBytes);
            String resultado = new String(inBuf);
            inStream.close();
            return resultado;
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void reinicializar()
    {
        nombreRutaF = null;
    }

    static String nombreRutaF;
    public FileWriter writer;
    public BufferedWriter buffer;
    public BufferedReader in;
    public PrintWriter output;
}
