package turismoEnLaTierraMediaGrupo4;

import java.util.Comparator;

public class MayorCostoDeAtraccion  implements Comparator<Atraccion>{

	
	@SuppressWarnings("deprecation")
	@Override
	public int compare(Atraccion o1, Atraccion o2) {

		  return new Integer( o1.getCosto()).compareTo( o2.getCosto());
	}


}
