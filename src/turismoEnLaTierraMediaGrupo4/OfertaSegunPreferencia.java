package turismoEnLaTierraMediaGrupo4;

import java.util.Comparator;

public class OfertaSegunPreferencia  implements Comparator<Ofertable>{

	private TipoAtraccion tipo;

	public OfertaSegunPreferencia(TipoAtraccion tipo ) {
		this.tipo = tipo;
	}  
	
	@Override
	public int compare(Ofertable o1, Ofertable o2) {
	double   resultado =0;
	  if(resultado == 0) {
		 return o1.getTipo().compareTo(o2.getTipo());
	  }if(resultado == 1){
		  return ( Double.compare(o1.getCosto(), o2.getCosto())) ;
		 } else {
				  Double.compare(o1.getTiempo(), o2.getTiempo());
		 }
	  

	
		return (int) resultado;
	}
	
	}


