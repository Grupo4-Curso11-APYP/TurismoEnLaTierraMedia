package turismoEnLaTierraMediaGrupo4;

public interface Ofertable {

	/*
	 * devuelve el costo
	 */
	public Double getCosto();

	/*
	 * devuelve el tiempo
	 */
	public Double getTiempo();

	/*
	 * devuelve el tipo
	 */
	public TipoAtraccion getTipo();

	/*
	 * pregunta si hay cupo disponible
	 */
	public boolean hayCupo();

	/*
	 * si hay cupo , resta un cupo disponible , para simular la reservacion
	 */
	public void reservarCupo();
	
	public  String  getNombre ();
}
