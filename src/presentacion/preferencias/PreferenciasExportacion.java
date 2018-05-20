/*   1:    */ package presentacion.preferencias;
/*   2:    Aqui se crea la pantalla de elección de la captura .pcap a export a xml*/ 
/*   3:    */ import java.awt.Container;
/*   4:    */ import java.awt.Insets;
/*   5:    */ import java.awt.event.ActionEvent;
/*   6:    */ import java.awt.event.ActionListener;
/*   7:    */ import javax.swing.BorderFactory;
/*   8:    */ import javax.swing.ButtonGroup;
/*   9:    */ import javax.swing.JButton;
/*  10:    */ import javax.swing.JCheckBox;
/*  11:    */ import javax.swing.JDialog;
/*  12:    */ import javax.swing.JLabel;
/*  13:    */ import javax.swing.JPanel;
/*  14:    */ import javax.swing.JScrollPane;
/*  15:    */ import javax.swing.JTextArea;
/*  16:    */ import javax.swing.JTextField;
/*  17:    */ import org.jdesktop.layout.GroupLayout;
/*  18:    */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*  19:    */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*  20:    */ import presentacion.Mediador;
/*  21:    */ import presentacion.comandos.CBAbrirFichero;
/*  22:    */ import presentacion.comandos.CBAceptar;
/*  23:    */ import presentacion.comandos.CBGuardarFichero;
/*  24:    */ import presentacion.comandos.Comando;
/*  25:    */ import presentacion.propiedadesVentana.CentrarVentana;
/*  26:    */ /** 
 * Clase Preferencias. 
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com,  rsg0040@alu.ubu.es
 * @version 1.3 
*/
/*  27:    */ public class PreferenciasExportacion
/*  28:    */   extends JDialog
/*  29:    */ {
/*  30:    */   Mediador mediador;
/*  31:    */   private boolean tipo;
/*  32:    */   private String title;
/*  33:    */   private Comando jButtonOrigen;
/*  34:    */   private Comando jButtonDestino;
/*  35:    */   private Comando jButtonAbrir;
/*  36:    */   private Comando jButtonGuardar;
/*  37:    */   private Comando jButtonAceptar;
/*  38:    */   private ButtonGroup buttonGroup1;
/*  39:    */   private ButtonGroup buttonGroup2;
/*  40:    */   private ButtonGroup buttonGroup3;
/*  41:    */   private JButton jButtonCancelar;
/*  42:    */   private JLabel jLabel1;
/*  43:    */   private JLabel jLabel2;
/*  44:    */   private JPanel jPanel2;
/*  45:    */   private JScrollPane jScrollPane1;
/*  46:    */   private JTextArea jTextArea1;
/*  47:    */   private static JTextField jTextFieldDestino;
/*  48:    */   private static JTextField jTextFieldOrigen;
/*  49:    */   private static JCheckBox jCheckBox1;
/*  50:    */   
/*  51:    */   public PreferenciasExportacion()
/*  52:    */   {
/*  53: 72 */     super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Exportar desde fichero a XML", true);
/*  54: 73 */     this.mediador = new Mediador();
/*  55: 74 */     initComponents();
/*  56: 75 */     setResizable(false);
/*  57: 76 */     new CentrarVentana(this);
/*  58: 77 */     setVisible(true);
/*  59:    */   }
/*  60:    */   
/*  61:    */   public PreferenciasExportacion(boolean tipo, Mediador med)
/*  62:    */   {
/*  63: 88 */     super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Exportar desde fichero a XML", true);
/*  64: 89 */     this.mediador = med;
/*  65: 90 */     this.tipo = tipo;
/*  66: 91 */     if (tipo) {
/*  67: 92 */       this.title = "Exportar desde fichero a XML";
/*  68:    */     } else {
/*  69: 94 */       this.title = "Preferencias de Export";
/*  70:    */     }
/*  71: 95 */     initComponents();
/*  72: 96 */     setResizable(false);
/*  73: 97 */     new CentrarVentana(this);
/*  74:    */   }
/*  75:    */   
/*  76:    */   private void initComponents()
/*  77:    */   {
/*  78:108 */     this.buttonGroup1 = new ButtonGroup();
/*  79:109 */     this.jScrollPane1 = new JScrollPane();
/*  80:110 */     this.jTextArea1 = new JTextArea();
/*  81:111 */     this.buttonGroup2 = new ButtonGroup();
/*  82:112 */     this.buttonGroup3 = new ButtonGroup();
/*  83:113 */     this.jPanel2 = new JPanel();
/*  84:114 */     this.jLabel1 = new JLabel();
/*  85:115 */     jTextFieldOrigen = new JTextField();
/*  86:    */     
/*  87:117 */     this.jButtonOrigen = new CBAbrirFichero(this.mediador, "GenerarFromFileXML");
/*  88:118 */     this.jLabel2 = new JLabel();
/*  89:119 */     jTextFieldDestino = new JTextField();
/*  90:    */     
/*  91:121 */     this.jButtonDestino = new CBGuardarFichero(this.mediador, "GenerarFromFileXML");
/*  92:122 */     this.jButtonCancelar = new JButton();
/*  93:    */     
/*  94:124 */     jCheckBox1 = new JCheckBox();
/*  95:    */     
/*  96:126 */     this.jButtonAbrir = new CBAbrirFichero(this.mediador, "GenerarFromFileXMLOpenXML");
/*  97:127 */     this.jButtonGuardar = new CBGuardarFichero(this.mediador, "GenerarFromFileXMLSaveXML");
/*  98:130 */     if (this.tipo)
/*  99:    */     {
/* 100:131 */       this.jButtonAceptar = new CBAceptar(this.mediador, "GenerarXML");
/* 101:    */     }
/* 102:    */     else
/* 103:    */     {
/* 104:134 */       this.jButtonCancelar.setVisible(false);
/* 105:135 */       this.jButtonAceptar = new CBAceptar(this.mediador, "Salir");
/* 106:    */     }
/* 107:138 */     this.jTextArea1.setColumns(20);
/* 108:139 */     this.jTextArea1.setRows(5);
/* 109:140 */     this.jScrollPane1.setViewportView(this.jTextArea1);
/* 110:    */     
/* 111:142 */     setDefaultCloseOperation(2);
/* 112:143 */     setTitle(this.title);
/* 113:    */     
/* 114:145 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder("Datos"));
/* 115:146 */     this.jLabel1.setText("Origen: ");
/* 116:    */     
/* 117:148 */     this.jLabel2.setText("Destino: ");
/* 118:    */     
/* 119:150 */     jCheckBox1.setText("Asigno fichero");
/* 120:151 */     jCheckBox1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 121:152 */     jCheckBox1.setMargin(new Insets(0, 0, 0, 0));
/* 122:    */     
/* 123:154 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 124:155 */     this.jPanel2.setLayout(jPanel2Layout);
/* 125:156 */     jPanel2Layout.setHorizontalGroup(
/* 126:157 */       jPanel2Layout.createParallelGroup(1)
/* 127:158 */       .add(jPanel2Layout.createSequentialGroup()
/* 128:159 */       .addContainerGap()
/* 129:160 */       .add(jPanel2Layout.createParallelGroup(1)
/* 130:161 */       .add(jPanel2Layout.createSequentialGroup()
/* 131:162 */       .add(jPanel2Layout.createParallelGroup(1)
/* 132:163 */       .add(this.jLabel1)
/* 133:164 */       .add(this.jLabel2))
/* 134:165 */       .addPreferredGap(0)
/* 135:166 */       .add(jPanel2Layout.createParallelGroup(1)
/* 136:167 */       .add(jTextFieldOrigen, -2, 189, -2)
/* 137:168 */       .add(jTextFieldDestino, -1, 189, 32767))
/* 138:169 */       .addPreferredGap(0)
/* 139:170 */       .add(jPanel2Layout.createParallelGroup(1)
/* 140:171 */       .add((CBGuardarFichero)this.jButtonDestino, 0, 0, 32767)
/* 141:172 */       .add((CBAbrirFichero)this.jButtonOrigen, -2, 31, -2)))
/* 142:173 */       .add(jCheckBox1, -2, 106, -2))
/* 143:174 */       .addContainerGap()));
/* 144:    */     
/* 145:176 */     jPanel2Layout.setVerticalGroup(
/* 146:177 */       jPanel2Layout.createParallelGroup(1)
/* 147:178 */       .add(jPanel2Layout.createSequentialGroup()
/* 148:179 */       .addContainerGap()
/* 149:180 */       .add(jPanel2Layout.createParallelGroup(3)
/* 150:181 */       .add(this.jLabel1)
/* 151:182 */       .add((CBAbrirFichero)this.jButtonOrigen)
/* 152:183 */       .add(jTextFieldOrigen, -2, -1, -2))
/* 153:184 */       .add(18, 18, 18)
/* 154:185 */       .add(jPanel2Layout.createParallelGroup(3)
/* 155:186 */       .add(jTextFieldDestino, -2, -1, -2)
/* 156:187 */       .add(this.jLabel2)
/* 157:188 */       .add((CBGuardarFichero)this.jButtonDestino))
/* 158:189 */       .addPreferredGap(0, 19, 32767)
/* 159:190 */       .add(jCheckBox1)
/* 160:191 */       .addContainerGap()));
/* 161:    */     
/* 162:    */ 
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:    */ 
/* 168:    */ 
/* 169:    */ 
/* 170:    */ 
/* 171:    */ 
/* 172:    */ 
/* 173:    */ 
/* 174:    */ 
/* 175:    */ 
/* 176:    */ 
/* 177:    */ 
/* 178:    */ 
/* 179:    */ 
/* 180:    */ 
/* 181:    */ 
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:    */ 
/* 188:    */ 
/* 189:    */ 
/* 190:    */ 
/* 191:    */ 
/* 192:    */ 
/* 193:    */ 
/* 194:    */ 
/* 195:    */ 
/* 196:    */ 
/* 197:    */ 
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:    */ 
/* 202:    */ 
/* 203:    */ 
/* 204:    */ 
/* 205:    */ 
/* 206:    */ 
/* 207:    */ 
/* 208:239 */     this.jButtonCancelar.setText("Cancelar");
/* 209:240 */     this.jButtonCancelar.addActionListener(new ActionListener()
/* 210:    */     {
/* 211:    */       public void actionPerformed(ActionEvent evt)
/* 212:    */       {
/* 213:242 */         PreferenciasExportacion.this.jButtonCancelarActionPerformed(evt);
/* 214:    */       }
/* 215:256 */     });
/* 216:257 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 217:258 */     if (this.tipo)
/* 218:    */     {
/* 219:261 */       getContentPane().setLayout(layout);
/* 220:262 */       layout.setHorizontalGroup(
/* 221:263 */         layout.createParallelGroup(1)
/* 222:264 */         .add(layout.createSequentialGroup()
/* 223:265 */         .add(layout.createParallelGroup(2)
/* 224:266 */         .add(layout.createSequentialGroup()
/* 225:267 */         .addContainerGap(161, 32767)
/* 226:268 */         .add(this.jButtonCancelar)
/* 227:269 */         .add(18, 18, 18)
/* 228:270 */         .add((CBAceptar)this.jButtonAceptar))
/* 229:271 */         .add(1, layout.createSequentialGroup()
/* 230:272 */         .addContainerGap()
/* 231:273 */         .add(this.jPanel2, -2, -1, -2)))
/* 232:274 */         .addContainerGap()));
/* 233:    */       
/* 234:276 */       layout.setVerticalGroup(
/* 235:277 */         layout.createParallelGroup(1)
/* 236:278 */         .add(layout.createSequentialGroup()
/* 237:279 */         .addContainerGap()
/* 238:280 */         .add(this.jPanel2, -2, -1, -2)
/* 239:281 */         .addPreferredGap(0)
/* 240:282 */         .add(layout.createParallelGroup(3)
/* 241:283 */         .add(this.jButtonCancelar)
/* 242:284 */         .add((CBAceptar)this.jButtonAceptar))
/* 243:285 */         .addContainerGap()));
/* 244:    */     }
/* 245:    */     else
/* 246:    */     {
/* 247:294 */       getContentPane().setLayout(layout);
/* 248:295 */       layout.setHorizontalGroup(
/* 249:296 */         layout.createParallelGroup(1)
/* 250:297 */         .add(layout.createSequentialGroup()
/* 251:298 */         .addContainerGap()
/* 252:299 */         .add(this.jPanel2, -2, -1, -2)
/* 253:300 */         .addContainerGap())
/* 254:301 */         .add(2, layout.createSequentialGroup()
/* 255:302 */         .addContainerGap(164, 32767)
/* 256:303 */         .add(layout.createParallelGroup(1, false)
/* 257:304 */         .add(2, layout.createSequentialGroup()
/* 258:305 */         .add((CBGuardarFichero)this.jButtonGuardar)
/* 259:306 */         .addPreferredGap(0, -1, 32767)
/* 260:307 */         .add((CBAbrirFichero)this.jButtonAbrir, -2, 74, -2))
/* 261:308 */         .add(2, layout.createSequentialGroup()
/* 262:309 */         .add(this.jButtonCancelar)
/* 263:310 */         .add(15, 15, 15)
/* 264:311 */         .add((CBAceptar)this.jButtonAceptar)))
/* 265:312 */         .addContainerGap()));
/* 266:    */       
/* 267:314 */       layout.setVerticalGroup(
/* 268:315 */         layout.createParallelGroup(1)
/* 269:316 */         .add(layout.createSequentialGroup()
/* 270:317 */         .addContainerGap()
/* 271:318 */         .add(this.jPanel2, -2, -1, -2)
/* 272:319 */         .addPreferredGap(0)
/* 273:320 */         .add(layout.createParallelGroup(3)
/* 274:321 */         .add((CBAbrirFichero)this.jButtonAbrir)
/* 275:322 */         .add((CBGuardarFichero)this.jButtonGuardar))
/* 276:323 */         .addPreferredGap(0, 14, 32767)
/* 277:324 */         .add(layout.createParallelGroup(3)
/* 278:325 */         .add(this.jButtonCancelar)
/* 279:326 */         .add((CBAceptar)this.jButtonAceptar))
/* 280:327 */         .addContainerGap()));
/* 281:    */     }
/* 282:330 */     pack();
/* 283:    */   }
/* 284:    */   
/* 285:    */   private void jButton3ActionPerformed(ActionEvent evt)
/* 286:    */   {
/* 287:339 */     dispose();
/* 288:    */   }
/* 289:    */   
/* 290:    */   private void jButtonCancelarActionPerformed(ActionEvent evt)
/* 291:    */   {
/* 292:348 */     dispose();
/* 293:    */   }
/* 294:    */   
/* 295:    */   public static String getOrigen()
/* 296:    */   {
/* 297:356 */     return jTextFieldOrigen.getText();
/* 298:    */   }
/* 299:    */   
/* 300:    */   public static void setOrigen(String aux)
/* 301:    */   {
/* 302:364 */     jTextFieldOrigen.setText(aux);
/* 303:    */   }
/* 304:    */   
/* 305:    */   public static String getDestino()
/* 306:    */   {
/* 307:372 */     return jTextFieldDestino.getText();
/* 308:    */   }
/* 309:    */   
/* 310:    */   public static void setDestino(String aux)
/* 311:    */   {
/* 312:379 */     jTextFieldDestino.setText(aux);
/* 313:    */   }
/* 314:    */   
/* 315:    */   public static boolean getMultiFile()
/* 316:    */   {
/* 317:387 */     if (jCheckBox1.isSelected()) {
/* 318:387 */       return false;
/* 319:    */     }
/* 320:388 */     return true;
/* 321:    */   }
/* 322:    */   
/* 323:    */   public static void setMultiFile(boolean aux)
/* 324:    */   {
/* 325:396 */     if (aux) {
/* 326:396 */       jCheckBox1.setSelected(false);
/* 327:    */     } else {
/* 328:397 */       jCheckBox1.setSelected(true);
/* 329:    */     }
/* 330:    */   }
/* 331:    */ }


/* Location:           F:\jad\Sniffer-II\main\main\
 * Qualified Name:     presentacion.preferencias.PreferenciasExportacion
 * JD-Core Version:    0.7.0.1
 */