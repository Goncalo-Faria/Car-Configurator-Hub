package CCH.business;

import CCH.dataaccess.ConfiguracaoDAO;

import java.util.List;
import java.util.Map;

public class Configuracao {

	private int id;
	private double preco;
	private double desconto;
	private Map<Integer, Componente> componentes;
	private Map<Integer, Pacote> pacotes;

	private ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();

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
		return configuracaoDAO.getComponentes(id);
	}

	public void setComponentes(Map<Integer, Componente> componentes) {
		this.componentes = componentes;
	}

	public Map<Integer, Pacote> getPacotes() {
		return configuracaoDAO.getPacotes(id);
	}

	public void setPacotes(Map<Integer, Pacote> pacotes) {
		this.pacotes = pacotes;
	}

	public Configuracao(int id, double preco, double desconto) {
		this.id = id;
		this.preco = preco;
		this.desconto = desconto;
	}

	/**
	 *
	 * @param precoMaximo
	 */
	public List<Componente> gerarConfiguracaoOtima(double precoMaximo) {
		// TODO - implement Configuracao.gerarConfiguracaoOtima
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param componenteId
	 */
	public Componente adiconarComponente(int componenteId) {
		// TODO - implement Configuracao.adiconarComponente
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param componenteId
	 */
	public void removerComponente(int componenteId) {
		// TODO - implement Configuracao.removerComponente
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param pacoteId
	 */
	public void adicionarPacote(int pacoteId) {
		// TODO - implement Configuracao.adicionarPacote
		throw new UnsupportedOperationException();
	}

	public List<Pacote> consultarPacotes() {
		// TODO - implement Configuracao.consultarPacotes
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param tipoComponente
	 */
	public List<Componente> consultarComponentes(TipoComponente tipoComponente) {
		// TODO - implement Configuracao.consultarComponentes
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param componentes
	 */
	public void aceitarConfiguracaoOtima(List<Componente> componentes) {
		// TODO - implement Configuracao.aceitarConfiguracaoOtima
		throw new UnsupportedOperationException();
	}

	public String getNome() {
		return "Configuração " + id;
	}

	@Override
	public String toString() {
		return "Configuracao{" +
				"id=" + id +
				", preco=" + preco +
				", desconto=" + desconto +
				'}';
	}
}