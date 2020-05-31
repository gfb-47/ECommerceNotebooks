package model;

public enum TipoSO {
	NAO_DEFINIDO(0, "Selecione um tipo..."),
	WINDOWS10(1, "Windows 10"),
	WINDOWS8(2, "Windows 8"),
	MACOS(3, "Mac OS"),
	UBUNTU(4, "Ubuntu"),
	DEBIAN(5, "Debian"),
	CHROMEOS(6, "ChromeOS");
	
	private int id;
	private String label;
	
	private TipoSO(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static TipoSO valueOf(int valor) {
		for (TipoSO TipoSO : TipoSO.values()) {
			if (valor == TipoSO.getId())
				return TipoSO;
		} 
		return null;
	}
}
