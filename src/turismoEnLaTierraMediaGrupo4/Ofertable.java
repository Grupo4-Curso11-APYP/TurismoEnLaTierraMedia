package turismoEnLaTierraMediaGrupo4;

import java.sql.SQLException;

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
	 * si hay cupo , resta un cupo disponible en las atracciones involucradas.
	 */
	public void reservarCupo() throws SQLException;
	
	/*
	 * devuelve el nombre del ofertable.
	 */
	public String getNombre();
}
