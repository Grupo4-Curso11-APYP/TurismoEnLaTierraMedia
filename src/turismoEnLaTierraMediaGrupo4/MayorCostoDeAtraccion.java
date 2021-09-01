package turismoEnLaTierraMediaGrupo4;

import java.util.Comparator;

public class MayorCostoDeAtraccion  implements Comparator<Atraccion>{

	
	
	@Override
	public int compare(Atraccion o1, Atraccion o2) {

		// corregi el metodo ya que investigue en javaDoc  que la manera en  que yo lo hacia estaba  obsoleta 
		
		  return Integer.compare(o1.getCosto(), o2.getCosto());
	}


}
