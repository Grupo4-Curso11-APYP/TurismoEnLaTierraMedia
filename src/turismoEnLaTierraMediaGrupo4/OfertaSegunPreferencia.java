package turismoEnLaTierraMediaGrupo4;

import java.util.Comparator;

public class OfertaSegunPreferencia implements Comparator<Ofertable> {

	private TipoAtraccion tipo;

	public OfertaSegunPreferencia(TipoAtraccion tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compare(Ofertable o1, Ofertable o2) {

		if (o1.getTipo().compareTo(o2.getTipo()) == 0) {
			if (o1.getClass().getSimpleName().compareTo(o1.getClass().getSimpleName()) == 0) {
				if (o1.getCosto().compareTo(o2.getCosto()) == 0) {
					return o2.getTiempo().compareTo(o1.getTiempo()); // prioriza mayor tiempo al estar o2 primero.
				}
				return o2.getCosto().compareTo(o1.getCosto()); // prioriza mayor costo al estar o2 primero.

			}
		}

		return o1.getTipo().compareTo(o2.getTipo());

	}

}
