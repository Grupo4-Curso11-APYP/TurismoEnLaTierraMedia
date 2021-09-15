package turismoEnLaTierraMediaGrupo4;

public class SinTiempoDisponible extends Exception{

	public SinTiempoDisponible() {
		super("No posee tiempo suficiente para la atracción");
	}
}
