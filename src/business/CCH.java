package business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CCH {
	private GestaoDeConfiguracao gestaoDeConfiguracao;
	private Map<Integer,Componente> componentes;
	private Map<Integer,Utilizador> utilizadores;
	private Map<Integer,Pacote> pacotes;
	private OperacaoFabril operacaoFabril;

	public CCH() {
		this.gestaoDeConfiguracao = new GestaoDeConfiguracao();
		this.componentes = new HashMap<>();
		this.utilizadores = new HashMap<>();
		this.pacotes = new HashMap<>();
		this.operacaoFabril = new OperacaoFabril();
	}

	public CCH(GestaoDeConfiguracao gestaoDeConfiguracao, Map<Integer,
			   Componente> componentes, Map<Integer, Utilizador> utilizadores,
			   Map<Integer, Pacote> pacotes,
			   OperacaoFabril operacaoFabril) {
		this.gestaoDeConfiguracao = gestaoDeConfiguracao;
		this.componentes = componentes;
		this.utilizadores = utilizadores;
		this.pacotes = pacotes;
		this.operacaoFabril = operacaoFabril;
	}

	public GestaoDeConfiguracao getGestaoDeConfiguracao() {
		return this.gestaoDeConfiguracao;
	}

	public void setGestaoDeConfiguracao(GestaoDeConfiguracao gestaoDeConfiguracao) {
		this.gestaoDeConfiguracao = gestaoDeConfiguracao;
	}

	public Map<Integer, Componente> getComponentes() {
		return this.componentes;
	}

	public void setComponentes(Map<Integer, Componente> componentes) {
		this.componentes = componentes;
	}

	public Map<Integer, Pacote> getPacotes() {
		return this.pacotes;
	}

	public void setPacotes(Map<Integer, Pacote> pacotes) {
		this.pacotes = pacotes;
	}


	public Map<Integer, Utilizador> getUtilizadores() {
		return this.utilizadores;
	}

	public void setUtilizadores(Map<Integer, Utilizador> utilizadores) {
		this.utilizadores = utilizadores;
	}

	public OperacaoFabril getOperacaoFabril() {
		return this.operacaoFabril;
	}

	public void setOperacaoFabril(OperacaoFabril operacaoFabril) {
		this.operacaoFabril = operacaoFabril;
	}

	/**
	 * 
	 * @param id
	 * @param password
	 */
	public void iniciarSessao(int id, String password) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param desconto
	 */
	public Pacote criarPacote(Double desconto) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pacoteId
	 */
	public void removerPacote(int pacoteId) {
		throw new UnsupportedOperationException();
	}

	public List<Utilizador> consultarFuncionarios() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param password
	 * @param tipo
	 */
	public Utilizador criarFuncionario(String nome, String password, TipoUtilizador tipo) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param utilizadorId
	 */
	public void removerFuncionario(int utilizadorId) {
		throw new UnsupportedOperationException();
	}

}