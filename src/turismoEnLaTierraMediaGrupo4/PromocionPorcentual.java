package turismoEnLaTierraMediaGrupo4;

import java.util.List;

public class PromocionPorcentual extends PromocionBase {

	private double descuento;

	public PromocionPorcentual(String nombre, List<Atraccion> packAtracciones,TipoAtraccion tipo,double descuento ) {
		super(nombre, packAtracciones, tipo);
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


