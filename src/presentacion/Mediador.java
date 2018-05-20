package presentacion;

import dominio.FachadaDominio;
import dominio.pcap.Insercion;
import dominio.pcapDumper.SaveTime;
import dominio.preferences.preferencesBeanCapture;
import dominio.preferences.preferencesBeanDefinicion;
import dominio.preferences.preferencesBeanExport;
import dominio.preferences.preferencesBeanExportInsercion;
import dominio.preferences.preferencesBeanFromFile;
import dominio.preferences.preferencesBeanMeta;
import dominio.properties.PropertiesBeanSniffer;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.jnetpcap.packet.PcapPacket;

import presentacion.avisos.Aviso;
import presentacion.capturandoDumper.ControlHilos;
import presentacion.capturandoDumper.Fcaptura;
import presentacion.capturandoDumper.TimePacket;
import presentacion.comandos.Comando;
import presentacion.preferencias.PreferenciasCaptura;
import presentacion.preferencias.PreferenciasConfiguracion;
import presentacion.preferencias.PreferenciasDefinicion;
import presentacion.preferencias.PreferenciasExportacion;
import presentacion.preferencias.PreferenciasFromFile;
import presentacion.preferencias.PreferenciasGenerarBat;
import presentacion.preferencias.PreferenciasInsercion;
import presentacion.preferencias.PreferenciasInsercionCapturados; //import presentacion.preferencias.PreferenciasIntruso;
import presentacion.preferencias.PreferenciasVisualizarInsercion;
import presentacion.propiedadesVentana.CentrarVentana;
import presentacion.seleccionFicheros.VentanaAbrirElegirFichero;
import presentacion.seleccionFicheros.VentanaElegirDirectorios;
import presentacion.seleccionFicheros.VentanaGuardarElegirFichero;
import presentacion.ventanaMenuSniffer.BarraHerramientas;
import presentacion.ventanaMenuSniffer.BarraMenu;
import presentacion.ventanaMenuSniffer.MenuSniffer;
import servicioAccesoDatos.FabricaAccesoDatos;
import servicioAccesoDatos.FabricaAccesoDatosIF;
import servicioAccesoDatos.FachadaFichero;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Clase Mediador, Gestiona las aperturas, cargas y visualizaciones de ficheros.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */
public class Mediador implements ActionListener, ItemListener,
		ListSelectionListener, TreeSelectionListener, WindowListener {
	private MenuSniffer MSniffer;
	private Fcaptura FCaptura;

	Thread hilo;

	public void actionPerformed(ActionEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void valueChanged(TreeSelectionEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void itemStateChanged(ItemEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void valueChanged(ListSelectionEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowDeactivated(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowActivated(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowIconified(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowDeiconified(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowClosing(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowClosed(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowOpening(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void windowOpened(WindowEvent e) {
		Comando cmd = (Comando) e.getSource();
		cmd.ejecutar();
	}

	public void actualizaTfRuta(String fRuta, String ventana) {
		if (ventana.equals("Capturas")) {
			PreferenciasConfiguracion.setCapturas(fRuta);
		}
		if (ventana.equals("Exportaciones")) {
			PreferenciasConfiguracion.setExportaciones(fRuta);
		}
		if (ventana.equals("Paremetrizacion")) {
			PreferenciasConfiguracion.setParametrizacion(fRuta);
		}
		if (ventana.equals("Scripts")) {
			PreferenciasConfiguracion.setScripts(fRuta);
		}
		if (ventana.equals("Fichero de Sincronismo")) {
			PreferenciasConfiguracion.setSyncFile(fRuta);
		}
		// if (ventana.equals("Resultado")) {
		// PreferenciasConfiguracion.setIntruso(fRuta);
		// }
		// if (ventana.equals("CapturaIntrusos")) {
		// PreferenciasIntruso.setOrigen(fRuta);
		// }
		// if (ventana.equals("Intrusos")) {
		// PreferenciasIntruso.setOrigen2(fRuta);
		// }
		// if (ventana.equals("ResultadoIntrusos")) {
		// PreferenciasIntruso.setDestino(fRuta);
		// }

	}

	public void cerrarVentana(Frame ventana) {
		ventana.dispose();
	}

	public Frame getVentanaPresentacion() {
		Frame frame = VentanaPresentacion.getFrames()[0];
		return frame;
	}

	public void cerrarVentana(Window ventana) {
		ventana.dispose();
	}

	public Frame getVentanaMenuSniffer() {
		Frame frame = MenuSniffer.getFrames()[1];
		return frame;
	}

	public Fcaptura getFCapture() {
		return this.FCaptura;
	}

	public void repaintVentana(Frame ventana) {
		ventana.show();
	}

	public boolean irAventana(String nombreBoton) {
		if (nombreBoton.equals("Salir")) {
			new VentanaSalir(this);
			return true;
		}
		if (nombreBoton.equals("Sniffer")) {
			this.MSniffer = new MenuSniffer(this);
			this.MSniffer.resize(this.MSniffer.WinWidth,
					this.MSniffer.WinHeight);

			new CentrarVentana(this.MSniffer);

			this.MSniffer.setVisible(true);

			return false;
		}
		return true;
	}

	public void savePcapFileCaptured(String Destino) {
		int aux = FachadaDominio.savePcapToFile(Destino);
		if (aux == 0) {
			setPanelEstado("Fichero Capturado guardado correctamente...");
		} else {
			setPanelEstado("No se ha podido guardar el fichero capturado");
		}
	}

	public void irInicioCapture(boolean tipo) {
		setPanelEstado("Iniciando preconfiguracion para captura de datos..");
		PreferenciasCaptura prefCap = new PreferenciasCaptura(tipo, this);
		PrefCapLlenarDispositivos();
		PrefCapLlenarDatos();
		prefCap.setVisible(true);
	}

	public void irInicioInsert(boolean tipo) {
		setPanelEstado("Insercion Paquetes Definidos");
		PreferenciasInsercion prefIns = new PreferenciasInsercion();
		PrefCapLlenarDispositivosInsercion();
		prefIns.setVisible(true);
	}

	public void irInicioInsertDef(boolean tipo) {
		setPanelEstado("Insercion Paquetes Definidos");
		PreferenciasInsercion prefIns = new PreferenciasInsercion();
		PrefCapLlenarDispositivosInsercion();
		prefIns.setVisible(true);
	}

	public void irInicioInsertCap(boolean tipo) {
		setPanelEstado("Insercion Paquetes Capturados");
		PreferenciasInsercionCapturados prefIns = new PreferenciasInsercionCapturados();
		PrefCapLlenarDispositivosInsercionCap();
		prefIns.setVisible(true);
	}

	/* Llamada a la constructor de la venta para la busqueda del intruso */
	// public void irProcesoIntruso(){
	//
	// setPanelEstado("Busqueda de Intrusos");
	// prefInt = new PreferenciasIntruso();
	// //prefInt.setVisible(true);
	//
	// }
	//
	// public void CargarDatosPantallaIntruso(){
	// PrefConfLlenarDatos("Intruso");
	// }
	//
	// public void saveFileIntrusos() {
	//
	// boolean aux =
	// FachadaDominio.FachadaSaveFileIntrusos(prefInt.getOrigen(),prefInt.getOrigen2(),prefInt.getDestino());
	// if (aux) {
	// setPanelEstado("Fichero Procesado y guardado correctamente...");
	// lanzaraviso("Fichero Procesado y guardado correctamente...",
	// "Terminado");
	// } else {
	// setPanelEstado("No se ha podido Procesar el fichero Intrusos");
	// }
	// }
	public void irInicioCaptureFromFile(boolean tipo) {
		setPanelEstado("Iniciando preconfiguracin para captura de datos..");
		PreferenciasFromFile prefCapFromFile = new PreferenciasFromFile(tipo,
				this);
		PrefCapLlenarDatosFromFile();
		prefCapFromFile.setVisible(true);
	}

	public void irAExportFromFile(boolean tipo) {
		setPanelEstado("Iniciando preconfiguracin para exportacin de datos..");

		PreferenciasExportacion prefExp = new PreferenciasExportacion(tipo,
				this);
		PrefExpLlenarDatos();
		prefExp.setVisible(true);
	}

	public void leerProperties(MenuSniffer aux) {
		PropertiesBeanSniffer pBS = FachadaDominio.getPropBeanSniffer();
		aux.WinX = Integer.parseInt(pBS.getWinX());
		aux.WinY = Integer.parseInt(pBS.getWinY());
		aux.WinHeight = Integer.parseInt(pBS.getWinHeight());
		aux.WinWidth = Integer.parseInt(pBS.getWinWidth());
	}

	public String leerPropertiesTableView() {
		PropertiesBeanSniffer pBS = FachadaDominio.getPropBeanSniffer();
		String aux = pBS.getTableView();
		return aux;
	}

	public void grabarProperties() {
		String aux = this.MSniffer.getColumPosition();
		FachadaDominio.setPropertiesColumns(this.MSniffer.getColumPosition());
		FachadaDominio.grabarProperties();
	}

	public void setPrefCapFichero(String aux) {
		PreferenciasCaptura.setFichero(aux);
	}

	public void setPrefCapFicheroFromFileSource(String aux) {
		PreferenciasFromFile.setFromFile(aux);
	}

	public void setPrefCapFicheroFromFileFichero(String aux) {
		PreferenciasFromFile.setFichero(aux);
	}

	public void setPrefGenFicheroPref(String aux) {
		PreferenciasGenerarBat.setFicheroPref(aux);
	}

	public void setPrefGenFicheroBat(String aux) {
		PreferenciasGenerarBat.setFicheroBat(aux);
	}

	public void setExportFromFilePref(String aux) {
		PreferenciasExportacion.setOrigen(aux);
	}

	public void setExportFromFileXML(String aux) {
		PreferenciasExportacion.setDestino(aux);
	}

	public void PrefLeerPreferencias() {
	}

	public void setFileReaded(String fReaded) {
		FachadaDominio.setFileReaded(fReaded);
	}

	public void PropLeerProperties() {
	}

	public void PrefCapLlenarDatos() {
		try {
			preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();

			PreferenciasCaptura.setDispositivo(pBC.getCapDispositive());
			PreferenciasCaptura.setFichero(pBC.getFilLocate());

			PreferenciasCaptura.setPromiscuo(pBC.getCapPromiscuousMode());

			PreferenciasCaptura.setHost(pBC.getCapHost());
			PreferenciasCaptura.setProtocolo(pBC.getCapProtocol());
			PreferenciasCaptura.setPuerto(pBC.getCapPort());

			PreferenciasCaptura.setAvanzado(pBC.getCapAdvance());
			if (pBC.getCapAdvanceId()) {
				PreferenciasCaptura.setAvanzado(true);
			} else {
				PreferenciasCaptura.setAvanzado(false);
			}
			PreferenciasCaptura.setProximoEspacioTipo(pBC.getFilSpaceType());
			PreferenciasCaptura.setProximoEspacio(pBC.getFilSpaceId());
			PreferenciasCaptura.setProximoEspacio(pBC.getFilSpace());
			PreferenciasCaptura.setProximoTiempoTipo(pBC.getFilTimeType());
			PreferenciasCaptura.setProximoTiempo(pBC.getFilTimeId());
			PreferenciasCaptura.setProximoTiempo(pBC.getFilTime());
			PreferenciasCaptura.setPila(pBC.getFilRingBufferId());
			// Modificación 2014
			PreferenciasCaptura.setPilaXML(pBC.getFilRingBufferXML());
			PreferenciasCaptura.setNFSsync(pBC.getNFSsync());
			PreferenciasCaptura.setPila(pBC.getFilRingBuffer());
			PreferenciasCaptura.setStop(pBC.getFilStopAfterId());
			PreferenciasCaptura.setStop(pBC.getFilStopAfter());

			PreferenciasCaptura.setDespuesPaquetes(pBC.getstpAfterPacketsId());
			PreferenciasCaptura.setDespuesPaquetes(pBC.getstpAfterPackets());

			PreferenciasCaptura.setDespuesEspacio(pBC.getstpAfterSpaceId());
			PreferenciasCaptura.setDespuesEspacio(pBC.getstpAfterSpace());
			PreferenciasCaptura.setDespuesEspacioTipo(pBC
					.getstpAfterSpaceType());

			PreferenciasCaptura.setDespuesTiempo(pBC.getstpAfterTimeId());
			PreferenciasCaptura.setDespuesTiempo(pBC.getstpAfterTime());
			PreferenciasCaptura.setDespuesTiempoTipo(pBC.getstpAfterTimeType());

			PreferenciasCaptura
					.setMultiplesFicheros(pBC.getFilMultipleFileId());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PrefCapLlenarDatosFromFile() {
		try {
			preferencesBeanFromFile pBFF = FachadaDominio.getPrefBeanFromFile();

			PreferenciasFromFile.setFromFile(pBFF.getffCapSource());
			PreferenciasFromFile.setFichero(pBFF.getffFilLocate());

			PreferenciasFromFile.setHost(pBFF.getffCapHost());
			PreferenciasFromFile.setProtocolo(pBFF.getffCapProtocol());
			PreferenciasFromFile.setPuerto(pBFF.getffCapPort());

			PreferenciasFromFile.setAvanzado(pBFF.getffCapAdvance());
			if (pBFF.getffCapAdvanceId()) {
				PreferenciasFromFile.setAvanzado(true);
			} else {
				PreferenciasFromFile.setAvanzado(false);
			}
			PreferenciasFromFile
					.setProximoEspacioTipo(pBFF.getffFilSpaceType());
			PreferenciasFromFile.setProximoEspacio(pBFF.getffFilSpaceId());
			PreferenciasFromFile.setProximoEspacio(pBFF.getffFilSpace());

			PreferenciasFromFile.setPila(pBFF.getffFilRingBufferId());
			// Modificación 2014
			// PreferenciasFromFile.setPilaXML(pBFF.getffFilRingBufferXML());
			PreferenciasFromFile.setPila(pBFF.getffFilRingBuffer());
			PreferenciasFromFile.setStop(pBFF.getffFilStopAfterId());
			PreferenciasFromFile.setStop(pBFF.getffFilStopAfter());

			PreferenciasFromFile.setDespuesPaquetes(pBFF
					.getffStpAfterPacketsId());
			PreferenciasFromFile
					.setDespuesPaquetes(pBFF.getffStpAfterPackets());

			PreferenciasFromFile.setMultiplesFicheros(pBFF
					.getffFilMultipleFileId());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PrefCapLlenarDispositivos() {
		PreferenciasCaptura.addDispositivo(
				FachadaDominio.getCapDispositivosPcapLibDes(),
				FachadaDominio.getCapDispositivosPcapLibDes());
	}

	public void PrefCapLlenarDispositivosInsercion() {
		PreferenciasInsercion.addDispositivo(
				FachadaDominio.getCapDispositivosPcapLibDes(),
				FachadaDominio.getCapDispositivosPcapLibDes());
	}

	public void PrefCapLlenarDispositivosInsercionCap() {
		PreferenciasInsercionCapturados.addDispositivo(
				FachadaDominio.getCapDispositivosPcapLibDes(),
				FachadaDominio.getCapDispositivosPcapLibDes());
	}

	public void PrefExpLlenarDatos() {
		try {
			preferencesBeanExport pBE = FachadaDominio.getPrefBeanExport();

			PreferenciasExportacion.setOrigen(pBE.getExpSource());
			PreferenciasExportacion.setDestino(pBE.getExpDestination());
			PreferenciasExportacion.setMultiFile(pBE.getExpMultifile());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PrefConfLlenarDatos() {
		try {
			PreferenciasConfiguracion
					.setParametrizacion(FachadaFicheroDirectorios
							.getdirectorio("DIR_PARAMETRIZACION"));
			PreferenciasConfiguracion.setScripts(FachadaFicheroDirectorios
					.getdirectorio("DIR_SCRIPTS"));
			PreferenciasConfiguracion.setCapturas(FachadaFicheroDirectorios
					.getdirectorio("DIR_CAPTURAS"));
			PreferenciasConfiguracion
					.setExportaciones(FachadaFicheroDirectorios
							.getdirectorio("DIR_EXPORTACIONES"));
			PreferenciasConfiguracion
					.setSyncFile(FachadaFicheroDirectorios
							.getdirectorio("FILE_FICHEROSINCRONIZADO"));
			// PreferenciasConfiguracion.setIntruso(FachadaFicheroDirectorios.getdirectorio("DIR_INTRUSO"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void PrefConfLlenarDatos(String operacion) {
	// try {
	// if (operacion=="Intruso"){
	// PreferenciasIntruso.setOrigen(FachadaFicheroDirectorios.getdirectorio("DIR_EXPORTACIONES"));
	// PreferenciasIntruso.setOrigen2(FachadaFicheroDirectorios.getdirectorio("DIR_DEFINICIONES"));
	// PreferenciasIntruso.setDestino(FachadaFicheroDirectorios.getdirectorio("DIR_INTRUSO"));
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public void PrefDefLlenarDatos(String ruta) {
		try {
			preferencesBeanDefinicion pBD = FachadaDominio
					.getPrefBeanDefinicion();

			PreferenciasDefinicion.setRFCProtocolo(pBD.getRFCProtocolo());
			PreferenciasDefinicion.setNombreProtocolo(pBD.getNomProtocolo());
			PreferenciasDefinicion
					.setCamposClaveProtocolo(pBD.getCamposClave());
			PreferenciasDefinicion.setNivelProtocolo(pBD.getNivelProtocolo());
			PreferenciasDefinicion.setCamposProtocolo(pBD.getNumCampos());
			PreferenciasDefinicion.setFicheroCapturas(ruta);

			PreferenciasDefinicion.setTablaCampos(pBD.getTabla());
			PreferenciasDefinicion.refrescar();
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PrefDefLlenarDatosInsercion(String ruta) {
		try {
			preferencesBeanDefinicion pBDefIns = FachadaDominio
					.getPrefBeanDefinicion();

			PreferenciasInsercion.setRFCProtocolo(pBDefIns.getRFCProtocolo());
			PreferenciasInsercion
					.setNombreProtocolo(pBDefIns.getNomProtocolo());
			PreferenciasInsercion.setNivelProtocolo(String.valueOf(pBDefIns
					.getNivelProtocolo()));
			PreferenciasInsercion.setFicheroDefinicion(ruta);

			PreferenciasInsercion.setTablaCampos(pBDefIns.getTabla());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PrefCapCogerDatos() {
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();

		pBC.setFilLocate(PreferenciasCaptura.getFichero());
		pBC.setCapDispositive(PreferenciasCaptura.getDispositivo());
		pBC.setCapPromiscuousMode(PreferenciasCaptura.getPromiscuo());
		pBC.setCapNormal(true);
		pBC.setCapHost(PreferenciasCaptura.getHost());
		pBC.setCapProtocol(PreferenciasCaptura.getProtocolo());
		pBC.setCapPort(PreferenciasCaptura.getPuerto());
		pBC.setCapAdvanceId(PreferenciasCaptura.getAvanzado());
		pBC.setCapAdvance(PreferenciasCaptura.getAvanzadoDato());

		pBC.setFilSpaceId(PreferenciasCaptura.getProximoEspacio());
		pBC.setFilSpaceType(PreferenciasCaptura.getProximoEspacioTipo());
		pBC.setFilSpace(PreferenciasCaptura.getProximoEspacioDato());

		pBC.setFilTimeId(PreferenciasCaptura.getProximoTiempo());
		pBC.setFilTimeType(PreferenciasCaptura.getProximoTiempoTipo());
		pBC.setFilTime(PreferenciasCaptura.getProximoTiempoDato());

		pBC.setFilRingBufferId(PreferenciasCaptura.getPila()); // Capturar en
																// pila SI/NO

		pBC.setFilRingBuffer(PreferenciasCaptura.getPilaDato()); // Número de
																	// ficheros
																	// de la
																	// pila
		pBC.setFilRingBufferXML(PreferenciasCaptura.getPilaXML()); // Captura
																	// XML
																	// circular
																	// SI/NO
		pBC.setNFSsync(PreferenciasCaptura.getNFSsync());// Capturar con el
															// fichero de
															// sincronización

		pBC.setFilStopAfterId(PreferenciasCaptura.getStop());
		pBC.setFilStopAfter(PreferenciasCaptura.getStopDato());

		pBC.setstpAfterPacketsId(PreferenciasCaptura.getDespuesPaquetes());
		pBC.setstpAfterPackets(PreferenciasCaptura.getDespuesPaquetesDato());
		pBC.setstpAfterSpaceId(PreferenciasCaptura.getDespuesEspacio());

		pBC.setstpAfterSpaceType(PreferenciasCaptura.getDespuesEspacioTipo());
		pBC.setstpAfterSpace(PreferenciasCaptura.getDespuesEspacioDato());
		pBC.setstpAfterTimeId(PreferenciasCaptura.getDespuesTiempo());

		pBC.setstpAfterTimeType(PreferenciasCaptura.getDespuesTiempoTipo());
		pBC.setstpAfterTime(PreferenciasCaptura.getDespuesTiempoDato());

		pBC.setFilMultipleFileId(PreferenciasCaptura.getMultiplesFicheros());
	}

	public void PrefCapCogerDatosFromFile() {
		preferencesBeanFromFile pBFF = FachadaDominio.getPrefBeanFromFile();

		pBFF.setffCapSource(PreferenciasFromFile.getFromFile());
		pBFF.setffFilLocate(PreferenciasFromFile.getFichero());

		pBFF.setffCapNormal(true);
		pBFF.setffCapHost(PreferenciasFromFile.getHost());
		pBFF.setffCapProtocol(PreferenciasFromFile.getProtocolo());
		pBFF.setffCapPort(PreferenciasFromFile.getPuerto());
		pBFF.setffCapAdvanceId(PreferenciasFromFile.getAvanzado());
		pBFF.setffCapAdvance(PreferenciasFromFile.getAvanzadoDato());

		pBFF.setffFilSpaceId(PreferenciasFromFile.getProximoEspacio());
		pBFF.setffFilSpaceType(PreferenciasFromFile.getProximoEspacioTipo());
		pBFF.setffFilSpace(PreferenciasFromFile.getProximoEspacioDato());

		pBFF.setffFilRingBufferId(PreferenciasFromFile.getPila());

		pBFF.setffFilRingBuffer(PreferenciasFromFile.getPilaDato());
		// pBFF.setFilRingBufferXML(PreferenciasCaptura.getPilaXML()); //Captura
		// XML circular SI/NO

		pBFF.setffFilStopAfterId(PreferenciasFromFile.getStop());
		pBFF.setffFilStopAfter(PreferenciasFromFile.getStopDato());

		pBFF.setffStpAfterPacketsId(PreferenciasFromFile.getDespuesPaquetes());
		pBFF.setffStpAfterPackets(PreferenciasFromFile.getDespuesPaquetesDato());

		pBFF.setffFilMultipleFileId(PreferenciasFromFile.getMultiplesFicheros());
	}

	public void PrefExpCogerDatos() {
		preferencesBeanExport pBE = FachadaDominio.getPrefBeanExport();

		pBE.setExpSource(PreferenciasExportacion.getOrigen());
		pBE.setExpDestination(PreferenciasExportacion.getDestino());
		pBE.setExpMultifile(PreferenciasExportacion.getMultiFile());
		pBE.setExpStatistics(false);
		pBE.setExpType("XML");
	}

	/**
	 * Metodo donde cargan los directorios por defecto, capturas,
	 * exportatciones, parametrizacion, scripts.
	 * 
	 * @param sin
	 *            valor de entrada
	 * @return sin valor de retorno
	 */
	public void PrefConfCogerDatos() {
		FachadaFicheroDirectorios.setdirectorio("DIR_CAPTURAS",
				PreferenciasConfiguracion.getCapturas());
		FachadaFicheroDirectorios.setdirectorio("DIR_EXPORTACIONES",
				PreferenciasConfiguracion.getExportaciones());
		FachadaFicheroDirectorios.setdirectorio("DIR_PARAMETRIZACION",
				PreferenciasConfiguracion.getParametrizacion());
		FachadaFicheroDirectorios.setdirectorio("DIR_SCRIPTS",
				PreferenciasConfiguracion.getScripts());
		FachadaFicheroDirectorios.setdirectorio("FILE_FICHEROSINCRONIZADO",
				PreferenciasConfiguracion.getSyncFile());

	}

	public void PrefCapGuardarXML(int estate, String ruta) {
		PrefCapCogerDatos();
		FachadaDominio.savePreferences(estate, ruta);
	}

	public void PrefCapGuardarXMLFromFile(int estate, String ruta) {
		PrefCapCogerDatosFromFile();
		FachadaDominio.savePreferences(estate, ruta);
	}

	public void PrefCapLeerXML(String ruta) {
		FachadaDominio.setPreferencesCapture(ruta);
		FachadaDominio.getPreferencesCapture(true);
		PrefCapLlenarDatos();
	}

	public void PrefExpLeerXML(String ruta) {
		FachadaDominio.setPreferencesExport(ruta);
		FachadaDominio.getPreferencesExport();
		PrefExpLlenarDatos();
	}

	public void PrefDefLeerXML(String ruta) {
		FachadaDominio.setPreferencesDefinicion(ruta);
		FachadaDominio.getPreferencesDefinicion();
		PrefDefLlenarDatos(ruta);
	}

	public void PrefDefLeerXMLInsercion(String ruta) {
		FachadaDominio.setPreferencesDefinicion(ruta);
		FachadaDominio.getPreferencesDefinicion();
		PrefDefLlenarDatosInsercion(ruta);
	}

	public void PrefExpGuardarXML(int estate, String ruta) {
		PrefExpCogerDatos();
		FachadaDominio.savePreferences(2, ruta);
	}

	public void PrefFFLeerXML(String ruta) {
		FachadaDominio.setPreferencesFromFile(ruta);
		FachadaDominio.getPreferencesFromFile();
		PrefCapLlenarDatosFromFile();
	}

	public void PrefDefGuardarXML(int estate, String ruta) {
		try {
			preferencesBeanDefinicion pBD = FachadaDominio
					.getPrefBeanDefinicion();

			prefDefCogerDatos(pBD);
			FachadaDominio.savePreferencesDefinicion(estate, ruta, pBD);
		} catch (NullPointerException e) {
			System.out
					.println("Error recogida preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prefDefCogerDatos(preferencesBeanDefinicion aux) {
		aux.setNomProtocolo(PreferenciasDefinicion.getNombreProtocolo());
		aux.setNumCampos(PreferenciasDefinicion.getNumCamposProtocolo());
		aux.setRFCProtocolo(PreferenciasDefinicion.getRFCProtocolo());
		aux.setCamposClave(PreferenciasDefinicion.getCamposClave());
		aux.setNivelProtocolo(PreferenciasDefinicion.getNivelProtocolo());

		aux.setTabla(PreferenciasDefinicion.getTabla());
	}

	public void AbrirFicheroCaptura(String ruta) {
		System.out.println("ABRIR  => " + ruta);
		this.MSniffer.abrirFichero(ruta);

		repaintVentana(getVentanaMenuSniffer());

		this.MSniffer.recargarDatos();
	}

	public void GenerarBatCaptura() {
		String sPref = PreferenciasGenerarBat.getFicheroPref();
		String sBat = PreferenciasGenerarBat.getFicheroBat();
		String sMvm = PreferenciasGenerarBat.getMvM();
		String sTipo = PreferenciasGenerarBat.getTipo();
		String sSO = PreferenciasGenerarBat.getSO();
		try {
			System.out.println("Pref => " + sPref);
			System.out.println("Bat => " + sBat);
			System.out.println("Tipo => " + sTipo);
			System.out.println("Mvm => " + sMvm);
			System.out.println("SO => " + sSO);
			FachadaDominio.setFileBat(sPref, sBat, sTipo, sMvm, sSO);
		} catch (Exception localException) {
		}
	}

	public void GenerarXMLFromFile() {
		PrefExpCogerDatos();

		String origen = PreferenciasExportacion.getOrigen();
		String destino = PreferenciasExportacion.getDestino();
		boolean mutifile = PreferenciasExportacion.getMultiFile();

		FachadaDominio.saveMetaOrPcapToXML(origen, destino, mutifile);

	}

	public void GenerarXMLProtocolo() {
		PrefExpCogerDatos();

		String origen = PreferenciasExportacion.getOrigen();
		String destino = PreferenciasExportacion.getDestino();
		boolean mutifile = PreferenciasExportacion.getMultiFile();

		FachadaDominio.saveMetaOrPcapToXML(origen, destino, mutifile);
	}

	public void irAGenerarBat() {
		new PreferenciasGenerarBat(this);
	}

	public void AbrirDespuesCaptura() {
		preferencesBeanMeta pBM = FachadaDominio.getPrefBeanMeta();
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();
		String sNameFile = "";
		String sPathFile = "";
		if (pBM.getMetLocRelativeId()) {
			sNameFile = pBM.getMetLocRelative();
			sPathFile = "";
		}
		if (pBM.getMetLocAbsotuteId()) {
			sNameFile = pBM.getMetLocAbsotuteName();
			sPathFile = pBM.getMetLocAbsotutePath();
		}
		String sNamePathFile;
		if ((pBM.getMetMultipleFileId()) || (pBC.getstpAfterSpaceId())
				|| (pBC.getstpAfterTimeId())) {
			sNamePathFile = sPathFile + System.getProperty("file.separator")
					+ sNameFile + "_" + pBM.getMetMFEnd();
		} else {
			sNamePathFile = sPathFile + System.getProperty("file.separator")
					+ sNameFile;
		}
		sNamePathFile = sNamePathFile + ".pcap";

		AbrirFicheroCaptura(sNamePathFile);

		setPanelEstado("Fichero Abierto => " + sNamePathFile);
	}

	public void freePantallaCaptura() {
		this.MSniffer.clearScreen();
		this.MSniffer.recargarDatos();
		repaintVentana(getVentanaMenuSniffer());
	}

	public void irFinCapture() {
		if (FachadaDominio.getEstadoCaptura()) {
			setPanelEstado("Captura Parada");
			System.out.println("Mediador -> Captura Parada");
			System.out.println("Mediador -> IrFinCapture => INICIO");
			FachadaDominio.stopCaptura();
			System.out
					.println("Mediador -> IrFinCapture => ABRIRDESPUESCAPTURA");
			AbrirDespuesCaptura();
			System.out.println("Mediador -> IrFinCapture => FIN");
			TimePacket.setParar(false);
			SaveTime.setParaSaveTime(false);
		} else {
			System.out.println("IrFinCapture => Ya Parada");
			setPanelEstado("Captura Parada");
		}
	}

	public void irFinInsert() {
	}

	public void irFinCapturePcapLib() {
		if (FachadaDominio.getEstadoCaptura()) {
			setPanelEstado("Parando Captura...");

			FachadaDominio.stopCapturaPcapLib();

			for (int x = 0; x <= 10; x++) {
				try {
					// System.out.println(x);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			while (!FachadaDominio.getEndAll() == true) {
			}
			setPanelEstado("Captura Parada");
			AbrirDespuesCaptura();
		} else {
			setPanelEstado("Captura Parada");
		}
	}

	public void irAAbrirElegirFichero(String ventana) {
		new VentanaAbrirElegirFichero(ventana, this);
	}

	public void irAGuardarElegirFichero(String ventana) {
		new VentanaGuardarElegirFichero(ventana);
	}

	public void irAVentanaElegirDirectorios(String ventana) {
		new VentanaElegirDirectorios(ventana);
	}

	public void irAAyuda() {
		File index = new File(
				FachadaFicheroDirectorios.getdirectorioData("DIR_HELP")
						+ System.getProperty("file.separator")
						+ "Ayuda-Sniffer.htm");

		FachadaDominio.mostrarAyudaSniffer(index.getAbsolutePath());
	}

	public void irAPrueba() {
		FachadaDominio
				.saveXMLfromEstate("C:\\Capturas\\captura_090708_180812_state.xml");
	}

	public void irAPrueba2() {
		String ficheroPcap = "E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I - Ubuntu\\files\\pruebas\\camm.pcap";
		ficheroPcap = "E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I - Ubuntu\\files\\pruebas\\command_cap.pcap";

		String ficheroXML = "E:\\060131 WWW\\WWW Trabajos 06 07\\PFC\\_PFC - Sniffer\\PFC - Sniffer I - Ubuntu\\files\\pruebas\\salida.xml";
		FachadaDominio.saveXMLOfflinePcapLib(ficheroPcap, ficheroXML);
	}

	public void irAAyudaFilter() {
		File index = new File(
				FachadaFicheroDirectorios.getdirectorioData("DIR_HELP")
						+ System.getProperty("file.separator")
						+ "filtrado.html");
		FachadaDominio.mostrarAyudaSniffer(index.getAbsolutePath());
	}

	public void botonAceptarInicoCaptura() {
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();
		try {
			PrefCapCogerDatos();

			FachadaDominio.openCaptura();
			FachadaDominio.setPrefCaptura();
			FachadaDominio.setListener();

			FachadaDominio.startCaptura();
			setPanelEstado(String.valueOf("Captura inicializada"));

			this.hilo = new ControlHilos(Thread.activeCount(), this);
			this.hilo.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al Capturar");
			new Aviso("Error al Capturar", "Error");
		}
	}

	public void botonAceptarConfiguracion() {
		PrefConfCogerDatos();
		guardardirectorios();
	}

	public boolean botonAceptarInicoCapturaDumper() {
		preferencesBeanCapture pBC = FachadaDominio.getPrefBeanCaptura();
		try {
			PrefCapCogerDatos();

			String aux = FachadaDominio.getPrefBeanCaptura()
					.getCapDispositive();
			if (!FachadaDominio.getPrefBeanCaptura().getCapDispositive()
					.equals("\n Seleccione un dispositivo")) {
				FachadaDominio.openCapturaPcapLib();
				FachadaDominio.setPrefCapturaDumper();
				FachadaDominio.setListenerPcapLib();
				FachadaDominio.setControlPacket(true);
				FachadaDominio.setPrefCapturaNFS(PreferenciasCaptura
						.getNFSsync());
				FachadaDominio.setPrefCapturaXMLCircular(PreferenciasCaptura
						.getPilaXML());
				this.FCaptura = new Fcaptura(this,
						FachadaDominio.getCountPacketHandler());
				this.FCaptura.show();
				this.FCaptura.repaint();
				FachadaDominio.startCapturaPcapLib();
				setPanelEstado(String.valueOf("Captura inicializada"));
				if ((pBC.getFilMultipleFileId()) || (pBC.getstpAfterTimeId())
						|| (pBC.getstpAfterSpaceId())
						|| (pBC.getstpAfterPacketsId())) {
					this.hilo = new ControlHilos(Thread.activeCount(), this);
					this.hilo.start(); // hilo controlos hilos
				}
				return true;
			}
			lanzaraviso("Dispositivo no seleccionado", "Informacion");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al Capturar");
			new Aviso("Error al Capturar", "Error");
		}
		return false;
	}

	public void botonAceptarInicoCapturaDumperFromFile() {
		try {
			PrefCapCogerDatosFromFile();

			FachadaDominio.saveMetaOrPcapToFile();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al Capturar");
			new Aviso("Error al Capturar", "Error");
		}
	}

	public Fcaptura getFCaptura() {
		return new Fcaptura(FachadaDominio.getCountPacketHandler());
	}

	public void guardarXML(File fAux) {
		int val = FachadaDominio.saveXML(fAux);
		if (val == 1) {
			lanzaraviso("Fichero XML guardado correctamente", "Terminado");
		} else {
			lanzaraviso("Fichero XML guardado correctamente", "Atencion");
		}
	}

	public void cargardirectorios() {
		FabricaAccesoDatosIF fabrica = new FabricaAccesoDatos();
		String auxDirectorios = FachadaFicheroDirectorios
				.getdirectorioData("DIR_PROPERTIES")
				+ System.getProperty("file.separator") + "userfiles.property";
		FachadaFichero f = fabrica.creaFachadaFichero("directorios",
				auxDirectorios);
		Object[] directoriosnom = f.leer();
	}

	public void guardardirectorios() {
		FabricaAccesoDatosIF fabrica = new FabricaAccesoDatos();
		String auxDirectorios = FachadaFicheroDirectorios
				.getdirectorioData("DIR_PROPERTIES")
				+ System.getProperty("file.separator") + "userfiles.property";
		FachadaFichero f = fabrica.creaFachadaFichero("directorios",
				auxDirectorios);
		f.escribir("");
		System.out.println("Preferencias de usuario grabadas correctamente");
	}

	public void irAFuenteDatos() {
		setPanelEstado("Seleccionando ficheros de datos...");
	}

	public void irAVentanaSalir() {
		new VentanaSalir(this);
	}

	public void irAConfigDirectorios() {
		setPanelEstado("Configuracion de Sniffer III");
		cargardirectorios();
		PreferenciasConfiguracion prefConf = new PreferenciasConfiguracion(this);
		PrefConfLlenarDatos();

		prefConf.setVisible(true);
	}

	public void irAAyudaAcercaDe() {
		new VentanaAcercaDe();
	}

	public void setPanelEstado(String estado) {
	}

	public void habiliatarBHelemento(int posicion) {
		BarraHerramientas.habilitarComponenteBarraHerramientas(posicion);
	}

	public void deshabilitarBHelemento(int posicion) {
		BarraHerramientas.deshabilitarComponenteBarraHerramientas(posicion);
	}

	public void deshabilitarMenuBarraMenus(int posicion) {
		BarraMenu.deshabilitarMenuBarraMenus(posicion);
	}

	public void habilitarMenuBarraMenus(int posicion) {
		BarraMenu.habilitarMenuBarraMenus(posicion);
	}

	public void deshabilitarComponenteBarraMenus(int posicion, int positem) {
		BarraMenu.deshabilitarComponenteBarraMenus(posicion, positem);
	}

	public void habilitarComponenteBarraMenus(int posicion, int positem) {
		BarraMenu.habilitarComponenteBarraMenus(posicion, positem);
	}

	public void EnabledComponenteBarraMenus(int posicion, int positem, int pos,
			boolean bEstado) {
		BarraMenu.EnabledComponenteBarraMenus(posicion, positem, pos, bEstado);
	}

	public void lanzaraviso(String mensaje, String tipo) {
		new Aviso(mensaje, tipo);
	}

	public void irAVentanaAbrirElegirFichero(String ventana) {
		new VentanaAbrirElegirFichero(ventana, this);
	}

	public void irInicioDefinicion(boolean tipo) {
		setPanelEstado("Definicion de Protocolos");
		PreferenciasDefinicion prefDef = new PreferenciasDefinicion();

		prefDef.setVisible(true);
	}

	public void ChequearDefinicionProtocolo() {
		FachadaDominio.chequearDefinicionProtocolo();
	}

	public void ChequearIdentificacionProtocolo() {
		Object aux = null;

		Object[][] arr = PreferenciasDefinicion.getTabla();
		for (int i = 0; i < arr.length - 1; i++) {
			int j = i + 1;
			if (Integer.valueOf(String.valueOf(arr[i][0])).intValue() > Integer
					.valueOf(String.valueOf(arr[j][0])).intValue()) {
				for (int k = 0; k < PreferenciasDefinicion.getColumnas(); k++) {
					aux = arr[i][k];
					arr[i][k] = arr[j][k];
					arr[j][k] = aux;
				}
				i = 0;
				j = 0;
			}
		}
		PreferenciasDefinicion.setTablaCampos(arr);
	}

	public void PrefInserCapRuta(String fRuta) {
		PreferenciasInsercionCapturados.setFicheroCapturas(fRuta);
	}
	
	public void PrefInserSyncRuta(String fRuta) {
		PreferenciasConfiguracion.setSyncFile(fRuta);
	}


	public void PrefInserDefinidosRuta(String fRuta) {
		PreferenciasInsercionCapturados.setFicheroCapturas(fRuta);
	}

	public void PrefDefinicionRuta(String fRuta) {
		PreferenciasDefinicion.setFicheroCapturas(fRuta);
	}

	public void irAInsertarPaquetes(String titulo, int tot) {
		if (titulo.equals("Insertar Paquetes Capturados")) {
			if ((PreferenciasInsercionCapturados.getDispositivo()
					.equals("Seleccione un dispositivo"))
					|| (PreferenciasInsercionCapturados.getRepeticiones() == 0)
					|| (PreferenciasInsercionCapturados.getRuta().equals(""))) {
				lanzaraviso("Datos Insercion Incompletos", "Error");
			} else {
				Insercion ins = new Insercion(
						PreferenciasInsercionCapturados.getDispositivo(),
						PreferenciasInsercionCapturados.getRuta(),
						PreferenciasInsercionCapturados.getRepeticiones(), 2);
				PreferenciasVisualizarInsercion prefIns = new PreferenciasVisualizarInsercion(
						"Insercion Paquetes Capturados", tot, ins);
				PreferenciasVisualizarInsercion.iniciarInsercion();

				prefIns.setVisible(true);
			}
		}
		if (titulo.equals("Insertar Paquetes Definidos")) {
			try {
				PcapPacket p = PreferenciasInsercion.crearPaquete();
				if ((p != null)
						&& (PreferenciasInsercion.getDispositivo() != "")) {
					Insercion ins = new Insercion(
							PreferenciasInsercion.getDispositivo(), p, 1);
					PreferenciasVisualizarInsercion prefIns = new PreferenciasVisualizarInsercion(
							"Insertar Paquetes Definidos", -1, ins);
					PreferenciasVisualizarInsercion.iniciarInsercion();
					prefIns.setVisible(true);
				} else {
					lanzaraviso("Datos Insercion Incompletos", "Error");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void PrefDefLeerXMLExportaciones(String fRuta) {
		PreferenciasInsercion.setFichero(fRuta);
		FachadaDominio.setPreferencesExportacion(fRuta);
		FachadaDominio.getPreferencesExportacion();
		PrefInsCapLlenarDatos();
	}

	public void PrefInsCapLlenarDatos() {
		try {
			preferencesBeanExportInsercion pBE = FachadaDominio
					.getPrefBeanExportInsercion();

			PreferenciasInsercion.setIpDestino(pBE.getIpDestino());
			PreferenciasInsercion.setIpOrigen(pBE.getIpOrigen());
			PreferenciasInsercion.setMacDestino(pBE.getMacDestino());
			PreferenciasInsercion.setMacOrigen(pBE.getMacOrigen());
		} catch (NullPointerException e) {
			System.out
					.println("Error lectura preferencias. Null Pointer Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
