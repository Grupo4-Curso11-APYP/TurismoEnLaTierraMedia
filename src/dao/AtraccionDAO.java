package dao;

import java.sql.SQLException;

import turismoEnLaTierraMediaGrupo4.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {
	
		public abstract Atraccion buscarPorId(Long id) throws SQLException;
	}
