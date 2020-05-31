	package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import model.Notebook;
import model.TipoSO;

public class NotebookDAO extends DAO<Notebook> {

	public boolean create(Notebook notebook) {

		boolean retorno = false;
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO notebook ");
		sql.append("	(modelo, marca, processador, placavideo, memoriaram, portasusb, polegadas, sistemaoperacional) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ? , ? , ? , ? , ? ) ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, notebook.getModelo());
			stat.setString(2, notebook.getMarca());
			stat.setString(3, notebook.getProcessador());
			stat.setString(4, notebook.getPlacaVideo());
			if (notebook.getMemoriaRAM() != null)
				stat.setInt(5, notebook.getMemoriaRAM());
			else
				stat.setNull(5, java.sql.Types.INTEGER);

			if (notebook.getPortasUSB() != null)
				stat.setInt(6, notebook.getPortasUSB());
			else
				stat.setNull(6, java.sql.Types.INTEGER);

			if (notebook.getPortasUSB() != null)
				stat.setInt(7, notebook.getPolegadas());
			else
				stat.setNull(7, java.sql.Types.INTEGER);
			
			if (notebook.getPortasUSB() != null)
				stat.setInt(8, notebook.getSistemaOperacional().getId());
			else
				stat.setNull(8, java.sql.Types.INTEGER);
			

			stat.execute();

			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");

			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public boolean update(Notebook notebook) {
		boolean retorno = false;
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE notebook ");
		sql.append("	SET modelo=?, marca=?, processador=?, placavideo=?, memoriaram=?, portasusb=?, polegadas=?, sistemaoperacional=?) ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, notebook.getModelo());
			stat.setString(2, notebook.getMarca());
			stat.setString(3, notebook.getProcessador());
			stat.setString(4, notebook.getPlacaVideo());
			if (notebook.getMemoriaRAM() != null)
				stat.setInt(5, notebook.getMemoriaRAM());
			else
				stat.setNull(5, java.sql.Types.INTEGER);

			if (notebook.getPortasUSB() != null)
				stat.setInt(6, notebook.getPortasUSB());
			else
				stat.setNull(6, java.sql.Types.INTEGER);

			if (notebook.getPortasUSB() != null)
				stat.setInt(7, notebook.getPolegadas());
			else
				stat.setNull(6, java.sql.Types.INTEGER);

			stat.setInt(8, notebook.getSistemaOperacional().getId());


			stat.execute();

			conn.commit();

			System.out.println("Alteração realizada com sucesso.");

			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;

	}

	public boolean delete(int id) {
		boolean retorno = false;
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM notebook ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

			stat.execute();

			conn.commit();

			System.out.println("Remoção realizada com sucesso.");

			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public List<Notebook> findAll() {
		List<Notebook> listaNotebook = new ArrayList<Notebook>();
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	modelo, marca, processador, placavideo, memoriaram, portasusb, polegadas, sistemaoperacional ");
		sql.append("FROM ");
		sql.append("	notebook ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			Notebook notebook = null;

			while (rs.next()) {
				notebook = new Notebook();
				notebook.setId(rs.getInt("id"));
				notebook.setModelo(rs.getString("modelo"));
				notebook.setMarca(rs.getString("marca"));
				notebook.setProcessador(rs.getString("processador"));
				notebook.setPlacaVideo(rs.getString("placavideo"));
				notebook.setMemoriaRAM(rs.getInt("memoriaram"));
				notebook.setPortasUSB(rs.getInt("portasusb"));
				notebook.setPolegadas(rs.getInt("polegadas"));
				notebook.setSistemaOperacional(TipoSO.valueOf(rs.getInt("tipousuario")));
				listaNotebook.add(notebook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaNotebook;
	}

	public List<Notebook> findByMarca(String marca) {
		List<Notebook> listaNotebook = new ArrayList<Notebook>();
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, modelo, marca, processador, placavideo, memoriaram, portasusb, polegadas, sistemaoperacional ");
		sql.append("FROM ");
		sql.append("	notebook ");
		sql.append("WHERE ");
		sql.append("	marca ilike ? ");
		sql.append("ORDER BY marca ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + marca + "%");

			ResultSet rs = stat.executeQuery();

			Notebook notebook = null;

			while (rs.next()) {
				notebook = new Notebook();
				notebook.setId(rs.getInt("id"));
				notebook.setModelo(rs.getString("modelo"));
				notebook.setMarca(rs.getString("marca"));
				notebook.setProcessador(rs.getString("processador"));
				notebook.setPlacaVideo(rs.getString("placavideo"));
				notebook.setMemoriaRAM(rs.getInt("memoriaram"));
				notebook.setPortasUSB(rs.getInt("portasusb"));
				notebook.setPolegadas(rs.getInt("polegadas"));
				notebook.setSistemaOperacional(TipoSO.valueOf(rs.getInt("sistemaoperacional")));
				listaNotebook.add(notebook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaNotebook;
	}

	public List<Notebook> findByModelo(String modelo) {
		List<Notebook> listaNotebook = new ArrayList<Notebook>();
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, modelo, marca, processador, placavideo, memoriaram, portasusb, polegadas, sistemaoperacional ");
		sql.append("FROM ");
		sql.append("	notebook ");
		sql.append("WHERE ");
		sql.append("	modelo ilike ? ");
		sql.append("ORDER BY modelo ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + modelo + "%");

			ResultSet rs = stat.executeQuery();

			Notebook notebook = null;

			while (rs.next()) {
				notebook = new Notebook();
				notebook.setId(rs.getInt("id"));
				notebook.setModelo(rs.getString("modelo"));
				notebook.setMarca(rs.getString("marca"));
				notebook.setProcessador(rs.getString("processador"));
				notebook.setPlacaVideo(rs.getString("placavideo"));
				notebook.setMemoriaRAM(rs.getInt("memoriaram"));
				notebook.setPortasUSB(rs.getInt("portasusb"));
				notebook.setPolegadas(rs.getInt("polegadas"));
				notebook.setSistemaOperacional(TipoSO.valueOf(rs.getInt("sistemaoperacional")));
				listaNotebook.add(notebook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaNotebook;
	}

	public Notebook findById(int id) {
		Notebook notebook = null;
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	(modelo, marca, processador, placavideo, memoriaram, portasusb, polegadas, sistemaoperacional) ");
		sql.append("FROM ");
		sql.append("	notebook ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				notebook = new Notebook();
				notebook.setId(rs.getInt("id"));
				notebook.setModelo(rs.getString("modelo"));
				notebook.setMarca(rs.getString("marca"));
				notebook.setProcessador(rs.getString("processador"));
				notebook.setPlacaVideo(rs.getString("placavideo"));
				notebook.setMemoriaRAM(rs.getInt("memoriaram"));
				notebook.setPortasUSB(rs.getInt("portasusb"));
				notebook.setPolegadas(rs.getInt("polegadas"));
				notebook.setSistemaOperacional(TipoSO.valueOf(rs.getInt("tipousuario")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return notebook;
	}

}
