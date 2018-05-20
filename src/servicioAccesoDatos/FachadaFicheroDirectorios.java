package servicioAccesoDatos;

import java.io.*;
import java.util.Properties;

/**
 * Clase FachadaFicheroDirectorios.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
// Referenced classes of package servicioAccesoDatos:
// FachadaFichero

public class FachadaFicheroDirectorios extends FachadaFichero {

	public FachadaFicheroDirectorios(String nombreRutaF) throws IOException {
		FachadaFichero.nombreRutaF = nombreRutaF;
		this.nombreRutaF = nombreRutaF;
	}

	public PrintWriter getOutput() {
		return output;
	}

	public static String getRutaFichero() {
		return nombreRutaF;
	}

	public Object[] leer() {
		try {
			BufferedReader inlong = new BufferedReader(new FileReader(
					nombreRutaF));
			String linea2;
			int nLineas;
			for (nLineas = 0; (linea2 = inlong.readLine()) != null; nLineas++)
				;
			in = new BufferedReader(new FileReader(nombreRutaF));
			Object etiAtriFichero[] = new Object[nLineas];
			String linea;
			for (int i = 0; (linea = in.readLine()) != null; i++)
				etiAtriFichero[i] = linea;

			in.close();
			for (int j = 0; j < etiAtriFichero.length; j++) {
				int pos = etiAtriFichero[j].toString().indexOf("=");
				if (pos > -1) {
					String aux1 = etiAtriFichero[j].toString()
							.substring(0, pos);
					String aux2 = etiAtriFichero[j].toString().substring(
							pos + 1, etiAtriFichero[j].toString().length());
					aux2 = aux2.replace("\\\\", "\\");
					aux2 = aux2.replace("\\:", ":");
					setdirectorio(aux1, aux2.replace("\\\\", "\\"));
				}
			}

			return etiAtriFichero;
		} catch (IOException e) {
			System.out.println("Se ha producido un error al leer");
			System.err.println(e.getMessage());
			return null;
		}
	}

	public String leerString() {
		try {
			FileInputStream inStream = new FileInputStream(nombreRutaF);
			int inBytes = inStream.available();
			byte inBuf[] = new byte[inBytes];
			int bytesRead = inStream.read(inBuf, 0, inBytes);
			String resultado = new String(inBuf);
			inStream.close();
			return resultado;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void escribir(String contenido) {
		Properties p = new Properties();
		boolean exists = true;
		try {
			FileOutputStream file = new FileOutputStream(getRutaFichero());
			for (int i = 0; i < nomdir.length; i++)
				p.put(nomdir[i].toString(), dir[i].toString());

			p.store(file, "Ficheros de Usuario");
			p.clear();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getdirectorio(String tipo) {
		int pos = -1;
		for (int i = 0; i < nomdir.length; i++)
			if (nomdir[i].equals(tipo))
				pos = i;

		if (pos == -1)
			return "";
		else
			return dir[pos];
	}

	public static void setdirectorio(String tipo, String ruta) {
		int pos = -1;
		for (int i = 0; i < nomdir.length; i++)
			if (nomdir[i].equals(tipo))
				pos = i;

		if (pos != -1)
			dir[pos] = ruta;
	}

	public static String getdirectorioData(String tipo) {
		int pos = -1;
		for (int i = 0; i < nomdirData.length; i++)
			if (nomdirData[i].equals(tipo))
				pos = i;

		if (pos == -1)
			return "";
		else
			return dirData[pos];
	}

	public static void reinicializar() {
		nombreRutaF = null;
	}

	static String nombreRutaF;
	public FileWriter writer;
	public BufferedWriter buffer;
	public BufferedReader in;
	public PrintWriter output;
	static String barra;
	public static String dir[];
	public static String nomdir[] = { "DIR_PARAMETRIZACION", "DIR_CAPTURAS",
			"DIR_RULES", "DIR_EXPORTACIONES", "DIR_SCRIPTS",
			"DIR_DEFINICIONES", "DIR_INTRUSO", "FILE_FICHEROSINCRONIZADO" };
	public static String dirData[];
	public static String nomdirData[] = { "DIR_PROPERTIES", "DIR_IMAGES",
			"DIR_HELP" };

	static {
		barra = "/";
		dir = (new String[] {
				(new File((new StringBuilder(".")).append(barra)
						.append("files").append(barra)
						.append("parametrizacion").toString()))
						.getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra)
						.append("files").append(barra).append("capturas")
						.toString())).getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra)
						.append("files").append(barra).append("rules")
						.toString())).getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra)
						.append("files").append(barra).append("exportaciones")
						.toString())).getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra)
						.append("files").append(barra).append("scripts")
						.toString())).getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra)
						.append("files").append(barra).append("definiciones")
						.toString())).getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra)
						.append("files").append(barra).append("intruso")
						.toString())).getAbsolutePath(),
						(new File((new StringBuilder(".")).append(barra)
								.append("files").append(barra).append("capturas")
								.toString())).getAbsolutePath()});
		dirData = (new String[] {
				(new File((new StringBuilder(".")).append(barra).append("data")
						.append(barra).append("properties").toString()))
						.getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra).append("data")
						.append(barra).append("images").toString()))
						.getAbsolutePath(),
				(new File((new StringBuilder(".")).append(barra).append("data")
						.append(barra).append("help").toString()))
						.getAbsolutePath() });
	}
	

}
