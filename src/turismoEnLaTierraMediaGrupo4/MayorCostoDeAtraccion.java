package turismoEnLaTierraMediaGrupo4;

import java.util.Comparator;

public class MayorCostoDeAtraccion  implements Comparator<Atraccion>{

	
	
	@Override
	public int compare(Atraccion o1, Atraccion o2) {

		// corregi el metodo ya que investigue en javaDoc  que la manera en  que yo lo hacia estaba  obsoleta 
		
		  return Integer.compare(o2.getCosto(), o1.getCosto());
	}
	
	// Esa manera ordena de menor a mayor, según nos enseñaron para ordenar de mayor a menor tendría que ser así si no me equivoco: 
	// return -1 * new MayorCostoDeAtraccion().compare(o1, o2);

	// no hace falta porque hacer todo eso (tambien es valido) pero con cambiar el orden de ordenacion ya los compara distinto ,
	//es decir en ves de poner o1.compare.o2  simplemente lo damos vuelta

}