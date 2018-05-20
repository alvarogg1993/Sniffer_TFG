/*   1:    */package presentacion.preferencias;

/*   2:    */
/*   3:    */import java.awt.Container;
/*   4:    */
import java.awt.event.ActionEvent;
/*   5:    */
import java.awt.event.ActionListener;

/*   6:    */
import javax.swing.BorderFactory;
/*   7:    */
import javax.swing.JButton;
/*   8:    */
import javax.swing.JDialog;
/*   9:    */
import javax.swing.JLabel;
/*  10:    */
import javax.swing.JPanel;
/*  11:    */
import javax.swing.JTextField;

/*  12:    */
import org.jdesktop.layout.GroupLayout;
/*  13:    */
import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*  14:    */
import org.jdesktop.layout.GroupLayout.SequentialGroup;

/*  15:    */
import presentacion.Mediador;
/*  16:    */
import presentacion.comandos.CBAbrirCarpeta;
import presentacion.comandos.CBAbrirFichero;
/*  17:    */
import presentacion.comandos.CBAceptar;
/*  18:    */
import presentacion.comandos.Comando;
/*  19:    */
import presentacion.propiedadesVentana.CentrarVentana;

/*  20:    */
/**
 * Clase PreferenciasConfiguracion.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
/* 21: */public class PreferenciasConfiguracion
/* 22: */extends JDialog
/* 23: */{
	/* 24: */Mediador mediador;
	/* 25: */Comando jButton1; //Capturas
	/* 26: */Comando jButton2; //Exportaciones
	/* 27: */Comando jButton3; //Scripts
	/* 28: */Comando jButton4; //Parametrización
	/* 29: */Comando jButton5; //Aceptar Configuración
	Comando jButton10;	//Botón selección fichero Sincronismo
	Comando jButton7;
	/* 30: */private JButton jButton6; //Cancelar
	/* 31: */private JLabel jLabel1;
	/* 32: */private JLabel jLabel2;
	/* 33: */private JLabel jLabel3;
	/* 34: */private JLabel jLabel4;
	private JLabel jLabel10; // Variable del fichero sincronizado
	private JLabel jLabel7;
	/* 35: */private JPanel jPanel1;

	/* 36: */private static JTextField jTextField1;
	/* 37: */private static JTextField jTextField2;
	/* 38: */private static JTextField jTextField3;
	/* 39: */private static JTextField jTextField4;
	private static JTextField jTextField10;	// Campo de texto del Fichero de sincronismo
	private static JTextField jTextField7;

	/* 40: */
	/* 41: */public PreferenciasConfiguracion()
	/* 42: */{
		/* 43: 64 */super(presentacion.ventanaMenuSniffer.MenuSniffer
				.getFrames()[0], "Configuración", true);
		/* 44: 65 */this.mediador = new Mediador();
		/* 45: 66 */initComponents();
		/* 46: 67 */setResizable(false);
		/* 47: 68 */new CentrarVentana(this);
		/* 48: */}

	/* 49: */
	/* 50: */public PreferenciasConfiguracion(Mediador med)
	/* 51: */{
		/* 52: 77 */super(presentacion.ventanaMenuSniffer.MenuSniffer
				.getFrames()[0], "Configuración....", true);
		/* 53: 78 */this.mediador = med;
		/* 54: 79 */initComponents();
		/* 55: 80 */setResizable(false);
		/* 56: 81 */new CentrarVentana(this);
		/* 57: */}

	/* 58: */
	/* 59: */private void initComponents()
	/* 60: */{
		/* 61: 91 */this.jPanel1 = new JPanel();
		/* 62: 92 */this.jLabel1 = new JLabel();
		/* 63: 93 */this.jLabel2 = new JLabel();
		/* 64: 94 */this.jLabel3 = new JLabel();
		/* 65: 95 */this.jLabel4 = new JLabel();
		this.jLabel10 = new JLabel();
		// this.jLabel7 = new JLabel();
		/* 66: 96 */jTextField1 = new JTextField();
		/* 67: 97 */jTextField2 = new JTextField();
		/* 68: 98 */jTextField3 = new JTextField();
		/* 69: 99 */jTextField4 = new JTextField();
		jTextField10 = new JTextField();
		// jTextField7 = new JTextField();
		/* 70:100 */this.jButton6 = new JButton();
		/* 71: */
		/* 72: */
		/* 73:103 */setDefaultCloseOperation(2);
		/* 74:104 */this.jPanel1.setBorder(BorderFactory
				.createTitledBorder("Sniffer III - Directorios de Usuario"));
		/* 75:105 */this.jLabel1.setText("Capturas:");
		/* 76: */
		/* 77:107 */this.jLabel2.setText("Exportaciones: ");
		/* 78: */
		/* 79:109 */this.jLabel3.setText("Scripts:");
		/* 80: */
		/* 81:111 */this.jLabel4.setText("Parametrización:");
		this.jLabel10.setText("Fichero de Sincronismo:");

		// /* 82: */ this.jLabel7.setText("Intrusos:");
		/* 83: */
		/* 84:114 */this.jButton1 = new CBAbrirCarpeta(this.mediador,
				"Capturas");
		/* 85:115 */this.jButton2 = new CBAbrirCarpeta(this.mediador,
				"Exportaciones");
		/* 86:116 */this.jButton3 = new CBAbrirCarpeta(this.mediador, "Scripts");
		/* 87:117 */this.jButton4 = new CBAbrirCarpeta(this.mediador,
				"Paremetrizacion");
//		this.jButton10 = new CBAbrirCarpeta(this.mediador,
//				"Fichero de Sincronismo");
		this.jButton10 = new CBAbrirFichero(this.mediador,
				"Fichero de Sincronismo");
		
		// /* 88: */ this.jButton7 = new CBAbrirCarpeta(this.mediador,
		// "Resultado");

		/* 89:119 */GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		/* 90:120 */this.jPanel1.setLayout(jPanel1Layout);
		/* 91:121 */jPanel1Layout.setHorizontalGroup(
		/* 92:122 */jPanel1Layout.createParallelGroup(1)
		/* 93:123 */.add(jPanel1Layout.createSequentialGroup()
		/* 94:124 */.addContainerGap()
		/* 95:125 */.add(jPanel1Layout.createParallelGroup(1)
				.add(this.jLabel10)
		/* 96:126 */.add(this.jLabel4)
		/* 97:127 */.add(this.jLabel3)
		/* 98:128 */.add(this.jLabel2)
		/* 99:129 */.add(this.jLabel1))

		/* 100:130 */.addPreferredGap(0)
		/* 101:131 */.add(jPanel1Layout.createParallelGroup(1, false)
				.add(jTextField10, -2, 220, -2)
		/* 102:132 */.add(jTextField4, -2, 220, -2)
		/* 103:133 */.add(jTextField3, -2, 220, -2)
		/* 104:134 */.add(jTextField2, -2, 220, -2)
		/* 105:135 */.add(jTextField1, -2, 220, -2))

		/* 106:136 */.add(6, 6, 6)
		/* 107:137 */.add(jPanel1Layout.createParallelGroup(1, false)
		/* 108:138 */.add((CBAbrirCarpeta) this.jButton2, 0, 0, 32767)
		/* 109:139 */.add((CBAbrirCarpeta) this.jButton3, -2, 33, 32767)
		/* 110:140 */.add((CBAbrirCarpeta) this.jButton4, -2, 33, 32767)
					.add((CBAbrirFichero) this.jButton10, -2, 33, 32767)
		/* 111:141 */.add((CBAbrirCarpeta) this.jButton1, 0, 0, 32767))
		/* 112:142 */.addContainerGap(-1, 32767)));
		/* 113: */
		/* 114:144 */jPanel1Layout.setVerticalGroup(
		/* 115:145 */jPanel1Layout.createParallelGroup(1)
		/* 116:146 */.add(jPanel1Layout.createSequentialGroup()
		/* 117:147 */.addContainerGap()
		/* 118:148 */.add(jPanel1Layout.createParallelGroup(3)
		/* 119:149 */.add(this.jLabel1)
		/* 120:150 */.add(jTextField1, -2, -1, -2)
		/* 121:151 */.add((CBAbrirCarpeta) this.jButton1))
		/* 122:152 */.add(11, 11, 11)
		/* 123:153 */.add(jPanel1Layout.createParallelGroup(3)
		/* 124:154 */.add(this.jLabel2)
		/* 125:155 */.add(jTextField2, -2, -1, -2)
		/* 126:156 */.add((CBAbrirCarpeta) this.jButton2))
		/* 127:157 */.add(14, 14, 14)
		/* 128:158 */.add(jPanel1Layout.createParallelGroup(3)
		/* 129:159 */.add(this.jLabel3)
		/* 130:160 */.add(jTextField3, -2, -1, -2)
		/* 131:161 */.add((CBAbrirCarpeta) this.jButton3))
		/* 132:162 */.add(14, 14, 14)
		/* 133:163 */.add(jPanel1Layout.createParallelGroup(3)
		/* 134:164 */.add(this.jLabel4)
		/* 135:165 */.add(jTextField4, -2, -1, -2)
		/* 136:166 */.add((CBAbrirCarpeta) this.jButton4))
					.add(14, 14, 14)
		/* 133:163 */.add(jPanel1Layout.createParallelGroup(3)
		/* 134:164 */.add(this.jLabel10)
		/* 135:165 */.add(jTextField10, -2, -1, -2)
					.add((CBAbrirFichero) this.jButton10))
				.add(14, 14, 14)
		/* 137:167 */.addContainerGap(-1, 32767)));
		/* 138: */

		/* 141:171 */this.jButton5 = new CBAceptar(this.mediador,
				"AceptarConfiguracion");
		/* 142: */
		/* 143:173 */this.jButton6.setText("Cancelar");
		/* 144:174 */this.jButton6.addActionListener(new ActionListener()
		/* 145: */{
			/* 146: */public void actionPerformed(ActionEvent evt)
			/* 147: */{
				/* 148:176 */PreferenciasConfiguracion.this
						.jButton6ActionPerformed(evt);
				/* 149: */}
			/* 150:179 */
		});
		/* 151:180 */GroupLayout layout = new GroupLayout(getContentPane());
		/* 152:181 */getContentPane().setLayout(layout);
		/* 153:182 */layout.setHorizontalGroup(
		/* 154:183 */layout.createParallelGroup(1)
		/* 155:184 */.add(layout.createSequentialGroup()
		/* 156:185 */.add(layout.createParallelGroup(2)
		/* 157:186 */.add(layout.createSequentialGroup()
		/* 158:187 */.addContainerGap()
		/* 159:188 */.add(this.jButton6)
		/* 160:189 */.addPreferredGap(0)
		/* 161:190 */.add((CBAceptar) this.jButton5))
		/* 162:191 */.add(1, layout.createSequentialGroup()
		/* 163:192 */.addContainerGap()
		/* 164:193 */.add(this.jPanel1, -2, -1, -2)))
		/* 165:194 */.addContainerGap(11, 32767)));
		/* 166: */
		/* 167:196 */layout.setVerticalGroup(
		/* 168:197 */layout.createParallelGroup(1)
		/* 169:198 */.add(layout.createSequentialGroup()
		/* 170:199 */.addContainerGap()
		/* 171:200 */.add(this.jPanel1, -2, -1, -2)
		/* 172:201 */.add(21, 21, 21)
		/* 173:202 */.add(layout.createParallelGroup(3)
		/* 174:203 */.add((CBAceptar) this.jButton5)
		/* 175:204 */.add(this.jButton6))
		/* 176:205 */.addContainerGap(-1, 32767)));
		/* 177: */
		/* 178:207 */pack();
		/* 179: */}

	/* 180: */
	/* 181: */private void jButton6ActionPerformed(ActionEvent evt)
	/* 182: */{
		/* 183:217 */dispose();
		/* 184: */}

	/* 185: */
	/* 186: */public static void setCapturas(String aux)
	/* 187: */{
		/* 188:226 */jTextField1.setText(aux);
		/* 189: */}

	/* 190: */
	/* 191: */public static String getCapturas()
	/* 192: */{
		/* 193:235 */return jTextField1.getText();
		/* 194: */}

	/* 195: */
	/* 196: */public static void setExportaciones(String aux)
	/* 197: */{
		/* 198:244 */jTextField2.setText(aux);
		/* 199: */}

	/* 200: */
	/* 201: */public static String getExportaciones()
	/* 202: */{
		/* 203:253 */return jTextField2.getText();
		/* 204: */}

	/* 205: */
	/* 206: */public static void setScripts(String aux)
	/* 207: */{
		/* 208:262 */jTextField3.setText(aux);
		/* 209: */}

	/* 210: */
	/* 211: */public static String getScripts()
	/* 212: */{
		/* 213:271 */return jTextField3.getText();
		/* 214: */}

	/* 215: */
	/* 216: */public static void setParametrizacion(String aux)
	/* 217: */{
		/* 218:280 */jTextField4.setText(aux);
		/* 219: */}

	/* 220: */
	/* 221: */public static String getParametrizacion()
	/* 222: */{
		/* 223:289 */return jTextField4.getText();
		/* 224: */}

	
	/* 216: */public static void setSyncFile(String aux)
	/* 217: */{
		/* 218:280 */jTextField10.setText(aux);
		/* 219: */}

	/* 220: */
	/* 221: */public static String getSyncFile()
	/* 222: */{
		/* 223:289 */return jTextField10.getText();
		/* 224: */}
	
	
	
	
	/* 225: */
}

/*
 * Location: F:\jad\Sniffer-II\main\main\
 * 
 * Qualified Name: presentacion.preferencias.PreferenciasConfiguracion
 * 
 * JD-Core Version: 0.7.0.1
 */