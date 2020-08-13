package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Session;
import dao.VendaDAO;
import model.ItemVenda;
import model.Usuario;
import model.Venda;

@Named
@ViewScoped
public class HistoricoVendaController implements Serializable {

	private static final long serialVersionUID = -117328257939598296L;
	private List<Venda> listaVenda = null;
	

	public List<Venda> getListaVenda() {
		
		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			listaVenda = dao.findByUsuario(usuario.getId());
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
		}
		return listaVenda;
	}
	
	
	public String detalhes(Venda venda) {
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.put("detalheVenda", venda);
		
		return "detalhesvenda.xhtml?faces-redirect=true";
	}
	
}
