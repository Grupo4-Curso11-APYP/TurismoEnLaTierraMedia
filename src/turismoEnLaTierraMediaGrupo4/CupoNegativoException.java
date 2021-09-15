package turismoEnLaTierraMediaGrupo4;

public class CupoNegativoException extends Exception {
public CupoNegativoException() {
	super("El cupo debe ser mayor que 0");
}
}
