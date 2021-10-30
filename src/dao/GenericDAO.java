package dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	/*
	 * Se encarga de contar todos los datos de una Tabla en especifico
	 */
	public int countAll() throws SQLException;

}
