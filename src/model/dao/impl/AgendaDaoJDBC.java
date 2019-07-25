package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import db.DB;
import db.DbIntegrityException;
import db.DbException;
import model.dao.AgendaDao;
import model.entities.Agenda;

public class AgendaDaoJDBC implements AgendaDao{

private Connection conn;
	
	public AgendaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Agenda findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM agenda WHERE Name = ?");
			st.setString(1, name);
			rs = st.executeQuery();
			if (rs.next()) {
				Agenda obj = new Agenda();
				obj.setId(rs.getInt("idAgenda"));
				obj.setName(rs.getString("Name"));
				obj.setNumber(rs.getString("Number"));
				obj.setEmail(rs.getString("Email"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public Agenda findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM agenda WHERE idAgenda = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Agenda obj = new Agenda();
				obj.setId(rs.getInt("idAgenda"));
				obj.setName(rs.getString("Name"));
				obj.setNumber(rs.getString("Number"));
				obj.setEmail(rs.getString("Email"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Agenda> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = (PreparedStatement) conn.prepareStatement(
				"SELECT * FROM agenda ORDER BY Name");
			rs = st.executeQuery();

			List<Agenda> list = new ArrayList<>();

			while (rs.next()) {
				Agenda obj = new Agenda();
				obj.setId(rs.getInt("idAgenda"));
				obj.setName(rs.getString("Name"));
				obj.setNumber(rs.getString("Number"));
				obj.setEmail(rs.getString("Email"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Agenda obj) {
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) conn.prepareStatement(
				"INSERT INTO agenda " +
				"(Name, Number, Email) " +
				"VALUES " +
				"(?, ?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getNumber());
			st.setString(3, obj.getEmail());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Agenda obj) {
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) conn.prepareStatement(
				"UPDATE agenda "
				+ "SET Name = ?, Number = ?, Email = ? "
				+ "WHERE idAgenda = ?");

			st.setString(1, obj.getName());
			st.setString(2, obj.getNumber());
			st.setString(3, obj.getEmail());
			st.setInt(4, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteByName(String name) {
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) conn.prepareStatement(
				"DELETE FROM agenda WHERE Name = ?");

			st.setString(1, name);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}
