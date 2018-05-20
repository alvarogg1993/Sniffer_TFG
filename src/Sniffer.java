import dominio.ComandoAcciones;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.jnetpcap.*;
import presentacion.VentanaPresentacion;

/**
 * Clase Sniffer, Clase Principal.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchezz
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */

public class Sniffer {

	public static PcapIf devices;

	public static void main(String args[]) {
		try {
			int paramError = 0;
			if (args.length == 0) {
				System.out.println("Modo Grafico");
				new Sniffer();
			} else if (args.length == 1) {
				if (args[0].equals("-graphic")) {
					System.out.println("Modo Grafico");
					new Sniffer();
				} else if (args[0].equals("-command"))
					paramError = 3;
				else if (args[0].equals("-help"))
					ayudaGeneral();
				else if (args[0].equals("-dispo")) {
					preConfigParam();
					ComandoAcciones com = new ComandoAcciones();
					com.pedirDispositivoCaptura(false);
				} else {
					paramError = 1;
				}
			} else if (args[0].equals("-command")) {
				if (args.length == 2) {
					if (args[1].equals("-scan"))
						paramError = 4;
					else if (args[1].equals("-export"))
						paramError = 6;
					else if (args[1].equals("-fromfile"))
						paramError = 7;
					else if (args[1].equals("-state"))
						paramError = 9;
					else
						paramError = 3;
				} else if (args.length == 3) {
					if (args[1].equals("-scan")) {
						if (args[2].equals("-dispo")) {
							paramError = 5;
						} else {
							preConfigParam();
							ComandoAcciones com = new ComandoAcciones();
							com.runScan(args[2]);
						}
					} else if (args[1].equals("-export")) {
						preConfigParam();
						ComandoAcciones com = new ComandoAcciones();
						com.runExport(args[2]);
					} else if (args[1].equals("-fromfile")) {
						preConfigParam();
						ComandoAcciones com = new ComandoAcciones();
						com.runFromFile(args[2]);
					} else if (args[1].equals("-state")) {
						preConfigParam();
						ComandoAcciones com = new ComandoAcciones();
						com.runState(args[2]);
					} else {
						paramError = 3;
					}
				} else if (args.length == 4) {
					if (args[1].equals("-scan")) {
						if (args[2].equals("-dispo")) {
							preConfigParam();
							ComandoAcciones com = new ComandoAcciones();
							int opt = com.pedirDispositivoCaptura(true);
							if (opt == 0)
								paramError = 101;
							else if (opt == -1)
								paramError = 103;
							else
								com.runScanDispo(args[3], opt);
						} else {
							paramError = 4;
						}
					} else {
						paramError = 3;
					}
				} else {
					paramError = 3;
				}
			} else {
				paramError = 2;
			}
			if (paramError > 0)
				System.out.println("");
			if (paramError == 1)
				System.out.println("!!! ERROR - Parametros incorrectos !!!");
			if (paramError == 2)
				System.out
						.println("!!! ERROR - Parametros incorrectos, no modo correcto !!!");
			if (paramError == 3)
				System.out
						.println("!!! ERROR - Parametros incorrectos en modo -command !!!");
			if (paramError == 4)
				System.out
						.println("!!! ERROR - Parametros incorrectos en modo -command -scan !!!");
			if (paramError == 5)
				System.out
						.println("!!! ERROR - Parametros incorrectos en modo -command -scan -dispo !!!");
			if (paramError == 6)
				System.out
						.println("!!! ERROR - Parametros incorrectos en modo -command -export !!!");
			if (paramError == 7)
				System.out
						.println("!!! ERROR - Parametros incorrectos en modo -command -fromfile !!!");
			if (paramError == 10)
				System.out
						.println("!!! ERROR - Parametros incorrectos en modo -command falta parametros!!!");
			if (paramError == 11)
				System.out
						.println("!!! ERROR - Parametros incorrectos en modo -command -estate !!!");
			if (paramError == 101)
				System.out
						.println("!!! No se ha seleccionado ningun dispositivo!!!");
			if (paramError == 102)
				System.out
						.println("!!! ERROR - Dispositivo seleccionado no es correcto!!!");
			if (paramError == 103)
				System.out
						.println("!!! ERROR - S\363lo teclado num\351rico - vuelva a inicar la aplicaci\363n");
			if (paramError == 99)
				System.out.println("!!! ERROR - oooo !!!");
			if (paramError > 0 && paramError < 100) {
				System.out.println("");
				ayudaGeneral();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Fallo.");
		}
	}

	public Sniffer() {
		preConfigParam();
		VentanaPresentacion f = new VentanaPresentacion(false);
	}

	public static void preConfigParam() {
		try {
			Properties p = new Properties(System.getProperties());
			System.setProperties(p);

			System.out.println(p);
			String OSName = System.getProperty("os.name").toLowerCase();

			if (OSName.indexOf("win") >= 0) { // SO Windows

				if (System.getProperty("os.arch").indexOf("x86") == 0)
					System.loadLibrary("./dll/32/jnetpcap");
				else {
					if (System.getProperty("os.arch").indexOf("amd64") == 0)
						System.loadLibrary("./dll/64/jnetpcap");
					else
						System.out
								.println("\n * * Sistema Operativo no encontrado ** ");
				}
			} else {
				if (OSName.indexOf("nix") >= 0 || OSName.indexOf("nux") >= 0
						|| OSName.indexOf("aix") > 0) {
					System.out.println("\n * * Sistema Operativo Unix **");
				} else
					System.out
							.println("\n * * Sistema Operativo no soportado **");
			}

		} catch (Exception e) {
			System.err.println("I/O failed.");
		}
		try {

			List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled
															// with NICs
			StringBuilder errbuf = new StringBuilder(); // For any error msgs

			int r = Pcap.findAllDevs(alldevs, errbuf);
			if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
				System.err.printf("Can't read list of devices, error is %s\n",
						errbuf.toString());
				return;
			}
			// System.out.println("HERE IS ALL THE DEVICES");
			for (int i = 0; i < alldevs.size(); i++) {
				// System.out.println(alldevs.get(i));
			}
			devices = alldevs.get(0); // We know we have atleast 1 device

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Cannot find Jpcap. Please install Jpcap.", "Error", 0);
			System.exit(-1);
		}
	}

