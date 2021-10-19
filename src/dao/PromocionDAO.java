package dao;
import java.sql.SQLException;

import turismoEnLaTierraMediaGrupo4.*;
public interface PromocionDAO extends GenericDAO<Promocion> {
	public abstract Promocion findByUsername(String promocionTipo) throws SQLException;
}
