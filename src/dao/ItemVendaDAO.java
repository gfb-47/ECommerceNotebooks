package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Notebook;
import model.TipoSO;
import model.ItemVenda;
import model.Venda;

public class ItemVendaDAO extends DAO<ItemVenda> {
	
	@Override
	public boolean create(ItemVenda itemVenda) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO public.itemVenda ");
		sql.append("	(valor, idvenda, idNotebook) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?) ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setFloat(1, itemVenda.getValor());
			stat.setInt(2, itemVenda.getVenda().getId());
			stat.setInt(3, itemVenda.getNotebook().getId());
			stat.execute();
			
			conn.commit();
			
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

	public List<ItemVenda> findByVenda(Venda venda) {
		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.valor, ");
		sql.append("  v.idlivro, ");
		sql.append("  l.modelo, ");
		sql.append("  l.marca, ");
		sql.append("  l.processador, ");
		sql.append("  l.memoriaram ");
		sql.append("  l.portasusb ");
		sql.append("  l.polegadas ");
		sql.append("  l.sistemaoperacional ");
		sql.append("  l.preco ");
		sql.append("  l.estoque ");
		sql.append("FROM ");
		sql.append("  public.itemvenda v, ");
		sql.append("  public.livro l ");
		sql.append("WHERE ");
		sql.append("  v.idNotebook = l.id AND ");
		sql.append("  v.idvenda = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, venda.getId());
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("id"));
				item.setValor(rs.getFloat("valor"));
				
				Notebook notebook = new Notebook();
				notebook.setId(rs.getInt("id"));
				notebook.setModelo(rs.getString("modelo"));
				notebook.setMarca(rs.getString("marca"));
				notebook.setProcessador(rs.getString("processador"));
				notebook.setPlacaVideo(rs.getString("placavideo"));
				notebook.setMemoriaRAM(rs.getInt("memoriaram"));
				notebook.setPortasUSB(rs.getInt("portasusb"));
				notebook.setPolegadas(rs.getInt("polegadas"));				
				notebook.setSistemaOperacional(TipoSO.valueOf(rs.getInt("sistemaoperacional")));
				notebook.setPreco(rs.getFloat("preco"));
				notebook.setEstoque(rs.getInt("estoque"));
				
				item.setNotebook(notebook);
				
				item.setVenda(venda);
				
				listaItemVenda.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaItemVenda;
	}
	
	@Override
	public boolean update(ItemVenda itemVenda) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

	@Override
	public List<ItemVenda> findAll() {
		return null;
	}

	@Override
	public ItemVenda findById(int id) {
		return null;
	}	

}