	// public static void preConfigParam() {
	// try {
	// Properties p = new Properties(System.getProperties());
	// System.setProperties(p);
	//
	// System.out.println(p);
	//
	// if (System.getProperty("os.name").toLowerCase().compareTo("linux") != 0){
	//
	// if (System.getProperty("os.arch").indexOf("x86")== 0)
	// System.loadLibrary("./dll/32/jnetpcap");
	// else{
	// if (System.getProperty("os.arch").indexOf("amd64")== 0)
	// System.loadLibrary("./dll/64/jnetpcap");
	// else
	// System.out.println("\n * * Sistema Operativo no encontrado ** ");
	// }
	// }else{
	// System.out.println("\n * * Sistema Operativo Linux **");
	// }
	//
	//
	// } catch (Exception e) {
	// System.err.println("I/O failed.");
	// }
	// try {
	//
	//
	// List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with
	// NICs
	// StringBuilder errbuf = new StringBuilder(); // For any error msgs
	//
	//
	// int r = Pcap.findAllDevs(alldevs, errbuf);
	// if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
	// System.err.printf("Can't read list of devices, error is %s\n",
	// errbuf.toString());
	// return;
	// }
	// //System.out.println("HERE IS ALL THE DEVICES");
	// for (int i = 0; i < alldevs.size(); i++) {
	// // System.out.println(alldevs.get(i));
	// }
	// devices = alldevs.get(0); // We know we have atleast 1 device
	//
	//
	//
	// } catch (Exception e) {
	// JOptionPane.showMessageDialog(null,"Cannot find Jpcap. Please install Jpcap.",
	// "Error", 0);
	// System.exit(-1);
	// }
	// }

	private static void ayudaGeneral() {
		System.out.println("Sniffer III - Ayuda");
		System.out.println("");
		System.out.println("java -jar sniffer.jar [-graphic]");
		System.out.println("                      [-command (-scan [-dispo] |");
		System.out.println("                      \t         -export |");
		System.out.println("                                 -fromfile |");
		System.out
				.println("                                 -state) par\341metros]");
		System.out.println("                      [-dispo]");
		System.out
				.println("\n  par\341metros\tArchivo .XML que contienen parametros del Sniffer.");
		System.out.println("  -graphic\tModo gr\341fico.");
		System.out.println("  -command\tModo comando.");
		System.out.println("  -dispo\tMuestra o elige dispositivo de captura.");
		System.out
				.println("  -scan\t\tCaptura de paquetes seg\372n los par\341metros");
		System.out
				.println("  -export\tExporta paquetes seg\372n los par\341metros");
		System.out
				.println("  -fromfile\tCaptura desde fichero seg\372n los par\341metros");
		System.out
				.println("  -estate\tExporta paquetes seg\372n los par\341metros");
		System.out.println("");
	}

}
