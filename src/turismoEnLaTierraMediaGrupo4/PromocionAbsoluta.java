package turismoEnLaTierraMediaGrupo4;

public class PromocionAbsoluta extends PromocionBase{

	
	   private double descuento;

	

	    
	    public PromocionAbsoluta(int numAtraccionesEnPromo, double descuento) {
		super(numAtraccionesEnPromo);
		this.descuento = descuento;
	}

		// se realiza  el descuento  absoluto  para esta promocion
	    @Override
	    public double getCosto() {
	        float precioFinal = 0;
	        for (Atraccion atraccion:this.packAtracciones) {
	            precioFinal += atraccion.getCosto();
	        }

	        precioFinal -= this.getDescuento();
	        return precioFinal;
	    }

	    @Override
	    public String ToString() {
	        // TODO Auto-generated method stub
	        return null;
	    }

	    public double getDescuento() {
	        return descuento;
	    }

}
