package turismoEnLaTierraMediaGrupo4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscribirItinerarios {
	
	public static void salidaItinerario(Usuario usuario) throws IOException {
		String nombreArchivo = usuario.getNombre();
		PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo+".out"));
		salida.println(usuario.toString());
		
		salida.close();
	}

}
