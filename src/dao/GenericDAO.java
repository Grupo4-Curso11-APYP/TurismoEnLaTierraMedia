package dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public int countAll() throws SQLException;

}
