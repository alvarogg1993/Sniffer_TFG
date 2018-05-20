
package presentacion;

 			
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

 			import javax.swing.GroupLayout;
 			import javax.swing.JButton;
 			import javax.swing.JDialog;
 			import javax.swing.JScrollPane;
 			import javax.swing.JTable;
 			import javax.swing.LayoutStyle;
 	
 			import javax.swing.table.DefaultTableModel;
 		
        	
import presentacion.propiedadesVentana.CentrarVentana;
/** 
 * Clase Ventanaintrusos, revisión con las capturas de posibles intrusos. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
public class VentanaIntrusos extends JDialog {
	 
	    private JButton jButtonCancelar;
	    private JScrollPane jScrollPane1;
	    private JTable Tabla;
	    private String nomFileDestino;
	    
	    /** Metodo se define la ventana que ha de enseñar los caminos de los archivos
	     *  para la realización del proceso.
	     * @param Destino 
	     * @exception exceptions Ningún error (Excepción) definida
	     */   
    public VentanaIntrusos(String Destino) {
        
    	super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Busqueda de Intrusos en Captura", true);
        initComponents();
       
        this.nomFileDestino = Destino;
        CargaDeDatos();
        setResizable(false);		
        new CentrarVentana(this);
        setVisible(true);
    }
  
    /** Metodo donde se inicializan los componentes para la creación de
     *  la pantalla .
     * @exception exceptions Ningún error (Excepción) definida
     */                  
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        Tabla = new JTable();
        jButtonCancelar = new JButton();

              Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Valores de M.A.C."
            }
        ));
        setTitle("Resultado de Captura de Intrusos");
        jScrollPane1.setViewportView(Tabla);

        jButtonCancelar.setText("Salir");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelar)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

	        this.jButtonCancelar.setText("Cerrar");
		        this.jButtonCancelar.addActionListener(new ActionListener(){
		        	public void actionPerformed (ActionEvent evt){
		        	    VentanaIntrusos.this.jButtonCancelarActionPerformed(evt);	
		        	}
		        });
		        

        pack();
    }                   
    /** Metodo donde se define la acción de un objeto tipo boton Aceptar
     * @params ActionEvent
     * @exception exceptions Ningún error (Excepción) definida
     */  
	    private void jButton3ActionPerformed(ActionEvent evt){
	     dispose();
	    }
	    /** Metodo donde se define la acción de un objeto tipo boton Cancelar
	     * @params ActionEvent
	     * @return Sin valor de retorno 
	     * @exception exceptions Ningún error (Excepción) definida
	     */ 	     	   
	   private void jButtonCancelarActionPerformed(ActionEvent evt){
	     dispose();
	    }
	    /** Metodo donde se cargan los datos por defecto del fichero captura,
	     * fichero Lista Negra y donde se dejara el resultado. 
	     * @exception exceptions controlada por si no corresponde algún valor
	     */ 	   
	   public void CargaDeDatos(){
		   
		   String sCadenaTabla;
		   
		   try {
            FileReader FDestinoTabla = new FileReader(nomFileDestino);
		    BufferedReader BufferDestinoTabla = new BufferedReader(FDestinoTabla);
			
			sCadenaTabla = BufferDestinoTabla.readLine();
		    
		    DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
		    
		    	while (sCadenaTabla!=null) {		    			
		    			modelo.addRow(new Object[]{sCadenaTabla});
		    			sCadenaTabla = BufferDestinoTabla.readLine();
		    	}// End While
	      } catch (IOException e) {
			  e.printStackTrace();
		  }
		  
	   }
	   
	   
}
