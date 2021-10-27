package dao;
import java.sql.SQLException;
import java.util.List;

import turismoEnLaTierraMediaGrupo4.*;
public interface PromocionDAO extends GenericDAO<Promocion> {
	public List<Promocion> findAll() throws SQLException;
	
}
