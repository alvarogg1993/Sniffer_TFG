//package presentacion.comandos;
//
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.JMenuItem;
//import javax.swing.KeyStroke;
//
//import presentacion.Mediador;
///** 
// * Clase CJMOperacionIntrusos. 
// * 
// * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
// * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
// * @version 1.3 
//*/
//public class CJMOperacionIntrusos extends JMenuItem implements Comando{
//
//	public CJMOperacionIntrusos(Mediador mediador)
//    {
//        super("Busqueda de Intrusos en Captura", 90);
//        KeyStroke ctrlZ = KeyStroke.getKeyStroke(90, 2);
//        setAccelerator(ctrlZ);
//        this.mediador = mediador;
//        addActionListener(mediador);
//     //   this.addMouseListener(this);
//    }
//
//    public void ejecutar()
//    {
//        /*Para visualizar la pantalla donde se han de poner los archivos a comparar.*/
//    	mediador.irProcesoIntruso();
//    	
//    }
//
//    private Mediador mediador;
//
//	
//	
//	
//}
