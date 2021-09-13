package turismoEnLaTierraMediaGrupo4;

import java.util.Comparator;

public class OfertaSegunPreferencia implements Comparator<Ofertable> {

	private TipoAtraccion tipo;

	public OfertaSegunPreferencia(TipoAtraccion tipo) {
		this.tipo = tipo;
	}

	/*
	 * @Param o1 o2 pasa dos objetos ofertables
	 * se comparan los ofertables priorizando tipo, luego las
	 *  promociones, después los ofertables más caros y por último los de mayor 
	 *  tiempo de visita.
	 */
	
	@Override
	public int compare(Ofertable o1, Ofertable o2) {

		if (o1.getTipo().compareTo(o2.getTipo()) == 0) {
			if (o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName()) == 0) {
				if (o1.getCosto().compareTo(o2.getCosto()) == 0) {
					return o2.getTiempo().compareTo(o1.getTiempo()); // prioriza mayor tiempo al estar o2 primero.
				}
				return o2.getCosto().compareTo(o1.getCosto()); // prioriza mayor costo al estar o2 primero.
			}
			if (o1.getClass() != Atraccion.class)
				return	-1;
			if (o2.getClass() != Atraccion.class)
				return 1;
		}
		if (o1.getTipo() == tipo || o2.getTipo() == tipo){
			if (o1.getTipo() == tipo){
				return -1;
			} if (o2.getTipo() == tipo) {
				return 1;
			}
		}

		if (o1.getClass() != Atraccion.class || o2.getClass() != Atraccion.class)
		{
			if (o1.getClass() != Atraccion.class && o2.getClass() == Atraccion.class){
				return -1;
			}
			if (o2.getClass() != Atraccion.class && o2.getClass() == Atraccion.class) {
				return 1;
			}
			if (o1.getClass() != Atraccion.class && o2.getClass() != Atraccion.class){
				if (o1.getCosto().compareTo(o2.getCosto()) == 0) {
					return o2.getTiempo().compareTo(o1.getTiempo());
				}
				return o2.getCosto().compareTo(o1.getCosto());
			}
		}

		if (o1.getClass() != Atraccion.class)
			return	-1;
		if (o2.getClass() != Atraccion.class)
			return 1;

		if (o1.getCosto().compareTo(o2.getCosto()) == 0)
			return o2.getTiempo().compareTo(o1.getTiempo());

		return o2.getCosto().compareTo(o1.getCosto());
	}

}
