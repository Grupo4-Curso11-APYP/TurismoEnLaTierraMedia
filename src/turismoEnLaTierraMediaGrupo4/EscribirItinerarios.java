package turismoEnLaTierraMediaGrupo4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscribirItinerarios {
	
	/*
	 * @param usuario pasa un usuario para que la salida de itinerarios se pueda
	 * realizar para todos los usuarios del sistema
	 * 
	 * salidaItinerario escribe y guarda en un archivo distinto para cada usuario
	 * su itinerario al finalizar las sugerencias.
	 */
	public static void salidaItinerario(Usuario usuario) throws IOException {
		String nombreArchivo = usuario.getNombre();
		PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo+".out"));
		salida.println(usuario.toString());
		
		salida.close();
	}

}
