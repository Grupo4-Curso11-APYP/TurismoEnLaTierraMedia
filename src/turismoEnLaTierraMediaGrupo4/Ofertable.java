package turismoEnLaTierraMediaGrupo4;

public interface Ofertable {

	public Double getCosto();
	public Double getTiempo();
    public TipoAtraccion getTipo();
    public boolean hayCupo();	
    public void reservarCupo();
}
