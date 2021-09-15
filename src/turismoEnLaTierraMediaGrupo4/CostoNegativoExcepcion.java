package turismoEnLaTierraMediaGrupo4;

public class CostoNegativoExcepcion extends Exception {

	public CostoNegativoExcepcion() {
		super("El presupuesto no puede ser menor a 1");
	}

}
