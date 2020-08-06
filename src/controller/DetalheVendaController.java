package controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Venda;

@Named
@ViewScoped
public class DetalheVendaController implements Serializable{

	private static final long serialVersionUID = -6311983806896886009L;
	private Venda venda;
	
	public DetalheVendaController() {
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("detalheVenda");
		venda = (Venda) flash.get("detalheVenda");
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
