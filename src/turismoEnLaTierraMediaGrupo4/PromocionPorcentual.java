package turismoEnLaTierraMediaGrupo4;

public class PromocionPorcentual extends PromocionBase {
	private double descuento;

    public PromocionPorcentual(int numAtraccionesEnPromo, 
             double descuento) {
        super(numAtraccionesEnPromo);
//        this.packAtracciones = new Atraccion[numAtraccionesEnPromo];  no es necesario inicializar el array cada vez que se crea una clase ya que se inicializo en la clase padre
        this.descuento = descuento;
    }

    
    // realiza  el descuento porcentual   para un pack de atracciones 
    @Override
    public double getCosto() {
        float precioFinal = 0;
        for (Atraccion atraccion:this.packAtracciones) {
            precioFinal += atraccion.getCosto();
        }

        precioFinal -= precioFinal / 100 * this.getDescuento();

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


