package CCH.business;

import CCH.dataaccess.ComponenteDAO;
import CCH.dataaccess.PacoteDAO;
import CCH.dataaccess.UtilizadorDAO;
import CCH.exception.WrongCredentialsException;

import java.util.ArrayList;
import java.util.List;

public class CCH {
	private GestaoDeConfiguracao gestaoDeConfiguracao;
	private OperacaoFabril operacaoFabril;
	private UtilizadorDAO utilizadorDAO;
	private PacoteDAO pacoteDAO;
	private ComponenteDAO componenteDAO;

	public CCH() {
		this.operacaoFabril = new OperacaoFabril();
		this.gestaoDeConfiguracao = new GestaoDeConfiguracao();
		this.utilizadorDAO = new UtilizadorDAO();
		this.pacoteDAO = new PacoteDAO();
		this.componenteDAO = new ComponenteDAO();
	}

	public GestaoDeConfiguracao getGestaoDeConfiguracao() {
		return gestaoDeConfiguracao;
	}

	public void setGestaoDeConfiguracao(GestaoDeConfiguracao gestaoDeConfiguracao) {
		this.gestaoDeConfiguracao = gestaoDeConfiguracao;
	}

	public OperacaoFabril getOperacaoFabril() {
		return operacaoFabril;
	}

	public void setOperacaoFabril(OperacaoFabril operacaoFabril) {
		this.operacaoFabril = operacaoFabril;
	}

	public UtilizadorDAO getUtilizadorDAO() {
		return utilizadorDAO;
	}

	public void setUtilizadorDAO(UtilizadorDAO utilizadorDAO) {
		this.utilizadorDAO = utilizadorDAO;
	}

	public PacoteDAO getPacoteDAO() {
		return pacoteDAO;
	}

	public void setPacoteDAO(PacoteDAO pacoteDAO) {
		this.pacoteDAO = pacoteDAO;
	}

	public ComponenteDAO getComponenteDAO() {
		return componenteDAO;
	}

	public void setComponenteDAO(ComponenteDAO componenteDAO) {
		this.componenteDAO = componenteDAO;
	}

	/**
	 *
	 * @param id
	 * @param password
	 */
	public Utilizador iniciarSessao(int id, String password) throws WrongCredentialsException {
		Utilizador utilizador = utilizadorDAO.get(id);
		boolean loggedIn = utilizador.validarCredenciais(id, password);

		if (!loggedIn) {
			throw new WrongCredentialsException();
		}

		return utilizador;
	}

	/**
	 *
	 * @param pacote
	 */
	public void criarPacote(Pacote pacote) {
		// TODO - implement CCH.criarPacote
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param pacoteId
	 */
	public void removerPacote(int pacoteId) {
		pacoteDAO.remove(pacoteId);
	}

	public List<Utilizador> consultarFuncionarios() {
		// TODO - implement CCH.consultarFuncionarios
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param nome
	 * @param password
	 * @param tipo
	 */
	public Utilizador criarFuncionario(String nome, String password, TipoUtilizador tipo) {
		// TODO - implement CCH.criarFuncionario
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param utilizadorId
	 */
	public void removerFuncionario(int utilizadorId) {
		// TODO - implement CCH.removerFuncionario
		throw new UnsupportedOperationException();
	}

	public List<Pacote> consultarPacotes() {
		return new ArrayList<>(pacoteDAO.values());
	}

	public List<Componente> consultarComponentes() {
		return new ArrayList<>(componenteDAO.values());
	}
}
