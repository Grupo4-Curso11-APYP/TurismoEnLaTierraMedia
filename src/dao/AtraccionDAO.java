package dao;

import java.sql.SQLException;
import java.util.List;

import turismoEnLaTierraMediaGrupo4.Atraccion;


public interface AtraccionDAO extends GenericDAO<Atraccion> {
	
		public abstract Atraccion buscarPorId(Long id) throws SQLException;
		
		public int insertarAtrac(String nombre, int costo, int tiempo, int cupoDisponible, String tipoAtraccion) throws SQLException;
		
		public List<Atraccion> findAll() throws SQLException;
		
		public int delete(Atraccion atraccion) throws SQLException;
		
		public int update(Atraccion atraccion) throws SQLException;
	}
