
package presentacion.comandos;

import java.awt.Font;
import javax.swing.JButton;
import presentacion.Mediador;
import presentacion.preferencias.PreferenciasInsercion;
/** 
 * Clase CBAbrirCarpeta. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package presentacion.comandos:
//            Comando

public class CBAceptar extends JButton
    implements Comando
{

    public CBAceptar(Mediador mediador, String ventana)
    {
        if(ventana.toLowerCase().equals("salir"))
        {
            setText("   Salir   ");
            setMnemonic('S');
        } else
        {
            setText("Aceptar");
            setMnemonic('A');
        }
        setFont(new Font("Arial", 1, 12));
        setAlignmentY(0.5F);
        titulo = ventana;
        this.mediador = mediador;
        addActionListener(mediador);
    }

    public void ejecutar()
    {
        boolean estado = true;
        if(titulo.equals("AceptarInicioCaptura"))
            estado = mediador.botonAceptarInicoCapturaDumper();
        if(titulo.equals("AceptarConfiguracion"))
            mediador.botonAceptarConfiguracion();
        if(titulo.equals("AceptarInicioCapturaFromFile"))
            mediador.botonAceptarInicoCapturaDumperFromFile();
        if(titulo.equals("GenerarScript"))
            mediador.GenerarBatCaptura();
        if(titulo.equals("GenerarXML"))
            mediador.GenerarXMLFromFile();
        if(titulo.equals("GenerarXMLProtocolo"))
            mediador.GenerarXMLProtocolo();
        if(titulo.equals("ChequearDefinicionProtocolo"))
        {
            mediador.ChequearDefinicionProtocolo();
            estado = false;
        }
        if(titulo.equals("RefrescarTablaProtocolo"))
        {
            mediador.ChequearIdentificacionProtocolo();
            estado = false;
        }
        if(titulo.equals("Insertar Paquetes Capturados"))
        {
            mediador.irAInsertarPaquetes("Insertar Paquetes Capturados", PreferenciasInsercion.getEnvios());
            estado = false;
        }
        if(titulo.equals("Insertar Paquetes Definidos"))
        {
            mediador.irAInsertarPaquetes("Insertar Paquetes Definidos", PreferenciasInsercion.getEnvios());
            estado = false;
        }
//        if(titulo.equals("GenerarArchivoIntrusos"))
//        {
//            mediador.saveFileIntrusos();
//            estado = false;
//        }
        if(estado)
            mediador.cerrarVentana(mediador.getVentanaPresentacion());
    }

    private Mediador mediador;
    private String titulo;
}
