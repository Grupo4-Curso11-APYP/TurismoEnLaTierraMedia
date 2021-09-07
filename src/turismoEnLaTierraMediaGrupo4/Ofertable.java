package turismoEnLaTierraMediaGrupo4;

public interface Ofertable {

	public double getCosto();
	public double getTiempo();
    TipoAtraccion getTipo();
    public boolean hayCupo();	
    public void reservarCupo();
}
