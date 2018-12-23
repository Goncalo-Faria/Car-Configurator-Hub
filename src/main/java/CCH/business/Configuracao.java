package CCH.business;

import java.util.*;

public class Configuracao {
	private Map<Integer,Componente> componentes;
	private int id;
	private double preco;
	private double desconto;

	private static int nextId = 1;

	public Configuracao() {
		this.componentes = new HashMap<>();
		this.id = Configuracao.getNextId();
		this.preco = 0;
		this.desconto = 0;
	}

	public Configuracao(Configuracao configuracao) {
		this.componentes = configuracao.getComponentes();
		this.id = configuracao.getId();
		this.preco = configuracao.getPreco();
		this.desconto = configuracao.getDesconto();
	}

	public Configuracao(int id, double preco, double desconto) {
		this.componentes = null;
		this.id = id;
		this.preco = preco;
		this.desconto = desconto;
	}

	public Configuracao(Map<Integer, Componente> componentes, double preco, double desconto) {
		this.componentes = componentes;
		this.id = Configuracao.getNextId();
		this.preco = preco;
		this.desconto = desconto;
	}

	public static int getNextId() {
		return nextId++;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Map<Integer, Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(Map<Integer, Componente> componentes) {
		this.componentes = componentes;
	}

	/**
	 * 
	 * @param precoMaximo
	 */
	public List<Componente> gerarConfiguracaoOtima(double precoMaximo) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param componenteId
	 */
	public Componente adiconarComponente(int componenteId) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param componenteId
	 */
	public void removerComponente(int componenteId) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pacoteId
	 */
	public void adicionarPacote(int pacoteId) {
		throw new UnsupportedOperationException();
	}

	public List<Pacote> consultarPacotes() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tipoComponente
	 */
	public List<Componente> consultarComponentes(TipoComponente tipoComponente) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param componentes
	 */
	public void aceitarConfiguracaoOtima(List<Componente> componentes) {
		throw new UnsupportedOperationException();
	}

}