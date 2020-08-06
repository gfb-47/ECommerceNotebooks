package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import dao.NotebookDAO;
import model.ItemVenda;
import model.Notebook;

@Named
@ViewScoped
public class VendaNotebookController implements Serializable {

	private static final long serialVersionUID = 2277440688138450399L;
	
	private String modelo;
	private List<Notebook> listaNotebook = null;
	
	public void pesquisar() {
		NotebookDAO dao = new NotebookDAO();
		listaNotebook = dao.findByModelo(modelo);
	}
	
	public void adicionar(int idNotebook) {
		NotebookDAO dao = new NotebookDAO();
		Notebook Notebook = dao.findById(idNotebook);
		// verifica se existe um carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona um carrinho (de itens de venda) na sessao
			Session.getInstance().setAttribute("carrinho", 
					new ArrayList<ItemVenda>());
		}
		
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = 
				(ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		
		// criando um item de venda para adicionar no carrinho
		ItemVenda item = new ItemVenda();
		item.setNotebook(Notebook);
		item.setValor(Notebook.getPreco());
		
		// adicionando o item no carrinho (variavel local) 
		carrinho.add(item);
		
		// atualizando o carrinho na sessao
		Session.getInstance().setAttribute("carrinho", carrinho);
		
		Util.addInfoMessage("Notebook adicionado no carrinho. "
				+ "Quantidade de Itens: " + carrinho.size());
		
	}

	public List<Notebook> getListaNotebook() {
		if (listaNotebook == null) {
			NotebookDAO dao = new NotebookDAO();
			listaNotebook = dao.findByModelo(getModelo());
			if (listaNotebook == null)
				listaNotebook = new ArrayList<Notebook>();
		}
		return listaNotebook;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
}
