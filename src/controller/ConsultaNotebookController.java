package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Notebook;
import dao.NotebookDAO;

@Named
@ViewScoped
public class ConsultaNotebookController implements Serializable {

	private static final long serialVersionUID = -6030583062190168719L;
	
	private int tipoFiltro = 1;
	private String filtro;
	private List<Notebook> listaNotebook;
	
	public void pesquisar() {
		NotebookDAO dao = new NotebookDAO();
		if (tipoFiltro == 1)
			listaNotebook = dao.findByMarca(getFiltro());
		else if(tipoFiltro == 2)
			listaNotebook = dao.findByModelo(getFiltro());
		else 
			System.out.println("To no else");
	}
	
	public String novoNotebook() {
		return "notebook.xhtml?faces-redirect=true";
	}
	
	public String editar(Notebook notebook) {
		NotebookDAO dao = new NotebookDAO();
		notebook = dao.findById(notebook.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();
		
		flash.put("flashNotebook", notebook);
		return "notebook.xhtml?faces-redirect=true";
	}

	public List<Notebook> getListaNotebook() {
		if (listaNotebook == null) {
			listaNotebook = new ArrayList<Notebook>();
		}
		return listaNotebook;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
	
	
}	