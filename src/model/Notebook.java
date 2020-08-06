package model;

public class Notebook extends Entity<Notebook> {

	private String modelo;
	private String marca;
	private String processador;
	private String placaVideo;
	private Integer memoriaRAM;
	private Integer portasUSB;
	private Integer polegadas;
	private TipoSO sistemaOperacional;
	private Float preco;
	private Integer estoque;
	
	

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	public String getPlacaVideo() {
		return placaVideo;
	}

	public void setPlacaVideo(String placaVideo) {
		this.placaVideo = placaVideo;
	}

	public Integer getMemoriaRAM() {
		return memoriaRAM;
	}

	public void setMemoriaRAM(Integer memoriaRAM) {
		this.memoriaRAM = memoriaRAM;
	}

	public Integer getPortasUSB() {
		return portasUSB;
	}

	public void setPortasUSB(Integer portasUSB) {
		this.portasUSB = portasUSB;
	}

	public Integer getPolegadas() {
		return polegadas;
	}

	public void setPolegadas(Integer polegadas) {
		this.polegadas = polegadas;
	}

	public TipoSO getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(TipoSO sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}


	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

}
