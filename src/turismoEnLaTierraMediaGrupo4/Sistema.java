package turismoEnLaTierraMediaGrupo4;

public class Sistema {

	protected Usuario[] usuarios;

	public Sistema(int numAtracciones, int numPromociones, int numUsuarios) {

		this.usuarios = new Usuario[numUsuarios];
	}

	/*
	 * como las sugericias son para el usuario y usuario entiende promocionBase y
	 * atraccion no hace falta dos arrays nuevos
	 * 
	 * @param usuario se pasa un usuario al cual se le va a sugerir la atraccion y
	 * la promocion si acepta alguna promocion se guardara su sugerencia en un nuevo
	 * array de usuarios que es lo que espera el metodo
	 */
	public Usuario[] sugerir(Usuario usuario) {
		Usuario[] lista = new Usuario[usuarios.length];

		for (int j = 0; j < usuarios.length; j++) {
			Usuario usuario1 = usuarios[j];
			for (int i = 0; i < lista.length; i++) {
				try {
					if ((usuario1.getTipoFavorito().equals(usuario.getPromociones()[i].getTipo()))
							&& (usuario1.getPresupuesto()) >= usuario.getPromociones()[i].getCosto()
							&& usuario1.getTiempoDisponible() >= usuario.getPromociones()[i].getTiempo()) {

						lista[i] = usuario1;

					}
				} catch (ArrayIndexOutOfBoundsException a) {
					System.out.println("la posicion a la cual esta queriendo acceder esta fuera del limite del array");
				}
			}
		}
		return lista;
	}

	// errores que veo tanto en esta como la de joa es que al iinvocar getCosto() no
	// se sabe a que promocion esta dirigiendose

}
