package dao;
import java.sql.SQLException;

import turismoEnLaTierraMediaGrupo4.*;
public interface PromocionDAO extends GenericDAO<Promocion> {
	public abstract Promocion buscarPorIdAtraccion(Long  id) throws SQLException;
}
