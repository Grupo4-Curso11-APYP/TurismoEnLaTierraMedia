package turismoEnLaTierraMediaGrupo4;

import java.util.List;

public class PromocionPorcentual extends PromocionBase {

	private double descuento;

	public PromocionPorcentual(String nombre, Atraccion [] packAtracciones,TipoAtraccion tipo,double descuento ) {
		super(nombre, packAtracciones, tipo);
		this.descuento = descuento;
	}

    
    // realiza  el descuento porcentual   para un pack de atracciones 
    @Override
    public Double getCosto() {
        double precioFinal = 0;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descuento);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionPorcentual other = (PromocionPorcentual) obj;
		return Double.doubleToLongBits(descuento) == Double.doubleToLongBits(other.descuento);
	}

}


