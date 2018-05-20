
package servicioAccesoDatos;

import java.io.*;
/** 
 * Clase FachadaFichero. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public abstract class FachadaFichero
{

    public FachadaFichero()
    {
    }

    public void creaFichero(String nombreRutaF)
        throws IOException
    {
        try
        {
            fEtiquetas = new File(nombreRutaF);
            FileOutputStream fosEtiquetas = new FileOutputStream(fEtiquetas);
        }
        catch(FileNotFoundException fileException)
        {
            System.err.println("Error al crear el fichero");
            throw fileException;
        }
    }

    public void abrir(String nombreRutaF)
        throws IOException
    {
        try
        {
            writer = new FileWriter(nombreRutaF);
            buffer = new BufferedWriter(writer);
            output = new PrintWriter(writer);
        }
        catch(SecurityException securityException)
        {
            System.err.println("No tiene permiso para escribir en el fichero");
            throw securityException;
        }
        catch(FileNotFoundException fileException)
        {
            System.err.println("Error al crear el fichero");
            throw fileException;
        }
    }

    public void cerrar()
        throws IOException
    {
        if(output != null)
            output.close();
    }

    public abstract PrintWriter getOutput();

    public abstract void escribir(String s);

    public abstract Object[] leer();

    public abstract String leerString();

    static String nombreRutaF;
    public FileWriter writer;
    public BufferedWriter buffer;
    public PrintWriter output;
    public File fEtiquetas;
}
