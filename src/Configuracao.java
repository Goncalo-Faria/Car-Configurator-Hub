import java.util.*;

public class Configuracao {
	private Map<Integer,Componente> componentes;
	private int id;
	private double preco;
	private double desconto;

	public Configuracao() {
		this.componentes = new HashMap<>();
	//	this.id = id;
		this.preco = 0;
		this.desconto = 0;
	}

	public Configuracao(Map<Integer, Componente> componentes, int id, double preco, double desconto) {
		this.componentes = componentes;
		this.id = id;
		this.preco = preco;
		this.desconto = desconto;
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