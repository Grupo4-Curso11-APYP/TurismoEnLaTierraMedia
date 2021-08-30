package turismoEnLaTierraMediaGrupo4;

import java.util.Arrays;
import java.util.Comparator;

public class Sistema {
	protected Atraccion[] atraccion;
	protected Usuario[] usuarios;
	protected PromocionBase[] promocion;

	public Sistema(int numAtracciones, int numPromociones, int numUsuarios) {
		this.atraccion = new Atraccion[numAtracciones];
		this.usuarios = new Usuario[numUsuarios];
		this.promocion = new PromocionBase[numPromociones];
	}

	/*
	 * @Param  se pasa por parametro una lista de usuarios 
	 *  @Variable  cant  sirve para cuando se cumple la condicion valla contrando todas las que cumplen
	 *  @variable indice  es para que pueda recorrer el segundo arrayy donde se van a guardar las sugerencias 
	 *  ahora si funciona xdxd
	 */
	public Usuario[] sugerir(Usuario usuarioList[]) {
		int cant = 0;
		int indice = 0;
		for (Usuario usuario1 : usuarioList) {
			for (PromocionBase pr : promocion) {
				while(!pr.llena()) {
				if ((usuario1.getTipoFavorito().equals(pr.getTipo())) && (usuario1.getPresupuesto()) >= pr.getCosto()
						&& usuario1.getTiempoDisponible() >= pr.getTiempo()) {
					cant++;
				}
			}}}	
		this.usuarios =new Usuario[cant];
		for (Usuario usuario2 : usuarioList) {
		for(PromocionBase pr2 : promocion) {		
			if ((usuario2.getTipoFavorito().equals(pr2.getTipo())) && (usuario2.getPresupuesto()) >= pr2.getCosto()
					&& usuario2.getTiempoDisponible() >= pr2.getTiempo()) {
				usuarios[indice] = usuario2;
				indice++;
			}	
		}}

		return usuarios;
	}

	/*
	 * @Param c se pasa por parametro el criterio por el cual se va a ordenar
	 */
	public void ordenar(Comparator<Atraccion> c) {
		Arrays.sort(atraccion, c);
		;
	}

	// errores que veo tanto en esta como la de joa es que al iinvocar getCosto() no
	// se sabe a que promocion esta dirigiendose

}
