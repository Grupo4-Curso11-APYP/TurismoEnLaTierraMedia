package turismoEnLaTierraMediaGrupo4;

import java.util.Comparator;

public class MayorTiempoDeVisita  implements Comparator<Atraccion>{

	@Override
	public int compare(Atraccion o1, Atraccion o2) {

		  return new Double( o1.getTiempo()).compareTo( o2.getTiempo());
	}

}
