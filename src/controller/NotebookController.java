package controller;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.TipoSO;
import dao.NotebookDAO;
import model.Notebook;

@Named
@ViewScoped
public class NotebookController extends Controller<Notebook> {

	private static final long serialVersionUID = 3320846114302215037L;

	public NotebookController() {
		super(new NotebookDAO());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashNotebook");
		entity = (Notebook) flash.get("flashNotebook");
	}

	@Override
	public Notebook getEntity() {
		if (entity == null)
			entity = new Notebook();
		return entity;
	}
	
	public TipoSO[] getListaTipoSO() {
		return TipoSO.values();
	}

}
